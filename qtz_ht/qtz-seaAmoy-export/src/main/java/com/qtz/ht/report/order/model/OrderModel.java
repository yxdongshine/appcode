package com.qtz.ht.report.order.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * <p>Title:OrderModel</p>
 * <p>Description:(计算订单模型)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月25日
 */
public class OrderModel implements SerializeFilter{

	private Long dmId;
	/** 商家Id */
	private Long sellerId;
	/** 订单价格 */
	private Double orderPrice;
	/** 支付方式 */
	private Integer paymentMethod;
	
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
