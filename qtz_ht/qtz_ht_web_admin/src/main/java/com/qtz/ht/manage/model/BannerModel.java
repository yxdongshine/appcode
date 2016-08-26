package com.qtz.ht.manage.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class BannerModel implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 4853043456585248168L;
	@Min(value=0,message="不能小于0" ,groups={First.class})
	@Max(value=1000000000000000000l,message="主键过大" ,groups={First.class})
	@NotNull(message="dmId不能为空" ,groups={First.class})
	private Long dmId;
	/** 链接地址 */
	//@NotBlank(message="链接地址不能为空",groups={First.class,Second.class})
	@URL(message="链接地址不正确",groups={First.class,Second.class})
	@Length(max=100,message="链接地址长度100",groups={First.class,Second.class})
	private String linkUrl;
	/** 排序 */
	@NotNull(message="排序不能为空",groups={First.class,Second.class})
	private Integer sort;
	/** 状态(0：启用，1：禁用) */
	@NotNull(message="排序不能为空",groups={First.class,Second.class})
	private Integer status;
	/** 图标 */
	@NotBlank(message="图标地址不能为空",groups={First.class,Second.class})
	@URL(message="图标地址不正确",groups={First.class,Second.class})
	@Length(max=100,message="图标地址长度100",groups={First.class,Second.class})
	private String icon;
	/** 版本类型 */
	@NotNull(message="排序不能为空",groups={First.class,Second.class})
	private Integer versionType;
	/** 客户端类型 */
	@NotNull(message="排序不能为空",groups={First.class,Second.class})
	private Integer clientType;
	/** 文字提示 */
	@Length(max=50,message="文字提示50字以内",groups={First.class,Second.class})
	private String promptText;
	/** 备注 */
	@Length(max=100,message="备注100字以内",groups={First.class,Second.class})
	private String notes;
	
	public Long getDmId() {
	    return this.dmId;
	}
	public void setDmId(Long dmId) {
	    this.dmId=dmId;
	}
	public String getLinkUrl() {
	    return this.linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
	    this.linkUrl=linkUrl;
	}
	public Integer getSort() {
	    return this.sort;
	}
	public void setSort(Integer sort) {
	    this.sort=sort;
	}
	public Integer getStatus() {
	    return this.status;
	}
	public void setStatus(Integer status) {
	    this.status=status;
	}
	public String getIcon() {
	    return this.icon;
	}
	public void setIcon(String icon) {
	    this.icon=icon;
	}
	public Integer getVersionType() {
	    return this.versionType;
	}
	public void setVersionType(Integer versionType) {
	    this.versionType=versionType;
	}
	public Integer getClientType() {
	    return this.clientType;
	}
	public void setClientType(Integer clientType) {
	    this.clientType=clientType;
	}
	public String getPromptText() {
	    return this.promptText;
	}
	public void setPromptText(String promptText) {
	    this.promptText=promptText;
	}
	public String getNotes() {
	    return this.notes;
	}
	public void setNotes(String notes) {
	    this.notes=notes;
	}
}
