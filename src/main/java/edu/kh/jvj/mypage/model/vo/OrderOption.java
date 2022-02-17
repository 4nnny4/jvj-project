package edu.kh.jvj.mypage.model.vo;

public class OrderOption {

	private int orderOptionNo;
	private int orderNo;
	private String orderOption;
	private int orderOptionAmount;
	
	public OrderOption() {}

	public OrderOption(int orderOptionNo, int orderNo, String orderOption, int orderOptionAmount) {
		super();
		this.orderOptionNo = orderOptionNo;
		this.orderNo = orderNo;
		this.orderOption = orderOption;
		this.orderOptionAmount = orderOptionAmount;
	}

	public int getOrderOptionNo() {
		return orderOptionNo;
	}

	public void setOrderOptionNo(int orderOptionNo) {
		this.orderOptionNo = orderOptionNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderOption() {
		return orderOption;
	}

	public void setOrderOption(String orderOption) {
		this.orderOption = orderOption;
	}

	public int getOrderOptionAmount() {
		return orderOptionAmount;
	}

	public void setOrderOptionAmount(int orderOptionAmount) {
		this.orderOptionAmount = orderOptionAmount;
	}

	@Override
	public String toString() {
		return "OrderOption [orderOptionNo=" + orderOptionNo + ", orderNo=" + orderNo + ", orderOption=" + orderOption
				+ ", orderOptionAmount=" + orderOptionAmount + "]";
	}
	
	
	
}
