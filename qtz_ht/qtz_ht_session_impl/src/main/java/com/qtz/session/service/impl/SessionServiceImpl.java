package com.qtz.session.service.impl;

import org.springframework.stereotype.Service;

import com.mall.core.common.Constants;
import com.mall.core.common.RedisUtils;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.DdmSession;
import com.qtz.session.service.SessionService;


/**
 * session 管理器
 * <p>Title:SessionManager</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2014-12-6
 */
@Service("sessionServiceImpl")
public class SessionServiceImpl implements SessionService {
	  
	private static LogTool log = LogTool.getInstance(SessionServiceImpl.class);
	/** redis中的session分组 */
	public static final String SESSION = "ht_session";
	
	/**(普通时间 SESSION 过期时间   5分钟)*/
	public static final long NOMAL_SESSION_TIMEOUT= 5 * 60 * 1000;
	
	 /**
	 *  用户 SESSION 过期时间   24小时
	 */
	public static final long USER_SESSION_TIMEOUT= 24 * 60 * 60 * 1000;
	/** 
	* 【获取session,如果不存在则创建一个新session】(这里用一句话描述这个方法的作用)
	* @param sid
	* @return
	* @throws Exception  
	*/
	public DdmSession getSession(String sid) {
		DdmSession s = null;
		if(null == sid || sid.length() == 0){
			s = new DdmSession();
		}else{
			try {
				Object obj = RedisUtils.getValue(sid,SESSION);
				if (obj instanceof DdmSession) {
					s = (DdmSession)obj;
				}
			} catch (ServiceException e) {
				log.error("",e);
			}
		}
		return s;
	}
	
	/** 
	* 【新建一个session】(这里用一句话描述这个方法的作用)
	* @return
	* @throws Exception  
	*/
	public DdmSession newSession() {
		DdmSession s = new DdmSession();
		try {
			RedisUtils.setValue(s.getId(), SESSION, s, NOMAL_SESSION_TIMEOUT);//session5分钟失效
		} catch (ServiceException e) {
			log.error("",e);
		}
		return s;
	}
	
	/** 
	* 【删除一个session】(这里用一句话描述这个方法的作用)
	* @param sid
	* @return
	* @throws Exception  
	*/
	public boolean removeSession(String sid) {
		try {
			RedisUtils.removeValue(sid,SESSION);
			return true;
		} catch (ServiceException e) {
			log.error("",e);
		}
		return false;
	}

	public void saveSession(DdmSession s) {
		try {
			if(null != s && null != s.getId()){
				long timeout = USER_SESSION_TIMEOUT;//
				if(null == s || null == s.getSessionObject(Constants.SESSION_USER)){
					timeout = NOMAL_SESSION_TIMEOUT ;//未登录,5分钟session
				}
				RedisUtils.setValue(s.getId(), SESSION, s, timeout);
			}
		} catch (ServiceException e) {
			log.error("",e);
		}
	}
}