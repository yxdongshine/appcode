package com.qtz.ht.order.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.qtz.ht.good.vo.HtStaffGoods;

/**
 * <p>Title:OrderModel</p>
 * <p>Description:(计算订间模型)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月25日
 */
@SuppressWarnings("serial")
public class OrderModel implements Serializable{

	private Long dmId;
	/** 商家Id */
	private Long sellerId;
	/** 商家名称  */
	private String sellerName;
	/** 订单价格 */
	private Double orderPrice;
	/** 优惠金额 */
	private Double couponPrice;
	/** 付款金额 */
	private Double paymentPrice;
	/** 支付方式 */
	private Integer paymentMethod;
	/** 商品集合及购买数量*/
	private List<HtStaffGoods> list;
	/**
	 * 订单总和成本价格
	 */
	private Double orderSumfinalPrice;
	
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
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Double getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(Double couponPrice) {
		this.couponPrice = couponPrice;
	}
	public Double getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(Double paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public List<HtStaffGoods> getList() {
		return list;
	}
	public void setList(List<HtStaffGoods> list) {
		this.list = list;
	}
	
	public Double getOrderSumfinalPrice() {
		return orderSumfinalPrice;
	}
	public void setOrderSumfinalPrice(Double orderSumfinalPrice) {
		this.orderSumfinalPrice = orderSumfinalPrice;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
