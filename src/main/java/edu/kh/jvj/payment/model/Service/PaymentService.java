package edu.kh.jvj.payment.model.Service;

import java.util.List;
import java.util.Map;

import edu.kh.jvj.mypage.model.vo.Coupon;
import edu.kh.jvj.onedayclass.model.vo.OnedayClass;
import edu.kh.jvj.payment.model.vo.KaKaoPayKey;
import edu.kh.jvj.payment.model.vo.OrderSubsOption;
import edu.kh.jvj.payment.model.vo.Payment;
import edu.kh.jvj.payment.model.vo.RegualrPayInfo;
import edu.kh.jvj.payment.model.vo.RegularPaySuccessSave;
import edu.kh.jvj.payment.model.vo.StoreOrderInfo;
import edu.kh.jvj.payment.model.vo.SubsOrder;

public interface PaymentService {

	
	
	/** 클래스 정보 가져오기
	 * @param productNo
	 * @return
	 */
	OnedayClass getClassSelect(String productNo);
	
	/**결제 정보 저장
	 * @param payInfo
	 * @return
	 */
	int insertPaymentInfo(Payment payInfo);

	List<OrderSubsOption> getOptionsList(String getOptionSearch);

	int getPerchaseSeq();

	int saveRegularPayKey(RegualrPayInfo rpi);

	KaKaoPayKey getKaKaoTid(String partner_order_id);

	int saveRegularSuccess(RegularPaySuccessSave saveRegularSuccess);

	List<RegualrPayInfo> getRegularPayList();
	
	RegualrPayInfo getRecieverInfo(int partner_order_id);

	String getProductMainImg(int productNo);

	int getPayDoneYn(String partner_order_id);

	Payment getPayResult(String merchant_uid);

	int saveStoreInfo(StoreOrderInfo storeInfo);

	List<Coupon> callCoupon(int memberNo);

	int possibleCheck(int productNo);

}
