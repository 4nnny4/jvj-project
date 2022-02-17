package edu.kh.jvj.cart.model.vo;

import java.util.Arrays;

public class Cart {

	private int cartNo; // 장바구니 번호
	private int productNo; // 상품번호
	private int addq; // 수량
	private int memberNo; // 회원번호
	private int[] optSetNum = {1427,1429,1428}; // 추가옵션 번호
	private String productName; // 상품이름
	private int price; // 상품 가격
	private String imgPath;// 이미지 경로
	private int parentNo; // 부모상품번호
	private int discountPer; // 할인율
	private int amount; // 재고
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getAddq() {
		return addq;
	}

	public void setAddq(int addq) {
		this.addq = addq;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public int[] getOptSetNum() {
		return optSetNum;
	}

	public void setOptSetNum(int[] optSetNum) {
		this.optSetNum = optSetNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public int getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(int discountPer) {
		this.discountPer = discountPer;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", productNo=" + productNo + ", addq=" + addq + ", memberNo=" + memberNo
				+ ", optSetNum=" + Arrays.toString(optSetNum) + ", productName=" + productName + ", price=" + price
				+ ", imgPath=" + imgPath + ", parentNo=" + parentNo + ", discountPer=" + discountPer + ", amount="
				+ amount + "]";
	}

	
	




}
