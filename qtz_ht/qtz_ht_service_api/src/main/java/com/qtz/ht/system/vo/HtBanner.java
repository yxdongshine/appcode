package com.qtz.ht.system.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtBanner</p>
 * <p>Description:横幅管理表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-15
 */
public class HtBanner extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1621160682211328L;

		/** 链接地址 */	private String linkUrl;	/** 排序 */	private Integer sort;	/** 状态(1：启用，2：禁用) */	private Integer status;	/** 图标 */	private String icon;	/** 版本类型  1：商家，2：个人*/	private Integer versionType;	/** 客户端类型(1：安卓,2：IOS,3:所有) */	private Integer clientType;	/** 文字提示 */	private String promptText;	/** 备注 */	private String notes;	/** 创建人ID */	private Long crUserId;	/** 创建时间 */	private Long crtime;
		public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getLinkUrl() {	    return this.linkUrl;	}	public void setLinkUrl(String linkUrl) {	    this.linkUrl=linkUrl;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public String getIcon() {	    return this.icon;	}	public void setIcon(String icon) {	    this.icon=icon;	}	public Integer getVersionType() {	    return this.versionType;	}	public void setVersionType(Integer versionType) {	    this.versionType=versionType;	}	public Integer getClientType() {	    return this.clientType;	}	public void setClientType(Integer clientType) {	    this.clientType=clientType;	}	public String getPromptText() {	    return this.promptText;	}	public void setPromptText(String promptText) {	    this.promptText=promptText;	}	public String getNotes() {	    return this.notes;	}	public void setNotes(String notes) {	    this.notes=notes;	}	public Long getCrUserId() {	    return this.crUserId;	}	public void setCrUserId(Long crUserId) {	    this.crUserId=crUserId;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}