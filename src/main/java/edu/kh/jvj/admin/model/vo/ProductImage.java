package edu.kh.jvj.admin.model.vo;

public class ProductImage {
	private int productImgNo;
	private String imgPath;
	private String imgName;
	private int imgLevel;
	private int productNo;
	public int getProductImgNo() {
		return productImgNo;
	}
	public void setProductImgNo(int productImgNo) {
		this.productImgNo = productImgNo;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public int getImgLevel() {
		return imgLevel;
	}
	public void setImgLevel(int imgLevel) {
		this.imgLevel = imgLevel;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	@Override
	public String toString() {
		return "ProductImage [productImgNo=" + productImgNo + ", imgPath=" + imgPath + ", imgName=" + imgName
				+ ", imgLevel=" + imgLevel + ", productNo=" + productNo + "]";
	}
	
}
