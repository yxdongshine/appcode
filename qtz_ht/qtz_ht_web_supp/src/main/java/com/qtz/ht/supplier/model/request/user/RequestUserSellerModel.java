package com.qtz.ht.supplier.model.request.user;

import org.hibernate.validator.constraints.Length;

import com.mall.core.common.StringLengthConstant;

/**
 * 
 * <p>Title:RequestUserSellerModel</p>
 * <p>Description:(商家模型)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年7月24日
 */
public class RequestUserSellerModel extends RequestBaseUserModel{
	/**
	 * 标签id  或者  行业id
	 */
	private Long categoryId;
	
	/**
	 * 设备  分割
	 */
	private String equipmentNames;
	
	/**
	 * 省
	 */
	 private Long province_id;
	 /**
	  * 市
	  */
	 private Long city_id;
	 /**
	  * 镇
	  */
	 private Long county_id;
	 /**
	  * 折扣点
	  */
	 private String scale;
	 
	 //private Double 
	 /**
	  * 商家输入地址
	  */
	 @Length(max=130)
	 private String enterAddress;
	 
	 /**
	  * 服务时间
	  */
	 @Length(max=20)
	 private String serviceTime;
	 
	 /**
	  * 更新地区标示
	  */
	 private Integer regionMark;
	 
	 /**
	  * 行政区 - 分割
	  */
	 private String region;
	 
	 
	 
	public Integer getRegionMark() {
		return regionMark;
	}


	public void setRegionMark(Integer regionMark) {
		this.regionMark = regionMark;
	}

	

	public String getScale() {
		return scale;
	}


	public void setScale(String scale) {
		this.scale = scale;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public String getEquipmentNames() {
		return equipmentNames;
	}


	public void setEquipmentNames(String equipmentNames) {
		this.equipmentNames = equipmentNames;
	}


	public Long getProvince_id() {
		return province_id;
	}


	public void setProvince_id(Long province_id) {
		this.province_id = province_id;
	}


	public Long getCity_id() {
		return city_id;
	}


	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}


	public Long getCounty_id() {
		return county_id;
	}


	public void setCounty_id(Long county_id) {
		this.county_id = county_id;
	}


	public String getEnterAddress() {
		return enterAddress;
	}


	public void setEnterAddress(String enterAddress) {
		this.enterAddress = enterAddress;
	}


	public String getServiceTime() {
		return serviceTime;
	}


	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	@Length(max=StringLengthConstant.userBusinessSignatLength)
	public String getSignat() {
		return signat;
	}


	public void setSignat(String signat) {
		this.signat = signat;
	}
	 
	 
}
