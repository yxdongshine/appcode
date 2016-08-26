package com.qtz.ht.user.page;


import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.user.vo.HtRole;
/**
 * <p>Title:HtRolePage</p>
 * <p>Description:商户角色分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
public class HtRolePage extends Pager<HtRole,Long> implements Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1506688407422977L;

		/** 主键 */	private Long dmId;	/** 角色名称 */	private String name;	/** 排序字段 */	private Integer sort;	/** 0 启用 1禁用 */	private Integer status;	/** 创建时间 */	private Long crtime;	/** 创建人 */	private String crname;	/** 用户类型 */	private Integer userType;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String getCrname() {	    return this.crname;	}	public void setCrname(String crname) {	    this.crname=crname;	}	public Integer getUserType() {	    return this.userType;	}	public void setUserType(Integer userType) {	    this.userType=userType;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}