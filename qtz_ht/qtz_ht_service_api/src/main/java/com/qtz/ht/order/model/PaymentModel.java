package com.qtz.ht.order.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * <p>Title:PaymentModel</p>
 * <p>Description:(换车活动支付模型)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月18日
 */
@SuppressWarnings("serial")
public class PaymentModel implements Serializable {

	/**剩余海淘补贴金额*/
	private Double subsidyAmount;
	/**订单金额*/
	private Double orderAmount;
	/** 商品名称 */
	private String goodName;
	/** 产品主图 */
	private String mainPicture;
	

	public Double getSubsidyAmount() {
		return subsidyAmount;
	}

	public void setSubsidyAmount(Double subsidyAmount) {
		this.subsidyAmount = subsidyAmount;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getMainPicture() {
		return mainPicture;
	}

	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
