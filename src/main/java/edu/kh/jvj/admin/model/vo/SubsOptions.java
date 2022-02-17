package edu.kh.jvj.admin.model.vo;

public class SubsOptions {
	
	private String productName;
	private int suboptionNo;
	private String subOptionName;
	private int subOptionCd;
	private int productNo;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getSuboptionNo() {
		return suboptionNo;
	}
	public void setSuboptionNo(int suboptionNo) {
		this.suboptionNo = suboptionNo;
	}
	public String getSubOptionName() {
		return subOptionName;
	}
	public void setSubOptionName(String subOptionName) {
		this.subOptionName = subOptionName;
	}
	public int getSubOptionCd() {
		return subOptionCd;
	}
	public void setSubOptionCd(int subOptionCd) {
		this.subOptionCd = subOptionCd;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	@Override
	public String toString() {
		return "SubsOptions [productName=" + productName + ", suboptionNo=" + suboptionNo + ", subOptionName="
				+ subOptionName + ", subOptionCd=" + subOptionCd + ", productNo=" + productNo + "]";
	}
}
