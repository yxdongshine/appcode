package com.qtz.ht.report.order.entity;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtPlatformWalletLog</p>
 * <p>Description:平台钱包流水VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-29
 */
public class HtPlatformWalletLog extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1640964025796608L;

	
	/** 商户信息id */
	private Long busiId;
	/** 订单id */
	private Long orderId;
	/** 收入金额 */
	private Double amount;
	/** 收入类型(1:订单2：提现) */
	private Integer incomeType;
	/** 支付类型 */
	private Integer paymentType;
	/** 平台扣点 */
	private Double platformPoints;
	/** 入账时间 */
	private Long incomeTime;
	/** 解冻时间 */
	private Long releaseTime;
	/** 对账状态:0完成,1冻结中 */
	private Integer recoStatus;
	/** 操作时间 */
	private Long crtime;
	
	
	public HtPlatformWalletLog() {
		super();
	}
	public HtPlatformWalletLog(Long busiId, Long orderId, Double amount, Integer incomeType, Integer paymentType,
			Double platformPoints, Long incomeTime, Long releaseTime, Integer recoStatus, Long crtime,Long dmId) {
		super();
		this.busiId = busiId;
		this.orderId = orderId;
		this.amount = amount;
		this.incomeType = incomeType;
		this.paymentType = paymentType;
		this.platformPoints = platformPoints;
		this.incomeTime = incomeTime;
		this.releaseTime = releaseTime;
		this.recoStatus = recoStatus;
		this.crtime = crtime;
		this.dmId = dmId;
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
	public Long getOrderId() {
	    return this.orderId;
	}
	public void setOrderId(Long orderId) {
	    this.orderId=orderId;
	}
	public Double getAmount() {
	    return this.amount;
	}
	public void setAmount(Double amount) {
	    this.amount=amount;
	}
	public Integer getIncomeType() {
	    return this.incomeType;
	}
	public void setIncomeType(Integer incomeType) {
	    this.incomeType=incomeType;
	}
	public Integer getPaymentType() {
	    return this.paymentType;
	}
	public void setPaymentType(Integer paymentType) {
	    this.paymentType=paymentType;
	}
	public Double getPlatformPoints() {
	    return this.platformPoints;
	}
	public void setPlatformPoints(Double platformPoints) {
	    this.platformPoints=platformPoints;
	}
	public Long getIncomeTime() {
	    return this.incomeTime;
	}
	public void setIncomeTime(Long incomeTime) {
	    this.incomeTime=incomeTime;
	}
	public Long getReleaseTime() {
	    return this.releaseTime;
	}
	public void setReleaseTime(Long releaseTime) {
	    this.releaseTime=releaseTime;
	}
	public Integer getRecoStatus() {
	    return this.recoStatus;
	}
	public void setRecoStatus(Integer recoStatus) {
	    this.recoStatus=recoStatus;
	}
	public Long getCrtime() {
	    return this.crtime;
	}
	public void setCrtime(Long crtime) {
	    this.crtime=crtime;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}