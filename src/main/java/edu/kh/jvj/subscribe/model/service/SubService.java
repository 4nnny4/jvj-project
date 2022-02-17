package edu.kh.jvj.subscribe.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.jvj.subscribe.model.vo.ProductImage;
import edu.kh.jvj.subscribe.model.vo.SearchVO;
import edu.kh.jvj.subscribe.model.vo.SubVO;

public interface SubService {

	/** 구독 빵 상세 조회
	 * @param map
	 * @return 
	 */
	//SubVO selectSubBread(Map<String, Integer> map);

	List<SubVO> selectSubBread(Map<String, Integer> map);

	List<ProductImage> selectProductImageList(Map<String, Integer> map);

	// 좋아요
	int likeSub(Map<String, Integer> map);

	int undolike(Map<String, Integer> map);

	int likecheck(Map<String, Integer> map);

	
	
	/** 검색
	 * @param search
	 * @return
	 */
	List<SearchVO> search(SearchVO search);


}
