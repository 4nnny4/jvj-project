package edu.kh.jvj.payment.model.vo;

public class RegualrPayInfo {
	private  int productCd =2   ;
	private  int productNo    ;
	private  String  productName       ;
	private  String  productOption    ;   
	private  String  tid    ;   
	private  String  partner_order_id    ;   
	private  int amount       ;
	private  int amount2       ;
     private int  totalPrice       ;
     private int  memberNo    ;
     private boolean shippingAddrEqualMemberAddr;   
     private String  shippingName       ;
     private String  memberId       ;
     private String  shippingAddr       ;
     private String  shippingPhone       ;
     private String  shippingEmail       ;
     private String  shippingMsg       ;
     private String userId;
     private  String  sid    ;   
     private  int  turms;
     
     
	public int getAmount2() {
		return amount2;
	}
	public void setAmount2(int amount2) {
		this.amount2 = amount2;
	}
	public int getProductCd() {
		return productCd;
	}
	public void setProductCd(int productCd) {
		this.productCd = productCd;
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
	public String getProductOption() {
		return productOption;
	}
	public void setProductOption(String productOption) {
		this.productOption = productOption;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getPartner_order_id() {
		return partner_order_id;
	}
	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public boolean isShippingAddrEqualMemberAddr() {
		return shippingAddrEqualMemberAddr;
	}
	public void setShippingAddrEqualMemberAddr(boolean shippingAddrEqualMemberAddr) {
		this.shippingAddrEqualMemberAddr = shippingAddrEqualMemberAddr;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getShippingAddr() {
		return shippingAddr;
	}
	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}
	public String getShippingPhone() {
		return shippingPhone;
	}
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}
	public String getShippingEmail() {
		return shippingEmail;
	}
	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}
	public String getShippingMsg() {
		return shippingMsg;
	}
	public void setShippingMsg(String shippingMsg) {
		this.shippingMsg = shippingMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getTurms() {
		return turms;
	}
	public void setTurms(int turms) {
		this.turms = turms;
	}
	@Override
	public String toString() {
		return "RegualrPayInfo [productCd=" + productCd + ", productNo=" + productNo + ", productName=" + productName
				+ ", productOption=" + productOption + ", tid=" + tid + ", partner_order_id=" + partner_order_id
				+ ", amount=" + amount + ", totalPrice=" + totalPrice + ", memberNo=" + memberNo
				+ ", shippingAddrEqualMemberAddr=" + shippingAddrEqualMemberAddr + ", shippingName=" + shippingName
				+ ", memberId=" + memberId + ", shippingAddr=" + shippingAddr + ", shippingPhone=" + shippingPhone
				+ ", shippingEmail=" + shippingEmail + ", shippingMsg=" + shippingMsg + ", userId=" + userId + ", sid="
				+ sid + ", turms=" + turms + "]";
	}   
}
