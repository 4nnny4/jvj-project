package edu.kh.jvj.notice.model.vo;

import java.util.List;

import edu.kh.jvj.admin.model.vo.MadeCoupon;

public class Notice {
	//NOTICE_NO
	private int noticeNo;
	//NOTICE_TITLE
	private String noticeTitle;
	//NOTICE_CONTENT
	private String content;
	private String editordata;
	//NOTICE_CREATE_DT
	private String createDt;
	//NOTICE_CD
	private int noticeCd;
	//NOTICE_TYPE_NM
	private String noticeCateName;
	private int loginAdmin;
	private String[] couponName;
	private String[] expireDate;
	private int[] discountPer;
	private int[] couponLimit;
	private List<MadeCoupon> coupons;
	public String[] getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String[] expireDate) {
		this.expireDate = expireDate;
	}
	public int[] getDiscountPer() {
		return discountPer;
	}
	public void setDiscountPer(int[] discountPer) {
		this.discountPer = discountPer;
	}
	public int[] getCouponLimit() {
		return couponLimit;
	}
	public void setCouponLimit(int[] couponLimit) {
		this.couponLimit = couponLimit;
	}
	public String getEditordata() {
		return editordata;
	}
	public void setEditordata(String editordata) {
		this.editordata = editordata;
	}
	public int getLoginAdmin() {
		return loginAdmin;
	}
	public void setLoginAdmin(int loginAdmin) {
		this.loginAdmin = loginAdmin;
	}
	public String[] getCouponName() {
		return couponName;
	}
	public void setCouponName(String[] couponName) {
		this.couponName = couponName;
	}
	public String getNoticeCateName() {
		return noticeCateName;
	}
	public void setNoticeCateName(String noticeCateName) {
		this.noticeCateName = noticeCateName;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public int getNoticeCd() {
		return noticeCd;
	}
	public void setNoticeCd(int noticeCd) {
		this.noticeCd = noticeCd;
	}
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", content=" + content + ", createDt="
				+ createDt + ", noticeCd=" + noticeCd + "]";
	}
	
}
