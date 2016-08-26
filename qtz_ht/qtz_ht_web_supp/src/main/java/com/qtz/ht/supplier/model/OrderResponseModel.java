package com.qtz.ht.supplier.model;

import com.qtz.orig.order.vo.HtOrder;


public class OrderResponseModel extends HtOrder{
	
	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 161243728618837685L;
	/** 供应商收入 */
	private Double staffRevenue;
	/** 供应商手机 */
	private String supplierPhone;
	/**
	 * 支付方式名称
	 */
	private String paymentTypeName;
	
	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	public Double getStaffRevenue() {
		return staffRevenue;
	}
	public void setStaffRevenue(Double staffRevenue) {
		this.staffRevenue = staffRevenue;
	}
	public String getSupplierPhone() {
		return supplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
}
