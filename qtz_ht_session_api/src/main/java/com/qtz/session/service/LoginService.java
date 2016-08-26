//package com.qtz.session.service;
//
//import com.alibaba.dubbo.rpc.RpcException;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.vo.DdmSession;
//
//
///**
// * <p>Title:LoginService</p>
// * <p>Description:(登录服务)</p>
// * <p>Copyright: Copyright (c) 2016</p>
// * <p>Company: 深圳市爱免费信息科技有限公司</p>
// * @author 唐礼军 - tanlijun
// * @version v1.0 2016年3月31日
// */
//public interface LoginService {
//	
//	/** 
//	 * 
//	* 【登录，注意登录后将生成一个新的session，sessionKey将改变】(这里用一句话描述这个方法的作用)
//	* @param userType
//	* 			用户类型：常量见付款记录表
//	* @param account
//	* 			登录账号
//	* @param password
//	* 			登录密码
//	* @param s
//	* 			用户会话
//	* @return	
//	* 			用户会话
//	* @throws ServiceException
//	* @throws RpcException
//	 */
//	DdmSession login(int userType,String account,String password,DdmSession s) throws ServiceException,RpcException;
//	
//	/** 
//	 * 
//	* 【登出】(这里用一句话描述这个方法的作用)
//	* @param userId
//	* 				用户ID
//	* @param sessionKey
//	* 				会话ID
//	* @return
//	* @throws ServiceException
//	* @throws RpcException
//	 */
//	boolean logout(Long userId,String sessionKey) throws ServiceException,RpcException;
//	
//	/** 
//	* 【获得用户名】(这里用一句话描述这个方法的作用)
//	* @param userId
//	* 			用户ID
//	* @param userType
//	* 			用户类型：常量见付款记录表
//	* @return
//	* 			用户名
//	* @throws ServiceException
//	* @throws RpcException
//	* @author 吴经纬
//	*/
//	String getUserName(Long userId,String userType) throws ServiceException,RpcException;
//}