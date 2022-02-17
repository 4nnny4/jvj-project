package edu.kh.jvj.review.model.service;

import java.util.List;

import edu.kh.jvj.review.model.vo.Review;
import edu.kh.jvj.review.model.vo.RvSearch;
import edu.kh.jvj.store.model.vo.Pagination;

public interface ReviewService {

	List<Review> selectOrder(int memberNo);

	String selectImgPath(int productNo);

	int writeReview(Review review);

	List<Review> selectReviewList(Pagination pagination, RvSearch rs);

	Pagination getPagination(int cp, int no);

	int countOrder(Review rv);

	int countReview(Review rv);

}
