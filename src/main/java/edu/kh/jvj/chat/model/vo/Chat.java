package edu.kh.jvj.chat.model.vo;

import java.sql.Date;

public class Chat {

	private int chatRoomNo; // 채팅방 번호
	
	private int memberNo; // 회원 번호
	private String memberName; // 회원 이름
	private int gradeCode; // 회원 등급 코드
	
	public Chat() {}

	public Chat(int chatRoomNo, int memberNo, String memberName, int gradeCode) {
		super();
		this.chatRoomNo = chatRoomNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.gradeCode = gradeCode;
	}

	public int getChatRoomNo() {
		return chatRoomNo;
	}

	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(int gradeCode) {
		this.gradeCode = gradeCode;
	}

	@Override
	public String toString() {
		return "Chat [chatRoomNo=" + chatRoomNo + ", memberNo=" + memberNo + ", memberName=" + memberName
				+ ", gradeCode=" + gradeCode + "]";
	}

	

	


	
	
	
}
