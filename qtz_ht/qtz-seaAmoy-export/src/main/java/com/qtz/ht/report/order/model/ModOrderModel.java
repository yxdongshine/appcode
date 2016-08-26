package com.qtz.ht.report.order.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class ModOrderModel  implements SerializeFilter{
	/** 订单Id */
	private Long orderId;
	/** 申请用户ID */
	private Long userId;
	/** 收货人身份ID htorder表中 */
	private String idCard;
	/**
	 * 买家名称  htorder表中
	 */
	private String vipName;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
}