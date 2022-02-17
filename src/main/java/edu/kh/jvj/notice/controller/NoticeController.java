package edu.kh.jvj.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.jvj.admin.model.vo.MadeCoupon;
import edu.kh.jvj.notice.model.service.NoticeService;
import edu.kh.jvj.notice.model.vo.Notice;
import edu.kh.jvj.store.model.vo.Pagination;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping("list")
	public String noticeListShow(@RequestParam(value="cp", defaultValue="1" )String cp, Model model,
			@RequestParam(value="cate", defaultValue="0" ) String cate
			) {
		Map<String, String> map = new HashMap<>();
		map.put("cp", cp);
		map.put("cate", cate);
		map.put("search", "");
		Pagination page = service.countNotice(map);
		page.setLimit(10);
		page.setPageSize(10);
		List<Notice> noticeList = service.selectNoticeList(page,map);
		
		model.addAttribute("noticeList",noticeList);
		model.addAttribute("pagination",page);
		model.addAttribute("cate",cate);
		model.addAttribute("cp",cp);
		
		return "/notice/noticeList";
	}
	@GetMapping("view")
	public String noticeDetail(@RequestParam(value="cp", defaultValue="1" )int cp, Model model,
			@RequestParam(value="cate", defaultValue="0" ) int cate, int noticeNo
			) {
		
		Notice notice= service.selectOneNotice(noticeNo);
		if(notice!=null &&notice.getNoticeCd()==1) {
			List<MadeCoupon> coupons = service.selectCoupons(noticeNo);
			model.addAttribute("coupons",coupons);
			System.out.println(coupons);
		}
		model.addAttribute("notice",notice);
		model.addAttribute("cate",cate);
		model.addAttribute("noticeNo",noticeNo);
		model.addAttribute("cp",cp);
		return "/notice/noticeDetail";
	}
	@PostMapping("giveCoupon")
	@ResponseBody
	public int giveCoupon(int madeCouponNo, int memberNo, Model model) {
		//정보조회
		int result = 0;
		MadeCoupon madeCoupon= service.getMadeCoupon(madeCouponNo); 
		if(madeCoupon!=null) {
			madeCoupon.setAdminNo(memberNo);
			madeCoupon.setCouponNo(madeCouponNo);
			result = service.insertCouponToMember(madeCoupon);
		}
		else {
			result = -3;
		}
		return result;
	}
	
}
