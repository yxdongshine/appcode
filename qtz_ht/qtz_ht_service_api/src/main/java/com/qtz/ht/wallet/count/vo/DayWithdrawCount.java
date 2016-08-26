package com.qtz.ht.wallet.count.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;

/**
 * <p>Title:DayWithdrawCount</p>
 * <p>Description:日提现额统计VO类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 聂恒
 * @version v1.0 2015-12-17
 */
public class DayWithdrawCount extends VO<Long> implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5978255292202727943L;
	
	/** 个人提现人数 */
	private Integer personNum;
	/** 个人提现额 */
	private Double personMoney;
	/** 商家提现人数 */
	private Integer sellerNum;
	/** 商家提现额 */
	private Double sellerMoney;
	/** 代理商提现人数 */
	private Integer agentNum;
	/** 代理商提现额 */
	private Double agentMoney;
	/** 统计时间 */
	private Long staTime;
	@Override
	public Long getDmId() {
	    return this.dmId;
	}
	@Override
	public void setDmId(Long dmId) {
	    this.dmId=dmId;
	}
	public Integer getPersonNum() {
		return personNum;
	}
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	public Double getPersonMoney() {
		return personMoney;
	}
	public void setPersonMoney(Double personMoney) {
		this.personMoney = personMoney;
	}
	public Integer getSellerNum() {
		return sellerNum;
	}
	public void setSellerNum(Integer sellerNum) {
		this.sellerNum = sellerNum;
	}
	public Double getSellerMoney() {
		return sellerMoney;
	}
	public void setSellerMoney(Double sellerMoney) {
		this.sellerMoney = sellerMoney;
	}
	public Integer getAgentNum() {
		return agentNum;
	}
	public void setAgentNum(Integer agentNum) {
		this.agentNum = agentNum;
	}
	public Double getAgentMoney() {
		return agentMoney;
	}
	public void setAgentMoney(Double agentMoney) {
		this.agentMoney = agentMoney;
	}
	public Long getStaTime() {
		return staTime;
	}
	public void setStaTime(Long staTime) {
		this.staTime = staTime;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
