package edu.kh.jvj.cart.model.vo;

import java.util.ArrayList;
import java.util.List;

public class Carrier {
	private	int mainProductNo; // 메인 상품 번호
	private int mainQ; // 메인 상품 갯수
	private List<Option> optionList = new ArrayList<Option>();
	private int memberNo; // 회원번호
	private int sumPrice; // 합계금액 ( 배송비 미포함 )
	private int discountPer ; // 할인율
	private String imgPath;// 이미지
	public Carrier() {
		// TODO Auto-generated constructor stub
	
	}

	public int getMainProductNo() {
		return mainProductNo;
	}

	public void setMainProductNo(int mainProductNo) {
		this.mainProductNo = mainProductNo;
	}

	public int getMainQ() {
		return mainQ;
	}

	public void setMainQ(int mainQ) {
		this.mainQ = mainQ;
	}

	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	public int getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(int discountPer) {
		this.discountPer = discountPer;
	}
	
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "Carrier [mainProductNo=" + mainProductNo + ", mainQ=" + mainQ + ", optionList=" + optionList
				+ ", memberNo=" + memberNo + ", sumPrice=" + sumPrice + ", discountPer=" + discountPer + ", imgPath="
				+ imgPath + "]";
	}

	

	
	
}
