//package com.qtz.session.service.impl;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.alibaba.dubbo.rpc.RpcException;
//import com.mall.core.common.Constants;
//import com.mall.core.common.DateUtil;
//import com.mall.core.common.ExceptionConstants;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.log.LogTool;
//import com.mall.core.vo.DdmSession;
//import com.qtz.dm.userwallet.enums.UserType;
//import com.qtz.ht.user.service.HtUserService;
//import com.qtz.ht.user.vo.HtUser;
//import com.qtz.ht.util.UserUtil;
//import com.qtz.session.service.LoginService;
//import com.qtz.session.service.SessionService;
//
//
///**
// * <p>Title:LoginServiceImpl</p>
// * <p>Description:(登录、登出)</p>
// * <p>Copyright: Copyright (c) 2016</p>
// * <p>Company: 深圳市爱免费信息科技有限公司</p>
// * @author 唐礼军 - tanlijun
// * @version v1.0 2016年3月31日
// */
//@Service("loginServiceImpl")
//public class LoginServiceImpl implements LoginService {
//	
//	/**初始化日志对象*/
//	private static final LogTool log = LogTool.getInstance(LoginServiceImpl.class);
//	/**注入供应商Service类*/
//	@Resource(name="sessionServiceImpl")
//	private SessionService sessionService;
//	@Resource(name="htUserServiceImpl")
//	private HtUserService htUserService;
//
//	public DdmSession login(int userType,String account,String password,DdmSession s) throws ServiceException,RpcException {
//		log.error(account + " 登录了 密码 = password；"+ password + "用户类型 = " + userType);
//		if(null == account || null == password){
//			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
//		}
//		if(null != s.getSessionObject(Constants.SESSION_USER)){
//			throw new ServiceException(ExceptionConstants.ERRORCODE_6, "当前session已经绑定登录用户");
//		}
//		DdmSession ns = s;
//		HtUser user = null;
//		if (UserType.PPUSER.value() == userType && UserUtil.isAdmin(account, password)) {//如果是管理员并且是超级管理员
//			user = new HtUser();
//			user.setAccount(account);
////			user.setPassword(password);
//			user.setDmId(1000l);//管理员默认ID
//			ns.save(Constants.SESSION_USER, user);
//			sessionService.saveSession(ns); //保存session
//			return ns;
//		} 
//		user = htUserService.getLoginVo(account,password,userType);
//		if (null == user) {
//			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1, "用户名或密码错误");
//		}
//		log.info("用户：【"+account+"】登陆系统成功!");
//		user.setPassword(null);
//		ns.save(Constants.SESSION_USER, user);
//		ns.setLastOperaTime(System.currentTimeMillis());
//		sessionService.saveSession(ns); //保存session
//		//更新用户最后登录时间
//		HtUser update = new HtUser();
//		update.setDmId(user.getDmId());
//		update.setLastLogin(DateUtil.getTimeInSeconds());
//		this.htUserService.modVoNotNull(update);
//		return ns;
//	}
//
//	public boolean logout(Long userId, String sid) throws ServiceException {
//		if(null == sid){
//			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误","入参错误，sessionKey="+sid);
//		}
//		DdmSession s = sessionService.getSession(sid);
//		if(null == s){
//			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"session无效", "session无效，sessionKey="+sid);
//		}
//		sessionService.removeSession(sid);//删除session
//		return true;
//	}
//
//	public String getUserName(Long userId, String userType) throws ServiceException, RpcException {
//		if(null == userId || (null == userType)){
//			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误","入参错误，sessionKey="+userType+"，userId="+userId);
//		}
//		HtUser user = htUserService.findVo(userId);
//		if (null != user) {
//			String userName = user.getRealName();
//			return userName;
//		}
//		return null;
//	}
//}