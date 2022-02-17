package edu.kh.jvj.member.model.service;

import java.util.Map;

import edu.kh.jvj.member.model.vo.Member;

public interface MemberService {

	/** 일반 회원가입
	 * @param member
	 * @return 
	 * @throws Exception
	 */
	int signUp(Member member);

	
	/** 인증번호 생성기
	 * @param inputEmail
	 * @return
	 */
	String authCodeMaker(String inputEmail);


	/** 로그인
	 * @param member
	 * @return loginMember
	 */
	Member login(Member member);


	/** 이메일 조회
	 * @param map
	 * @return
	 */
	String searchId(Map<String, String> map);

	
	/** 비밀번호 변경 조회
	 * @param member
	 * @return
	 */
	int pwCheck(Member member);


	/** 이메일 중복 검사
	 * @param inputEmail
	 * @return
	 */
	int emailDupCheck(String memberEmail);


	/** 비밀번호 찾기 - 비밀번호 변경
	 * @param map
	 * @return result
	 */
	int searchPwResult(Map<String, String> map);


	/** 로그인 api 회원 정보 조회
	 * @param snsUser
	 * @return member
	 */
	Member getSnsUser(Member snsUser);


	

	

	
	
	
	
	
}
