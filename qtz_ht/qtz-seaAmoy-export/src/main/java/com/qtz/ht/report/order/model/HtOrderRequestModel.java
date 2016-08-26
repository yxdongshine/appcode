
package com.qtz.ht.report.order.model;

import com.alibaba.fastjson.serializer.SerializeFilter;

/** 
 * ClassName:HtOrderRequestModel <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年7月11日 下午3:37:52 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class HtOrderRequestModel implements SerializeFilter{
	/**
	 * 商家编号
	 */
	private Long busiId;
	/**
	 * 订单状态
	 */
    private Integer status;
    
    /**
     * 收货人地址
     */
    private String detailAddr;
    
    /**
     * 订单编号
     */
    private Long dmId;
    
    /**
     * 收货人姓名
     */
    private String consignee;
    
    /**
     * 收货人手机号码
     */
    private String mphoneNo;
    /**
     * 下单开始时间
     */
    private Long startCrtime;
    
    /**
     * 下单结束时间
     */
    private Long endCrtime;

	public Long getBusiId() {
		return busiId;
	}

	public void setBusiId(Long busiId) {
		this.busiId = busiId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getMphoneNo() {
		return mphoneNo;
	}

	public void setMphoneNo(String mphoneNo) {
		this.mphoneNo = mphoneNo;
	}

	public Long getStartCrtime() {
		return startCrtime;
	}

	public void setStartCrtime(Long startCrtime) {
		this.startCrtime = startCrtime;
	}

	public Long getEndCrtime() {
		return endCrtime;
	}

	public void setEndCrtime(Long endCrtime) {
		this.endCrtime = endCrtime;
	}
    
    
    
}
  