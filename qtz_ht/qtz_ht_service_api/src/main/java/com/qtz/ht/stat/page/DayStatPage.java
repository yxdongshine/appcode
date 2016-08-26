package com.qtz.ht.stat.page;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
/**
 * <p>Title:DayStatPage</p>
 * <p>Description:每天营业统计分页类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 -xin.tu
 * @version v1.0 2015-11-07
 */
public class DayStatPage extends Pager<com.qtz.ht.stat.vo.DayStat,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1394657053116417L;

		/**  */	private Long dmId;	/** 总营业额 */	private Double turnover;	/** 总提现额 */	private Double presentValue;	/** 余额 */	private Double balance;	/** 统计时间 */	private Long statisticalTime;	/** 创建时间 */	private Long crtime;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Double getTurnover() {	    return this.turnover;	}	public void setTurnover(Double turnover) {	    this.turnover=turnover;	}	public Double getPresentValue() {	    return this.presentValue;	}	public void setPresentValue(Double presentValue) {	    this.presentValue=presentValue;	}	public Double getBalance() {	    return this.balance;	}	public void setBalance(Double balance) {	    this.balance=balance;	}	public Long getStatisticalTime() {	    return this.statisticalTime;	}	public void setStatisticalTime(Long statisticalTime) {	    this.statisticalTime=statisticalTime;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}