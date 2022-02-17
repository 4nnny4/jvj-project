package edu.kh.jvj.mypage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.mypage.model.vo.Coupon;
import edu.kh.jvj.mypage.model.vo.CouponStatus;
import edu.kh.jvj.mypage.model.vo.Like;
import edu.kh.jvj.mypage.model.vo.Pagination;
import edu.kh.jvj.mypage.model.vo.Purchase;
import edu.kh.jvj.mypage.model.vo.Order;

@Repository
public class MypageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	/** 쿠폰 페이지 전체 쿠폰 수
	 * @param coupon 
	 * @param search 
	 * @param member 
	 * @param coupon 
	 * @return
	 */
	public int getCouponCount(Coupon coupon) {
		return sqlSession.selectOne("mypageMapper.getCouponCount",coupon);
	}


	/** 페이지에 보여지는 쿠폰 수 지정
	 * @param pagination
	 * @param search 
	 * @return
	 */
	public List<Coupon> getCouponList(Pagination pagination, Coupon coupon) {
		
		int offset = (pagination.getCurrentPage() - 1)* pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSession.selectList("mypageMapper.selectCouponList", coupon , rowBounds);
	}


	/** 쿠폰 카테고리
	 * @return
	 */
	public List<CouponStatus> statusCategory() {
		return sqlSession.selectList("mypageMapper.statusCategory");
	}


	/** 좋아요 전체 목록 조회
	 * @return
	 */
	public int getLikeCount(Like like) {
		return sqlSession.selectOne("mypageMapper.getLikeCount", like);
	}


	/** 좋아요 상세 목록 조회
	 * @param pagination2
	 * @param like
	 * @return
	 */
	public List<Like> getLikeList(Pagination pagination, Like like) {
		int offset = (pagination.getCurrentPage() - 1)* pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSession.selectList("mypageMapper.getLikeList", like , rowBounds);
	}


	/** 좋아요 취소
	 * @param map
	 * @return
	 */
	public int cancleLike(Like like) {
		return sqlSession.delete("mypageMapper.cancleLike",like);
	}


	/** 회원 정보 수정
	 * @param member
	 * @return
	 */
	public int memberUpdate(Member member) {
		return sqlSession.update("mypageMapper.memberUpdate", member);
	}



	/** 암호화된 비밀번호 조회
	 * @param map
	 * @return
	 */
	public String selectDecodePw(String memberNo) {
		return sqlSession.selectOne("mypageMapper.selectDecodePw", memberNo);
		
	}


	/** 현재 비밀번호와 일치시 다시 암호화 후 DB에 저장
	 * @param map
	 * @return
	 */
	public int modifyPassword(Map<String, String> map) {
		return sqlSession.update("mypageMapper.modifyPassword", map);
	}


	/** 일반회원 탈퇴
	 * @param memberNo
	 * @return result
	 */
	public int secession(int memberNo) {
		return sqlSession.update("mypageMapper.secession", memberNo);
	}


	/** 일반 결제 리스트
	 * @param order
	 * @return
	 */
	public List<Order> purList(Order order, Pagination pagination) {
		
		int offset = (pagination.getCurrentPage() - 1)* pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSession.selectList("mypageMapper.purList", order, rowBounds);
	}



 
	/** 클래스 결제 리스트
	 * @param order
	 * @return
	 */
	public List<Order> classPurList(Order order, Pagination pagination) {
		
		int offset = (pagination.getCurrentPage() - 1)* pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSession.selectList("mypageMapper.classPurList", order, rowBounds);
	}


	/** 정기 구독 결제 리스트
	 * @param order
	 * @return
	 */
	
	public List<Order> subscriptionList(Order order, Pagination pagination) {
		
		int offset = (pagination.getCurrentPage() - 1)* pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSession.selectList("mypageMapper.subscription", order, rowBounds);
	}


	/** 메인페이지 리스트
	 * @param order
	 * @return
	 */
	public List<Order> mypageMain(Order order) {
		return sqlSession.selectList("mypageMapper.mypageMain", order);
	}


	public int purchaseListCount(Order order) {
		
		return sqlSession.selectOne("mypageMapper.purchaseListCount", order);
	}


	public int classListCount(Order order) {
		return sqlSession.selectOne("mypageMapper.classListCount", order);
	}


	public int subListCount(Order order) {
		return sqlSession.selectOne("mypageMapper.subListCount", order);
	}

	
	/** 회원 날짜계산
	 * @param member
	 * @return member
	 */
	public int memberDate(Member member) {
		return sqlSession.selectOne("mypageMapper.memberDate", member);
	}

	/** 일반 상품 결제 취소
	 * @param order
	 * @return result
	 */
	public int cancelPayment(Order order) {
		return sqlSession.update("mypageMapper.cancelPayment", order);
	}


	/** 원데이 클래스 결제 취소
	 * @param order
	 * @return result
	 */
	public int cancelOnedayClass(Order order) {
		return sqlSession.update("mypageMapper.cancelOnedayClass", order);
	}




	/** 구독 결제 취소
	 * @param order
	 * @return result
	 */
	public int cancelSubscription(Order order) {
		return sqlSession.update("mypageMapper.cancelSubscription", order);
	}



	











	

	






	

	
	
}
