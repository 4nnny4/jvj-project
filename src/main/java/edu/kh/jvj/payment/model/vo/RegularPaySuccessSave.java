package edu.kh.jvj.payment.model.vo;

import java.util.Map;

public class RegularPaySuccessSave extends KaKaoPayKey{
	private String aid;
	private String cid;
	private String sid;
	private String payment_method_type;
	private String item_name;
	private String quantity;
	private Map<String, String> amount;
	private String created_at;
	private String approved_at;
	private int payTerm;
	
	public int getPayTerm() {
		return payTerm;
	}
	public void setPayTerm(int payTerm) {
		this.payTerm = payTerm;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPayment_method_type() {
		return payment_method_type;
	}
	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Map<String, String> getAmount() {
		return amount;
	}
	public void setAmount(Map<String, String> amount) {
		this.amount = amount;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getApproved_at() {
		return approved_at;
	}
	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}
	@Override
	public String toString() {
		return "RegularPaySuccessSave [aid=" + aid + ", cid=" + cid + ", sid=" + sid + ", payment_method_type="
				+ payment_method_type + ", item_name=" + item_name + ", quantity=" + quantity + ", amount=" + amount
				+ ", created_at=" + created_at + ", approved_at=" + approved_at + "]";
	}
	
	
}
