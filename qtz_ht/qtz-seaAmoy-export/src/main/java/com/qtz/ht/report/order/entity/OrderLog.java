package com.qtz.ht.report.order.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * <p>Title:OrderLog</p>
 * <p>Description:(订单日志模型)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月14日
 */
public class OrderLog  implements SerializeFilter {

	/**  */
	private Long dmId;
	/** 订单id */
	private Long orderId;
	/** 下单时间 */
	private Long time;
	/** 订单状态 */
	private Integer status;
	/** 备注 */
	private String notes;
	
	
	public OrderLog(Long dmId, Long orderId, Long time, Integer status, String notes) {
		super();
		this.dmId = dmId;
		this.orderId = orderId;
		this.time = time;
		this.status = status;
		this.notes = notes;
	}

	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
}