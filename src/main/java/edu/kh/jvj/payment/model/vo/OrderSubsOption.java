package edu.kh.jvj.payment.model.vo;

public class OrderSubsOption {
	private int optionNo;
	private String optioanName;
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public String getOptioanName() {
		return optioanName;
	}
	public void setOptioanName(String optioanName) {
		this.optioanName = optioanName;
	}
	@Override
	public String toString() {
		return "OrderSubsOption [optionNo=" + optionNo + ", optioanName=" + optioanName + "]";
	}
	
}
