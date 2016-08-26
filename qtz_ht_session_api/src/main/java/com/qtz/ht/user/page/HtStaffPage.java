package com.qtz.ht.user.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.user.vo.HtStaff;
/**
 * <p>Title:HtStaffPage</p>
 * <p>Description:海淘员工表分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
public class HtStaffPage extends Pager<HtStaff,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1609896795703297L;
	
	private Long dmId;
		/** 登录账号 */	private String account;	/** 登录密码 */	private String pwd;	/** 员工名字 */	private String name;	/** 手机号 */	private String mphone;	/** 员工性别  0  女  1 男 */	private Integer sex;	/** 座机 */	private String phone;	/** 身份证号 */	private String idCard;	/** 入职时间 */	private Long entryTime;	/** 部门 */	private Long department;	/** 职位 */	private Long position;	/** 个人简介 */	private String persionTxt;	/** 0  在职   1  离职 */	private Integer jobOn;	/** 录入员工 */	private Long crUser;	/** 0 正常 1 屏蔽 */	private Integer status;	/** 创建时间 */	private Long crtime;	/** 紧急联系人 */	private String contact;	/** 紧急联系电话 */	private String contactPhone;	/** 籍贯 */	private String placeOrigin;	/** 现居住地 */	private String currentResidence;	/** 最后登录时间 */	private Long lastLogin;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getAccount() {	    return this.account;	}	public void setAccount(String account) {	    this.account=account;	}	public String getPwd() {	    return this.pwd;	}	public void setPwd(String pwd) {	    this.pwd=pwd;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getMphone() {	    return this.mphone;	}	public void setMphone(String mphone) {	    this.mphone=mphone;	}	public Integer getSex() {	    return this.sex;	}	public void setSex(Integer sex) {	    this.sex=sex;	}	public String getPhone() {	    return this.phone;	}	public void setPhone(String phone) {	    this.phone=phone;	}	public String getIdCard() {	    return this.idCard;	}	public void setIdCard(String idCard) {	    this.idCard=idCard;	}	public Long getEntryTime() {	    return this.entryTime;	}	public void setEntryTime(Long entryTime) {	    this.entryTime=entryTime;	}	public Long getDepartment() {	    return this.department;	}	public void setDepartment(Long department) {	    this.department=department;	}	public Long getPosition() {	    return this.position;	}	public void setPosition(Long position) {	    this.position=position;	}	public String getPersionTxt() {	    return this.persionTxt;	}	public void setPersionTxt(String persionTxt) {	    this.persionTxt=persionTxt;	}	public Integer getJobOn() {	    return this.jobOn;	}	public void setJobOn(Integer jobOn) {	    this.jobOn=jobOn;	}	public Long getCrUser() {	    return this.crUser;	}	public void setCrUser(Long crUser) {	    this.crUser=crUser;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String getContact() {	    return this.contact;	}	public void setContact(String contact) {	    this.contact=contact;	}	public String getContactPhone() {	    return this.contactPhone;	}	public void setContactPhone(String contactPhone) {	    this.contactPhone=contactPhone;	}	public String getPlaceOrigin() {	    return this.placeOrigin;	}	public void setPlaceOrigin(String placeOrigin) {	    this.placeOrigin=placeOrigin;	}	public String getCurrentResidence() {	    return this.currentResidence;	}	public void setCurrentResidence(String currentResidence) {	    this.currentResidence=currentResidence;	}	public Long getLastLogin() {	    return this.lastLogin;	}	public void setLastLogin(Long lastLogin) {	    this.lastLogin=lastLogin;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}