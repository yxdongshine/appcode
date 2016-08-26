package com.qtz.ht.order.service;

import com.mall.core.exception.ServiceException;
import com.qtz.ht.order.model.PaymentModel;

/**
 * <p>Title:TransferActivityService</p>
 * <p>Description:(换车活动service)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军- tanlijun
 * @version v1.0 2016�?�?8�?
 */
public interface TransferActivityService{
	

	/**
	* 【提交订单并支付】
	* @param userId
	* 			用户id
	* @param goodId
	* 			商品id
	* @param idCard
	* 			身份证号
	* @param payPassword
	* 			支付密码
	* @throws ServiceException  
	*/
	void addOrder(Long userId, String userName, Long goodId, String idCard, String payPassword, String mobilePhone) throws ServiceException;
	
	/**
	* 【获取海淘补贴并校验补贴金额是否足够】
	* @param userId
	* @param goodId
	* @throws ServiceException  
	*/
	PaymentModel getSubsidy(Long userId, Long goodId)throws ServiceException;
}