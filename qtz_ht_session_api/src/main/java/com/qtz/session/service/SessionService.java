package com.qtz.session.service;

import com.alibaba.dubbo.rpc.RpcException;
import com.mall.core.vo.DdmSession;

/**
 * session 管理器
 * <p>更改session后必须调用saveSession方法</p>
 * <p>Title:SessionManager</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2014-12-6
 */
public interface SessionService {
	  
	/**
	* 【获取session,如果不存在则创建一个新session】(这里用一句话描述这个方法的作用)
	* <p>更改session后必须调用saveSession方法</p>
	* @param sid
	* @return
	* @throws RpcException  
	*/
	public DdmSession getSession(String sid) throws RpcException;
	
	/**
	 * 【保存session】(这里用一句话描述这个方法的作用)
	 * @param sid
	 * @return
	 * @throws RpcException  
	 */
	public void saveSession(DdmSession s) throws RpcException;
	
	/**
	* 【新建一个session】(这里用一句话描述这个方法的作用)
	* @return
	* @throws RpcException  
	*/
	public DdmSession newSession() throws RpcException;
	
	/**
	* 【删除一个session】(这里用一句话描述这个方法的作用)
	* @param sid
	* @throws RpcException  
	*/
	public boolean removeSession(String sid) throws RpcException;
}