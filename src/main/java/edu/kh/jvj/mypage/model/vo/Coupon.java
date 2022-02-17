package edu.kh.jvj.mypage.model.vo;

import java.sql.Date;

public class Coupon {
	
	private int couponNo; // 쿠폰 번호
	private String couponName; // 쿠폰 이름
	private Double discountPer; // 할인률
	
	private Date provideDate; // 발급일
	private Date expireDate; // 만료일
	
	private int couponStatusCode; // 쿠폰 상태 코드
	private String couponStatusName; // 쿠폰 상태 코드 이름
	
	private int memberNo; // 회원 번호
	private String service;
	
	

	public Coupon() {}



	@Override
	public String toString() {
		return "Coupon [couponNo=" + couponNo + ", couponName=" + couponName + ", discountPer=" + discountPer
				+ ", provideDate=" + provideDate + ", expireDate=" + expireDate + ", couponStatusCode="
				+ couponStatusCode + ", couponStatusName=" + couponStatusName + ", memberNo=" + memberNo + ", service="
				+ service + "]";
	}



	public int getCouponNo() {
		return couponNo;
	}



	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}



	public String getCouponName() {
		return couponName;
	}



	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}



	public Double getDiscountPer() {
		return discountPer;
	}



	public void setDiscountPer(Double discountPer) {
		this.discountPer = discountPer;
	}



	public Date getProvideDate() {
		return provideDate;
	}



	public void setProvideDate(Date provideDate) {
		this.provideDate = provideDate;
	}



	public Date getExpireDate() {
		return expireDate;
	}



	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}



	public int getCouponStatusCode() {
		return couponStatusCode;
	}



	public void setCouponStatusCode(int couponStatusCode) {
		this.couponStatusCode = couponStatusCode;
	}



	public String getCouponStatusName() {
		return couponStatusName;
	}



	public void setCouponStatusName(String couponStatusName) {
		this.couponStatusName = couponStatusName;
	}



	public int getMemberNo() {
		return memberNo;
	}



	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}



	public String getService() {
		return service;
	}



	public void setService(String service) {
		this.service = service;
	}



	public Coupon(int couponNo, String couponName, Double discountPer, Date provideDate, Date expireDate,
			int couponStatusCode, String couponStatusName, int memberNo, String service) {
		super();
		this.couponNo = couponNo;
		this.couponName = couponName;
		this.discountPer = discountPer;
		this.provideDate = provideDate;
		this.expireDate = expireDate;
		this.couponStatusCode = couponStatusCode;
		this.couponStatusName = couponStatusName;
		this.memberNo = memberNo;
		this.service = service;
	}






	
	
	
	
	
	
	
}
