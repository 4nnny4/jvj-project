package edu.kh.jvj.review.model.vo;

public class Review {
	
	private int reviewNo; //리뷰번호
	private int orderNo; // 주문번호
	private int productNo; // 상품번호
	private String title; // 리뷰제목
	private int point; // 평점
	private String content; // 내용
	private String date; // 날짜
	private int memberNo; // 회원번호
	private int categoryNo; // 카테고리 번호
	private String productName; // 상품이름
	private String imgPath; // 이미지 경로 
	private String writer; // 작성자

	public Review() {
		// TODO Auto-generated constructor stub
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", orderNo=" + orderNo + ", productNo=" + productNo + ", title=" + title
				+ ", point=" + point + ", content=" + content + ", date=" + date + ", memberNo=" + memberNo
				+ ", categoryNo=" + categoryNo + ", productName=" + productName + ", imgPath=" + imgPath + ", writer="
				+ writer + "]";
	}

	

	
}
