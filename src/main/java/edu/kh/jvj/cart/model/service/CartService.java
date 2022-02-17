package edu.kh.jvj.cart.model.service;

import java.util.List;

import edu.kh.jvj.cart.model.vo.Cart;
import edu.kh.jvj.member.model.vo.Member;

public interface CartService {

	int addCart(Cart cart);

	int addSub(Cart cart2);

	List<Cart> selectCartList(Member member);

	List<Cart> selectOptionList(Member member);

	int deleteCart(Cart cart);

	List<Cart> selectProductList(int memberNo);

	int selectAmount(int productNo);

	int updateCart(Cart cart2);

	int deleteAllCart(int memberNo);

	int plusAddq(Cart cart);

	Cart selectPdtAmount(Cart cart);

	Cart selectProductOne(Cart cart2);

	int minusAddq(int cartNo);


}
