package edu.kh.jvj.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import edu.kh.jvj.cart.model.service.CartService;
import edu.kh.jvj.cart.model.vo.Carrier;
import edu.kh.jvj.cart.model.vo.Cart;
import edu.kh.jvj.cart.model.vo.Option;
import edu.kh.jvj.member.model.vo.Member;

@Controller
@SessionAttributes({ "loginMember" })
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService service;

	@GetMapping("")
	public String cartForward(@ModelAttribute("loginMember") Member member, Cart cart, Model model) {

		List<Cart> cartList = service.selectCartList(member);
		System.out.println(cartList);
		List<Carrier> carrierList = new ArrayList<Carrier>();
		for (Cart cart2 : cartList) {
			int sum = 0;
			if (cart2.getParentNo() == 0) {
				Carrier carrier = new Carrier();
				sum += cart2.getPrice() * cart2.getAddq() * (100 - cart2.getDiscountPer()) / 100;
				// 가격을 더한다
				carrier.setImgPath(cart2.getImgPath());
				carrier.setMainProductNo(cart2.getProductNo());
				carrier.setMainQ(cart2.getAddq());
				carrier.setMemberNo(member.getMemberNo());
				carrier.setDiscountPer(cart2.getDiscountPer());

				List<Option> optionList = new ArrayList<Option>();

				for (Cart cart3 : cartList) {
					if (cart3.getParentNo() == cart2.getCartNo()) {
						Option op = new Option();
						sum += cart3.getPrice() * cart3.getAddq() * (100 - cart3.getDiscountPer()) / 100;
						op.setOptionName(cart3.getProductName());
						op.setOptionNo(cart3.getProductNo());
						op.setOptionQ(cart3.getAddq());
						optionList.add(op);

					}
				}
				carrier.setOptionList(optionList);

				carrier.setSumPrice(sum);
				carrierList.add(carrier);
				System.out.println(carrier);
			}

		}
		System.out.println(carrierList);
		model.addAttribute("carrierList", carrierList);
		model.addAttribute("cartList", cartList);

		return "member/cart";
	}
	
	@ResponseBody
	@PostMapping("toPayment")
	public String toPayment(@ModelAttribute("loginMember") Member member, Cart cart, Model model) {
		
		
		List<Cart> cartList = service.selectCartList(member);
	
		List<Carrier> carrierList = new ArrayList<Carrier>();
		
		for (Cart cart2 : cartList) {
			int sum = 0;
			if (cart2.getParentNo() == 0) {
				Carrier carrier = new Carrier();
				sum += cart2.getPrice() * cart2.getAddq() * (100 - cart2.getDiscountPer()) / 100;
				// 가격을 더한다
				carrier.setImgPath(cart2.getImgPath());
				carrier.setMainProductNo(cart2.getProductNo());
				carrier.setMainQ(cart2.getAddq());
				carrier.setMemberNo(member.getMemberNo());
				carrier.setDiscountPer(cart2.getDiscountPer());

				List<Option> optionList = new ArrayList<Option>();

				for (Cart cart3 : cartList) {
					if (cart3.getParentNo() == cart2.getCartNo()) {
						Option op = new Option();
						sum += cart3.getPrice() * cart3.getAddq() * (100 - cart3.getDiscountPer()) / 100;
						op.setOptionName(cart3.getProductName());
						op.setOptionNo(cart3.getProductNo());
						op.setOptionQ(cart3.getAddq());
						optionList.add(op);

					}
				}
				carrier.setOptionList(optionList);

				carrier.setSumPrice(sum);
				carrierList.add(carrier);
	
			}

		}
		return new Gson().toJson(carrierList);
	}
	
	
	@PostMapping("addCart")
	@ResponseBody
	public int addCart(int storeNo, int addq, String arrays, String adStock, String adPrice,
			@ModelAttribute("loginMember") Member member, Cart cart) {
		int memberNo = member.getMemberNo(); // 회원번호
		String arrayQ[] = arrays.split(","); // 추가상품 갯수
//		String arrStock[] = adStock.split(","); // 재고
//		String arrPrice[] = adPrice.split(",");// 추가상품 가격

		// 상품 세팅
		cart.setProductNo(storeNo); // 상품번호
		cart.setAddq(addq); // 상품 수량
		cart.setMemberNo(memberNo);
		// 상품 추가
		int addMainResultNo = service.addCart(cart);

		if (addMainResultNo > 0) { // 상품 추가됐을때 추가옵션 추가
			for (int i = 0; i < arrayQ.length; i++) {
				if (Integer.parseInt(arrayQ[i]) == 0) { // 수량 0일시 건너뛰기
					continue;
				}
				Cart cart2 = new Cart();
				cart2.setAddq(Integer.parseInt(arrayQ[i])); // 옵션 수량
				cart2.setCartNo(cart.getCartNo()); // 카트번호
				cart2.setProductNo(cart2.getOptSetNum()[i]); // 옵션번호
				cart2.setMemberNo(memberNo); // 회원번호

				int addSub = service.addSub(cart2);

			}

		}

		return addMainResultNo;
	}

	@PostMapping("deleteCart")
	@ResponseBody
	public int deleteCart(int cartNo, @ModelAttribute("loginMember") Member member, Cart cart) {

		cart.setCartNo(cartNo);
		cart.setMemberNo(member.getMemberNo());
		int result = service.deleteCart(cart);

		return result;
	}

	// 카트 최신화
	@PostMapping("updateCart")
	@ResponseBody
	public int updateCart(@ModelAttribute("loginMember") Member member, Cart cart) {

		int memberNo = member.getMemberNo();
		int result = 0;
		List<Cart> productList = service.selectProductList(memberNo);

		for (Cart pdt : productList) {
			int amount = service.selectAmount(pdt.getProductNo());

			// 재고보다 상품 수량이 더많을때
			if (pdt.getAddq() > amount) {
				Cart cart2 = new Cart();
				cart2.setProductNo(pdt.getProductNo());
				cart2.setMemberNo(memberNo);
				result = service.updateCart(cart2);
			}
		}
		return result;
	}

	// 작은카트

	@ResponseBody
	@PostMapping("selectModalCart")
	public String selectModalCart(@ModelAttribute("loginMember") Member member, Cart cart, Model model) {
		int result = 0;
		List<Cart> cartList = service.selectCartList(member);
		if (!cartList.isEmpty()) {
			result = 1;
		}

		return new Gson().toJson(cartList);
	}

	@ResponseBody
	@PostMapping("deleteAllCart")
	public int deleteAllCart(@ModelAttribute("loginMember") Member member, Cart cart) {

		int result = service.deleteAllCart(member.getMemberNo());
		return result;
	}

	@ResponseBody
	@PostMapping("plusAddq")
	public int plusAddq(@ModelAttribute("loginMember") Member member, int cartNo, Cart cart) {

		int result = 0;
		cart.setCartNo(cartNo);
		cart.setMemberNo(member.getMemberNo());
		Cart cart2 = service.selectPdtAmount(cart);
		cart2.setMemberNo(member.getMemberNo());
		Cart cart3 = service.selectProductOne(cart2);
		if (cart2.getAmount() >= cart3.getAddq() + 1) {
			result = service.plusAddq(cart);
		}

		return result;
	}

	@ResponseBody
	@PostMapping("minusAddq")
	public int minusAddq(int cartNo, Cart cart) {

		int result = service.minusAddq(cartNo);

		return result;
	}

	@ResponseBody
	@PostMapping("calc")
	public int calc(HttpSession session, Member member) {

		int result = 0;

		if (session.getAttribute("loginMember") != null) {
			member = (Member) session.getAttribute("loginMember");

			List<Cart> cartList = service.selectCartList(member);
			System.out.println(cartList);

			for (Cart cart : cartList) {
				result += cart.getPrice() * (100 - cart.getDiscountPer()) / 100 * cart.getAddq();
			}
		}
		return result;
	}
}
