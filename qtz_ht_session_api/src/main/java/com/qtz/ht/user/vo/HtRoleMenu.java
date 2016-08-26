package com.qtz.ht.user.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtRoleMenu</p>
 * <p>Description:商户角色菜单VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
public class HtRoleMenu extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1506688409700352L;

		/** 角色ID */	private Long roleId;	/** 菜单ID */	private Long menuId;	/** 创建人 */	private String crname;	/** 创建时间 */	private Long crtime;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getRoleId() {	    return this.roleId;	}	public void setRoleId(Long roleId) {	    this.roleId=roleId;	}	public Long getMenuId() {	    return this.menuId;	}	public void setMenuId(Long menuId) {	    this.menuId=menuId;	}	public String getCrname() {	    return this.crname;	}	public void setCrname(String crname) {	    this.crname=crname;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}