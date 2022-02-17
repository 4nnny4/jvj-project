package edu.kh.jvj.admin.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.jvj.admin.model.vo.Admin;
import edu.kh.jvj.admin.model.vo.MadeCoupon;
import edu.kh.jvj.admin.model.vo.ProductWrite;
import edu.kh.jvj.admin.model.vo.Reviews;
import edu.kh.jvj.admin.model.vo.SalesRank;
import edu.kh.jvj.admin.model.vo.SearchedMember;
import edu.kh.jvj.admin.model.vo.SimpleProduct;
import edu.kh.jvj.admin.model.vo.SubsInfo;
import edu.kh.jvj.admin.model.vo.SubsOptions;
import edu.kh.jvj.notice.model.vo.Notice;
import edu.kh.jvj.store.model.vo.Pagination;
import edu.kh.jvj.store.model.vo.Store;

public interface AdminService {

	/** Product Insert 
	 * @param images
	 * @param product
	 * @param webPath
	 * @param serverPath
	 * @return result
	 * ProductWrite- writecate 상품대분류
	 */
	int insertProduct(List<MultipartFile> images, ProductWrite product, String webPath,
			String serverPath);

	/** Notice Insert
	 * @param title
	 * @param noticecate
	 * @param editordata
	 * @return result
	 */
	int insertNotice(Notice notices);
	int updateNotice(String title, String noticecate, String editordata, String noticeNo);

	/** findMatchedAdminIdPw
	 * @param admin
	 * @return count(*)
	 */
	Admin findMatchAdmin(Admin admin);

	/** 회원정보검색결과 회원리스트
	 * @param dataMap
	 * @param page
	 * @return
	 */
	List<SearchedMember> searchMember(Map<String, String> dataMap, Pagination page);

	/** 추가옵션상품
	 * @param map
	 * @return
	 */
	int insertOptionP(Map<String, String> map);

	/** 결과조회 전체 회원 수
	 * @param dataMap
	 * @return
	 */
	Pagination countMember(Map<String, String> dataMap);

	/** 결과조회 전체 상품 수 
	 * @param dataMap
	 * @return
	 */
	Pagination countProduct(Map<String, String> dataMap);

	/** 결과조회 전체 상품 리스트
	 * @param dataMap
	 * @param page
	 * @return
	 */
	List<SimpleProduct> productselect(Map<String, String> dataMap, Pagination page);

	/** 수정시 스토어 상세
	 * @param productNo
	 * @return
	 */
	Store getStoreInfo(int productNo);

	/** 상품 update
	 * @param images
	 * @param product
	 * @param webPath
	 * @param serverPath
	 * @return
	 */
	int updateProduct(List<MultipartFile> images, ProductWrite product, String webPath, String serverPath);

	String sendmessage();

	SubsInfo getSubsInfo(Map<String, Integer> dataMap);

	List<SubsOptions> selectSubsOption(int productNo);

	int addSubsOption(SubsOptions subsOption);

	int deleteSubsOption(int suboptionNo);

	int changeSubsOption(SubsOptions subsOptions);

	List<String> selectImgList();

	int makingCoupon(MadeCoupon mCoupon);

	List<SubsInfo> getSubsList(Map<String,String> dataMap, Pagination page);

	Pagination countSubsMember(Map<String, String> dataMap);

	List<SalesRank> getStoreRanks();

	Pagination countReview(Map<String, String> dataMap);

	List<Reviews> getReviewList(Map<String, String> dataMap, Pagination page);

	String getReview(int reviewNo);

	int blindReview(Map<String, Integer> dataMap);
	
}
