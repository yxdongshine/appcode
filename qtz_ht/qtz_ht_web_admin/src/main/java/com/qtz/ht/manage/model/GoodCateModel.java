package com.qtz.ht.manage.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class GoodCateModel implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 4853043456585248168L;
	@Min(value=0,message="不能小于0")
	@Max(value=1000000000000000000l,message="主键过大")
	private Long dmId;
	/** 分类名称*/
	@NotBlank(message="分类名称不能为空")
	@Length(min=2,max=20,message="分类名称2-20之间")
	private String cataName;
	/** 父级id */
	@Min(value=0,message="不能小于0")
	@Max(value=1000000000000000000l,message="父级id过大")
	private Long pid = 0l;
	/** 排序 */
	@Max(value=1000,message="排序最大1000")
	@Min(value=0,message="排序最小0")
	private Integer sort = 99;
	/** 分类图标 */
	@URL(message="分类图标格式错误")
	@Length(min=2,max=100,message="分类图标地址长度在2-100之间")
	private String icon;
	/** 是否显示  1正常 2屏蔽*/
	@Max(value=2,message="分类级别最大2")
	@Min(value=1,message="分类级别最小1")
	private Integer status = 1;
	/** 分类级别 */
	@Max(value=3,message="分类级别最大4")
	@Min(value=1,message="分类级别最小1")
	private Integer cataLevel = 1;
	/** 分类描述 */
	@NotBlank(message="分类名称不能为空")
	@Length(min=2,max=100,message="分类名称2-100之间")
	private String cataDescribe;
	
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	public String getCataName() {
		return cataName;
	}
	public void setCataName(String cataName) {
		this.cataName = cataName;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCataLevel() {
		return cataLevel;
	}
	public void setCataLevel(Integer cataLevel) {
		this.cataLevel = cataLevel;
	}
	public String getCataDescribe() {
		return cataDescribe;
	}
	public void setCataDescribe(String cataDescribe) {
		this.cataDescribe = cataDescribe;
	}
}
