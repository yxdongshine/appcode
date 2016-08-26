package com.qtz.session.service.impl;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.mall.core.common.UUIDFactory;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;


/**
 * session模型
 * 注意：当Session状态改变时需要立刻保存至缓存
 * <p>Title:Session</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2014-12-6
 */
public class Session implements java.io.Serializable {
	
	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 1650817916835784512l;

	private static LogTool log = LogTool.getInstance(Session.class);

	/**创建时间*/
	private long creationTime;
	/**session id */
	private String id;
	/**最后一次操作时间*/
	private long lastAccessedTime;
	/**session中参数集合*/
	private Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();
	/**用户ID*/
	private Long userid;
	/**用户类型:1 个人账户2 商家账户  3 管理员*/
	private int userType;
	/**用户姓名*/
	private String userName;

	/**
	 * 构造函数 (需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})
	 */
	public Session(){/**初始化一个新session*/
		creationTime = System.currentTimeMillis();
		id = UUIDFactory.newUUID();
		lastAccessedTime = creationTime;
	}
	
	/**
	 * 构造函数 (需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})
	 */
	public Session(String id, Long userid, int userType) {
		super();
		this.id = id;
		this.userid = userid;
		this.userType = userType;
	}

	/** 
	* 【获取参数】(这里用一句话描述这个方法的作用)
	* @param key
	* @return  
	*/
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	/** 
	* 【获取参数名称集合】(这里用一句话描述这个方法的作用)
	* @return  
	*/
	public Enumeration<String> getAttributeNames() {
		Set<String> names = new HashSet<String>();
		names.addAll(attributes.keySet());
		return Collections.enumeration(names);
	}

	/** 
	* 【设置参数】(需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})
	* @param key
	* @param value  
	 * @throws Exception 
	*/
	public synchronized void setAttribute(String key, Object value) throws ServiceException {
		try {
			attributes.put(key, value);
		} catch (Exception e) {
			log.error("设置参数错误", e);
		}
	}

	/** 
	* 【删除参数】(需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})
	* @param key  
	*/
	public synchronized void removeAttribute(String key) {
		attributes.remove(key);
	}

	/** 
	* 【销毁所有参数】(需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})  
	*/
	public synchronized void invalidate() {
		attributes.clear();
		attributes = null;
		userid = null;
		userType = 0;
	}

	/** 
	* 【获得创建时间】(这里用一句话描述这个方法的作用)
	* @return  
	*/
	public long getCreationTime() {
		return creationTime;
	}

	/** 
	* 【获得session id】(这里用一句话描述这个方法的作用)
	* @return  
	*/
	public String getId() {
		return id;
	}

	/** 
	* 【获得最后一次提取 session 时间】(这里用一句话描述这个方法的作用)
	* @return  
	*/
	public long getLastAccessedTime() {
		return lastAccessedTime;
	}

	/** 
	* 【获得用户ID】(这里用一句话描述这个方法的作用)
	* @return  
	*/
	public Long getUserid() {
		return userid;
	}

	/** 
	* 【获得 用户类型】(这里用一句话描述这个方法的作用)
	* @return  
	*/
	public int getUserType() {
		return userType;
	}

	/** 
	* 【设置 用户ID】(需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})
	* @param userid
	* @throws Exception  
	*/
	public void setUserid(Long userid) {
		try {
			this.userid = userid;
		} catch (Exception e) {
			log.error("设置用户ID错误", e);
		}
	}

	/** 
	* 【设置用户类型】(需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})
	* @param userType
	* @throws Exception  
	*/
	public void setUserType(int userType) {
		try {
			this.userType = userType;
		} catch (Exception e) {
			log.error("设置用户类型错误", e);
		}
	}

	/** 
	* 【设置最后一次 提取 session 时间】(需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})
	* @param lastAccessedTime
	* @throws Exception  
	*/
	public void setLastAccessedTime(long lastAccessedTime) {
		try {
			this.lastAccessedTime = lastAccessedTime;
		} catch (Exception e) {
			log.error("设置最后一次操作时间错误", e);
		}
	}

	/** 
	* 【获得用户名】(这里用一句话描述这个方法的作用)
	* @return  
	*/
	public String getUserName() {
		return userName;
	}

	/** 
	* 【设置用户名】(需要手动调用保存session方法生效，{@link com.hsz.session.service.SessionService#saveSession})
	* @param userName  
	 * @throws Exception 
	*/
	public void setUserName(String userName) {
		try {
			this.userName = userName;
		} catch (Exception e) {
			log.error("设置用户ID错误", e);
		}
	}

	@Override
	public String toString() {
		return "Session [creationTime=" + creationTime + ", id=" + id
				+ ", lastAccessedTime=" + lastAccessedTime + ", attributes="
				+ attributes + ", userid=" + userid + ", userType=" + userType
				+ ", userName=" + userName + "]";
	}
}