package com.qtz.ht.user.service;

import com.alibaba.dubbo.rpc.RpcException;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.DdmSession;
import com.qtz.ht.user.vo.HtStaff;

/**
 * <p>Title:HtStaffService</p>
 * <p>Description:海淘员工表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-06
 */
public interface HtStaffService extends BaseService<HtStaff,Long> {
	
	public static final String HTSTAFF_RESET_PASSWORD ="123456";
	
	/** 
	 * 
	* 【登录，注意登录后将生成一个新的session，sessionKey将改变】(这里用一句话描述这个方法的作用)
	* @param userType
	* 			用户类型：常量见付款记录表
	* @param account
	* 			登录账号
	* @param password
	* 			登录密码
	* @param s
	* 			用户会话
	* @return	
	* 			用户会话
	* @throws ServiceException
	* @throws RpcException
	 */
	DdmSession login(String account,String password,DdmSession s) throws ServiceException,RpcException;
	
	/** 
	 * 
	* 【登出】(这里用一句话描述这个方法的作用)
	* @param userId
	* 				用户ID
	* @param sessionKey
	* 				会话ID
	* @return
	* @throws ServiceException
	* @throws RpcException
	 */
	boolean logout(Long userId,String sessionKey) throws ServiceException,RpcException;
}