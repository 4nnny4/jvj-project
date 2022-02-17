package edu.kh.jvj.payment.model.vo;

public class KaKaoPayKey {
	private int partner_order_id;
	private String userId;
	private String tid;
	public int getPartner_order_id() {
		return partner_order_id;
	}
	public void setPartner_order_id(int partner_order_id) {
		this.partner_order_id = partner_order_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "KaKaoPayKey [partner_order_id=" + partner_order_id + ", userId=" + userId + ", tid=" + tid + "]";
	}
	
	
}
