package edu.kh.jvj.payment.scheduling;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.kh.jvj.admin.model.service.AdminService;
import edu.kh.jvj.payment.model.Service.PaymentService;
import edu.kh.jvj.payment.model.vo.RegualrPayInfo;

@Component
public class RegularPaymentScheduling {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaymentService service;
	
	//임의 수정 금지
	@Scheduled(fixedDelay = 60000)
	public void regularPayment() {
		logger.info("[scheduling] 실행!");
		List<RegualrPayInfo> toPay = service.getRegularPayList();
		logger.info("정기결제리스트{}",toPay );
		
		for(RegualrPayInfo onePay: toPay) {
			if(payProcess(onePay)){
				logger.debug("정기결제 성공");
			}
			else {
				logger.debug("정기결제 실패");
			}
		}
	}
	 
	 private boolean payProcess(RegualrPayInfo onePay) {
		 try {
				URL addr =new URL("https://kapi.kakao.com/v1/payment/subscription");
				
				String encodeProductName = URLEncoder.encode(onePay.getProductOption(), "UTF-8");
				String encodeUserId = URLEncoder.encode(onePay.getUserId(), "UTF-8");

				HttpURLConnection kapayconn= (HttpURLConnection)addr.openConnection();
				//요청하는 클라이언트 <-> 카카오페이 연결해주는 줄
				kapayconn.setRequestProperty("Authorization", "KakaoAK 87ca80fc70aa46c3b2afb02867943095");
				kapayconn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				kapayconn.setDoOutput(true);
				//이 연결을 통해서 서버에 전해줄것이 있는지 여부
				String parameter = "cid=TCSUBSCRIP" // 가맹점 코드
						+ "&sid="+onePay.getSid() // 가맹점 주문번호
						+ "&partner_order_id="+onePay.getPartner_order_id() // 가맹점 주문번호
						+ "&partner_user_id="+encodeUserId// 가맹점 회원 id
						+ "&item_name="+encodeProductName // 상품명
						+ "&quantity="+onePay.getAmount() // 상품 수량
						+ "&total_amount="+onePay.getTotalPrice()// 총 금액
						+ "&vat_amount=0" // 부가세
						+ "&tax_free_amount=0" // 상품 비과세 금액
						+ "&approval_url="+"http://kh-aclass.xyz:8080/jvj/"+"/payment/payresult?result="+1111 // 결제 성공 시
						+ "&fail_url="+"http://kh-aclass.xyz:8080/jvj/"+"/fail/asdsadsad" // 결제 실패 시
						+ "&cancel_url="+"http://kh-aclass.xyz:8080/jvj/"+"/subscribe/subBread";
				OutputStream output = kapayconn.getOutputStream();
				//전달해주는 객체 생성
				DataOutputStream daoutput = new DataOutputStream(output);
				//데이터 주는애
				
				daoutput.writeBytes(parameter);
				//데이터 넣어줌
				
				//가지고있는걸 전달하고 데이터 포기
				daoutput.close();
				//close 하면 flush 하고 끝내기때문
				
				int result = kapayconn.getResponseCode();
				//통신 결과 반환받음
				InputStream instream;
				//결과 200=성공
				if(result == 200) {
					instream= kapayconn.getInputStream();
				}else {
					instream= kapayconn.getErrorStream();
				}
				//받아온 결과를 읽어야 한다
				InputStreamReader reader = new InputStreamReader(instream);
				//읽는 스트림 선언
				
				BufferedReader bufferR = new BufferedReader(reader);
				String resultJSON = bufferR.readLine();
				System.out.println(resultJSON);
				return true;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		 	return false;
	 }
}
