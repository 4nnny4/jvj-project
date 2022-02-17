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
		//1 개행문자 처리
		product.setTitle(XSS(product.getTitle()));
		//2. 상품 메인+상세 등록
		//메인등록 (상품번호 시퀀스 , 이름 , 가격 , 생성일(default) , 상품카테고리)
		result= dao.insertProductCommon(product);
		//2-1 일반스토어 
		if(product.getWritecate()==1) {
			product.setDetailcontents(XSS(product.getDetailcontents()));
			result = dao.insertStoreProduct(product); 
			if(product.getDiscountyn().equals("yes")) {
				result = dao.insertStoreDiscount(product); 
			}
		}
		//2-3 클래스 페이지
		else {
			String plustime="";
			plustime += (product.getStarthour()<=9? "0"+product.getStarthour():product.getStarthour())+":"
					+(product.getStartminute()==0? "0"+product.getStartminute():product.getStartminute())+ " ~ " 
					 + (product.getEndhour()<=9? "0"+product.getEndhour():product.getEndhour())+":"
					 +(product.getEndminute()==0? "0"+product.getEndminute():product.getEndminute());
			product.setStartEndTime(plustime);
			//수업시간 설정
			product.setClassdate(product.getClassdate());
			//일시 성정
			
			 result = dao.insertClassProduct(product); 
		}
		
		//3. 상품 이미지 등록
		if(result>0) {
			List<ProductImage> imgList = new ArrayList<ProductImage>();
			for(int i =0 ; i< images.size();i++) {
				// i == images의 인덱스 == 업로드된 파일의 레벨
				
				// 각 인덱스 요소에 파일이 업로드 되었는지 검사
				if(!images.get(i).getOriginalFilename().equals("")) {
					// 업로드가 된 경우
					// MultipartFile에서 DB저장 필요한 데이터만을 추출하여
					// ProductImage 객체에 담은 후 imgList 추가
					
					ProductImage img = new ProductImage();
					img.setImgPath(webPath);
					img.setImgName(fileRename(images.get(i).getOriginalFilename()));
					img.setImgLevel(i);
					img.setProductNo(product.getProductNo());
					
					imgList.add(img);
				}
			}
			
			//imgList에 업로드된 이미지가 있다면 DAO 호출
			if(!imgList.isEmpty()){
				result = dao.insertImgList(imgList);
			      
//			      5) 삽입 성공한 행의 개수와 imgList 개수가 같을 때
//			      파일을 서버에 저장
//			      서버즈탭 -> fin server -> Overview
//				server modeules without publishing 체크 -> 프로젝트 경로 일치시키기
//				저장되는 파일 경로를 현재 프로젝트로 지정할 수 있음
				if(result == imgList.size()) {// 성공 ==> 파일저장
					
					//images - MultipartFile List , 실제 파일 자체 + 정보
					//imgList - BoardImage List, DB에 저장할 파일 정보
					for(int i =0 ; i<imgList.size(); i++) {
						// 업로드된 파일이 있는 images의 인덱스 요소를 얻어와 
						// 지정된 경로와 이름으로 파일로 변환하여 저장
						
						try {
							images.get(imgList.get(i).getImgLevel())
							.transferTo(new File(serverPath+imgList.get(i).getImgName() ));
							log.info("Product 이미지 저장 {}", serverPath+imgList.get(i).getImgName());
						}catch (Exception e) {
							e.printStackTrace();
							//파일 변환이 실패할 경우
						}
					}
				}
				else {
					/* 예외 */
				}
			}
			
		}
		return result;
	}
	

	// 공지사항 작성
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
	// 관리자 로그인 ID PW 확인
	@Override
	public Admin findMatchAdmin(Admin admin) {
		return dao.findMatchAdmin(admin); 
	}
	
	//멤버 서치
	@Override
	public List<SearchedMember> searchMember(Map<String, String> dataMap, Pagination page) {
		
		return dao.searchMember(dataMap,page); 
	}

	//옵션 상품
	@Override
	public int insertOptionP(Map<String, String> map) {
		return dao.insertOptionP(map); 
	}


	//멤버 서치 페이지네이션
	@Override
	public Pagination countMember(Map<String, String> dataMap) {
		int cp = Integer.parseInt(dataMap.get("cp"));
		int listcount = dao.countMember(dataMap);
		
		return new Pagination(listcount,cp);
	}
	//상품 서치 페이지네이션
	@Override
	public Pagination countProduct(Map<String, String> dataMap) {
		int cp = Integer.parseInt(dataMap.get("cp"));
		int listcount = dao.countProduct(dataMap);
		return new Pagination(listcount,cp);
	}

	//상품 리스트 반환
	@Override
	public List<SimpleProduct> productselect(Map<String, String> dataMap, Pagination page) {
		return dao.productselect(dataMap,page); 
	}
	//스토어 Detail 및 이미지 
	@Override
	public Store getStoreInfo(int productNo) {
		Store store =dao.getStoreInfo(productNo);
		return store;
	}
	
	//상품 update
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateProduct(List<MultipartFile> images, ProductWrite product, String webPath, String serverPath) {
		int result = 0;
		//1 개행문자 처리
		product.setTitle(XSS(product.getTitle()));
		//2. 상품 메인+상세 등록
		//메인등록 (상품번호 시퀀스 , 이름 , 가격 , 생성일(default) , 상품카테고리)
		result= dao.updateProductCommon(product);
		//2-1 일반스토어 
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
		//2-3 클래스 페이지
		else if(product.getWritecate()==3){
			String plustime="";
			plustime += (product.getStarthour()<=9? "0"+product.getStarthour():product.getStarthour())+":"
					+(product.getStartminute()==0? "0"+product.getStartminute():product.getStartminute())+ " ~ " 
					 + (product.getEndhour()<=9? "0"+product.getEndhour():product.getEndhour())+":"
					 +(product.getEndminute()==0? "0"+product.getEndminute():product.getEndminute());
			product.setStartEndTime(plustime);
			//수업시간 설정
			product.setClassdate(product.getClassdate());
			//일시 성정
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
			// i == images의 인덱스 == 업로드된 파일의 레벨
			// 각 인덱스 요소에 파일이 업로드 되었는지 검사
			if(!images.get(i).getOriginalFilename().equals("")) {
				// 업로드가 된 경우
				// MultipartFile에서 DB저장 필요한 데이터만을 추출하여
				// ProductImage 객체에 담은 후 imgList 추가
				
				ProductImage img = new ProductImage();
				img.setImgPath(webPath);
				img.setImgName(fileRename(images.get(i).getOriginalFilename()));
				img.setImgLevel(i);
				img.setProductNo(product.getProductNo());
				imgList.add(img);
			}
		}
		
		//imgList에 업로드된 이미지가 있다면 DAO 호출
		if(!imgList.isEmpty()){
			result = dao.insertImgList(imgList);
		      
//		      5) 삽입 성공한 행의 개수와 imgList 개수가 같을 때
//		      파일을 서버에 저장
//		      서버즈탭 -> fin server -> Overview
//			server modeules without publishing 체크 -> 프로젝트 경로 일치시키기
//			저장되는 파일 경로를 현재 프로젝트로 지정할 수 있음
			if(result == imgList.size()) {// 성공 ==> 파일저장
				
				//images - MultipartFile List , 실제 파일 자체 + 정보
				//imgList - BoardImage List, DB에 저장할 파일 정보
				for(int i =0 ; i<imgList.size(); i++) {
					// 업로드된 파일이 있는 images의 인덱스 요소를 얻어와 
					// 지정된 경로와 이름으로 파일로 변환하여 저장
					
					try {
						images.get(imgList.get(i).getImgLevel())
						.transferTo(new File(serverPath+imgList.get(i).getImgName() ));
					}catch (Exception e) {
						e.printStackTrace();
						//파일 변환이 실패할 경우
					}
				}
			}
			else {
				/* 예외 */
			}
		}
			
		return result;
	}
//	구독상품 수정 전 조회
	@Override
	public SubsInfo getSubsInfo(Map<String, Integer> dataMap) {
		return dao.getSubsInfo(dataMap);
	}	
	//구독옵션조회
	@Override
	public List<SubsOptions> selectSubsOption(int productNo) {
		return dao.selectSubsOption(productNo);
	}
	
	//구독옵션 추가
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addSubsOption(SubsOptions subsOption) {
		int result = dao.addSubsOption(subsOption);
		return result;
	}
	//구독옵션 삭제
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteSubsOption(int suboptionNo) {
		int result = dao.deleteSubsOption(suboptionNo);
		return result;
	}
	//구독옵션 변경
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int changeSubsOption(SubsOptions subsOptions){
		int result = dao.changeSubsOption(subsOptions);
		return result;
	}
	// 크로스 사이트 스크립트 방지 처리 메소드
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

	   // 파일명 변경 메소드
	   public static String fileRename(String originFileName) {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	      String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

	      int ranNum = (int) (Math.random() * 100000); // 5자리 랜덤 숫자 생성

	      String str = "_" + String.format("%05d", ranNum);

	      String ext = originFileName.substring(originFileName.lastIndexOf("."));

	      return date + str + ext;
	   }
	   
	   // 개행 문자 변경 메소드
	   public static String changeNewLine(String content) {
	      return content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	      
	      
	   }

	@Override
	public String sendmessage() {
		Long time = System.currentTimeMillis();
		List<MessagesRequestDto> list = new ArrayList<>();
		MessagesRequestDto oneMRD = new MessagesRequestDto("01025639598","내가보낸 메세지");
		Gson gson = new Gson();
		list.add(oneMRD);
		
//		Body Json
		SmsRequestDto SRD = new SmsRequestDto();
		SRD.setType("SMS");
		SRD.setContentType("COMM");
		SRD.setCountryCode("82");
		SRD.setFrom("01025639598");
		SRD.setMessages(list);
		SRD.setContent("-장발장-");
		
		String jsonBody = gson.toJson(SRD);
		
		String accessKey="zC9zNinRIJqHLInx7Ajx";
		String serviceId="ncp:sms:kr:275225299182:final_jvj_prj";
		
		String hostUrl ="https://sens.apigw.ntruss.com/sms/v2/services/"
			+serviceId+"/messages";
		System.out.println(jsonBody);
		// 헤더에서 여러 설정값들을 잡아준다.
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
		//시키는대로 헤더를 암호화한다
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

	// 이미지 스케쥴러
	@Override
	public List<String> selectImgList() {
		return dao.selectImgList();
	}
	
	//쿠폰 추가
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

