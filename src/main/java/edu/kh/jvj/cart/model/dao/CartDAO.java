package edu.kh.jvj.cart.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.jvj.cart.model.vo.Cart;
import edu.kh.jvj.member.model.vo.Member;

@Repository
public class CartDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public int addCart(Cart cart) {
		// TODO Auto-generated method stub
		return mybatis.insert("cartMapper.addCart",cart);
	}

	public int addSub(Cart cart2) {
		
		return  mybatis.insert("cartMapper.addSub",cart2);
	}

	public List<Cart> selectCartList(Member member) {
		
		return mybatis.selectList("cartMapper.selectCartList",member);
	}

	public List<Cart> selectOptionList(Member member) {
		// TODO Auto-generated method stub
		return mybatis.selectList("cartMapper.selectOptionList",member);
	}

	public int deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		return mybatis.delete("cartMapper.deleteCart",cart);
	}

	public List<Cart> selectProductList(int memberNo) {
		
		return mybatis.selectList("cartMapper.selectProductList",memberNo);
	}

	public int selectAmount(int productNo) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("cartMapper.selectAmount",productNo);
	}

	public int updateCart(Cart cart2) {
		// TODO Auto-generated method stub
		return mybatis.delete("cartMapper.updateCart",cart2);
	}

	public int deleteAllCart(int memberNo) {
	
		return mybatis.delete("cartMapper.deleteAllCart",memberNo);
	}

	public int plusAddq(Cart cart) {
		
		return mybatis.update("cartMapper.plusAddq",cart);
	}

	public Cart selectPdtAmount(Cart cart) {
		return mybatis.selectOne("cartMapper.selectPdtAmount",cart);
		
	}

	public Cart selectProductOne(Cart cart2) {
		
		return mybatis.selectOne("cartMapper.selectProductOne",cart2);
	}

	public int minusAddq(int cartNo) {

		return mybatis.update("cartMapper.minusAddq",cartNo);
	}

	

}
