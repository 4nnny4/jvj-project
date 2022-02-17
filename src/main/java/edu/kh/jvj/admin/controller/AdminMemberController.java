package edu.kh.jvj.admin.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.jvj.admin.model.service.AdminService;
import edu.kh.jvj.admin.model.vo.Admin;

@Controller
@RequestMapping
@SessionAttributes({"loginAdmin"})
public class AdminMemberController {
	private final AdminService service;
	@Autowired
	public AdminMemberController(AdminService service) {
		this.service = service;
	}
	private Logger log = LoggerFactory.getLogger(getClass());
	//로그인 페이지
	@GetMapping("admin/login")
	public String AdmingLogin() {
		return "/admin/adminLogin";
	}
	//로그인 일치시 관리자페이지 메인 이동
	@PostMapping("admin/board/main")
	public String AdmingLoginProcess( 
			@ModelAttribute Admin admin, HttpServletRequest req, HttpServletResponse resp,
			RedirectAttributes ra, Model model
			) {
			String path = "";
			
			Admin checkedAdmin = service.findMatchAdmin(admin);
			
			if(checkedAdmin!=null) {
				checkedAdmin.setAdminId(admin.getAdminId());
				model.addAttribute("loginAdmin", checkedAdmin);
				path = "admin/adminMain";
				
				Cookie cookie = new Cookie("saveId" , admin.getAdminId());
				if(admin.getSaveId()!=null) {
					//아이디 저장 체크 시
					cookie.setMaxAge(60*60*24*30);
				}
				else {
					cookie.setMaxAge(0);
				}
				cookie.setPath(req.getContextPath()+"/admin");
				resp.addCookie(cookie);
			}
			else {
				ra.addFlashAttribute("message", "일치하는 정보가 없습니다!");
				path = "redirect:/admin/login";
			}
			/*
			 * if(result>0) { path = "admin/adminMain"; } else {
			 * 
			 * }
			 */
			/* path = "redirect:/admin/login"; */
		return path;
	}
	// router 새로고침 처리 
	@GetMapping("admin/board/route/*")
	public String refresh( @ModelAttribute(value="loginAdmin") Admin loginAdmin) {
			log.info("관리자페이지 새로고침");
			return "admin/adminMain";
	}
	
	@GetMapping(value="admin/board/adminlogout")
	public String adminlogout(SessionStatus status) {
		status.setComplete();
		
		return "admin/adminLogin";
	}
}
