package edu.kh.jvj.admin.model.vo;

import java.util.List;

import edu.kh.jvj.onedayclass.model.vo.ProductImage;

public class SubsInfo extends SimpleProduct{
	
	private List<ProductImage> classImgList;
	private String userId;
	private String userNickName;
	private String StartDay;
	private String options;
	private String YN;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getStartDay() {
		return StartDay;
	}
	public void setStartDay(String startDay) {
		StartDay = startDay;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getYN() {
		return YN;
	}
	public void setYN(String yN) {
		YN = yN;
	}
	public List<ProductImage> getClassImgList() {
		return classImgList;
	}
	public void setClassImgList(List<ProductImage> classImgList) {
		this.classImgList = classImgList;
	}
	@Override
	public String toString() {
		return "SubsInfo [classImgList=" + classImgList + "]";
	}
}
