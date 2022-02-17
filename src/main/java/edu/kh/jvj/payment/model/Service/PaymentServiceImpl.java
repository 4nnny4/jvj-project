package edu.kh.jvj.payment.model.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.mypage.model.vo.Coupon;
import edu.kh.jvj.onedayclass.model.vo.OnedayClass;
import edu.kh.jvj.payment.model.dao.PaymentDAO;
import edu.kh.jvj.payment.model.vo.KaKaoPayKey;
import edu.kh.jvj.payment.model.vo.OrderSubsOption;
import edu.kh.jvj.payment.model.vo.Payment;
import edu.kh.jvj.payment.model.vo.RegualrPayInfo;
import edu.kh.jvj.payment.model.vo.RegularPaySuccessSave;
import edu.kh.jvj.payment.model.vo.StoreOrderInfo;
import edu.kh.jvj.payment.model.vo.SubsOrder;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentDAO dao;
	
	@Override
	public OnedayClass getClassSelect(String productNo) {
		return dao.getClassSelect(productNo);
	}
	
	//결제 정보 저장
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertPaymentInfo(Payment payInfo) {
		
		int result = 0;
		
		//구독상품 주문정보
		if(payInfo.getProductCd() == 3) {
			
			int countPossiblePeople = dao.getPossible(payInfo);
			if(payInfo.getAmount()>countPossiblePeople) {
				result= -1;
				return result;
			}
			
			//purchase insert
			result = dao.insertPaymentInfo(payInfo);
			
			//상세 정보 저장
			result = dao.insertOrderListInfo(payInfo);
				
			//상품의 욥션 정보 저장
			result = dao.insertOrderOptionInfo(payInfo);
			
			result = dao.updatePeopleInfo(payInfo);
				
			//API 결제 키 저장
			result = dao.insertAPIorderKey(payInfo);
				
		}
		return result;
	}

	@Override
	public List<OrderSubsOption> getOptionsList(String getOptionSearch) {
		return dao.getOptionsList(getOptionSearch);
	}

	@Override
	public int getPerchaseSeq() {
		return dao.getPerchaseSeq();
	}

	@Override
	public KaKaoPayKey getKaKaoTid(String partner_order_id) {
		return dao.getKaKaoTid(partner_order_id);
	}
	
	//주문정보 일시저장
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveRegularPayKey(RegualrPayInfo rpi) {
		
		if(rpi.isShippingAddrEqualMemberAddr()) {
			Member reciever = dao.getAddrInfo(rpi.getMemberNo());
			System.out.println(reciever);
			rpi.setShippingAddr(reciever.getMemberAddress());
			rpi.setShippingEmail(reciever.getMemberEmail());
			rpi.setShippingPhone(reciever.getMemberPhone());
			rpi.setShippingName(reciever.getMemberName());
		}
		System.out.println(rpi);
		
		return  dao.saveRegularPayKey(rpi);
	}
	//주문성공
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveRegularSuccess(RegularPaySuccessSave saveRegularSuccess) {
		
		//sid저장하고
		int result = dao.saveRegularSuccess(saveRegularSuccess);
		//구매내역 넣고
		
			//1. 상품번호 , 합계금액, 회원번호 가져오기
		Payment searchpay = dao.selectPayInfo(saveRegularSuccess.getPartner_order_id());
		//거래정보 Y로 업데이트
		result = dao.updatePayStatement(saveRegularSuccess.getPartner_order_id());
		
		//상세 정보 저장
		searchpay.setPurchaseNo(saveRegularSuccess.getPartner_order_id());
		searchpay.setAmount(Integer.parseInt(saveRegularSuccess.getQuantity()));
		result = dao.insertOrderListInfo(searchpay);
		//옵션명 저장
		//상품의 욥션 정보 저장
		searchpay.setProductOption(saveRegularSuccess.getItem_name());
		result = dao.insertOrderOptionInfo(searchpay);
		
		return result;
	}

	@Override
	public List<RegualrPayInfo> getRegularPayList() {
		return dao.getRegularPayList();
	}

	@Override
	public RegualrPayInfo getRecieverInfo(int partner_order_id) {
		return dao.getRecieverInfo(partner_order_id);
	}

	@Override
	public String getProductMainImg(int productNo) {
		return dao.getProductMainImg(productNo);
	}

	@Override
	public int getPayDoneYn(String partner_order_id) {
		return dao.getPayDoneYn(partner_order_id);
	}

	@Override
	public Payment getPayResult(String merchant_uid) {
		return dao.getPayResult(merchant_uid);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveStoreInfo(StoreOrderInfo storeInfo) {
		storeInfo.setProductNamesArr(storeInfo.getProductNames().split("/"));
		storeInfo.setProductPricesArr(storeInfo.getProductPrices().split("/"));
		storeInfo.setProductNosArr(storeInfo.getProductNos().split("/"));;
		storeInfo.setProductQuantitiesArr(storeInfo.getProductQuantities().split(" ,"));
		int result = dao.saveStoreInfo(storeInfo); 
		if(result>0) {
			
			int count = storeInfo.getProductNamesArr().length;
			for(int i = 0 ; i < count ; i++) {
				storeInfo.setPna(storeInfo.getProductNamesArr()[i]);
				storeInfo.setPqu(storeInfo.getProductQuantitiesArr()[i]);
				storeInfo.setPno(storeInfo.getProductNosArr()[i]);
				storeInfo.setPpr(storeInfo.getProductPricesArr()[i]);
				result=dao.saveStoreDetail(storeInfo);
				result=dao.saveStoreOption(storeInfo);
				result=dao.saveStorepayKey(storeInfo);
			}
			if(Integer.parseInt(storeInfo.getUsedCouponNo())>0) {
				result = dao.deleteCoupon(storeInfo);
			}
			result = dao.deletecart(storeInfo);
		}
		
		return result;
	}

	@Override
	public List<Coupon> callCoupon(int memberNo) {
		return dao.callCoupon(memberNo);
	}

	@Override
	public int possibleCheck(int productNo) {
		return dao.possibleCheck(productNo);
	}
	



}
