package com.qtz.ht.user.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtMenu</p>
 * <p>Description:商户员工菜单VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
public class HtMenu extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1506688413304832L;

		/** 父ID  无父级默认为0 */	private Long pid;	/**  */	private String menuName;	/** 保留字段，页面地址 */	private String menuUrl;	/** 排序字段 */	private Integer menuSort;	/** 是否禁用（1启用 2禁用） */	private Integer isDisable;	/** 创建时间 */	private Long crtime;	/** 创建人 */	private String crname;	/** 级别 */	private Integer level;	/** 导航主页ID */	private Long id;	/** 导航页名称 */	private String pname;	/** 用户类型 */	private Integer userType;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getPid() {	    return this.pid;	}	public void setPid(Long pid) {	    this.pid=pid;	}	public String getMenuName() {	    return this.menuName;	}	public void setMenuName(String menuName) {	    this.menuName=menuName;	}	public String getMenuUrl() {	    return this.menuUrl;	}	public void setMenuUrl(String menuUrl) {	    this.menuUrl=menuUrl;	}	public Integer getMenuSort() {	    return this.menuSort;	}	public void setMenuSort(Integer menuSort) {	    this.menuSort=menuSort;	}	public Integer getIsDisable() {	    return this.isDisable;	}	public void setIsDisable(Integer isDisable) {	    this.isDisable=isDisable;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String getCrname() {	    return this.crname;	}	public void setCrname(String crname) {	    this.crname=crname;	}	public Integer getLevel() {	    return this.level;	}	public void setLevel(Integer level) {	    this.level=level;	}	public Long getId() {	    return this.id;	}	public void setId(Long id) {	    this.id=id;	}	public String getPname() {	    return this.pname;	}	public void setPname(String pname) {	    this.pname=pname;	}	public Integer getUserType() {	    return this.userType;	}	public void setUserType(Integer userType) {	    this.userType=userType;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}