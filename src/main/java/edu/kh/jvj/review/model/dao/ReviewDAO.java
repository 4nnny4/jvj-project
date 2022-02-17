package edu.kh.jvj.review.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.kh.jvj.review.model.vo.Review;
import edu.kh.jvj.review.model.vo.RvSearch;
import edu.kh.jvj.store.model.vo.Pagination;

@Component
public class ReviewDAO {
	
	@Autowired
	private SqlSession mybatis;

	public List<Review> selectOrder(int memberNo) {
		
		return mybatis.selectList("reviewMapper.selectOrder",memberNo);
	}

	public String selectImgPath(int productNo) {
		
		return mybatis.selectOne("reviewMapper.selectImgPath",productNo);
	}

	public int writeReview(Review review) {
	
		return mybatis.insert("reviewMapper.writeReview",review);
	}

	public List<Review> selectReviewList(Pagination pagination, RvSearch rs) {
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return mybatis.selectList("reviewMapper.selectReviewList",rs,rowBounds);
	}

	public int getListCount(int no) {
	
		return mybatis.selectOne("reviewMapper.getListCount", no);
	}

	public int countOrder(Review rv) {

		return mybatis.selectOne("reviewMapper.countOrder",rv);
	}

	public int countReview(Review rv) {
		
		return mybatis.selectOne("reviewMapper.countReview",rv);
	}
	
}
