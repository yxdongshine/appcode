package com.qtz.ht.manage.model.request.order;

import com.qtz.orig.order.vo.HtOrder;


public class OrderResponseModel extends HtOrder{
	
	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 161243728618837685L;
	/** 平台收入 */
	private Double platformRevenue;
	/** 供应商收入 */
	private Double staffRevenue;
	/** 平台扣点 */
	private Double platformPoints;
	/** 供应商手机 */
	private String supplierPhone;
	/**
	 * 身份证号码
	 */
	private String IDCard;
	/**
	 * 支付方式名称
	 */
	private String paymentTypeName;
	
	
	
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	public Double getPlatformRevenue() {
		return platformRevenue;
	}
	public void setPlatformRevenue(Double platformRevenue) {
		this.platformRevenue = platformRevenue;
	}
	public Double getStaffRevenue() {
		return staffRevenue;
	}
	public void setStaffRevenue(Double staffRevenue) {
		this.staffRevenue = staffRevenue;
	}
	public Double getPlatformPoints() {
		return platformPoints;
	}
	public void setPlatformPoints(Double platformPoints) {
		this.platformPoints = platformPoints;
	}
	public String getSupplierPhone() {
		return supplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
}
