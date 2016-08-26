package com.qtz.ht.manage.model.request.user;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Length;

import com.mall.core.common.StringLengthConstant;

/**
 * 
 * <p>Title:RequestUserModel</p>
 * <p>Description:(请求用户模型)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年7月23日
 */
public class RequestUserPersonalModel extends RequestBaseUserModel{
		
	
	/**
	 * xingbie
	 */
	@DecimalMax(value="1")
	@DecimalMin(value="0")
	private Integer sex;
	
	
	//private String enterAddress;
	/**
	 * 出生日期
	 */
	private Long dob;
	/**
	 *公司 
	 */
	private String company;
	/**
	 * 城市
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
	  * 学校
	  */
	 private String graduateSchool;
	 /**
	  * 职业
	  */
	 private String professional;
	 
	 /**
	  * 更新地区标示
	  */
	 private Integer regionMark;
	 
	 
	 @Length(max=StringLengthConstant.userpersonageSignatLength)
	 private String signat;
	 
	 
	 
	 
	 
	 
	 
	 
	 
	public Integer getRegionMark() {
		return regionMark;
	}

	public void setRegionMark(Integer regionMark) {
		this.regionMark = regionMark;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getSignat() {
		return signat;
	}

	public void setSignat(String signat) {
		this.signat = signat;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public Long getDob() {
		return dob;
	}

	public void setDob(Long dob) {
		this.dob = dob;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	 
	 
}
