package edu.kh.jvj.member.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.jvj.member.model.dao.MemberDAO;
import edu.kh.jvj.member.model.vo.MailHandler;
import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.member.model.vo.TempKey;


@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO dao;
	
//	@Autowired
//	private JavaMailSender mailSender;	
//	
	@Autowired 
	private BCryptPasswordEncoder encoder;

	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	
	// 회원가입 
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int signUp(Member member) {
		
		// 일반 회원가입
		if( (member.getService()).equals("") ) {
			String encPw = encoder.encode(member.getMemberPw());
			member.setMemberPw(encPw);
		} 
		return dao.signUp(member);
	}

	// 인증번호
	@Override
	public String authCodeMaker(String inputEmail) {
		return null;
	}

	//이메일 찾기
	@Override
	public String searchId(Map<String, String> map) {
		return dao.searchId(map);
	}
		
	// 비밀번호 찾기
	@Override
	public int pwCheck(Member member) {
		return dao.pwCheck(member);
	}
	
	

	// 로그인
	@Override
	public Member login(Member member) {
		
		String encPw = encoder.encode(member.getMemberPw());
		
		Member loginMember = dao.login(member.getMemberEmail());
		
		if(loginMember != null && encoder.matches(member.getMemberPw(), loginMember.getMemberPw())) {
			loginMember.setMemberPw(null);
		} else {
			loginMember = null;
		}
		return loginMember;
	}
	
	// 이메일 중복 검사
	@Override
	public int emailDupCheck(String memberEmail) {
		return dao.emailDupCheck(memberEmail);
	}
	
	// 비밀번호 찾기 - 비밀번호 변경
	@Override
	public int searchPwResult(Map<String, String> map) {
		String encPw = encoder.encode(map.get("newPw1"));
		map.put("newPw1", encPw);
		
		return dao.searchPwResult(map);
	}

	
	// 로그인 api 회원 정보 조회
	@Override
	public Member getSnsUser(Member snsUser) {
		return dao.getSnsUser(snsUser);
	}

	
	


}
