package edu.kh.jvj.store.model.service;

import java.util.List;

import edu.kh.jvj.review.model.vo.Review;
import edu.kh.jvj.store.model.vo.Pagination;
import edu.kh.jvj.store.model.vo.Search;
import edu.kh.jvj.store.model.vo.Store;

public interface StoreService {

	List<Store> selectStoreList(Pagination pagination, Search search);

	Pagination getPagination(int cp, Search search);

	Store selectStoreDetail(Store store);

	List<Store> storeImgSelect(int no);

	int likeit(Store store);

	int doesntLikeit(Store store);

	List<Store> advantage();

	int selectAmount(Store store);

	int selectpdtCount(Store store);

	int expiredSale();

	List<Store> selectRankProduct();


}
