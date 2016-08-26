package com.qtz.ht.supplier.model.request.user;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * 
 * <p>Title:UserReceivingInfoRequestModel</p>
 * <p>Description:(用户收货地址请求模型)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年8月31日
 */
public class UserReceivingInfoRequestModel {
	private Long dmId;
	
	/**
	 * 收货人性别
	 */
	@DecimalMax(value="1")
	@DecimalMin(value="0")
	private Integer sex;
	/**
	 * 用户手机号码
	 */
	private String phone;
	/**
	 * 收货人名字
	 */
	private String name;
	/**
	 * 收货人地址
	 */
	private String address;
	
	/**
	 * 门牌号
	 */
	private String houseNumber;
	
	/**
	 * 是否默认
	 */
	@DecimalMax(value="1")
	@DecimalMin(value="1")
	private Integer isDefault;
	
	
	
	
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	

}
