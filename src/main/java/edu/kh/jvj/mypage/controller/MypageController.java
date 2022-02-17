package edu.kh.jvj.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.jvj.common.Util;
import edu.kh.jvj.member.model.service.SnsLoginService;
import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.member.model.vo.SnsToken;
import edu.kh.jvj.member.model.vo.SnsValue;
import edu.kh.jvj.mypage.model.service.MypageService;
import edu.kh.jvj.mypage.model.vo.Coupon;
import edu.kh.jvj.mypage.model.vo.CouponStatus;
import edu.kh.jvj.mypage.model.vo.Like;
import edu.kh.jvj.mypage.model.vo.Pagination;
import edu.kh.jvj.mypage.model.vo.Order;

@Controller
@RequestMapping("mypage/*")
@SessionAttributes({"loginMember", "token"})
public class MypageController {
	
	@Autowired
	private MypageService service;
	


	@Autowired
	private SnsValue kakaoSns;
	


	// 메인 페이지 
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String mypageMain(@ModelAttribute("loginMember") Member loginMember, Order order, Model model, Member member) {	
		
		System.out.println(loginMember);
		
		order.setMemberNo(loginMember.getMemberNo());
		order.setMemberName(loginMember.getMemberName());
		order.setService(loginMember.getService());
		order.setEnrollDate(loginMember.getEnrollDate());
		
		List<Order> mainList = service.mypageMain(order);
		
		
		int memberDate = service.memberDate(loginMember);
		
		model.addAttribute("mainList", mainList);
		model.addAttribute("memberDate", memberDate);
		
		return "mypages/mypageMain";
	}
	
	// 일반 상품 결제 페이지 이동
		@RequestMapping(value = "purchase", method = RequestMethod.GET)
		public String mypageShoppingList(@ModelAttribute("loginMember") Member loginMember, Order order, Model model,
										@RequestParam(value="cp", required=false, defaultValue="1") int cp) {
					
			order.setMemberNo(loginMember.getMemberNo());
			order.setService(loginMember.getService());
			
			
			Pagination pagination = service.purPagination(cp, order);
						
			List<Order> purchase = service.purList(order,pagination);
						
			model.addAttribute("purchase", purchase);
			model.addAttribute("pagination", pagination);
						
						
			return "mypages/mypageShopping";
			}
	
	// 일반 결제 취소
	@ResponseBody
	@RequestMapping(value = "updatePur", method = RequestMethod.POST) 
	public int cancelPayment(Order order, @ModelAttribute("loginMember") Member loginMember, int productNo, RedirectAttributes ra) {
		
		order.setMemberNo(loginMember.getMemberNo());
		order.setPurchaseNo(productNo);
		
		return service.cancelPayment(order);
		
	}
	
	
	// 클래스 구매 페이지
	@RequestMapping(value = "class", method = RequestMethod.GET)
	public String mypageClass(@ModelAttribute("loginMember") Member loginMember, Order order, Model model,
							@RequestParam(value="cp", required=false, defaultValue="1") int cp ) {
		
		order.setMemberNo(loginMember.getMemberNo());
		order.setService(loginMember.getService());
		
		Pagination pagination = service.classPagination(cp, order);
		List<Order> classList = service.classList(pagination,order);
		
		model.addAttribute("classList", classList);
		
		model.addAttribute("pagination", pagination);
		
		return "mypages/mypageClass";
	}
	
	// 클래스 결제 취소
	@ResponseBody
	@RequestMapping(value = "cancleClass", method = RequestMethod.POST) 
	public int cancelOnedayClass(Order order, @ModelAttribute("loginMember") Member loginMember, int productNo, RedirectAttributes ra) {
			
		order.setMemberNo(loginMember.getMemberNo());
		order.setPurchaseNo(productNo);
			
		return service.cancelOnedayClass(order);
			
	}
	
	
	
	
	// 정기 구독 조회
	@RequestMapping(value = "sub", method = RequestMethod.GET)
	public String mypageSub(@ModelAttribute("loginMember") Member loginMember, Order order, Model model,
						 	@RequestParam(value="cp", required=false, defaultValue="1") int cp) {
		
		order.setMemberNo(loginMember.getMemberNo());
		order.setService(loginMember.getService());
		
		
		Pagination pagination = service.subPagination(cp, order);
		List<Order> subList = service.subscription(order, pagination);
		
		model.addAttribute("subList", subList);
		
		model.addAttribute("pagination", pagination);
		
		return "mypages/mypageSub";
	}
	
	// 클래스 결제 취소
		@ResponseBody
		@RequestMapping(value = "cancleSub", method = RequestMethod.POST) 
		public int cancelSubscription(Order order, @ModelAttribute("loginMember") Member loginMember, int productNo, RedirectAttributes ra) {
				
			order.setMemberNo(loginMember.getMemberNo());
			order.setPurchaseNo(productNo);
				
			return service.cancelSubscription(order);
				
		}
	
	
	
	// 마이페이지 쿠폰
	@RequestMapping(value = "coupon", method = RequestMethod.GET)
	public String mypageCoupon( Coupon coupon, 
			@RequestParam(value="cp", required=false, defaultValue="1") int cp, 
			Model model, @ModelAttribute("loginMember") Member loginMember
			) {
		
		coupon.setMemberNo(loginMember.getMemberNo());
		coupon.setService(loginMember.getService());
			
		Pagination pagination = service.couponPagination(cp,coupon);
		List<Coupon> couponList = service.couponList(pagination, coupon);
		
		
		
		List<CouponStatus> couponStatus = service.statusCategory();
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("couponList", couponList);
		model.addAttribute("couponStatus", couponStatus);
		
		return "mypages/mypageCoupon";
	}
	
	
	// 마이페이지 좋아요
	@RequestMapping(value = "love", method = RequestMethod.GET)
	public String mypageLove(@RequestParam(value="cp", required=false, defaultValue="1") int cp, 
			Model model, @ModelAttribute("loginMember") Member loginMember, 
			Like like) {
		
		like.setMemberNo(loginMember.getMemberNo());
		like.setService(loginMember.getService());
		
		Pagination pagination = service.getLikePagination(cp, like);
		
		List<Like> likeList = service.getLikeList(pagination, like);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("likeList", likeList);
		
		return "mypages/mypageLove";
	}
	
	// 마이페이지 좋아요 취소
	@ResponseBody
	@RequestMapping(value = "deletelike", method = RequestMethod.POST) 
	public int cancelLike(Like like, @ModelAttribute("loginMember") Member loginMember, int productNo, RedirectAttributes ra) {
		
		like.setMemberNo(loginMember.getMemberNo());
		like.setProductNo(productNo);
		
		return  service.cancleLike(like);
		
	}

	// 내 정보 조회 화면 전환
	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String mypageMove() {
		return "mypages/mypageInfo";
	}
	
	// 마이페이지 내 정보 조회
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String mypageInfo(@ModelAttribute("loginMember") Member loginMember, 
							@RequestParam Map<String, String> param, 
							Member member, RedirectAttributes ra, Model model
							) {
		member.setMemberNo(loginMember.getMemberNo());
		member.setService(loginMember.getService());
		member.setMemberNickname(param.get("memberNickname"));
		member.setMemberAddress(param.get("updateAddress"));
		member.setMemberPhone(param.get("updatePhone"));
		member.setMemberName(param.get("memberName"));
		
		int result = service.memberUpdate(member);
		
		
		if (result > 0) {
			Util.swalSetMessage("회원 정보 수정 성공", "회원정보가 변경되었습니다", "success", ra);
			 member.setMemberNickname(param.get("memberNickname"));
			 member.setMemberAddress(param.get("updateAddress"));
			 member.setMemberPhone(param.get("updatePhone"));
			 member.setMemberName(param.get("memberName"));
		} else {
			Util.swalSetMessage("회원 정보 수정 실패", "관리자에게 문의해주세요", "error", ra);
		}
		return "redirect:/mypage/info";
	}
	
	
	
	// 비밀번호 변경 페이지 이동
	@RequestMapping(value = "password", method = RequestMethod.GET)
	public String modifyPwPage() {
		
		return "mypages/mypageModifyPw";
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPw(@ModelAttribute("loginMember") Member loginMember, 
							Member member, RedirectAttributes ra,
							String memberPw, String modifyPw1) {
		
		 Map<String, String> map = new HashMap<String, String>(); 
		 map.put("memberNo",loginMember.getMemberNo() + ""); 
		 map.put("memberPw", memberPw);
		 map.put("modifyPw", modifyPw1);
		  
		 int pwUpdate = service.updatePw(map);
		 
		 if (pwUpdate > 0) {
				Util.swalSetMessage("회원 정보 수정 성공", "비밀번호가 변경되었습니다", "success", ra);
				return "redirect:info";
				
			} else {
				Util.swalSetMessage("회원 정보 수정 실패", "관리자에게 문의해주세요", "error", ra);
				return "redirect:main";
			}
	}

	
	// 일반 회원 탈퇴
	@RequestMapping(value = "secession", method = RequestMethod.GET)
	public String secession(@ModelAttribute("loginMember") Member loginMember, Model model,
			SessionStatus status, RedirectAttributes ra) {
		
		String path = null;
		int result = 0;
		int kaResult = 0;
		String token = "";
		
		try {
			if(loginMember.getService() == null) {
				result = service.secession(loginMember.getMemberNo());
			} else if(loginMember.getService().equals("kakao")) {
				token = (String)model.getAttribute("token");
				kaResult = service.getKakaoToken(token);
				
				if(kaResult > 0) { // 카카오 회원 상태 탈퇴로 변경 
					result = service.secession(loginMember.getMemberNo());
				}
				
			} else if(loginMember.getService().equals("naver")) {
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (result > 0) { // 성공
			Util.swalSetMessage("회원 탈퇴 성공", "탈퇴 되었습니다.", "success", ra);
			status.setComplete(); // 세션만료

			path = "/";

		} else { // 실패
			Util.swalSetMessage("회원 탈퇴 실패", "다시 시도해주시길 바랍니다.", "error", ra);
			path = "secession";
		}
		
		return "redirect:" + path;
	}
	
	
	
	
	
	
	/*
	@GetMapping("review")
	public String mypageReview() {
		return "mypages/mypageReview";
	}
	@GetMapping("recorrect")
	public String ReviewWrite() {
		return "mypages/reviewWrite";
	}
	*/
	
	
	
	public String menu(@ModelAttribute("loginMember") Member loginMember, Member member ,Model model) {
		member.setService(loginMember.getService());
		return "mypages/mypageMenu";
	}
	
}
