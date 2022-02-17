package edu.kh.jvj.payment.model.vo;

import java.util.List;

import edu.kh.jvj.admin.model.vo.SubsInfo;

public class SubsOrder extends SubsInfo{
	
	
	private int totalPrice;
	private int totalAmount;
	private String optionSelector;
	
	private List<OrderSubsOption> optionList;

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOptionSelector() {
		return optionSelector;
	}

	public void setOptionSelector(String optionSelector) {
		this.optionSelector = optionSelector;
	}

	public List<OrderSubsOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<OrderSubsOption> optionList) {
		this.optionList = optionList;
	}

	@Override
	public String toString() {
		return "SubsOrder [totalPrice=" + totalPrice + ", totalAmount=" + totalAmount + ", optionSelector="
				+ optionSelector + ", optionList=" + optionList + "]";
	}

	

}
