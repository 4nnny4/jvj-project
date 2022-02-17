package edu.kh.jvj.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.jvj.common.Util;
import edu.kh.jvj.member.model.service.MailService;
import edu.kh.jvj.member.model.service.MemberService;
import edu.kh.jvj.member.model.service.SnsLoginService;
import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.member.model.vo.SnsToken;
import edu.kh.jvj.member.model.vo.SnsValue;

@Controller
@RequestMapping("/member/*")
@SessionAttributes({"loginMember","token"}) 
public class MemberController {

	@Autowired
	private MemberService service;

	@Autowired
	private MailService mailService;

	@Autowired 
	JavaMailSender mailSender;

	@Autowired
	private SnsValue naverSns;

	@Autowired
	private SnsValue kakaoSns;

	private Logger log = LoggerFactory.getLogger(getClass());


	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {

		long startMs = System.currentTimeMillis(); // 서비스 시작 시의 ms 값

		SnsLoginService naverLogin = new SnsLoginService(naverSns);
		model.addAttribute("naver_url", naverLogin.getSnsAuthURL());

		SnsLoginService kakaoLogin = new SnsLoginService(kakaoSns);
		model.addAttribute("kakao_url", kakaoLogin.getSnsAuthURL());

		long endMs = System.currentTimeMillis(); // 서비스 종료 시의 ms 값

		long takeTime = (endMs - startMs);

		log.info("로그인 주소 소요시간 : {} ms", takeTime);

		return "member/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Member member, @RequestParam(value="save", required = false) String save,
			Model model, HttpServletRequest req, HttpServletResponse resp) {

		long startMs = System.currentTimeMillis(); // 서비스 시작 시의 ms 값

		Member loginMember = service.login(member);
		String path = "";

		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());

			if(save != null) {
				cookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				cookie.setMaxAge(0);
			}

			cookie.setPath(req.getContextPath());

			resp.addCookie(cookie);

			path = "redirect:/";
		} else {
			path = "member/login";
			Util.swalSetMessage("로그인 실패", "아이디 또는 비밀번호를 확인해주세요.", "error", model);
		}

		long endMs = System.currentTimeMillis(); // 서비스 종료 시의 ms 값
		long takeTime = (endMs - startMs);

		log.info("로그인 소요시간 : {} ms", takeTime);

		return path;
	}

	@RequestMapping(value="/{service}/callback", method = {RequestMethod.GET, RequestMethod.POST})
	public String snsLoginCallback(@PathVariable("service") String snsService, Model model, @RequestParam String code, HttpSession session) throws Exception{
		
		long startMs = System.currentTimeMillis(); // 서비스 시작 시의 ms 값

		SnsValue sns = null;

		if(StringUtils.equals("naver", snsService)) {
			sns = naverSns;
		} else if(StringUtils.equals("kakao", snsService)) {
			sns = kakaoSns;
		}

		// 1. code를 이용해서 access_token 받기
		// 2. access_token 이용해서 사용자 profile 정보 가져오기
		SnsLoginService snsLogin = new SnsLoginService(sns);
		Member snsUser = null;

		if(StringUtils.equals("naver", snsService)) {
			snsUser = snsLogin.getNaverUserProfile(code, snsService);
		} else if(StringUtils.equals("kakao", snsService)) {
			SnsToken snsToken = snsLogin.getKakaoToken(code);
			
			if(snsToken != null) {
				snsUser = snsLogin.getKakaoProfile(snsToken, snsService);
				model.addAttribute("token", snsToken.getAccess_Token());
			}
		}

		System.out.println(snsUser);

		// 3. DB에 해당 유저가 존재하는지 체크
		Member member = service.getSnsUser(snsUser);
		String path = "";
		
		

		if(member == null) { // 회원이 없는 경우
			model.addAttribute("snsUser", snsUser);
			path = "member/signUp";
		} else { // 회원이 있는 경우
			// 4. 존재 시 강제 로그인, 미 존재시 가입페이지 !!
			model.addAttribute("loginMember", member);
			path =  "redirect:/";
		}

		long endMs = System.currentTimeMillis(); // 서비스 종료 시의 ms 값
		long takeTime = (endMs - startMs);

		log.info("sns callback 로그인 or 회원가입 소요시간 : {} ms", takeTime);
		
		return path;
	}
	
	// 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(SessionStatus status) {
		status.setComplete();

		return "redirect:/";
	}



	@RequestMapping(value = "signUp", method = RequestMethod.GET)
	public String signUp() {
		return "member/signUp";
	}

	// 회원 가입
	@RequestMapping(value="signUp", method = RequestMethod.POST)
	public String signUp(Member member, Model model, RedirectAttributes ra) {
		int result = service.signUp(member);

		String path = "";


		if(result > 0) { // 성공(redirect)
			model.addAttribute("loginMember", member);
			Util.swalSetMessage("회원 가입 성공!",  member.getMemberName() + "님! 회원가입 완료.", "success", ra);
			path = "redirect:/";

		} else { // 실패(forward)
			Util.swalSetMessage("회원 가입 실패!",  "관리자에 문의해주세요.", "error", model);
			path = "member/login";
		}

		return path;
	}

	// 이메일 중복 검사(ajax)
	@RequestMapping(value="emailDupCheck", method = RequestMethod.POST)
	@ResponseBody
	public int emailDupCheck(String memberEmail) {   
		
		long startMs = System.currentTimeMillis(); 
		
		int result = service.emailDupCheck(memberEmail);

		long endMs = System.currentTimeMillis(); // 서비스 종료 시의 ms 값
		long takeTime = (endMs - startMs);
		
		log.info("이메일 중복검사 소요시간 : {} ms", takeTime);
		
		return result;
		
		
	}


	// 이메일 찾기 화면 전환
	@RequestMapping(value = "searchId", method = RequestMethod.GET)
	public String searchId() {
		return "member/searchId";
	}

	// 이메일 찾기
	@RequestMapping(value = "searchId", method = RequestMethod.POST)
	public String searchId(String memberName, String[] phone, Model model) {

		Map<String, String> map = new HashMap<String, String>();
		String memberPhone = "";

		for(int i = 0; i < phone.length; i++) {
			if(i == 2) {
				memberPhone += phone[i];
			} else {
				memberPhone += phone[i] + "-";
			}
		}

		map.put("memberName", memberName);
		map.put("memberPhone", memberPhone);

		String memberEmail = service.searchId(map);

		String path = null;

		// 동일 아이디 조회
		if(memberEmail != null) { // 조회 시 값이 있다면
			model.addAttribute("memberEmail", memberEmail);
			path =  "member/searchIdResult";

		} else {
			Util.swalSetMessage("존재하지 않는 회원입니다", null, "error", model);
			path =  "member/searchId";
		}
		return  path;
	}

	// 비밀번호 찾기
	@RequestMapping(value = "searchPw", method = RequestMethod.GET)
	public String searchPw() {
		return "member/searchPw";
	}


	@RequestMapping(value = "searchPw", method = RequestMethod.POST)
	public String searchPw(String memberEmail,  Model model) {
		// 쿠키 설정 카운트는 쿠키에 설정해서 카운트다운

		model.addAttribute("memberEmail", memberEmail);
		return "member/searchPwResult";
	}


	// 이메일과 변경할 비밀번호
	@RequestMapping(value = "searchPwResult", method = RequestMethod.POST)
	public String searchPwResult(
			String memberEmail, String newPw1, Model model) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("memberEmail", memberEmail);
		map.put("newPw1", newPw1);


		// 비밀번호 업데이트
		int result = service.searchPwResult(map);

		if(result > 0) {
			Util.swalSetMessage("비밀번호가 변경되었습니다.", null, "success", model);
		} else {
			Util.swalSetMessage("비밀번호가 변경되지 않았습니다.", null, "error", model);
		}

		return "member/login";
	}




	// 카운트 다운
	@ResponseBody
	@RequestMapping("emailCountdown")
	private int emailCountdown(HttpServletRequest request, String memberEmail) {

		return 0;
	}

	// 인증번호 전송
	@ResponseBody
	@RequestMapping("email")
	private int sendEmail(HttpServletRequest request, String memberEmail) {
		HttpSession session = request.getSession();
		mailService.mailSend(session, memberEmail);

		return 0;
	}

	// 인증번호 확인
	@ResponseBody
	@RequestMapping("certification")
	private boolean emailCertification(HttpServletRequest request, String memberEmail, int inputCode) {

		HttpSession session = request.getSession();
		boolean result = mailService.emailCertification(session, memberEmail, inputCode);
		return result;

	}






}