package edu.kh.jvj.mypage.model.vo;

public class CouponStatus {

	private int couponStatusCode; // 쿠폰 상태 코드
	private String couponStatusName; // 쿠폰 상태 코드 이름
	
	
	public CouponStatus () {}
	
	
	public CouponStatus(int couponStatusCode, String couponStatusName) {
		super();
		this.couponStatusCode = couponStatusCode;
		this.couponStatusName = couponStatusName;
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
	@Override
	public String toString() {
		return "CouponStatus [couponStatusCode=" + couponStatusCode + ", couponStatusName=" + couponStatusName + "]";
	}
	
	
}
