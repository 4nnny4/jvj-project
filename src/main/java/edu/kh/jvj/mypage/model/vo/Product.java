package edu.kh.jvj.mypage.model.vo;

import java.sql.Date;

public class Product {
	
	private int productNo;
	private String productName;
	private int productPrice;
	private Date createDate;
	private int productCode;
	
	public Product() {}
	
	
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
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


	public Product(int productNo, String productName, int productPrice, Date createDate, int productCode) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.createDate = createDate;
		this.productCode = productCode;
	}


	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", createDate=" + createDate + ", productCode=" + productCode + "]";
	}
	

}
