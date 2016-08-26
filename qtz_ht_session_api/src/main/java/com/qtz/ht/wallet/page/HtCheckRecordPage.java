package com.qtz.ht.wallet.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.wallet.vo.HtCheckRecord;
/**
 * <p>Title:HtCheckRecordPage</p>
 * <p>Description:商户提现记录表分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-29
 */
public class HtCheckRecordPage extends Pager<HtCheckRecord,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1641145257101313L;
	
	private Long dmId;
	
	/** 信息ID */
	private Long busiId;
	/** 申请日期 */
	private Long applytime;
	/**
	 * 申请结束时间
	 */
	private Long applyEndTime;
	/** 提现用户 */
	private Long userid;
	/** 提现金额 */
	private Double amount;
	/**
	 * 最大的提现金额
	 */
	private Double maxAmount;
	/**
	 * 模糊查询多个条件
	 */
	private String someInfo;
	
	/** 收款银行 */
	private String bankname;
	/** 收款卡号 */
	private String bankno;
	/** 持卡人名字 */
	private String cardholdername;
	/** 状态(0：表示对账中，1：表示已提现，2：表示驳回，3：表示取消) */
	private Integer status;
	/** 源单ID */
	private Long sourceId;
	/** 创建人 */
	private Long createby;
	/** 创建时间 */
	private Long createon;
	/** 处理人 */
	private Long dealby;
	/** 处理时间 */
	private Long dealon;
	/** 处理备注 */
	private String dealremark;
	/** 更新人 */
	private Long updateby;
	/** 更新时间 */
	private Long updateon;
	/** 解冻时间 */
	private Long releasedate;
	
	public Long getDmId() {
	    return this.dmId;
	}
	public void setDmId(Long dmId) {
	    this.dmId=dmId;
	}
	public Long getBusiId() {
	    return this.busiId;
	}
	public void setBusiId(Long busiId) {
	    this.busiId=busiId;
	}
	public Long getApplytime() {
	    return this.applytime;
	}
	public void setApplytime(Long applytime) {
	    this.applytime=applytime;
	}
	public Long getUserid() {
	    return this.userid;
	}
	public void setUserid(Long userid) {
	    this.userid=userid;
	}
	public Double getAmount() {
	    return this.amount;
	}
	public void setAmount(Double amount) {
	    this.amount=amount;
	}
	public String getBankname() {
	    return this.bankname;
	}
	public void setBankname(String bankname) {
	    this.bankname=bankname;
	}
	
	
	public String getBankno() {
	    return this.bankno;
	}
	public void setBankno(String bankno) {
	    this.bankno=bankno;
	}
	public String getCardholdername() {
	    return this.cardholdername;
	}
	public void setCardholdername(String cardholdername) {
	    this.cardholdername=cardholdername;
	}
	public Integer getStatus() {
	    return this.status;
	}
	public void setStatus(Integer status) {
	    this.status=status;
	}
	public Long getSourceId() {
	    return this.sourceId;
	}
	public void setSourceId(Long sourceId) {
	    this.sourceId=sourceId;
	}
	public Long getCreateby() {
	    return this.createby;
	}
	public void setCreateby(Long createby) {
	    this.createby=createby;
	}
	public Long getCreateon() {
	    return this.createon;
	}
	public void setCreateon(Long createon) {
	    this.createon=createon;
	}
	public Long getDealby() {
	    return this.dealby;
	}
	public void setDealby(Long dealby) {
	    this.dealby=dealby;
	}
	public Long getDealon() {
	    return this.dealon;
	}
	public void setDealon(Long dealon) {
	    this.dealon=dealon;
	}
	public String getDealremark() {
	    return this.dealremark;
	}
	public void setDealremark(String dealremark) {
	    this.dealremark=dealremark;
	}
	public Long getUpdateby() {
	    return this.updateby;
	}
	public void setUpdateby(Long updateby) {
	    this.updateby=updateby;
	}
	public Long getUpdateon() {
	    return this.updateon;
	}
	public void setUpdateon(Long updateon) {
	    this.updateon=updateon;
	}
	public Long getReleasedate() {
	    return this.releasedate;
	}
	public void setReleasedate(Long releasedate) {
	    this.releasedate=releasedate;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Long getApplyEndTime() {
		return applyEndTime;
	}
	public void setApplyEndTime(Long applyEndTime) {
		this.applyEndTime = applyEndTime;
	}
	public Double getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}
	public String getSomeInfo() {
		return someInfo;
	}
	public void setSomeInfo(String someInfo) {
		this.someInfo = someInfo;
	}
	
}