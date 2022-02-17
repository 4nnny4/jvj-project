package edu.kh.jvj.onedayclass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.jvj.onedayclass.model.service.OnedayClassService;
import edu.kh.jvj.onedayclass.model.vo.OnedayClass;
import edu.kh.jvj.review.model.service.ReviewService;
import edu.kh.jvj.review.model.vo.Review;
import edu.kh.jvj.review.model.vo.RvSearch;
import edu.kh.jvj.store.model.vo.Pagination;

@Controller
@RequestMapping("onedayclass/*")
@SessionAttributes({ "loginNo" })
public class OnedayClassController {

	private Logger log = LoggerFactory.getLogger(getClass());

	private final OnedayClassService service;
	@Autowired
	private ReviewService rService;

	@Autowired
	public OnedayClassController(OnedayClassService service) {
		this.service = service;
	}

	@GetMapping("list")
	public String showOnedayClassList(Model model) {
		return "/onedayclass/onedayClassList";
	}

	@GetMapping("view/{productNo}")
	public String showOnedayClassDetail(@PathVariable int productNo, Model model,
			@RequestParam(value = "sr", required = false, defaultValue = "0") int sr,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		Map<String, Integer> map = new HashMap<>();

		map.put("productNo", productNo);
///////////////////////////////////////////////////////////////////////		

// 리뷰 가져오기 ( no 에 상품 번호 넣기 , 클래스 파라미터에 RvSearch 가져오기)
		Pagination pagination = rService.getPagination(cp, productNo);
		RvSearch search = new RvSearch();
		search.setCp(cp);
		search.setNo(productNo);
		search.setSr(sr);
		List<Review> reviewList = rService.selectReviewList(pagination, search);
		if (!reviewList.isEmpty()) {
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("pagination", pagination);
			model.addAttribute("search", search);
		}

///////////////////////////////////////////////////////////////////////	

		OnedayClass Oneclass = service.selectOneClass(map);
		if (Oneclass != null) {
			Oneclass.setProductNo(productNo);
			model.addAttribute("Oneclass", Oneclass);
			model.addAttribute("productNo", productNo);
		} else {
			model.addAttribute("message", "해당 클래스가 접근 불가 상태입니다.");
		}

		return "/onedayclass/onedayClassDetail";
	}

	// produces="application/json;charset=UTF-8"
	@PostMapping(value = "list")
	@ResponseBody
	public ResponseEntity<List<OnedayClass>> scrollListAdd(@RequestBody Map<String, String> pagination, Model model) {

		long startMs = System.currentTimeMillis(); // 서비스 시작 시의 ms 값

		List<OnedayClass> oneLineList = service.scrollListAdd(pagination);

		long endMs = System.currentTimeMillis(); // 서비스 종료 시의 ms 값
		
		long takeTime = (endMs - startMs);
		
		if (oneLineList.isEmpty()) {
			log.info("검색기준 : {}", pagination.get("getType"));
			log.info("결과 : {}", "결과 클래스가 없습니다");
			model.addAttribute("message", "진행 중 클래스가 없습니다");
		} else {
			log.info("검색기준 : {}", pagination.get("getType"));
			log.info("반환 ondDayClass 개수: {}", oneLineList.size());
		}
		log.info("원데이클래스 로딩 소요시간 : {} ms", takeTime);

		return new ResponseEntity<>(oneLineList, HttpStatus.OK);
	}

	@PostMapping("likeclass")
	@ResponseBody
	public int likeclass(int loginNo, int productNo) {

		Map<String, Integer> map = new HashMap<>();
		map.put("loginNo", loginNo);
		map.put("productNo", productNo);

		int result = service.likeclass(map);

		return result;
	}

	@PostMapping("undolike")
	@ResponseBody
	public int undolike(int loginNo, int productNo) {

		Map<String, Integer> map = new HashMap<>();
		map.put("loginNo", loginNo);
		map.put("productNo", productNo);

		int result = service.undolike(map);
		return result;
	}

	@PostMapping("likecheck")
	@ResponseBody
	public int likecheck(int loginNo, int productNo) {
		System.out.println(loginNo);
		System.out.println(productNo);
		Map<String, Integer> map = new HashMap<>();
		map.put("loginNo", loginNo);
		map.put("productNo", productNo);

		int result = service.likecheck(map);
		System.out.println("why?" + result);
		return result;
	}

	/*
	 * @PostMapping("getPlace")
	 * 
	 * @ResponseBody public String getPlace(String mapAddress) {
	 * System.out.println("mapAddress=" + mapAddress); try {
	 * 
	 * String urldata = "http://dapi.kakao.com/v2/local/search/address.json?query=";
	 * //인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다. String address =
	 * URLEncoder.encode(mapAddress, "UTF-8");
	 * 
	 * URL addr =new URL(urldata+address);
	 * 
	 * HttpURLConnection getaddress= (HttpURLConnection)addr.openConnection();
	 * 
	 * getaddress.setRequestProperty("X-Requested-With", "curl");
	 * getaddress.setRequestProperty("Authorization",
	 * "KakaoAK e96f24a02e71efe8bd0070389f452c0f");
	 * getaddress.setRequestProperty("Content-type",
	 * "application/json;charset=utf-8"); getaddress.setDoOutput(true);
	 * getaddress.setUseCaches(false); getaddress.setDefaultUseCaches(false);
	 * 
	 * int result = getaddress.getResponseCode(); //통신 결과 반환받음 InputStream instream;
	 * //결과 200=성공 if(result == 200) { instream= getaddress.getInputStream(); }else
	 * { instream= getaddress.getErrorStream(); } //받아온 결과를 읽어야 한다 InputStreamReader
	 * reader = new InputStreamReader(instream); //읽는 스트림 선언 BufferedReader bufferR
	 * = new BufferedReader(reader); String resultJSON = bufferR.readLine();
	 * System.out.println(resultJSON); return resultJSON; } catch(Exception e) {
	 * e.printStackTrace(); } return null; }
	 */
}
