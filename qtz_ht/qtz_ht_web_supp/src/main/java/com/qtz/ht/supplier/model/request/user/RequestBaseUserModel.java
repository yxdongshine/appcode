package com.qtz.ht.supplier.model.request.user;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.mall.core.common.RegexUtil;
import com.mall.core.common.StringLengthConstant;
import com.qtz.dm.user.vo.Local;

/**
 * 
 * <p>Title:RequestBaseUserModel</p>
 * <p>Description:(公共属性)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年7月24日
 */
public class RequestBaseUserModel {
	
	private String headImg;
	@Length(max=StringLengthConstant.nickNameLength)
	private String nickName;
	@Pattern(regexp=RegexUtil.str_eng_num_length,message="账户格式错误")
	private String account;
	
	protected String signat;
	
	@Valid
	private Local loc;
	
	
	private String invitationCode;
	/**
	 * 联系方式
	 */
	@Length(max=StringLengthConstant.contactPhone)
	private String contactPhone;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSignat() {
		return signat;
	}

	public void setSignat(String signat) {
		this.signat = signat;
	}

	public Local getLoc() {
		return loc;
	}

	public void setLoc(Local loc) {
		this.loc = loc;
	}
	
	
}
