package edu.kh.jvj.payment.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.mypage.model.vo.Coupon;
import edu.kh.jvj.onedayclass.model.vo.OnedayClass;
import edu.kh.jvj.payment.model.vo.KaKaoPayKey;
import edu.kh.jvj.payment.model.vo.OrderSubsOption;
import edu.kh.jvj.payment.model.vo.Payment;
import edu.kh.jvj.payment.model.vo.RegualrPayInfo;
import edu.kh.jvj.payment.model.vo.RegularPaySuccessSave;
import edu.kh.jvj.payment.model.vo.StoreOrderInfo;
import edu.kh.jvj.payment.model.vo.SubsOrder;


@Repository
public class PaymentDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	
	/** 원데이 클래스 정보 가져오기
	 * @param productNo
	 * @return list
	 */
	public OnedayClass getClassSelect(String productNo) {
		return sqlSession.selectOne("paymentMapper.getClassSelect", productNo);
	}



	public int insertPaymentInfo(Payment payInfo) {
		return sqlSession.insert("paymentMapper.insertPaymentInfo", payInfo);
	}



	public int insertOrderListInfo(Payment payInfo) {
		return sqlSession.insert("paymentMapper.insertOrderListInfo", payInfo);
	}



	public int insertOrderOptionInfo(Payment payInfo) {
		return sqlSession.insert("paymentMapper.insertOrderOptionInfo", payInfo);
	}



	public int insertAPIorderKey(Payment payInfo) {
		return sqlSession.insert("paymentMapper.insertAPIorderKey", payInfo);
	}



	public List<OrderSubsOption> getOptionsList(String getOptionSearch) {
		return sqlSession.selectList("paymentMapper.getOptionsList", getOptionSearch);
	}



	public int getPerchaseSeq() {
		return sqlSession.selectOne("paymentMapper.getPerchaseSeq");
	}



	public int saveRegularPayKey(RegualrPayInfo rpi) {
		sqlSession.insert("paymentMapper.saveTempOrder",rpi);
		
		return sqlSession.insert("paymentMapper.saveRegularPayKey",rpi);
	}



	public KaKaoPayKey getKaKaoTid(String partner_order_id) {
		return sqlSession.selectOne("paymentMapper.getKaKaoTid",partner_order_id);
	}



	public int saveRegularSuccess(RegularPaySuccessSave saveRegularSuccess) {
		return sqlSession.update("paymentMapper.saveRegularSuccess",saveRegularSuccess);
	}



	public Payment selectPayInfo(int partner_order_id) {
		return sqlSession.selectOne("paymentMapper.selectPayInfo",partner_order_id);
	}



	public List<RegualrPayInfo> getRegularPayList() {
		return sqlSession.selectList("paymentMapper.getRegularPayList");
	}



	public int updatePayStatement(int partner_order_id) {
		return sqlSession.update("paymentMapper.updatePayStatement",partner_order_id);
	}



	public Member getAddrInfo(int memberNo) {
		return sqlSession.selectOne("paymentMapper.getAddrInfo",memberNo);
	}


	public RegualrPayInfo getRecieverInfo(int partner_order_id) {
		return sqlSession.selectOne("paymentMapper.getRecieverInfo",partner_order_id);
	}

	public String getProductMainImg(int productNo) {
		return sqlSession.selectOne("paymentMapper.getProductMainImg",productNo);
	}



	public int getPayDoneYn(String partner_order_id) {
		return sqlSession.selectOne("paymentMapper.getPayDoneYn",partner_order_id);
	}



	public Payment getPayResult(String merchant_uid) {
		return sqlSession.selectOne("paymentMapper.getPayResult",merchant_uid);
	}



	public int updatePeopleInfo(Payment payInfo) {
		return sqlSession.update("paymentMapper.updatePeopleInfo",payInfo);
	}



	public int getPossible(Payment payInfo) {
		return sqlSession.selectOne("paymentMapper.getPossible",payInfo);
	}



	public int saveStoreInfo(StoreOrderInfo storeInfo) {
		return sqlSession.insert("paymentMapper.saveStoreInfo",storeInfo);
	}



	public int saveStoreDetail(StoreOrderInfo storeInfo) {
		return sqlSession.insert("paymentMapper.saveStoreDetail",storeInfo);
	}



	public int saveStoreOption(StoreOrderInfo storeInfo) {
		return sqlSession.insert("paymentMapper.saveStoreOption",storeInfo);
	}

	
	public int saveStorepayKey(StoreOrderInfo storeInfo) {
		return sqlSession.insert("paymentMapper.saveStorepayKey",storeInfo);
	}



	public int deletecart(StoreOrderInfo storeInfo) {
		return sqlSession.delete("paymentMapper.deletecart",storeInfo);
	}



	public List<Coupon> callCoupon(int memberNo) {
		int offset = 0;
		int limit = 10;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSession.selectList("paymentMapper.callCoupon",memberNo,rowBounds);
	}

	public int deleteCoupon(StoreOrderInfo storeInfo) {
		return sqlSession.update("paymentMapper.deleteCoupon",storeInfo);
	}

	public int possibleCheck(int productNo) {
		return sqlSession.selectOne("paymentMapper.possibleCheck",productNo);
	}								
	

}
