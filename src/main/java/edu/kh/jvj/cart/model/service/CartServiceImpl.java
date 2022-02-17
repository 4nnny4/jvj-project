package edu.kh.jvj.cart.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.jvj.cart.model.dao.CartDAO;
import edu.kh.jvj.cart.model.vo.Cart;
import edu.kh.jvj.member.model.vo.Member;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO dao;

	@Override
	@Transactional
	public int addCart(Cart cart) {
		
		return dao.addCart(cart);
	}

	@Override
	@Transactional
	public int addSub(Cart cart2) {
		
		return dao.addSub(cart2);
	}

	@Override
	public List<Cart> selectCartList(Member member) {
		// TODO Auto-generated method stub
		return dao.selectCartList(member);
	}

	@Override
	public List<Cart> selectOptionList(Member member) {
		
		return dao.selectOptionList(member);
	}

	@Override
	@Transactional
	public int deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		return dao.deleteCart(cart);
	}

	@Override
	public List<Cart> selectProductList(int memberNo) {
	
		return dao.selectProductList(memberNo);
	}

	@Override
	public int selectAmount(int productNo) {
		
		return dao.selectAmount(productNo);
	}

	@Override
	@Transactional
	public int updateCart(Cart cart2) {
		// TODO Auto-generated method stub
		return dao.updateCart(cart2);
	}

	@Transactional
	@Override
	public int deleteAllCart(int memberNo) {
		// TODO Auto-generated method stub
		return dao.deleteAllCart(memberNo);
	}

	@Override
	@Transactional
	public int plusAddq(Cart cart) {
		
		return dao.plusAddq(cart);
	}

	@Override
	public Cart selectPdtAmount(Cart cart) {
		
		return dao.selectPdtAmount(cart);
		
	}

	@Override
	public Cart selectProductOne(Cart cart2) {
		// TODO Auto-generated method stub
		return dao.selectProductOne(cart2);
	}

	@Override
	@Transactional
	public int minusAddq(int cartNo) {
		
		return dao.minusAddq(cartNo);
	}

	

}
