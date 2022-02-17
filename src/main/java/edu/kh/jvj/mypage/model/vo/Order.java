package edu.kh.jvj.mypage.model.vo;

import java.sql.Date;

public class Order {

	private int orderNo; // 구매내역 번호
	private int purchaseNo; // 구매 번호
	private int productNo; // 상품 번호
	private int productAmount; // 상품 구매상품 개수
	private int discount; // 할인율
	private int totalPrice; // 총 결제 금액
	private int memberNo; // 회원 번호
	private Date enrollDate;
	private String memberName;
	private String edt;
	private String service;
	
	private int orderOptionNo;
	private String orderOption;
	private int orderOptionAmount;
	
	private String productName;
	private int productPrice;
	private Date createDate;
	private int productCode;
	
	private int classImgNo;
	private String productImgPath;
	private String productImgName;
	private int productImgLevel;

	
	private String paymentStatus;
	private Date paymentDate;
	private String refunYN;
	
	public Order() {}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEdt() {
		return edt;
	}

	public void setEdt(String edt) {
		this.edt = edt;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getOrderOptionNo() {
		return orderOptionNo;
	}

	public void setOrderOptionNo(int orderOptionNo) {
		this.orderOptionNo = orderOptionNo;
	}

	public String getOrderOption() {
		return orderOption;
	}

	public void setOrderOption(String orderOption) {
		this.orderOption = orderOption;
	}

	public int getOrderOptionAmount() {
		return orderOptionAmount;
	}

	public void setOrderOptionAmount(int orderOptionAmount) {
		this.orderOptionAmount = orderOptionAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getClassImgNo() {
		return classImgNo;
	}

	public void setClassImgNo(int classImgNo) {
		this.classImgNo = classImgNo;
	}

	public String getProductImgPath() {
		return productImgPath;
	}

	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}

	public String getProductImgName() {
		return productImgName;
	}

	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}

	public int getProductImgLevel() {
		return productImgLevel;
	}

	public void setProductImgLevel(int productImgLevel) {
		this.productImgLevel = productImgLevel;
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

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", purchaseNo=" + purchaseNo + ", productNo=" + productNo
				+ ", productAmount=" + productAmount + ", discount=" + discount + ", totalPrice=" + totalPrice
				+ ", memberNo=" + memberNo + ", enrollDate=" + enrollDate + ", memberName=" + memberName + ", edt="
				+ edt + ", service=" + service + ", orderOptionNo=" + orderOptionNo + ", orderOption=" + orderOption
				+ ", orderOptionAmount=" + orderOptionAmount + ", productName=" + productName + ", productPrice="
				+ productPrice + ", createDate=" + createDate + ", productCode=" + productCode + ", classImgNo="
				+ classImgNo + ", productImgPath=" + productImgPath + ", productImgName=" + productImgName
				+ ", productImgLevel=" + productImgLevel + ", paymentStatus=" + paymentStatus + ", paymentDate="
				+ paymentDate + ", refunYN=" + refunYN + "]";
	}

	public Order(int orderNo, int purchaseNo, int productNo, int productAmount, int discount, int totalPrice,
			int memberNo, Date enrollDate, String memberName, String edt, String service, int orderOptionNo,
			String orderOption, int orderOptionAmount, String productName, int productPrice, Date createDate,
			int productCode, int classImgNo, String productImgPath, String productImgName, int productImgLevel,
			String paymentStatus, Date paymentDate, String refunYN) {
		super();
		this.orderNo = orderNo;
		this.purchaseNo = purchaseNo;
		this.productNo = productNo;
		this.productAmount = productAmount;
		this.discount = discount;
		this.totalPrice = totalPrice;
		this.memberNo = memberNo;
		this.enrollDate = enrollDate;
		this.memberName = memberName;
		this.edt = edt;
		this.service = service;
		this.orderOptionNo = orderOptionNo;
		this.orderOption = orderOption;
		this.orderOptionAmount = orderOptionAmount;
		this.productName = productName;
		this.productPrice = productPrice;
		this.createDate = createDate;
		this.productCode = productCode;
		this.classImgNo = classImgNo;
		this.productImgPath = productImgPath;
		this.productImgName = productImgName;
		this.productImgLevel = productImgLevel;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.refunYN = refunYN;
	}

	

	
	
}
