package com.qtz.ht.wallet.page;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.wallet.vo.HtWallet;
/**
 * <p>Title:HtWalletPage</p>
 * <p>Description:钱包表分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 唐礼军 - 1309025893@qq.com
 * @version v1.0 2016-05-20
 */
public class HtWalletPage extends Pager<HtWallet,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1670773073643521L;

	
	/**  */
	private Long dmId;
	/** 信息ID */
	private Long busiId;
	/** 对账中总额 */
	private Double totalReconciliation;
	/** 货款总额 */
	private Double totalPaymentGoods;
	/** 提现总额 */
	private Double totalCash;
	/** 退款总额 */
	private Double totalRefund;
	/** 支付密码 */
	private String payPwd;
	/** 用户类型 */
	private Integer userType;
	/** 创建人 */
	private Long createBy;
	/** 创建时间 */
	private Long createOn;
	/** 更新人 */
	private Long updateBy;
	/** 更新时间 */
	private Long updateOn;
	/** 开始时间 */
	private Long startTime;
	/** 结束时间 */
	private Long endTime;
	/** 手机号 */
	private String mobilePhone;
	/**
	 * 供应商名称
	 */
	private String busiName;
	
	
	public String getBusiName() {
		return busiName;
	}
	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}
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
	public Double getTotalReconciliation() {
	    return this.totalReconciliation;
	}
	public void setTotalReconciliation(Double totalReconciliation) {
	    this.totalReconciliation=totalReconciliation;
	}
	public Double getTotalPaymentGoods() {
	    return this.totalPaymentGoods;
	}
	public void setTotalPaymentGoods(Double totalPaymentGoods) {
	    this.totalPaymentGoods=totalPaymentGoods;
	}
	public Double getTotalCash() {
	    return this.totalCash;
	}
	public void setTotalCash(Double totalCash) {
	    this.totalCash=totalCash;
	}
	public Double getTotalRefund() {
	    return this.totalRefund;
	}
	public void setTotalRefund(Double totalRefund) {
	    this.totalRefund=totalRefund;
	}
	public String getPayPwd() {
	    return this.payPwd;
	}
	public void setPayPwd(String payPwd) {
	    this.payPwd=payPwd;
	}
	public Integer getUserType() {
	    return this.userType;
	}
	public void setUserType(Integer userType) {
	    this.userType=userType;
	}
	public Long getCreateBy() {
	    return this.createBy;
	}
	public void setCreateBy(Long createBy) {
	    this.createBy=createBy;
	}
	public Long getCreateOn() {
	    return this.createOn;
	}
	public void setCreateOn(Long createOn) {
	    this.createOn=createOn;
	}
	public Long getUpdateBy() {
	    return this.updateBy;
	}
	public void setUpdateBy(Long updateBy) {
	    this.updateBy=updateBy;
	}
	public Long getUpdateOn() {
	    return this.updateOn;
	}
	public void setUpdateOn(Long updateOn) {
	    this.updateOn=updateOn;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}