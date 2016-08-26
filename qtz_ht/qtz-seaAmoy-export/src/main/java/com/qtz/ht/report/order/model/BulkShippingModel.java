package com.qtz.ht.report.order.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class BulkShippingModel  implements SerializeFilter{
	/** 订单Id */
	private Long orderId;
	/** 快递公司名称 */
	private String logisticsCompany;
	/** 快递单号 */
	private String logisticsNumber;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getLogisticsCompany() {
		return logisticsCompany;
	}
	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	public String getLogisticsNumber() {
		return logisticsNumber;
	}
	public void setLogisticsNumber(String logisticsNumber) {
		this.logisticsNumber = logisticsNumber;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}