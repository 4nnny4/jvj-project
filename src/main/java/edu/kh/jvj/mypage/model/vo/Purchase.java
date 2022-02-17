package edu.kh.jvj.mypage.model.vo;

import java.sql.Date;

public class Purchase {
	
	private int purchaseNo;
	private int productCode;
	private int productNo;
	private int totalPrice;
	private String paymentStatus;
	private Date paymentDate;
	private String refunYN;
	private int memberNo;
	
	
	public Purchase(int purchaseNo, int productCode, int productNo, int totalPrice, String paymentStatus,
			Date paymentDate, String refunYN, int memberNo) {
		super();
		this.purchaseNo = purchaseNo;
		this.productCode = productCode;
		this.productNo = productNo;
		this.totalPrice = totalPrice;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.refunYN = refunYN;
		this.memberNo = memberNo;
	}


	public int getPurchaseNo() {
		return purchaseNo;
	}


	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}


	public int getProductCode() {
		return productCode;
	}


	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}


	public int getProductNo() {
		return productNo;
	}


	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public String getRefunYN() {
		return refunYN;
	}


	public void setRefunYN(String refunYN) {
		this.refunYN = refunYN;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	@Override
	public String toString() {
		return "Purchase [purchaseNo=" + purchaseNo + ", productCode=" + productCode + ", productNo=" + productNo
				+ ", totalPrice=" + totalPrice + ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate
				+ ", refunYN=" + refunYN + ", memberNo=" + memberNo + "]";
	}
	
	
	
	
}
