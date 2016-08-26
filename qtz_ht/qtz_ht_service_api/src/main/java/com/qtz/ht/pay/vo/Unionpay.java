package com.qtz.ht.pay.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:Unionpay</p>
 * <p>Description:银联VO类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 -xin.tu
 * @version v1.0 2015-10-13
 */
public class Unionpay extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1359085367298048L;

		/** 银联支付时间 */	private String txnTime;
	/** 订单id*/
	private Long orderId;
	
		public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getTxnTime() {	    return this.txnTime;	}	public void setTxnTime(String txnTime) {	    this.txnTime=txnTime;	}
		public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}