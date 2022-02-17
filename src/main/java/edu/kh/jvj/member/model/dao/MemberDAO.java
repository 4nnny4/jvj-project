package edu.kh.jvj.member.model.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.jvj.member.model.vo.Member;

@Repository
public class MemberDAO {
	
	@Autowired	
	private SqlSessionTemplate sqlSession; 
	
	
	
	/** 일반 회원가입
	 * @param member
	 * @return result
	 */
	public int signUp(Member member) {
		
		// 일반회원가입
		if(member.getService() == null) {
			return sqlSession.insert("memberMapper.signUp", member);
		} else { // sns회원가입
			return sqlSession.insert("memberMapper.snsSignUp", member);
		}
		
	}
	

	/** 로그인
	 * @param map
	 * @return loginMember
	 */
	public Member login(String memberEmail) {
		return sqlSession.selectOne("memberMapper.login", memberEmail);
	}


	/** 비밀번호 찾기 
	 * @param member
	 * @return
	 */
	public int pwCheck(Member member) {
		return sqlSession.selectOne("memberMapper.pwCheck", member);
	}


	/** 이메일 중복검사
	 * @param inputEmail
	 * @return
	 */
	public int emailDupCheck(String memberEmail) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.emailDupCheck", memberEmail);
	}


	/** 이메일 찾기
	 * @param map
	 * @return
	 */
	public String searchId(Map<String, String> map) {
		return sqlSession.selectOne("memberMapper.searchId", map);
	}


	/** 비밀번호찾기 - 비밀번호 변경
	 * @param map
	 * @return result
	 */
	public int searchPwResult(Map<String, String> map) {
		return sqlSession.update("memberMapper.searchPwResult", map);
	}


	/** 로그인 api 회원 정보 조회
	 * @param snsUser
	 * @return member
	 */
	public Member getSnsUser(Member snsUser) {
		return sqlSession.selectOne("memberMapper.selectSnsUser", snsUser);
	}


	


	


	
	
}
