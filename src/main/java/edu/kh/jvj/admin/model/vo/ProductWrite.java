package edu.kh.jvj.admin.model.vo;

public class ProductWrite extends SimpleProduct{
	private String editordata; //설명내용(섬머노트)
	
	//일반스토어
	private int stock;
	private int storecate;
	private String discountyn;
	private String discountStart;
	private String discountEnd;
	private double discountPer;
	private String detailcontents;
	
	
	//클래스 
	private int place; //지점
	private int people; //최대인원
	private String classdate; //일시
	private int starthour; //시작시간
	private int startminute;//시작 분
	private int endhour;//끝 시간
	private int endminute; //끝 분
	private String startEndTime;
	public String getEditordata() {
		return editordata;
	}
	public void setEditordata(String editordata) {
		this.editordata = editordata;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStorecate() {
		return storecate;
	}
	public void setStorecate(int storecate) {
		this.storecate = storecate;
	}
	public String getDiscountyn() {
		return discountyn;
	}
	public void setDiscountyn(String discountyn) {
		this.discountyn = discountyn;
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
	public double getDiscountPer() {
		return discountPer;
	}
	public void setDiscountPer(double discountPer) {
		this.discountPer = discountPer;
	}
	public String getDetailcontents() {
		return detailcontents;
	}
	public void setDetailcontents(String detailcontents) {
		this.detailcontents = detailcontents;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public String getClassdate() {
		return classdate;
	}
	public void setClassdate(String classdate) {
		this.classdate = classdate;
	}
	public int getStarthour() {
		return starthour;
	}
	public void setStarthour(int starthour) {
		this.starthour = starthour;
	}
	public int getStartminute() {
		return startminute;
	}
	public void setStartminute(int startminute) {
		this.startminute = startminute;
	}
	public int getEndhour() {
		return endhour;
	}
	public void setEndhour(int endhour) {
		this.endhour = endhour;
	}
	public int getEndminute() {
		return endminute;
	}
	public void setEndminute(int endminute) {
		this.endminute = endminute;
	}
	public String getStartEndTime() {
		return startEndTime;
	}
	public void setStartEndTime(String startEndTime) {
		this.startEndTime = startEndTime;
	}
	@Override
	public String toString() {
		return "ProductWrite [editordata=" + editordata + ", stock=" + stock + ", storecate=" + storecate
				+ ", discountyn=" + discountyn + ", discountStart=" + discountStart + ", discountEnd=" + discountEnd
				+ ", discountPer=" + discountPer + ", detailcontents=" + detailcontents + ", place=" + place
				+ ", people=" + people + ", classdate=" + classdate + ", starthour=" + starthour + ", startminute="
				+ startminute + ", endhour=" + endhour + ", endminute=" + endminute + ", startEndTime=" + startEndTime
				+ "]";
	}
	
	
}
