package edu.kh.jvj.subscribe.model.vo;

public class SearchVO {
	// 담아올 값
	// 상품명 금액 
	
	
	private int productNo ;
	private String productName;
	private String productCode;
	private int productPrice;
	private String storeAmount;
	
	
	// + 인풋 값
	private String sv;

	
	public SearchVO() {}

	
	
	




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




	public String getProductCode() {
		return productCode;
	}




	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}




	public String getSv() {
		return sv;
	}


	public void setSv(String sv) {
		this.sv = sv;
	}




	public String getStoreAmount() {
		return storeAmount;
	}

	public void setStoreAmount(String storeAmount) {
		this.storeAmount = storeAmount;
	}


	@Override
	public String toString() {
		return "SearchVO [productNo=" + productNo + ", productName=" + productName + ", productCode=" + productCode
				+ ", productPrice=" + productPrice + ", storeAmount=" + storeAmount + ", sv=" + sv + "]";
	}

	

	



	
	
}
