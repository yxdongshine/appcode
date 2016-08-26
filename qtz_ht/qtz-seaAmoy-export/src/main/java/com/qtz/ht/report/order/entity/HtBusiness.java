package com.qtz.ht.report.order.entity;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.alibaba.fastjson.serializer.SerializeFilter;
/**
 * <p>Title:HtBusiness</p>
 * <p>Description:商户信息表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-08
 */
public class HtBusiness implements SerializeFilter {
	
	/** 平台钱包为信息id*/
	public static final long ADMIN_WALLET_ID = 1000l;
	
	private Long dmId;
	/** 结算折扣 */
	private Double settDiscount;
	/** 对账周期（单位 天） */
	private Integer recoCycle;	
	
	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	public Double getSettDiscount() {
		return settDiscount;
	}

	public void setSettDiscount(Double settDiscount) {
		this.settDiscount = settDiscount;
	}

	public Integer getRecoCycle() {
		return recoCycle;
	}

	public void setRecoCycle(Integer recoCycle) {
		this.recoCycle = recoCycle;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}