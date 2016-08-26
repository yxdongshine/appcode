package com.qtz.ht.report.session.service;

import com.mall.core.vo.DdmSession;


/**
 * session 管理器
 * <p>更改session后必须调用saveSession方法</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月8日
 */
public interface SessionService {
	  
	/**
	* 【获取session,如果不存在则创建一个新session】(这里用一句话描述这个方法的作用)
	* <p>更改session后必须调用saveSession方法</p>
	* @param sid
	* @return
	* @throws RpcException  
	*/
	public DdmSession getSession(String sid);
	
	/**
	 * 【保存session】(这里用一句话描述这个方法的作用)
	 * @param sid
	 * @return
	 * @throws RpcException  
	 */
	public void saveSession(DdmSession s);
	
	/**
	* 【新建一个session】(这里用一句话描述这个方法的作用)
	* @return
	* @throws RpcException  
	*/
	public DdmSession newSession();
	
	/**
	* 【删除一个session】(这里用一句话描述这个方法的作用)
	* @param sid
	* @throws RpcException  
	*/
	public boolean removeSession(String sid);
}