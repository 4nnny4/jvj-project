package edu.kh.jvj.admin.model.vo;

import java.util.List;

public class NewNotice {
	//NOTICE_NO
	private int noticeNo;
	//NOTICE_TITLE
	private String noticeTitle;
	//NOTICE_CONTENT
	private String content;
	//NOTICE_CREATE_DT
	private String createDt;
	//NOTICE_CD
	private int noticeCd;
	//NOTICE_TYPE_NM
	private String noticeCateName;
	private List<MadeCoupon> couponList;
	
	public List<MadeCoupon> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<MadeCoupon> couponList) {
		this.couponList = couponList;
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
