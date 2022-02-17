package edu.kh.jvj.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.jvj.review.model.dao.ReviewDAO;
import edu.kh.jvj.review.model.vo.Review;
import edu.kh.jvj.review.model.vo.RvSearch;
import edu.kh.jvj.store.model.vo.Pagination;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDAO dao;

	@Override
	public List<Review> selectOrder(int memberNo) {
		
		return dao.selectOrder(memberNo);
	}

	@Override
	public String selectImgPath(int productNo) {
		// TODO Auto-generated method stub
		return dao.selectImgPath(productNo);
	}

	@Override
	@Transactional
	public int writeReview(Review review) {

		return dao.writeReview(review);
	}

	@Override
	public List<Review> selectReviewList(Pagination pagination, RvSearch rs) {

		return dao.selectReviewList(pagination,rs);
	}

	@Override
	public Pagination getPagination(int cp, int no) {
		// 전체 게시글 수 카운트
		int listCount = 0;
	
			 listCount = dao.getListCount(no);
		return new Pagination(listCount,cp);
	}

	@Override
	public int countOrder(Review rv) {
		
		return dao.countOrder(rv);
	}

	@Override
	public int countReview(Review rv) {
	
		return dao.countReview(rv);
	}
}
