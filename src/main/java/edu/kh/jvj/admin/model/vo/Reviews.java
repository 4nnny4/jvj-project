package edu.kh.jvj.admin.model.vo;

public class Reviews {
	private String search;
	private int cate;
	private int cp;
	
	private int reviewNo;
	private int reviewPoint;
	private String productName;
	private String userId;
	private String reviewContent;
	private String reviewTitle;
	private String reviewStatus;
	
	public int getReviewPoint() {
		return reviewPoint;
	}
	public void setReviewPoint(int reviewPoint) {
		this.reviewPoint = reviewPoint;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getCate() {
		return cate;
	}
	public void setCate(int cate) {
		this.cate = cate;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	@Override
	public String toString() {
		return "Reviews [search=" + search + ", cate=" + cate + ", cp=" + cp + ", reviewNo=" + reviewNo
				+ ", productName=" + productName + ", userId=" + userId + ", reviewContent=" + reviewContent
				+ ", reviewTitle=" + reviewTitle + ", reviewStatus=" + reviewStatus + "]";
	}
	
}
