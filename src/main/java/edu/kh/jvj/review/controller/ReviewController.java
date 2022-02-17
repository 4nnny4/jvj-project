package edu.kh.jvj.review.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.jvj.common.Util;
import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.review.model.service.ReviewService;
import edu.kh.jvj.review.model.vo.Review;
import edu.kh.jvj.store.model.vo.Pagination;

@Controller
@RequestMapping("board/review/*")
@SessionAttributes({"loginMember"})
public class ReviewController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ReviewService service;
	@GetMapping("write")
	public String WriteReview(@ModelAttribute("loginMember") Member member,Model model) {
		
		// 1.회원번호로 결제테이블을 불러온다.
		List<Review> orderList =  service.selectOrder(member.getMemberNo());	
		List<Review> realList =  new ArrayList<Review>();
		int tempNo[] = {} ;
		// 2. 이미지 불러오기
		Set<Integer> set = new HashSet<Integer>();
	for(Review review2 : orderList) {
		set.add(review2.getProductNo());
	}
		for(Review review : orderList) {
			
			//2.1 프로덕트 넘버로 리뷰썻는지 안썻는지 카운트
			Review rv  = new Review(); 
			rv.setMemberNo(member.getMemberNo());
			rv.setProductNo(review.getProductNo());
			int or = service.countOrder(rv);
			int cr = service.countReview(rv);
			review.setImgPath(service.selectImgPath(review.getProductNo()));	
			
		
			   
			if ( or<=cr) {
				continue;
			}else {
				
			if(set.contains(review.getProductNo())) {
				realList.add(review);
				set.remove(review.getProductNo());
			}
				
			}
		 
		}
		

		
		logger.info(orderList.toString());
		model.addAttribute("orderList",realList);
		
		
		return "review/reviewWrite";
	}
	
	@PostMapping("writeReview")
	public String writeReview(Review review,
			@ModelAttribute("loginMember") Member member, RedirectAttributes ra) {
		logger.info(review+"");
		review.setMemberNo(member.getMemberNo());
		int result = service.writeReview(review);
		if( result> 0) {
			
			Util.swalSetMessage("리뷰작성","리뷰 작성이 완료되었습니다.","success", ra);
		} else {
			
			Util.swalSetMessage("작성실패","관리자에게 문의 하세요","success", ra);
		}

		return "redirect:/board/review/write";
	}
}
