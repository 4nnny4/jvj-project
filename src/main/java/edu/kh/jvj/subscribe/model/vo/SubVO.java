package edu.kh.jvj.subscribe.model.vo;

import java.util.List;

import edu.kh.jvj.onedayclass.model.vo.ProductImage;

public class SubVO {

	// 상품
	private int productNo; // PROCUT_NO
	private String productName; // PRODUCT_NM
	private int productPrice; // PRODUCT_PRICE
	private String createDt; // CREATE_DT
	private final int productCode = 2; // PRODUCT_CD

	// 상품 상세(STORE_DETAIL)
	private String storeExp; // STORE_EXP NULL
	private int storeAmount; // STORE_AMOUNT NOT NULL
	private String storeMemo; // STORE_MEMO NULL

	// 옵션(SUB_OPTION)
	private int subOptionNo; // SUB_OPTION_NO
	private String subOptionContent; // SUB_OPTION_CONTENT
	private int subOptionCode; // SUB_OPTION_CD

	private int likecount; // LIKECOUNT
	private int likedone; //

	
	private String imgPathName;	//PRODUCT_IMG_PATH
	private List<ProductImage> subImgList;	//이미지
	
	private double ratingAgv; // RATING_AVG

	private int memberNo; // 회원번호 MEMBER_NO

	public SubVO() {
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getStoreExp() {
		return storeExp;
	}

	public void setStoreExp(String storeExp) {
		this.storeExp = storeExp;
	}

	public int getStoreAmount() {
		return storeAmount;
	}

	public void setStoreAmount(int storeAmount) {
		this.storeAmount = storeAmount;
	}

	public String getStoreMemo() {
		return storeMemo;
	}

	public void setStoreMemo(String storeMemo) {
		this.storeMemo = storeMemo;
	}

	public int getSubOptionNo() {
		return subOptionNo;
	}

	public void setSubOptionNo(int subOptionNo) {
		this.subOptionNo = subOptionNo;
	}

	public String getSubOptionContent() {
		return subOptionContent;
	}

	public void setSubOptionContent(String subOptionContent) {
		this.subOptionContent = subOptionContent;
	}

	public int getSubOptionCode() {
		return subOptionCode;
	}

	public void setSubOptionCode(int subOptionCode) {
		this.subOptionCode = subOptionCode;
	}

	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}

	public int getLikedone() {
		return likedone;
	}

	public void setLikedone(int likedone) {
		this.likedone = likedone;
	}

	public String getImgPathName() {
		return imgPathName;
	}

	public void setImgPathName(String imgPathName) {
		this.imgPathName = imgPathName;
	}

	public List<ProductImage> getSubImgList() {
		return subImgList;
	}

	public void setSubImgList(List<ProductImage> subImgList) {
		this.subImgList = subImgList;
	}

	public double getRatingAgv() {
		return ratingAgv;
	}

	public void setRatingAgv(double ratingAgv) {
		this.ratingAgv = ratingAgv;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getProductCode() {
		return productCode;
	}

	@Override
	public String toString() {
		return "SubVO [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", createDt=" + createDt + ", productCode=" + productCode + ", storeExp=" + storeExp
				+ ", storeAmount=" + storeAmount + ", storeMemo=" + storeMemo + ", subOptionNo=" + subOptionNo
				+ ", subOptionContent=" + subOptionContent + ", subOptionCode=" + subOptionCode + ", likecount="
				+ likecount + ", likedone=" + likedone + ", imgPathName=" + imgPathName + ", subImgList=" + subImgList
				+ ", ratingAgv=" + ratingAgv + ", memberNo=" + memberNo + "]";
	}

	
	
	

}
