package edu.kh.jvj.admin.model.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import edu.kh.jvj.admin.model.dao.AdminDAO;
import edu.kh.jvj.admin.model.vo.Admin;
import edu.kh.jvj.admin.model.vo.MadeCoupon;
import edu.kh.jvj.admin.model.vo.MessagesRequestDto;
import edu.kh.jvj.admin.model.vo.ProductImage;
import edu.kh.jvj.admin.model.vo.ProductWrite;
import edu.kh.jvj.admin.model.vo.Reviews;
import edu.kh.jvj.admin.model.vo.SalesRank;
import edu.kh.jvj.admin.model.vo.SearchedMember;
import edu.kh.jvj.admin.model.vo.SimpleProduct;
import edu.kh.jvj.admin.model.vo.SmsRequestDto;
import edu.kh.jvj.admin.model.vo.SubsInfo;
import edu.kh.jvj.admin.model.vo.SubsOptions;
import edu.kh.jvj.notice.model.vo.Notice;
import edu.kh.jvj.store.model.vo.Pagination;
import edu.kh.jvj.store.model.vo.Store;

@Service
public class AdminServiceImpl implements AdminService{
	
	private final AdminDAO dao;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public AdminServiceImpl(AdminDAO dao) {
		this.dao = dao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertProduct(List<MultipartFile> images, ProductWrite product, String webPath,String serverPath) {
		int result = 0;
		//1 ???????????? ??????
		product.setTitle(XSS(product.getTitle()));
		//2. ?????? ??????+?????? ??????
		//???????????? (???????????? ????????? , ?????? , ?????? , ?????????(default) , ??????????????????)
		result= dao.insertProductCommon(product);
		//2-1 ??????????????? 
		if(product.getWritecate()==1) {
			product.setDetailcontents(XSS(product.getDetailcontents()));
			result = dao.insertStoreProduct(product); 
			if(product.getDiscountyn().equals("yes")) {
				result = dao.insertStoreDiscount(product); 
			}
		}
		//2-3 ????????? ?????????
		else {
			String plustime="";
			plustime += (product.getStarthour()<=9? "0"+product.getStarthour():product.getStarthour())+":"
					+(product.getStartminute()==0? "0"+product.getStartminute():product.getStartminute())+ " ~ " 
					 + (product.getEndhour()<=9? "0"+product.getEndhour():product.getEndhour())+":"
					 +(product.getEndminute()==0? "0"+product.getEndminute():product.getEndminute());
			product.setStartEndTime(plustime);
			//???????????? ??????
			product.setClassdate(product.getClassdate());
			//?????? ??????
			
			 result = dao.insertClassProduct(product); 
		}
		
		//3. ?????? ????????? ??????
		if(result>0) {
			List<ProductImage> imgList = new ArrayList<ProductImage>();
			for(int i =0 ; i< images.size();i++) {
				// i == images??? ????????? == ???????????? ????????? ??????
				
				// ??? ????????? ????????? ????????? ????????? ???????????? ??????
				if(!images.get(i).getOriginalFilename().equals("")) {
					// ???????????? ??? ??????
					// MultipartFile?????? DB?????? ????????? ??????????????? ????????????
					// ProductImage ????????? ?????? ??? imgList ??????
					
					ProductImage img = new ProductImage();
					img.setImgPath(webPath);
					img.setImgName(fileRename(images.get(i).getOriginalFilename()));
					img.setImgLevel(i);
					img.setProductNo(product.getProductNo());
					
					imgList.add(img);
				}
			}
			
			//imgList??? ???????????? ???????????? ????????? DAO ??????
			if(!imgList.isEmpty()){
				result = dao.insertImgList(imgList);
			      
//			      5) ?????? ????????? ?????? ????????? imgList ????????? ?????? ???
//			      ????????? ????????? ??????
//			      ???????????? -> fin server -> Overview
//				server modeules without publishing ?????? -> ???????????? ?????? ???????????????
//				???????????? ?????? ????????? ?????? ??????????????? ????????? ??? ??????
				if(result == imgList.size()) {// ?????? ==> ????????????
					
					//images - MultipartFile List , ?????? ?????? ?????? + ??????
					//imgList - BoardImage List, DB??? ????????? ?????? ??????
					for(int i =0 ; i<imgList.size(); i++) {
						// ???????????? ????????? ?????? images??? ????????? ????????? ????????? 
						// ????????? ????????? ???????????? ????????? ???????????? ??????
						
						try {
							images.get(imgList.get(i).getImgLevel())
							.transferTo(new File(serverPath+imgList.get(i).getImgName() ));
							log.info("Product ????????? ?????? {}", serverPath+imgList.get(i).getImgName());
						}catch (Exception e) {
							e.printStackTrace();
							//?????? ????????? ????????? ??????
						}
					}
				}
				else {
					/* ?????? */
				}
			}
			
		}
		return result;
	}
	

	// ???????????? ??????
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertNotice(Notice notices) {
		
		notices.setNoticeTitle(XSS(notices.getNoticeTitle()));

		int result = dao.insertNotice(notices); 
		
		if(result>0 && notices.getCouponName() != null) {
			List<MadeCoupon> couponList = new ArrayList<MadeCoupon>();
			for(int i = 0 ; i< notices.getCouponName().length ; i++) {
				MadeCoupon coupons = new MadeCoupon();
				coupons.setCouponLimit(notices.getCouponLimit()[i]);
				coupons.setExpireDate(notices.getExpireDate()[i]);
				coupons.setAdminNo(notices.getLoginAdmin());
				coupons.setDiscountPer(notices.getDiscountPer()[i]);
				coupons.setCouponName(notices.getCouponName()[i]);
				coupons.setNoticeNo(notices.getNoticeNo());
				couponList.add(coupons);
			}
			result = dao.addMakeCoupons(couponList);
		}
		
		
		 return result;
	}
	@Override
	
	
	
	@Transactional(rollbackFor = Exception.class)
	public int updateNotice(String title, String noticecate, String editordata, String noticeNo) {
		title= XSS(title);
		Map<String, String> noticeMap = new HashMap<String, String>();
		
		noticeMap.put("title", title);
		noticeMap.put("noticecate", noticecate);
		noticeMap.put("editordata", editordata);
		noticeMap.put("noticeNo", noticeNo);
		
		 return dao.updateNotice(noticeMap); 
	}
	// ????????? ????????? ID PW ??????
	@Override
	public Admin findMatchAdmin(Admin admin) {
		return dao.findMatchAdmin(admin); 
	}
	
	//?????? ??????
	@Override
	public List<SearchedMember> searchMember(Map<String, String> dataMap, Pagination page) {
		
		return dao.searchMember(dataMap,page); 
	}

	//?????? ??????
	@Override
	public int insertOptionP(Map<String, String> map) {
		return dao.insertOptionP(map); 
	}


	//?????? ?????? ??????????????????
	@Override
	public Pagination countMember(Map<String, String> dataMap) {
		int cp = Integer.parseInt(dataMap.get("cp"));
		int listcount = dao.countMember(dataMap);
		
		return new Pagination(listcount,cp);
	}
	//?????? ?????? ??????????????????
	@Override
	public Pagination countProduct(Map<String, String> dataMap) {
		int cp = Integer.parseInt(dataMap.get("cp"));
		int listcount = dao.countProduct(dataMap);
		return new Pagination(listcount,cp);
	}

	//?????? ????????? ??????
	@Override
	public List<SimpleProduct> productselect(Map<String, String> dataMap, Pagination page) {
		return dao.productselect(dataMap,page); 
	}
	//????????? Detail ??? ????????? 
	@Override
	public Store getStoreInfo(int productNo) {
		Store store =dao.getStoreInfo(productNo);
		return store;
	}
	
	//?????? update
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateProduct(List<MultipartFile> images, ProductWrite product, String webPath, String serverPath) {
		int result = 0;
		//1 ???????????? ??????
		product.setTitle(XSS(product.getTitle()));
		//2. ?????? ??????+?????? ??????
		//???????????? (???????????? ????????? , ?????? , ?????? , ?????????(default) , ??????????????????)
		result= dao.updateProductCommon(product);
		//2-1 ??????????????? 
		if(product.getWritecate()==1) {
			product.setDetailcontents(XSS(product.getDetailcontents()));
			result = dao.updateStoreProduct(product); 
			result= dao.checkIsDiscount(product);
			if(product.getDiscountyn().equals("yes")) {
				if(result>0) result = dao.updateStoreDiscount(product);
				else result=dao.insertStoreDiscount(product); 
			}
			else {
				if(result>0) result=dao.deleteStoreDiscount(product); 
			}
		}
		//2-3 ????????? ?????????
		else if(product.getWritecate()==3){
			String plustime="";
			plustime += (product.getStarthour()<=9? "0"+product.getStarthour():product.getStarthour())+":"
					+(product.getStartminute()==0? "0"+product.getStartminute():product.getStartminute())+ " ~ " 
					 + (product.getEndhour()<=9? "0"+product.getEndhour():product.getEndhour())+":"
					 +(product.getEndminute()==0? "0"+product.getEndminute():product.getEndminute());
			product.setStartEndTime(plustime);
			//???????????? ??????
			product.setClassdate(product.getClassdate());
			//?????? ??????
			 result = dao.updateClassProduct(product); 
		}
		String temp = product.getAfterImageCheck();
		String deleteLevels ="";
		for(int i =0 ; i<temp.length() ; i++) {
			if(temp.charAt(i)=='1') {
				if(!deleteLevels.equals("")) {
					deleteLevels += ",";
				}
				deleteLevels += (" "+i);
			}
			else {
				
			}
		}
		if(!deleteLevels.equals("")) {
			Map<String, String> map = new HashMap<>();
			map.put("productNo", ""+product.getProductNo());
			map.put("deleteLevels", deleteLevels);
			result=dao.deleteImgs(map);
		}
		List<ProductImage> imgList = new ArrayList<ProductImage>();
		for(int i =0 ; i< images.size();i++) {
			// i == images??? ????????? == ???????????? ????????? ??????
			// ??? ????????? ????????? ????????? ????????? ???????????? ??????
			if(!images.get(i).getOriginalFilename().equals("")) {
				// ???????????? ??? ??????
				// MultipartFile?????? DB?????? ????????? ??????????????? ????????????
				// ProductImage ????????? ?????? ??? imgList ??????
				
				ProductImage img = new ProductImage();
				img.setImgPath(webPath);
				img.setImgName(fileRename(images.get(i).getOriginalFilename()));
				img.setImgLevel(i);
				img.setProductNo(product.getProductNo());
				imgList.add(img);
			}
		}
		
		//imgList??? ???????????? ???????????? ????????? DAO ??????
		if(!imgList.isEmpty()){
			result = dao.insertImgList(imgList);
		      
//		      5) ?????? ????????? ?????? ????????? imgList ????????? ?????? ???
//		      ????????? ????????? ??????
//		      ???????????? -> fin server -> Overview
//			server modeules without publishing ?????? -> ???????????? ?????? ???????????????
//			???????????? ?????? ????????? ?????? ??????????????? ????????? ??? ??????
			if(result == imgList.size()) {// ?????? ==> ????????????
				
				//images - MultipartFile List , ?????? ?????? ?????? + ??????
				//imgList - BoardImage List, DB??? ????????? ?????? ??????
				for(int i =0 ; i<imgList.size(); i++) {
					// ???????????? ????????? ?????? images??? ????????? ????????? ????????? 
					// ????????? ????????? ???????????? ????????? ???????????? ??????
					
					try {
						images.get(imgList.get(i).getImgLevel())
						.transferTo(new File(serverPath+imgList.get(i).getImgName() ));
					}catch (Exception e) {
						e.printStackTrace();
						//?????? ????????? ????????? ??????
					}
				}
			}
			else {
				/* ?????? */
			}
		}
			
		return result;
	}
//	???????????? ?????? ??? ??????
	@Override
	public SubsInfo getSubsInfo(Map<String, Integer> dataMap) {
		return dao.getSubsInfo(dataMap);
	}	
	//??????????????????
	@Override
	public List<SubsOptions> selectSubsOption(int productNo) {
		return dao.selectSubsOption(productNo);
	}
	
	//???????????? ??????
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addSubsOption(SubsOptions subsOption) {
		int result = dao.addSubsOption(subsOption);
		return result;
	}
	//???????????? ??????
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteSubsOption(int suboptionNo) {
		int result = dao.deleteSubsOption(suboptionNo);
		return result;
	}
	//???????????? ??????
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int changeSubsOption(SubsOptions subsOptions){
		int result = dao.changeSubsOption(subsOptions);
		return result;
	}
	// ????????? ????????? ???????????? ?????? ?????? ?????????
	   public static String XSS(String param) {
	      String result = param;
	      if (param != null) {
	         result = result.replaceAll("&", "&amp;");
	         result = result.replaceAll("<", "&lt;");
	         result = result.replaceAll(">", "&gt;");
	         result = result.replaceAll("\"", "&quot;");
	      }

	      return result;
	   }

	   // ????????? ?????? ?????????
	   public static String fileRename(String originFileName) {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	      String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

	      int ranNum = (int) (Math.random() * 100000); // 5?????? ?????? ?????? ??????

	      String str = "_" + String.format("%05d", ranNum);

	      String ext = originFileName.substring(originFileName.lastIndexOf("."));

	      return date + str + ext;
	   }
	   
	   // ?????? ?????? ?????? ?????????
	   public static String changeNewLine(String content) {
	      return content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	      
	      
	   }

	@Override
	public String sendmessage() {
		Long time = System.currentTimeMillis();
		List<MessagesRequestDto> list = new ArrayList<>();
		MessagesRequestDto oneMRD = new MessagesRequestDto("01025639598","???????????? ?????????");
		Gson gson = new Gson();
		list.add(oneMRD);
		
//		Body Json
		SmsRequestDto SRD = new SmsRequestDto();
		SRD.setType("SMS");
		SRD.setContentType("COMM");
		SRD.setCountryCode("82");
		SRD.setFrom("01025639598");
		SRD.setMessages(list);
		SRD.setContent("-?????????-");
		
		String jsonBody = gson.toJson(SRD);
		
		String accessKey="zC9zNinRIJqHLInx7Ajx";
		String serviceId="ncp:sms:kr:275225299182:final_jvj_prj";
		
		String hostUrl ="https://sens.apigw.ntruss.com/sms/v2/services/"
			+serviceId+"/messages";
		System.out.println(jsonBody);
		// ???????????? ?????? ??????????????? ????????????.
		try {
			String sig = makeSignature(time);
			URL url = new URL(hostUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("content-type", "application/json;charset=utf-8");
			con.setRequestProperty("x-ncp-apigw-timestamp", time.toString());
			con.setRequestProperty("x-ncp-iam-access-key", accessKey);
			con.setRequestProperty("x-ncp-apigw-signature-v2", sig);
			con.setRequestMethod("POST");
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(jsonBody.getBytes());
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode==202) {
				br= new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			else {
				System.out.println(responseCode);
				br= new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine=br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			String result = response.toString();
			System.out.println(result);
			return result;
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String makeSignature(Long time) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		//??????????????? ????????? ???????????????
		String serviceId="ncp:sms:kr:275225299182:final_jvj_prj";
		
		String space = " "; 
		// one space 
		String newLine = "\n"; 
		// new line String method = "POST"; 
		// method 
		String url = "/sms/v2/services/"+serviceId+"/messages"; 
		// url (include query string) 
		String timestamp = time.toString(); 
		// current timestamp (epoch)
		String accessKey="zC9zNinRIJqHLInx7Ajx";
		// access key id (from portal or Sub Account) 
		String secretKey="3CQEI2J2UJ1QNQshiM6Vq6828X00N5lRSviCXBhw";
		String message = new StringBuilder() 
				.append("POST") .append(space) 
				.append(url) .append(newLine) 
				.append(timestamp) .append(newLine) 
				.append(accessKey) .toString(); 
		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256"); mac.init(signingKey); 
		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8")); 
		String encodeBase64String = new String(Base64.getEncoder().encode(rawHmac));
		
		return encodeBase64String;
	}

	// ????????? ????????????
	@Override
	public List<String> selectImgList() {
		return dao.selectImgList();
	}
	
	//?????? ??????
	@Override
	public int makingCoupon(MadeCoupon mCoupon) {
		String ranName ="jvj"+ UUID.randomUUID();
		mCoupon.setHashName(ranName);
		return dao.makingCoupon(mCoupon);
	}

	@Override
	public List<SubsInfo> getSubsList(Map<String,String> dataMap, Pagination page) {
		return dao.getSubsList(dataMap,page);
	}

	@Override
	public Pagination countSubsMember(Map<String, String> dataMap) {
		int cp = Integer.parseInt(dataMap.get("cp"));
		int listcount = dao.countSubsMember(dataMap);
		return new Pagination(listcount,cp);
	}

	@Override
	public List<SalesRank> getStoreRanks() {
		return dao.getStoreRanks();
	}

	@Override
	public Pagination countReview(Map<String, String> dataMap) {
		int count = dao.countReview(dataMap);
		int cp = Integer.parseInt(dataMap.get("cp"));
		return new Pagination(count,cp);
	}

	@Override
	public List<Reviews> getReviewList(Map<String, String> dataMap, Pagination page) {
		return dao.getReviewList(dataMap,page);
	}

	@Override
	public String getReview(int reviewNo) {
		return dao.getReview(reviewNo);
	}

	@Override
	public int blindReview(Map<String, Integer> dataMap) {
		return dao.blindReview(dataMap);
	}

}

