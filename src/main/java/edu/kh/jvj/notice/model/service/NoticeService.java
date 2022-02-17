package edu.kh.jvj.notice.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.jvj.admin.model.vo.MadeCoupon;
import edu.kh.jvj.notice.model.vo.Notice;
import edu.kh.jvj.store.model.vo.Pagination;

public interface NoticeService {

	/** 공지사항 총 개수 : 페이지네이션 계산
	 * @return COUNT(*)
	 */
	Pagination countNotice(Map<String, String> map);
	

	/** 공지사항 상세
	 * @param noticeNo
	 * @return
	 */
	Notice selectOneNotice(int noticeNo);

	/** 공지사항 검색 리스트
	 * @param page
	 * @param dataMap( searchInput, noticecate)
	 * @return
	 */
	List<Notice> selectNoticeList(Pagination page, Map<String, String> dataMap);


	List<MadeCoupon> selectCoupons(int noticeNo);


	MadeCoupon getMadeCoupon(int madeCouponNo);

	int insertCouponToMember(MadeCoupon madeCoupon);

}
