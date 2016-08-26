package com.qtz.ht.user.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcException;
import com.mall.core.common.Constants;
import com.mall.core.common.DateUtil;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.DdmSession;
import com.qtz.ht.user.dao.HtStaffDao;
import com.qtz.ht.user.service.HtStaffService;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.session.service.SessionService;

/**
 * <p>Title:HtStaffServiceImpl</p>
 * <p>Description:海淘员工表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-06
 */
@Service("htStaffServiceImpl")
public class HtStaffServiceImpl extends BaseServiceImpl<HtStaff,Long> implements HtStaffService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtStaffServiceImpl.class);
	/**注入海淘员工表DAO接口类*/
	@Resource(name="htStaffDaoImpl")
    private HtStaffDao dao;
	@Resource(name="sessionServiceImpl")
	private SessionService sessionService;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtStaff,Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	
	public DdmSession login(String account,String password,DdmSession s) throws ServiceException,RpcException {
		log.debug(account + " 登录了 密码 = password；"+ password);
		if(null == account || null == password){
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		if(null != s.getSessionObject(Constants.SESSION_USER)){
			throw new ServiceException(ExceptionConstants.ERRORCODE_6, "当前session已经绑定登录用户");
		}
		DdmSession ns = s;
		HtStaff user = null;
//		if (UserUtil.isAdmin(account, password)) {//如果是管理员并且是超级管理员
//			user = new HtStaff();
//			user.setAccount(account);
//			user.setName("admin");
//			user.setPassword(password);
//			user.setDmId(HtStaff.ADMIN_WALLET_ID);//管理员默认ID
//			ns.save(Constants.SESSION_USER, user);
//			sessionService.saveSession(ns); //保存session
//			return ns;
//		} 
		user = this.getLoginVo(account,password);
		if (null == user) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1, "用户名或密码错误");
		}
		log.info("用户：【"+account+"】登陆系统成功!");
		user.setPwd(null);
		ns.save(Constants.SESSION_USER, user);
		ns.setLastOperaTime(System.currentTimeMillis());
		sessionService.saveSession(ns); //保存session
		//更新用户最后登录时间
		HtStaff update = new HtStaff();
		update.setDmId(user.getDmId());
		update.setLastLogin(DateUtil.getTimeInSeconds());
		super.modVoNotNull(update);
		return ns;
	}

	private HtStaff getLoginVo(String account, String password) throws ServiceException{
		try {
			return dao.getLoginVo(account,password,Constants.ZERO,Constants.ZERO);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"登录查询用户失败");
		}
	}
	public boolean logout(Long userId, String sid) throws ServiceException {
		if(null == sid){
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误","入参错误，sessionKey="+sid);
		}
		DdmSession s = sessionService.getSession(sid);
		if(null == s){
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"session无效", "session无效，sessionKey="+sid);
		}
		sessionService.removeSession(sid);//删除session
		return true;
	}
}