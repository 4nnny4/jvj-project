package edu.kh.jvj.mypage.model.vo;

public class ProductImage {
	
	//CLASS_IMG_NO
	private int classImgNo;
	
	//CLASS_IMG_PATH
	private String productImgPath;
	
	//PRODUCT_IMG_NAME
	private String productImgName;
	
	//PRODUCT_IMG_LEVEL
	private int productImgLevel;
	
	//PRODUCT_NO
	private int productNo;

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

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	@Override
	public String toString() {
		return "ProductImage [classImgNo=" + classImgNo + ", productImgPath=" + productImgPath + ", productImgName="
				+ productImgName + ", productImgLevel=" + productImgLevel + ", productNo=" + productNo + "]";
	}
	
	
}
