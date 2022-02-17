package edu.kh.jvj.admin.model.vo;

public class MadeCoupon {
	private int couponNo;
	private String couponName;
	private String createDate;
	private String expireDate;
	private int discountPer;
	private int couponLimit;
	private int adminNo;
	private String hashName;
	private int couponStatusCode;
	private int noticeNo;
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public int getDiscountPer() {
		return discountPer;
	}
	public void setDiscountPer(int discountPer) {
		this.discountPer = discountPer;
	}
	public int getCouponLimit() {
		return couponLimit;
	}
	public void setCouponLimit(int couponLimit) {
		this.couponLimit = couponLimit;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getHashName() {
		return hashName;
	}
	public void setHashName(String hashName) {
		this.hashName = hashName;
	}
	public int getCouponStatusCode() {
		return couponStatusCode;
	}
	public void setCouponStatusCode(int couponStatusCode) {
		this.couponStatusCode = couponStatusCode;
	}
	@Override
	public String toString() {
		return "MadeCoupon [couponNo=" + couponNo + ", couponName=" + couponName + ", createDate=" + createDate
				+ ", expireDate=" + expireDate + ", discountPer=" + discountPer + ", couponLimit=" + couponLimit
				+ ", adminNo=" + adminNo + ", hashName=" + hashName + ", couponStatusCode=" + couponStatusCode
				+ ", noticeNo=" + noticeNo + "]";
	}
	
	
}
