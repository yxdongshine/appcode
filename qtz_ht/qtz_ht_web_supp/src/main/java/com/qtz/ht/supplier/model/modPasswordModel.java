package com.qtz.ht.supplier.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.mall.core.common.RegexUtil;

public class modPasswordModel implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = -79889800619670980L;

	/** 手机 */
	@NotNull(message="手机不能为空")
	@Pattern(regexp = RegexUtil.MOBILE,message = "手机号格式错误")
	private String mphone;
	/** 验证码 */
	@NotNull(message="验证码不能为空")
	@Length(min=6,max=6,message="验证码为6位")
	private String verificationCode;
	/**  */
	@NotNull(message="确认密码不能为空")
	private String confirmPassword;
	/**  */
	@NotNull(message="新密码不能为空")
	private String newPassword;
	
	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
