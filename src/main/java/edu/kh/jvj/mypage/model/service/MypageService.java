package edu.kh.jvj.mypage.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.member.model.vo.SnsToken;
import edu.kh.jvj.mypage.model.vo.Coupon;
import edu.kh.jvj.mypage.model.vo.CouponStatus;
import edu.kh.jvj.mypage.model.vo.Like;
import edu.kh.jvj.mypage.model.vo.Pagination;
import edu.kh.jvj.mypage.model.vo.Pagination2;
import edu.kh.jvj.mypage.model.vo.Purchase;
import edu.kh.jvj.mypage.model.vo.Order;

public interface MypageService {

	/** 쿠폰 페이지네이션
	 * @param cp
	 * @param coupon 
	 * @param search 
	 * @param serch 
	 * @return
	 */
	Pagination couponPagination(int cp, Coupon coupon);

	/** 쿠폰 목록조회
	 * @param pagination
	 * @param coupon
	 * @return
	 */
	List<Coupon> couponList(Pagination pagination, Coupon coupon);

	/** 쿠폰 카테고리
	 * @return
	 */
	List<CouponStatus> statusCategory();

	/** 좋아요 페이지네이션
	 * @param cp
	 * @return
	 */
	Pagination getLikePagination(int cp, Like like);

	/** 좋아요 목록 조회
	 * @param pagination2
	 * @param like
	 * @return
	 */
	List<Like> getLikeList(Pagination paginationLike, Like like);

	/** 좋아요 취소
	 * @param map
	 * @return
	 */
	int cancleLike(Like like);

	/** 회원 정보 수정
	 * @param member
	 * @return
	 */
	int memberUpdate(Member member);

	/** 비밀번호 수정
	 * @param map
	 * @return
	 */
	int updatePw(Map<String, String> map);


	/** 일반 결제 리스트
	 * @param order
	 * @param pagination 
	 * @return
	 */
	List<Order> purList(Order order, Pagination pagination);
	
    
    
    /** api 회원탈퇴 
     * @param memberNo
     * @return result
     */
    int secession(int memberNo) throws Exception;
    

    int getKakaoToken(String snsToken) throws Exception;
    
    

	/** 원데이 클래스 결제 리스트
	 * @param pagination 
	 * @param order
	 * @return
	 */
	List<Order> classList(Pagination pagination, Order order);
	

	/** 정기구독 결제 리스트
	 * @param order
	 * @return
	 */
	List<Order> subscription(Order order, Pagination pagination);

	/** 메인 페이지 리스트
	 * @param order
	 * @return
	 */
	List<Order> mypageMain(Order order);

	Pagination purPagination(int cp, Order order);

	Pagination classPagination(int cp, Order order);

	Pagination subPagination(int cp, Order order);

	
	
	
	
	/** 일반 결제 취소
	 * @param order
	 * @return result
	 */
	int cancelPayment(Order order);

	/** 클래스 결제 취소
	 * @param order
	 * @return result
	 */
	int cancelOnedayClass(Order order);

	

	/** 회원 가입일 계산
	 * @param member
	 * @return
	 */
	int memberDate(Member loginMember);

	/** 구독 취소
	 * @param order
	 * @return
	 */
	int cancelSubscription(Order order);

	


}