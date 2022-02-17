package edu.kh.jvj.subscribe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import edu.kh.jvj.review.model.service.ReviewService;
import edu.kh.jvj.review.model.vo.Review;
import edu.kh.jvj.review.model.vo.RvSearch;
import edu.kh.jvj.store.model.vo.Pagination;
import edu.kh.jvj.subscribe.model.service.SubService;
import edu.kh.jvj.subscribe.model.vo.ProductImage;
import edu.kh.jvj.subscribe.model.vo.SearchVO;
import edu.kh.jvj.subscribe.model.vo.SubVO;


@Controller
@SessionAttributes({"loginMember"}) 
@RequestMapping("/subscribe/*")
public class SubController {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SubService service;
	@Autowired
	private ReviewService rService;
	// 구독 메인 
	@GetMapping("subMain")
	public String subMain() {
		return "/subscribe/subMain";
	}

	
	// 빵 상세 조회
	@GetMapping("subBread")
	public String subBread(SubVO subVO, Model model,@RequestParam(value="sr",required=false,defaultValue ="0")int sr ,
			@RequestParam(value="cp",required=false,defaultValue ="1")int cp) {
		long startMs = System.currentTimeMillis(); // 서비스 시작 시의 ms 값
		Map<String , Integer> map = new HashMap<>();
		
		map.put("productNo", 1437);
		int no = 1437;
		List<SubVO> subVOList = service.selectSubBread(map);
		List<ProductImage> subVOImgList = service.selectProductImageList(map);
		
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
		////////////////////////////////////////////////////////////////////
		
		
		long endMs = System.currentTimeMillis(); // 서비스 종료 시의 ms 값
		long takeTime = (endMs - startMs);
		
		
		if(subVOList != null) {
			model.addAttribute("subVOList", subVOList);
			model.addAttribute("subVOImgList", subVOImgList);

			return "subscribe/subBread";
		} else {
			
		}
		
		log.info("빵 상세 조회 로딩 소요시간 : {} ms", takeTime);
		
		return "subscribe/subBread";
	}
	
	// 커피 상세 조회
	@GetMapping("subCoffee")
	public String subCoffee(SubVO subVO, Model model,@RequestParam(value="sr",required=false,defaultValue ="0")int sr ,
			@RequestParam(value="cp",required=false,defaultValue ="1")int cp) {
		long startMs = System.currentTimeMillis(); // 서비스 시작 시의 ms 값
		Map<String , Integer> map = new HashMap<>();
		
		map.put("productNo", 1438);
		int no = 1438;
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
		////////////////////////////////////////////////////////////////////
		List<SubVO> subVOList = service.selectSubBread(map);
		List<ProductImage> subVOImgList = service.selectProductImageList(map);
		
		long endMs = System.currentTimeMillis(); // 서비스 종료 시의 ms 값
		long takeTime = (endMs - startMs);
		
		
		if(subVOList != null) {
			model.addAttribute("subVOList", subVOList);
			model.addAttribute("subVOImgList", subVOImgList);
		} else {
			
		}
		
		log.info("빵 상세 조회 로딩 소요시간 : {} ms", takeTime);
		
		return "subscribe/subCoffee";
	}
	
	// 좋아요
	@PostMapping("likeSub")
	@ResponseBody
	public int likeclass(int loginMember, int productNo) {

		Map<String, Integer> map = new HashMap<>();
		map.put("loginMember", loginMember);
		map.put("productNo", productNo);

		int result = service.likeSub(map);

		return result;
	}
	
	@PostMapping("undolike")
	@ResponseBody
	public int undolike(int loginMember, int productNo) {

		Map<String, Integer> map = new HashMap<>();
		map.put("loginMember", loginMember);
		map.put("productNo", productNo);

		int result = service.undolike(map);
		return result;
	}

	@PostMapping("likecheck")
	@ResponseBody
	public int likecheck(int loginMember, int productNo) {
		System.out.println(loginMember);
		System.out.println(productNo);
		Map<String, Integer> map = new HashMap<>();
		map.put("loginMember", loginMember);
		map.put("productNo", productNo);
		
		int result = service.likecheck(map);
		System.out.println("why?" + result);
		return result;
	}
	
	
	

	// 검색창
	@ResponseBody
	@RequestMapping(value = "main/search", method = RequestMethod.GET)
	public String search(SearchVO search) {
		List<SearchVO> list = service.search(search);
		
		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		
		
		return jsonList;
	}
	
	

}
