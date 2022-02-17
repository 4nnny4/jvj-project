package edu.kh.jvj.subscribe.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.jvj.subscribe.model.dao.SubDAO;
import edu.kh.jvj.subscribe.model.vo.ProductImage;
import edu.kh.jvj.subscribe.model.vo.SearchVO;
import edu.kh.jvj.subscribe.model.vo.SubVO;

@Service
public class SubServiceImpl implements SubService{

	@Autowired
	private SubDAO dao;

	// 빵 상세조회
//	@Override
//	public SubVO selectSubBread(Map<String, Integer> map) {
//		
//		return dao.selectSubBread(map);
//	}

	@Override
	public List<SubVO> selectSubBread(Map<String, Integer> map) {
		return dao.selectSubBread(map);
	}

	@Override
	public List<ProductImage> selectProductImageList(Map<String, Integer> map) {
		return dao.selectProductImageList(map);
	}
	
	
	// 좋아요
	@Override
	public int likeSub(Map<String, Integer> map) {
		return dao.likeSub(map);
	}

	@Override
	public int undolike(Map<String, Integer> map) {
		return dao.undolike(map);
	}

	@Override
	public int likecheck(Map<String, Integer> map) {
		return dao.likecheck(map);
	}

	
	
	
	// 검색
	@Override
	public List<SearchVO> search(SearchVO search) {
		return dao.search(search);
	}

	

	
}
