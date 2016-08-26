package com.qtz.ht.manage.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.mall.core.common.RegexUtil;

public class StaffModel implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = -79889800619670980L;
	@Min(value=0,message="不能小于0")
	@Max(value=1000000000000000000l,message="主键过大")
	private Long dmId;
	/** 员工名称*/
	@NotBlank(message="员工名称不能为空")
	@Length(min=2,max=30,message="员工名称2-30之间")
	private String name;
	/** 手机 */
	@NotBlank(message="手机不能为空")
	@Pattern(regexp = RegexUtil.MOBILE,message = "手机号格式错误")
	private String mphone;
	/** 账号 */
	@NotBlank(message="账号不能为空")
	@Length(min=2,max=20,message="账号2-20之间")
	private String account;
	/** 性别 */
	@NotNull(message="性别不能为空")
	@Max(value=3,message="性别最大3")
	@Min(value=0,message="性别最小0")
	private Integer sex;
	/** 在职状态 */
	@NotNull(message="在职状态不能为空")
	@Max(value=3,message="在职状态最大3")
	@Min(value=0,message="在职状态最小0")
	private Integer jobOn;
	/** 入职时间 */
	@NotNull(message="入职时间不能为空")
	private Long entryTime;
	/** 屏蔽状态 */
	@NotNull(message="屏蔽状态不能为空")
	@Max(value=3,message="屏蔽状态最大3")
	@Min(value=0,message="屏蔽状态最小0")
	private Integer status;
	/** 个人简介 */
	@Length(min=0,max=200, message="个人简介1-200之间")
	private String persionTxt;
	/** 身份证号 */
	@Pattern(regexp=RegexUtil.IDCARD,message="身份证号码格式错误")
	private String idCard;
	
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getJobOn() {
		return jobOn;
	}
	public void setJobOn(Integer jobOn) {
		this.jobOn = jobOn;
	}
	public Long getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Long entryTime) {
		this.entryTime = entryTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPersionTxt() {
		return persionTxt;
	}
	public void setPersionTxt(String persionTxt) {
		this.persionTxt = persionTxt;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}
