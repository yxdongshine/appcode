package com.qtz.ht.good.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.good.vo.HtGoodLog;
/**
 * <p>Title:HtGoodLogPage</p>
 * <p>Description:商户商品操作记录分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
public class HtGoodLogPage extends Pager<HtGoodLog,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1609857969014785L;
	
	private Long dmId;
		/** 商品ID */	private Long goodId;	/** 操作动作 */	private Integer operatAction;	/** 操作备注 */	private String operateContent;	/** 操作凭证 */	private String voucherAddr;	/** 操作说明 */	private String operatExplain;	/** 操作人id */	private Long operatorId;	/** 操作人名字 */	private String operatorName;	/** 操作人类型 */	private Integer operaType;	/** 操作时间 */	private Long operaTime;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getGoodId() {	    return this.goodId;	}	public void setGoodId(Long goodId) {	    this.goodId=goodId;	}	public Integer getOperatAction() {	    return this.operatAction;	}	public void setOperatAction(Integer operatAction) {	    this.operatAction=operatAction;	}	public String getOperateContent() {	    return this.operateContent;	}	public void setOperateContent(String operateContent) {	    this.operateContent=operateContent;	}	public String getVoucherAddr() {	    return this.voucherAddr;	}	public void setVoucherAddr(String voucherAddr) {	    this.voucherAddr=voucherAddr;	}	public String getOperatExplain() {	    return this.operatExplain;	}	public void setOperatExplain(String operatExplain) {	    this.operatExplain=operatExplain;	}	public Long getOperatorId() {	    return this.operatorId;	}	public void setOperatorId(Long operatorId) {	    this.operatorId=operatorId;	}	public String getOperatorName() {	    return this.operatorName;	}	public void setOperatorName(String operatorName) {	    this.operatorName=operatorName;	}	public Integer getOperaType() {	    return this.operaType;	}	public void setOperaType(Integer operaType) {	    this.operaType=operaType;	}	public Long getOperaTime() {	    return this.operaTime;	}	public void setOperaTime(Long operaTime) {	    this.operaTime=operaTime;	}	public String toString() {		return ToStringBuilder.reflectionToString(this);	}
}