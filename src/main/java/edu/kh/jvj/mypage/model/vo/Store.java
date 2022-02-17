package edu.kh.jvj.mypage.model.vo;



public class Store {
	
	private int storeNo; //상품번호
	private String storeName; // 상품명 
	private int price; // 가격
	private String memo; // 상품간단설명	
	private int likeit; // 좋아요 (좋아요함 1, 안함 0)
	private int stock;  // 재고
	private String storecate; // 빵종류
	private  String imgPath; // 이미지경로 
	private int discountPer; // 할인율
	
	// 상품 상세
	private String storeExp; // 상품상세설명	
	private String createDate; // 등록일
	
	
	private int memberNo; // 회원번호 
	private String discountStart; // 할인 시작일
	private String discountEnd; // 할인 종료일
	
	public Store() {
		
	}

	public int getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(int storeNo) {
		this.storeNo = storeNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getLikeit() {
		return likeit;
	}

	public void setLikeit(int likeit) {
		this.likeit = likeit;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getStorecate() {
		return storecate;
	}

	public void setStorecate(String storecate) {
		this.storecate = storecate;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getStoreExp() {
		return storeExp;
	}

	public void setStoreExp(String storeExp) {
		this.storeExp = storeExp;
	}

	public int getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(int discountPer) {
		this.discountPer = discountPer;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getDiscountStart() {
		return discountStart;
	}

	public void setDiscountStart(String discountStart) {
		this.discountStart = discountStart;
	}

	public String getDiscountEnd() {
		return discountEnd;
	}

	public void setDiscountEnd(String discountEnd) {
		this.discountEnd = discountEnd;
	}

	@Override
	public String toString() {
		return "Store [storeNo=" + storeNo + ", storeName=" + storeName + ", price=" + price + ", createDate="
				+ createDate + ", memo=" + memo + ", likeit=" + likeit + ", stock=" + stock + ", storecate=" + storecate
				+ ", imgPath=" + imgPath + ", storeExp=" + storeExp + ", discountPer=" + discountPer + ", memberNo="
				+ memberNo + ", discountStart=" + discountStart + ", discountEnd=" + discountEnd + "]";
	}
	
	
}
