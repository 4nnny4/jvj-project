package edu.kh.jvj.chat.model.vo;

import java.sql.Date;

public class ChatMessage {
	
	private int cmNo; // 채팅 메세지 번호
	private String message; // 작성된 채팅 메세지 내용
	private Date createDate; // 작성된 채팅 메세지 작성 시간
	private int chatRoomNo; // 채팅방 번호
	private int memberNo; // 회원번호
	private String memberName; //회원 닉네임
	
	public  ChatMessage() {}
	
	public int getCmNo() {
		return cmNo;
	}
	public void setCmNo(int cmNo) {
		this.cmNo = cmNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public ChatMessage(int cmNo, String message, Date createDate, int chatRoomNo, int memberNo, String memberName) {
		super();
		this.cmNo = cmNo;
		this.message = message;
		this.createDate = createDate;
		this.chatRoomNo = chatRoomNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
	}
	@Override
	public String toString() {
		return "ChatMessage [cmNo=" + cmNo + ", message=" + message + ", createDate=" + createDate + ", chatRoomNo="
				+ chatRoomNo + ", memberNo=" + memberNo + ", memberName=" + memberName + "]";
	}
	
	
	
	
}
