package edu.kh.jvj.onedayclass.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.jvj.onedayclass.model.vo.OnedayClass;

@Repository
public class OnedayClassDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**ClassList 1페이지 리턴
	 * @return List
	 */
	public List<OnedayClass> selsectClassList() {	
		/* return sqlSession.selectList("dsad"); */
		return null;
	}

	public List<OnedayClass> scrollListAdd(Map<String, String> pagination) {
		//RowBounds : Offset과 limit값을 넘겨 받아 페이징 처리를 쉽게
		// 할 수 있게 하는 객체
		//offset : 몇 행을 건너 뛸 것인지 지정 
		//limit : 건너 뛴 위치부터 몇 행을 조회할지 지정
		
		int limit =Integer.parseInt(pagination.get("offset"));
		int offset = (Integer.parseInt(pagination.get("pagination")) -1 )* limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSession.selectList("classListMapper.scrollListAdd",pagination,rowBounds);
	}

	public OnedayClass selectOneClass(Map<String, Integer> map) {
		return sqlSession.selectOne("classListMapper.selectOneClass", map);
	}

	public int likeclass(Map<String, Integer> map) {
		return sqlSession.insert("classListMapper.likeclass", map);
	}

	public int undolike(Map<String, Integer> map) {
		return sqlSession.delete("classListMapper.undolike", map);
	}

	public int likecheck(Map<String, Integer> map) {
		return sqlSession.selectOne("classListMapper.likecheck", map);
	}

	public int doUpdateOverdueClass() {
		return sqlSession.update("classListMapper.doUpdateOverdueClass");
	}
	
	
	
}
