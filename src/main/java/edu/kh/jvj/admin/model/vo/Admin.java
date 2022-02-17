package edu.kh.jvj.admin.model.vo;

public class Admin {
	private String adminId;
	private String adminPw;
	private String saveId;
	private int memberNo;
	private String nickName;
	private String adminName;
	private int matchYn;
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getSaveId() {
		return saveId;
	}
	public void setSaveId(String saveId) {
		this.saveId = saveId;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getMatchYn() {
		return matchYn;
	}
	public void setMatchYn(int matchYn) {
		this.matchYn = matchYn;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminPw=" + adminPw + ", saveId=" + saveId + ", memberNo=" + memberNo
				+ ", nickName=" + nickName + ", adminName=" + adminName + ", matchYn=" + matchYn + "]";
	}

	
	
	
}
