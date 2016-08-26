package com.qtz_ht_timer.task.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:HtWallet</p>
 * <p>Description:钱包表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 唐礼军 - 1309025893@qq.com
 * @version v1.0 2016-05-20
 */
public class HtWallet extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1670773073643520L;

	
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
	/** 非数据库字段 未付货款 */
	private Double unpaidPayment;
	/** 非数据库字段 已付货款 */
	private Double paidPayment;
	/** 非数据库字段 供应商头像 */
	private String trademark;
	/** 非数据库字段 供应商简称 */
	private String simpleName;
	
	public HtWallet() {
		super();
	}
	
	public HtWallet(Long busiId, Double totalReconciliation, Double totalPaymentGoods, Double totalCash,
			Double totalRefund, String payPwd, Integer userType, Long createBy, Long createOn, Long updateBy,
			Long updateOn) {
		super();
		this.busiId = busiId;
		this.totalReconciliation = totalReconciliation;
		this.totalPaymentGoods = totalPaymentGoods;
		this.totalCash = totalCash;
		this.totalRefund = totalRefund;
		this.payPwd = payPwd;
		this.userType = userType;
		this.createBy = createBy;
		this.createOn = createOn;
		this.updateBy = updateBy;
		this.updateOn = updateOn;
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
	
	public Double getUnpaidPayment() {
		return unpaidPayment;
	}
	
	public void setUnpaidPayment(Double unpaidPayment) {
		this.unpaidPayment = unpaidPayment;
	}
	
	public Double getPaidPayment() {
		return paidPayment;
	}
	
	public void setPaidPayment(Double paidPayment) {
		this.paidPayment = paidPayment;
	}
	public String toString() {
	    return "[" + "dmId:" + getDmId() +"," + "busiId:" + getBusiId() +"," + "totalReconciliation:" + getTotalReconciliation() +"," + "totalPaymentGoods:" + getTotalPaymentGoods() +"," + "totalCash:" + getTotalCash() +"," + "totalRefund:" + getTotalRefund() +"," + "payPwd:" + getPayPwd() +"," + "userType:" + getUserType() +"," + "createBy:" + getCreateBy() +"," + "createOn:" + getCreateOn() +"," + "updateBy:" + getUpdateBy() +"," + "updateOn:" + getUpdateOn() +"]";
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
}