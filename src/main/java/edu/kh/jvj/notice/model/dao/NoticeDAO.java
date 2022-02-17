package edu.kh.jvj.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.jvj.admin.model.vo.MadeCoupon;
import edu.kh.jvj.notice.model.vo.Notice;
import edu.kh.jvj.store.model.vo.Pagination;

@Repository
public class NoticeDAO {

	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int countNotice(Map<String, String> map) {
		return sqlSession.selectOne("adminMapper.countNotice",map);
	}
	public Notice selectOneNotice(int noticeNo) {
		return sqlSession.selectOne("adminMapper.oneNotice",noticeNo);
	}
	public List<Notice> selectSearchNoticeList(Pagination page, Map<String, String> dataMap) {
		//offset : 몇행을 건너 뛸것인지
		//limit 건너뛴 위치부터 몇행을 조회할지
		int limit = page.getLimit();
		int offset = (page.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		return sqlSession.selectList("adminMapper.noticeSearchList",dataMap,rowBounds);
	}
	public List<MadeCoupon> selectCoupons(int noticeNo) {
		return sqlSession.selectList("adminMapper.selectCoupons",noticeNo);
	}
	public MadeCoupon getMadeCoupon(int madeCouponNo) {
		return sqlSession.selectOne("adminMapper.getMadeCoupon",madeCouponNo);
	}
	public int insertCouponToMember(MadeCoupon madeCoupon) {
		return sqlSession.insert("adminMapper.insertCouponToMember",madeCoupon);
	}
	public int deductionCoupon(int madeCouponNo) {
		return sqlSession.update("adminMapper.deductionCoupon", madeCouponNo);
	}
	public void ChangeCouponStatus(int madeCouponNo) {
		sqlSession.update("adminMapper.ChangeCouponStatus", madeCouponNo);
	}
	public int countGetCoupon(MadeCoupon madeCoupon) {
		return sqlSession.selectOne("adminMapper.countGetCoupon",madeCoupon);
	}
	public void insertCouponHistory(MadeCoupon madeCoupon) {
		sqlSession.insert("adminMapper.insertCouponHistory",madeCoupon);
	}
	
}
