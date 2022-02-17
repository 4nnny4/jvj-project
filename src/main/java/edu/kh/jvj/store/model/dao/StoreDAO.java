package edu.kh.jvj.store.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.jvj.store.model.vo.Pagination;
import edu.kh.jvj.store.model.vo.Search;
import edu.kh.jvj.store.model.vo.Store;

@Repository
public class StoreDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public List<Store> selectStoreList(Pagination pagination, Search search) {

		// offset : 몇행을 건너 뛸것인지
		// limit 건너뛴 위치부터 몇행을 조회할지
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);

		return mybatis.selectList("storeMapper.selectStoreList", search, rowBounds);

	}

	public int getListCount(Search search) {

		return mybatis.selectOne("storeMapper.getListCount", search);
	}

	public Store selectStoreDetail(Store store) {

		return mybatis.selectOne("storeMapper.selectStoreDetail", store);

	}

	public List<Store> storeImgSelect(int no) {

		return mybatis.selectList("storeMapper.storeImgSelect", no);
	}

	public int likeit(Store store) {

		return mybatis.insert("storeMapper.likeit", store);
	}

	public int doesntLikeit(Store store) {
		// TODO Auto-generated method stub
		return mybatis.delete("storeMapper.doesntLikeit", store);
	}

	public List<Store> advantage() {
		// TODO Auto-generated method stub
		return mybatis.selectList("storeMapper.advantage");
	}

	public int selectAmount(Store store) {

		return mybatis.selectOne("storeMapper.selectAmount",store);
	}

	public int selectpdtCount(Store store) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("storeMapper.selectpdtCount",store);
	}

	public int expiredSale() {
		
		return mybatis.delete("storeMapper.expiredSale");
	}

	public List<Store> selectRankProduct() {
		// TODO Auto-generated method stub
		return mybatis.selectList("storeMapper.selectRankProduct");
	}

}
