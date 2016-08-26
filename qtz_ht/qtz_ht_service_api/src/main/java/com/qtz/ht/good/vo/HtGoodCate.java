package com.qtz.ht.good.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtGoodCate</p>
 * <p>Description:商户商品分类VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-11
 */
public class HtGoodCate extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1615750346164224L;

		/** 分类名字 */	private String cataName;	/** 分类描述 */	private String cataDescribe;	/** 父级ID */	private Long pid;	/** 排序 */	private Integer sort;	/** 图标 */	private String icon;	/** 级别 */	private Integer cataLevel;	/** 1正常 2屏蔽 */	private Integer status;	/** 创建人ID */	private Long cruser;	/** 创建时间 */	private Long crtime;
		public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getCataName() {	    return this.cataName;	}	public void setCataName(String cataName) {	    this.cataName=cataName;	}	public String getCataDescribe() {	    return this.cataDescribe;	}	public void setCataDescribe(String cataDescribe) {	    this.cataDescribe=cataDescribe;	}	public Long getPid() {	    return this.pid;	}	public void setPid(Long pid) {	    this.pid=pid;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public String getIcon() {	    return this.icon;	}	public void setIcon(String icon) {	    this.icon=icon;	}	public Integer getCataLevel() {	    return this.cataLevel;	}	public void setCataLevel(Integer cataLevel) {	    this.cataLevel=cataLevel;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public Long getCruser() {	    return this.cruser;	}	public void setCruser(Long cruser) {	    this.cruser=cruser;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}