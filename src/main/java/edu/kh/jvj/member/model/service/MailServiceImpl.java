package edu.kh.jvj.member.model.service;

import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import edu.kh.jvj.member.model.vo.MailHandler;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	// 인증번호 전송
	@Override
	public void mailSend(HttpSession session, String memberEmail) {
		
		try{
			MailHandler mailHandler = new MailHandler(javaMailSenderImpl);
			Random random = new Random(System.currentTimeMillis());
			long start = System.currentTimeMillis();
			
			int result = 100000 + random.nextInt(900000);
			
			
			mailHandler.setTo(memberEmail);
			mailHandler.setFrom("bongguking08@gmail.com");
			mailHandler.setSubject("JVJ 인증번호입니다.");
			String htmlContent = "<table align='center' style='border: 1px solid rgb(187, 192, 196);' border='0' cellspacing='0' cellpadding='0' width='700'>" 
									+ "<tbody><tr><td style='padding: 24px 14px 0px;'>" 
									
									 + "<table border='0' cellspacing='0' cellpadding='0' width='670'>" +
									 "<tbody><!-- 로고 -->" +
									 "<tr><td style='text-align: left;'  style='padding: 10px 34px;  line-height: 18px; font-family: NanumBarunGothic; font-size: 12px; '>"
									 + "</td></tr><!-- //로고 --><!-- 상단메인배너 -->"
					 
		                               
		                            
	                            + "<tr><td style='padding: 0px 0px 0px 10px; color: rgb(57, 57, 57); line-height: 19px; font-size: 13px;'>"
	                            + "<p>안녕하세요. <strong>JeanValJean</strong> 입니다.</p>"
					
	                            + "</td></tr><!-- //인사말 -->"
	                            
	                            
	                            + "<tr><td><!-- 컨텐츠 -->"
	                                + "<table style='color: rgb(57, 57, 57); line-height: 19px; font-family: NanumBarunGothic; font-size: 12px;' border='0' cellspacing='0' cellpadding='0' width='670'>"
	                                + 	"<tbody><tr><td style='padding: 23px 0px 0px;'>"
	                                + 		"<table style='line-height: 15px; font-family: NanumBarunGothic; font-size: 12px; border-top-color: rgb(213, 213, 213); border-top-width: 1px; border-top-style: solid;' border='0' cellspacing='0' cellpadding='0' width='100%'>"
	                                +			"<tr><th colspan='1' rowspan='1' scope='col' style='padding: 13px 10px 10px; color: rgb(128, 135, 141); font-family: NanumBarunGothic; font-size: 15px; font-weight: normal; border-right-color: rgb(213, 213, 213); border-bottom-color: rgb(213, 213, 213); border-left-color: rgb(213, 213, 213); border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; background-color: rgb(245, 246, 245);' width='30%'>인증번호</th>"
	                                +				"<td align='center' valign='middle' style='padding: 13px 10px 10px; color: rgb(57, 57, 57); border-right-color: rgb(213, 213, 213); border-bottom-color: rgb(213, 213, 213); border-left-color: rgb(213, 213, 213); border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; font-weight= bold;'>" + result + "</td>"
	                                + "</tr></table></td></tr>"
	                                + "<td height='10'>&nbsp;</td></tr><!-- 컨텐츠 공통 여백 --><!-- 주문 상품 정보 --><tr><td>"
	                               
	                                + "</table><!-- //컨텐츠 --></td></tr><!-- 맺음말 -->"
	                                + "<tr><td style='padding: 30px 0px 60px 10px; color: rgb(57, 57, 57); line-height: 19px; font-family: NanumBarunGothic; font-size: 12px;'>"
	                                + "<p>항상 고객님께서 만족하실 수 있도록 정성껏 모시겠습니다.</p>"
	                                + "<p style='margin-top: 13px;'>저희 쇼핑몰을 이용해주셔서 진심으로 감사드립니다.</p>"
	                                + " </td></tr><!-- //맺음말 --></tbody>"
	                                + "</table></td></tr><!-- 회사소개 -->"
	                                
	                                + "<tr><td style='padding: 24px 34px; color: rgb(255, 255, 255); line-height: 18px; font-family: NanumBarunGothic; font-size: 12px; background-color: rgb(202, 205, 212);'>"
	                                + "<p>Tel : <strong>2022-0216</strong> | Fax : <br>"
	                                + "55075 서울시 종로구 완산구 호동길 24 (삼천동3가) JeanValJean<br>"
	                                + "대표이사 : 구독좋아요알림설정 | 개인정보관리책임자 : 구독 | 사업자 등록번호 [111-11-11111] | 통신판매업 신고 : 2022-서울-0303 </p>"
	                                + "<p>Copyright(c) 구독좋아요알림설정 all rights reserved.</p>"
	                                + "</td></tr><!-- //회사소개 --></tbody>"
	                                + "</table>";
	                                
			
			mailHandler.setText(htmlContent, true);
			
			mailHandler.send();
			
			long end = System.currentTimeMillis();
			
			session.setAttribute(""+memberEmail, result);
			System.out.println( "실행시간 : "+ ( end - start)/1000.0 );
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	//인증번호 확인
	@Override
	public boolean emailCertification(HttpSession session, String memberEmail, int inputCode) {
		try {
			int generationCode = (int) session.getAttribute(memberEmail);
			
			if( generationCode == inputCode) 	return true;
			else								return false;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	
	
	
	

}
