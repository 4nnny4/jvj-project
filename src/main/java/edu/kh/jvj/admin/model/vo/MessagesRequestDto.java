package edu.kh.jvj.admin.model.vo;

public class MessagesRequestDto {
	private String to; 
	private String content;
	
	public MessagesRequestDto() {
	}
	
	public MessagesRequestDto(String to, String content) {
		this.to = to;
		this.content = content;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "MessagesRequestDto [to=" + to + ", content=" + content + "]";
	}
	
}
