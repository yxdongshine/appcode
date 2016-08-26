package com.qtz.ht.wallet.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtCheckRecord</p>
 * <p>Description:商户提现记录表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-29
 */
public class HtCheckRecord extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1641145257101312L;

	
	/** 信息ID */
	private Long busiId;
	/** 申请日期 */
	private Long applytime;
	/** 提现用户 */
	private Long userid;
	/** 提现金额 */
	private Double amount;
	/** 收款银行 */
	private String bankname;
	/** 支行名称 */
	private String bankBranch;
	/** 银行账户类型 0 对公 1对私 */
	private Integer accountType;
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
	
	
	public HtCheckRecord() {
		super();
	}
	public HtCheckRecord(Long dmId,Long busiId, Long applytime, Long userid, Double amount, String bankname,String bankBranch,
			 String bankno,Integer accountType,String cardholdername, Integer status, Long sourceId, Long createby,
			 Long createon, Long dealby, Long dealon, String dealremark, Long updateby, Long updateon, Long releasedate) {
		super();
		this.dmId = dmId;
		this.busiId = busiId;
		this.applytime = applytime;
		this.userid = userid;
		this.amount = amount;
		this.bankname = bankname;
		this.bankBranch = bankBranch;
		this.accountType = accountType;
		this.bankno = bankno;
		this.cardholdername = cardholdername;
		this.status = status;
		this.sourceId = sourceId;
		this.createby = createby;
		this.createon = createon;
		this.dealby = dealby;
		this.dealon = dealon;
		this.dealremark = dealremark;
		this.updateby = updateby;
		this.updateon = updateon;
		this.releasedate = releasedate;
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
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
}