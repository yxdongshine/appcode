package com.qtz.session.model;

import java.io.Serializable;

/**
 * 用户模型
 * <p>Title:UserModel</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2015-1-6
 */
public class UserModel implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = -7942010636902911455L;

	private Integer userType = null;
	private Long userId = null;
	private String userName = null;
	private String sessionKey = null;
	
	public UserModel() {
		super();
	}

	public UserModel(Integer userType, Long userId, String userName,String sessionKey) {
		super();
		this.userType = userType;
		this.userId = userId;
		this.userName = userName;
		this.sessionKey = sessionKey;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}