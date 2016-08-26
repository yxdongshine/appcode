package com.qtz.ht.manage.model.request.order;

import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;
import com.mall.core.exception.ExceptionCode;

/**
 * 
 * <p>Title:OrderRequestModel</p>
 * <p>Description:(订单请求模型)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年9月1日
 */
public class OrderRequestModel {
	/**
	 * 收货信息id
	 */
	//@NotEmpty
	private Long receivingInfoId;
	/**
	 * 商品键值对
	 */
	@NotEmpty(message=ExceptionCode.NULL_EXCEPTION+"")
	private Map<Long, Integer> goodsMaps;
	
	
	/**
	 * 劵id
	 */
	private Long couponId;
	/**
	 * 订单类型
	 */
	@NotEmpty
	@DecimalMin(value="1")
	@DecimalMax(value="2")
	private Integer orderType;
	
	
	/**
	 * 预约时间
	 */
	private java.lang.Long makeTime;
	
	/**
	 * 备注
	 */
	@Length(max=50)
	private String note;
	
	public OrderRequestModel() {
		super();
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public java.lang.Long getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(java.lang.Long makeTime) {
		this.makeTime = makeTime;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Long getReceivingInfoId() {
		return receivingInfoId;
	}
	public void setReceivingInfoId(Long receivingInfoId) {
		this.receivingInfoId = receivingInfoId;
	}
	
	
	public Map<Long, Integer> getGoodsMaps() {
		return goodsMaps;
	}
	public void setGoodsMaps(Map<Long, Integer> goodsMaps) {
		this.goodsMaps = goodsMaps;
	}
	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
	
	
	
	
	
	

}
