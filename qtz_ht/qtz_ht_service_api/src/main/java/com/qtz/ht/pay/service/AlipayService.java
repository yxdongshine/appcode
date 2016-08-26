package com.qtz.ht.pay.service;

import java.util.Map;

import com.mall.core.exception.ServiceException;

public interface AlipayService {
	
	
	/**
	 * 
	  * 【验证消息是否是支付宝发出的合法消息】
	  * @param params
	  * @return
	  * @throws ServiceException  
	  * @time:2015年10月13日 下午2:53:13
	  * @author 涂鑫
	  * @version
	 */
	public  boolean verify(Map<String, String> params) throws ServiceException;
	
	/**
	 * 
	  * 【查询支付支付状态信息】
	  * @param orderId
	  * @return
	  * @throws ServiceException  
	  * @time:2015年10月13日 下午3:31:07
	  * @author 涂鑫
	  * @version
	 */
	public String query(Long orderId) throws ServiceException;

}
