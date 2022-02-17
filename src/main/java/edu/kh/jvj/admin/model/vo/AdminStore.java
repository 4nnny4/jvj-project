package edu.kh.jvj.admin.model.vo;

import java.util.List;

import edu.kh.jvj.onedayclass.model.vo.ProductImage;
import edu.kh.jvj.store.model.vo.Store;

public class AdminStore extends Store{
	private List<ProductImage> classImgList;

	public List<ProductImage> getClassImgList() {
		return classImgList;
	}

	public void setClassImgList(List<ProductImage> classImgList) {
		this.classImgList = classImgList;
	}

	@Override
	public String toString() {
		return "AdminStore [classImgList=" + classImgList + "]";
	}
	
	
}
