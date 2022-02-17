package edu.kh.jvj.admin.model.vo;

public class SearchedMember {
	
	//MEMBER_NO
	private int memberNo;
	//MEMBER_EMAIL
	private String memberEmail;
	//MEMBER_NICKNAME
	private String memberNickName;
	//MEMBER_NAME
	private String memberName;
	//ENROLL_DT
	private String enrollDt;
	//STATUS_CD
	private int statusCode;
	private int paysum;
	
	public int getPaysum() {
		return paysum;
	}
	public void setPaysum(int paysum) {
		this.paysum = paysum;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(String enrollDt) {
		this.enrollDt = enrollDt;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "SearchedMember [memberNo=" + memberNo + ", memberEmail=" + memberEmail + ", memberNickName="
				+ memberNickName + ", memberName=" + memberName + ", enrollDt=" + enrollDt + ", statusCode="
				+ statusCode + "]";
	}
	
	
	
	
}
