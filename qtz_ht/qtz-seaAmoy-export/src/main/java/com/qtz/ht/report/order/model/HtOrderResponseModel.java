
package com.qtz.ht.report.order.model;


import java.util.Date;

import com.alibaba.fastjson.serializer.SerializeFilter;

/** 
 * 
 * 订单表 和 订单商品表联合内连接查询 返回的原始数据
 * ClassName:HtOrderResponseModel <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年7月11日 下午1:53:22 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class HtOrderResponseModel implements SerializeFilter{

	/**
	 * 订单编号 htorder 表
	 */
	private Long dmId;
	
	/**
	 * 商品规格  暂时没有
	 */
	private String goodsStandard;
	/**
	 * 订单状态 htorder表中
	 */
	private String status;
	
	/**
	 * 买家名称  htorder表中
	 */
	private String vipName;
	
	/**
	 * 商品名称  htordergoods表中
	 */
	private String goodsName;
	
	/**
	 * 商品成本价
	 */
	private Double finalPrice;
	/**
	 * 商品规格  htordergoods表中
	 */
//	private String goodSpec;
	
	/**
	 * 商品售价 htordergoods表中
	 */
	private Double saleMoney;
	
	/**
	 * 商品数量 htordergoods表中
	 */
	private Integer goodsNum;
	/**
	 * 订单金额
	 */
	private Double payMoney;
	/**
	 * orderFinalPrice 订单成本价格
	 */
	private Double orderFinalPrice;
	/**
	 * 订单收货人  htorder表中
	 */
	private String consignee;
	
	/**
	 * 详细地址(省市县)htorder表中
	 */
	private String detailAddr;
	/**
	 * 门牌号(街道字段)htorder表中
	 */
	private String houseNumber;
	/**
	 * 收货人手机号 htorder表中
	 */
	private String mphoneNo;
	
	/**
	 * 收货人身份ID htorder表中
	 */
	private String idCard;
	/**
	 * 收货人身份姓名 htorder表中
	 */
	private String idName;
	
	
	/**
	 * 隐藏身份姓名  htorder表中
	 */
//	private String hideIdCard;
	
	/**
	 * 订单创建时间 (也就是下单时间)htorder表中
	 */
	private Date crtime;
	
	/**
	 * 支付时间  htorder表中
	 */
	private Date payTime;
	
	/**
	 * 补贴金额
	 */
	private Double deductionPrice;
	/**
	 * 支付方式信息
	 */
	private String paymentWay;
	
	/**
	 * 供应商信息
	 */
	private String staffName;
	
	/**
	 *订单备注 备注  htorder表中
	 */
	private String noteVip;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	//	public String getGoodSpec() {
//		return goodSpec;
//	}
//	public void setGoodSpec(String goodSpec) {
//		this.goodSpec = goodSpec;
//	}
	public Double getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(Double saleMoney) {
		this.saleMoney = saleMoney;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	
	public Double getOrderFinalPrice() {
		return orderFinalPrice;
	}
	public void setOrderFinalPrice(Double orderFinalPrice) {
		this.orderFinalPrice = orderFinalPrice;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getDetailAddr() {
		return detailAddr;
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getMphoneNo() {
		return mphoneNo;
	}
	public void setMphoneNo(String mphoneNo) {
		this.mphoneNo = mphoneNo;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
//	public String getHideIdCard() {
//		return hideIdCard;
//	}
//	public void setHideIdCard(String hideIdCard) {
//		this.hideIdCard = hideIdCard;
//	}
	
	public Date getCrtime() {
		return crtime;
	}
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getNoteVip() {
		return noteVip;
	}
	public void setNoteVip(String noteVip) {
		this.noteVip = noteVip;
	}
	public String getGoodsStandard() {
		return goodsStandard;
	}
	public void setGoodsStandard(String goodsStandard) {
		this.goodsStandard = goodsStandard;
	}
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public Double getDeductionPrice() {
		return deductionPrice;
	}
	public void setDeductionPrice(Double deductionPrice) {
		this.deductionPrice = deductionPrice;
	}
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
}
  