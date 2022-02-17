package edu.kh.jvj.subscribe.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.jvj.subscribe.model.vo.ProductImage;
import edu.kh.jvj.subscribe.model.vo.SearchVO;
import edu.kh.jvj.subscribe.model.vo.SubVO;

@Repository
public class SubDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 빵 상세 페이지
	 * @param map
	 * @return SubVO
	 */
//	public SubVO selectSubBread(Map<String, Integer> map) {
//		
//		return sqlSession.selectList("subscribeMapper.selectSubBread", map);
//	}

	public List<SubVO> selectSubBread(Map<String, Integer> map) {
		return sqlSession.selectList("subscribeMapper.selectSubBread", map);
	}

	public List<ProductImage> selectProductImageList(Map<String, Integer> map) {
		return sqlSession.selectList("subscribeMapper.selectProductImageList", map);
	}
	
	
	// 좋아요
	public int likeSub(Map<String, Integer> map) {
		return sqlSession.insert("subscribeMapper.likeSub", map);
	}

	public int undolike(Map<String, Integer> map) {
		return sqlSession.delete("subscribeMapper.undolike", map);
	}

	public int likecheck(Map<String, Integer> map) {
		return sqlSession.selectOne("subscribeMapper.likecheck", map);
	}

	
	// 검색
	public List<SearchVO> search(SearchVO search) {
		return sqlSession.selectList("subscribeMapper.search", search);
	}
}
