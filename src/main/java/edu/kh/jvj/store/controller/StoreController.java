package edu.kh.jvj.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.review.model.service.ReviewService;
import edu.kh.jvj.review.model.vo.Review;
import edu.kh.jvj.review.model.vo.RvSearch;
import edu.kh.jvj.store.model.service.StoreService;
import edu.kh.jvj.store.model.vo.Pagination;
import edu.kh.jvj.store.model.vo.Search;
import edu.kh.jvj.store.model.vo.Store;



@Controller
@SessionAttributes({"loginMember"}) 
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private StoreService service;
	@Autowired
	private ReviewService rService;
	
	
	@GetMapping("")
	public String storeForward(@RequestParam(value="cp",required=false,defaultValue ="1")
	int cp, Model model,Search search,HttpSession session ,Member member){
		if(session.getAttribute("loginMember")!=null) {
			
			member = (Member)session.getAttribute("loginMember");
			search.setMemberNo(member.getMemberNo());

		}
		Pagination pagination = service.getPagination(cp,search);
		List<Store> storeList = service.selectStoreList(pagination,search);
		
		List<Store> rankProduct = service.selectRankProduct();
		if(!rankProduct.isEmpty()) {
			
			for(Store s : rankProduct) {
				int best = s.getStoreNo();
				model.addAttribute("best",best);
			}
		}
		
		
		model.addAttribute("store",storeList);
		model.addAttribute("pagination",pagination);
		model.addAttribute("search",search);
		return "store/storeEx";
	}
	// 좋아요
	@PostMapping("likeit")
	@ResponseBody
	public int likeit(int productNo,HttpSession session, Member member,Store store) {
		

		member =(Member)session.getAttribute("loginMember");
		store.setStoreNo(productNo);
		store.setMemberNo(member.getMemberNo());
		int result =  service.likeit(store);
		
		return result;
	}
	// 싫어요
	@PostMapping("doesntLikeit")
	@ResponseBody
	public int doesntLikeit(int productNo,HttpSession session, Member member,Store store) {
		
	
		member =(Member)session.getAttribute("loginMember");
		store.setStoreNo(productNo);
		store.setMemberNo(member.getMemberNo());
		int result =  service.doesntLikeit(store);
		
		return result;
	}	
	@GetMapping("info/{no}")
	public String detailForward(Model model,@PathVariable("no") int no,HttpSession session,Store store,@RequestParam(value="sr",required=false,defaultValue ="0")int sr ,
			@RequestParam(value="cp",required=false,defaultValue ="1")int cp) {
		
		// 스토어 상세조회
		if(session.getAttribute("loginMember") != null) {
			int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
			store.setMemberNo(memberNo);
			
		}
		store.setStoreNo(no);
		Store store1 = service.selectStoreDetail(store); 
		// 스토어 상품 이미지 가져오기
		List<Store> imgLevelList = service.storeImgSelect(no);
		

		
		
		// 추가옵션 상품 가져오기
		List<Store> advantage = service.advantage();

///////////////////////////////////////////////////////////////////////		
		
		// 리뷰 가져오기 ( no 에 상품 번호 넣기 , 클래스 파라미터에 RvSearch 가져오기)
		Pagination pagination = rService.getPagination(cp,no);
		RvSearch search = new RvSearch();
		search.setCp(cp);
		search.setNo(no);
		search.setSr(sr);
		List<Review> reviewList = rService.selectReviewList(pagination,search);
		if(!reviewList.isEmpty()) {
			model.addAttribute("reviewList",reviewList);
			model.addAttribute("pagination",pagination);
			model.addAttribute("search",search);
		}
		
///////////////////////////////////////////////////////////////////////	
		model.addAttribute("store",store1);
		model.addAttribute("advantage",advantage);
		model.addAttribute("imgLevel", imgLevelList);
		return "store/storeDetail";
	}
	
	
	// 스토어 재고확인 ( 만들다맘 )
	@PostMapping("selectAmount")
	@ResponseBody
	public int selectAmount(@ModelAttribute("loginMember") Member member, int storeNo,Store store) {
		int  result = 0;
		int memberNo =member.getMemberNo();
		
		store.setMemberNo(memberNo);
		store.setStoreNo(storeNo);
		int pdtCount = service.selectpdtCount(store);
		if( pdtCount>0) {
			store.setStock(storeNo);
			result = service.selectAmount(store);
		}else if(pdtCount == 0){
			result = 1;
		}
		
		
		System.out.println(result);
		
			return result;
	}
}