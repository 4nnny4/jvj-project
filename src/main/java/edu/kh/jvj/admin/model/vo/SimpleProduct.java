package edu.kh.jvj.admin.model.vo;

public class SimpleProduct {
	private int productNo;
	private String title;
	private int price;
	private int writecate;
	private String cateName;
	private String createDate;
	private String currentImageCheck;
	private String afterImageCheck;
	
	public String getCurrentImageCheck() {
		return currentImageCheck;
	}
	public void setCurrentImageCheck(String currentImageCheck) {
		this.currentImageCheck = currentImageCheck;
	}
	public String getAfterImageCheck() {
		return afterImageCheck;
	}
	public void setAfterImageCheck(String afterImageCheck) {
		this.afterImageCheck = afterImageCheck;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "SimpleProduct [productNo=" + productNo + ", title=" + title + ", price=" + price + ", writecate="
				+ writecate + ", cateName=" + cateName + "]";
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getWritecate() {
		return writecate;
	}
	public void setWritecate(int writecate) {
		this.writecate = writecate;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	
}
