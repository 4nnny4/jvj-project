package edu.kh.jvj.admin.model.vo;

public class SalesRank {
	private int rank;
	private int productNo;
	private int sales;
	private String productName;
	private String payday;
	
	public String getPayday() {
		return payday;
	}
	public void setPayday(String payday) {
		this.payday = payday;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "SalesRank [rank=" + rank + ", productNo=" + productNo + ", sales=" + sales + ", productName="
				+ productName + "]";
	}
	
}
