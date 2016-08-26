package com.qtz.ht.sendmessage.service;

import com.mall.core.exception.ServiceException;
import com.qtz.ht.sendmessage.model.SmsCode;
/**
 * 
 * <p>Title:SendMessageService</p>
 * <p>Description:(验证码服务)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015年3月12日
 */
public interface SendMessageService {
	/**
	 * 
	* 【发送短信验证码】
	* @param mphone 接收手机
	* @return
	 */
	SmsCode sendSmsCode(String mphone) throws ServiceException;
	/**
	 * 
	* 【发送短信验证码】
	* @param mphone 接收手机
	* @param code 接收的验证码
	* @return
	 */
	SmsCode sendSmsCode(String mphone,String code) throws ServiceException;
	/**
	 * 
	  * 【发送支付密码验证码】
	  * @param mphone
	  * @return
	  * @throws ServiceException  
	  * @time:2015年9月23日 上午10:10:44
	  * @author 涂鑫
	  * @version
	 */
	SmsCode sendSmsPayPwdCode(String mphone) throws ServiceException;
	/**
	 * 
	* 【验证注册验证码】(验证成功，销毁验证码)
	* @param mphope 接收手机
	* @param code 接收到的验证码
	* @return
	 */
	boolean regSmsCode(String mphope,String code) throws ServiceException;
	/**
	 * 
	  * 【校验验证码】
	  * @param key						redis key
	  * @param code						验证码
	  * @param mphone					手机号码
	  * @return
	  * @throws ServiceException  
	  * @time:2015年9月23日 上午10:33:08
	  * @author 涂鑫
	  * @version
	 */
	boolean regSmsCode(String key,String code ,String mphone) throws ServiceException;
	
}
