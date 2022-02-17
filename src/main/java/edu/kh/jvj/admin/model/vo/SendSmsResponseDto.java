package edu.kh.jvj.admin.model.vo;

import java.sql.Timestamp;
public class SendSmsResponseDto {
	private String statusCode; 
	private String statusName; 
	private String requestId; 
	private Timestamp requestTime;
	
	public SendSmsResponseDto() {
	}
	public SendSmsResponseDto(String statusCode, String statusName, String requestId, Timestamp requestTime) {
		super();
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.requestId = requestId;
		this.requestTime = requestTime;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Timestamp getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}
	@Override
	public String toString() {
		return "SendSmsResponseDto [statusCode=" + statusCode + ", statusName=" + statusName + ", requestId="
				+ requestId + ", requestTime=" + requestTime + ", getStatusCode()=" + getStatusCode()
				+ ", getStatusName()=" + getStatusName() + ", getRequestId()=" + getRequestId() + ", getRequestTime()="
				+ getRequestTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	} 
}
