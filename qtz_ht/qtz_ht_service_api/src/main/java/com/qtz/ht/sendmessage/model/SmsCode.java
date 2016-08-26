package com.qtz.ht.sendmessage.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;

/**
 * 
 * <p>Title:SmsCode</p>
 * <p>Description:短信验证码</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015年3月11日
 */
public class SmsCode extends VO<Long> implements java.io.Serializable{
	
	private static final long serialVersionUID = 9033764795706479467L;

	private String mphone; //发送手机号
	
	private String code;//发送的验证码
	
	private long sendTime;//发送时间

	
	
	public SmsCode() {
		super();
	}

	
	
	public SmsCode(String mphone, String code) {
		super();
		this.mphone = mphone;
		this.code = code;
		this.sendTime = new Date().getTime();
	}

	/**
	 * 
	* 【验证验证码是否过期】(这里用一句话描述这个方法的作用)
	* @return
	 */
	public boolean isTrue(){
		
		if(0l == this.sendTime) return false;
		
		
		
		return true;
	}
	

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
