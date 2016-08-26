package com.qtz.ht.good.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtGoodLog</p>
 * <p>Description:商户商品操作记录VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
public class HtGoodLog extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1609857969014784L;

	
	
	
		super();
	}
	
	public HtGoodLog(Long goodId, Integer operatAction, String operateContent, String voucherAddr, String operatExplain,
			Long operatorId, String operatorName, Integer operaType, Long operaTime) {
		super();
		this.goodId = goodId;
		this.operatAction = operatAction;
		this.operateContent = operateContent;
		this.voucherAddr = voucherAddr;
		this.operatExplain = operatExplain;
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operaType = operaType;
		this.operaTime = operaTime;
	}
	
	public Long getDmId() {
	
		return ToStringBuilder.reflectionToString(this);
	}
}