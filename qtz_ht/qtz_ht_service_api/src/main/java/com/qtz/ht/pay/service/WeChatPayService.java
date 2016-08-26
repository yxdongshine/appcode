package com.qtz.ht.pay.service;

import com.mall.core.exception.ServiceException;

public interface WeChatPayService{
	
	/**
	 * 
	  * 【推送微信支付订单消息服务】
	  * @param orderId				订单
	  * @return  
	  * @time:2015年10月13日 上午10:47:31
	  * @author 涂鑫
	  * @version
	 */
	public  String pushOrderInfo(Long orderId) throws ServiceException;
	/**
	 * 
	  * 【推送pp订单信息】
	  * @param orderId
	  * @return
	  * @throws ServiceException  
	  * @time:2015年11月26日 上午9:27:19
	  * @author 涂鑫
	  * @version
	 */
	public String pushPpOrderInfo(Long orderId) throws ServiceException;
	/**
	 * 
	  * 【主动查询微信支付订单】
	  * @param orderId
	  * @return
	  * @throws ServiceException  
	  * @time:2015年11月6日 上午9:49:33
	  * @author 涂鑫
	  * @version
	 */
	public String query(Long orderId) throws ServiceException;

}
