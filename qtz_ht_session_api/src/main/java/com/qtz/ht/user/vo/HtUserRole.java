package com.qtz.ht.user.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtUserRole</p>
 * <p>Description:商户用户与角色关联VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
public class HtUserRole extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1506688405342208L;
		/** 员工ID */	private Long staffId;	/** 角色ID */	private Long roleid;	/** 创建人ID */	private Long crUserId;	/** 创建人 */	private String crname;	/** 创建时间 */	private Long crtime;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getStaffId() {	    return this.staffId;	}	public void setStaffId(Long staffId) {	    this.staffId=staffId;	}	public Long getRoleid() {	    return this.roleid;	}	public void setRoleid(Long roleid) {	    this.roleid=roleid;	}	public Long getCrUserId() {	    return this.crUserId;	}	public void setCrUserId(Long crUserId) {	    this.crUserId=crUserId;	}	public String getCrname() {	    return this.crname;	}	public void setCrname(String crname) {	    this.crname=crname;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}