package edu.kh.jvj.payment.model.vo;

import java.util.Arrays;

public class StoreOrderInfo {
    private String productCd;
    private String productNos;
    private String productNames;
    private String productQuantities;
    private String productPrices;
    private String pno;
    private String pna;
    private String pqu;
    private String ppr;
    private String[] productNosArr;
    private String[] productNamesArr;
    private String[] productQuantitiesArr;
    private String[] productPricesArr;
    private int totalPrice;
    private int memberNo;
    private String shippingAddr;
    private String usedCouponNo;
    private String shippingAddrEqualMemberAddr;
    private String shippingName;
    private String shippingPhone;
    private String shippingEmail;
    private String shippingMsg;
    private String result_merchant_uid;
    private String result_imp_uid;
    private int purchaseNo;
    private int orderNo;
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getProductNos() {
		return productNos;
	}
	public void setProductNos(String productNos) {
		this.productNos = productNos;
	}
	public String getProductNames() {
		return productNames;
	}
	public void setProductNames(String productNames) {
		this.productNames = productNames;
	}
	public String getProductQuantities() {
		return productQuantities;
	}
	public void setProductQuantities(String productQuantities) {
		this.productQuantities = productQuantities;
	}
	public String getProductPrices() {
		return productPrices;
	}
	public void setProductPrices(String productPrices) {
		this.productPrices = productPrices;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public String getPna() {
		return pna;
	}
	public void setPna(String pna) {
		this.pna = pna;
	}
	public String getPqu() {
		return pqu;
	}
	public void setPqu(String pqu) {
		this.pqu = pqu;
	}
	public String getPpr() {
		return ppr;
	}
	public void setPpr(String ppr) {
		this.ppr = ppr;
	}
	public String[] getProductNosArr() {
		return productNosArr;
	}
	public void setProductNosArr(String[] productNosArr) {
		this.productNosArr = productNosArr;
	}
	public String[] getProductNamesArr() {
		return productNamesArr;
	}
	public void setProductNamesArr(String[] productNamesArr) {
		this.productNamesArr = productNamesArr;
	}
	public String[] getProductQuantitiesArr() {
		return productQuantitiesArr;
	}
	public void setProductQuantitiesArr(String[] productQuantitiesArr) {
		this.productQuantitiesArr = productQuantitiesArr;
	}
	public String[] getProductPricesArr() {
		return productPricesArr;
	}
	public void setProductPricesArr(String[] productPricesArr) {
		this.productPricesArr = productPricesArr;
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
	public String getShippingAddr() {
		return shippingAddr;
	}
	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}
	public String getUsedCouponNo() {
		return usedCouponNo;
	}
	public void setUsedCouponNo(String usedCouponNo) {
		this.usedCouponNo = usedCouponNo;
	}
	public String getShippingAddrEqualMemberAddr() {
		return shippingAddrEqualMemberAddr;
	}
	public void setShippingAddrEqualMemberAddr(String shippingAddrEqualMemberAddr) {
		this.shippingAddrEqualMemberAddr = shippingAddrEqualMemberAddr;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
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
	public String getResult_merchant_uid() {
		return result_merchant_uid;
	}
	public void setResult_merchant_uid(String result_merchant_uid) {
		this.result_merchant_uid = result_merchant_uid;
	}
	public String getResult_imp_uid() {
		return result_imp_uid;
	}
	public void setResult_imp_uid(String result_imp_uid) {
		this.result_imp_uid = result_imp_uid;
	}
	public int getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "StoreOrderInfo [productCd=" + productCd + ", productNos=" + productNos + ", productNames="
				+ productNames + ", productQuantities=" + productQuantities + ", productPrices=" + productPrices
				+ ", pno=" + pno + ", pna=" + pna + ", pqu=" + pqu + ", ppr=" + ppr + ", productNosArr="
				+ Arrays.toString(productNosArr) + ", productNamesArr=" + Arrays.toString(productNamesArr)
				+ ", productQuantitiesArr=" + Arrays.toString(productQuantitiesArr) + ", productPricesArr="
				+ Arrays.toString(productPricesArr) + ", totalPrice=" + totalPrice + ", memberNo=" + memberNo
				+ ", shippingAddr=" + shippingAddr + ", usedCouponNo=" + usedCouponNo + ", shippingAddrEqualMemberAddr="
				+ shippingAddrEqualMemberAddr + ", shippingName=" + shippingName + ", shippingPhone=" + shippingPhone
				+ ", shippingEmail=" + shippingEmail + ", shippingMsg=" + shippingMsg + ", result_merchant_uid="
				+ result_merchant_uid + ", result_imp_uid=" + result_imp_uid + ", purchaseNo=" + purchaseNo
				+ ", orderNo=" + orderNo + "]";
	}
    
}






