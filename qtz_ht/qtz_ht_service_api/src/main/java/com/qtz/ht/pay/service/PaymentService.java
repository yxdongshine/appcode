package com.qtz.ht.pay.service;

import com.mall.core.exception.ServiceException;

public interface PaymentService {
	/**
	 * 
	  * 【处理交易结果】	
	  * @param orderId				订单id
	  * @param threeSerialNumber	第三方流水号	
	  * @param receivePrice			收到货款
	  * @throws ServiceException  
	  * @time:2015年9月17日 下午4:26:36
	  * @author 涂鑫
	  * @version
	 */
	public void updateDealPayResult(Long orderId,String threeSerialNumber,double receivePrice,Integer payType) throws ServiceException;
	/**
	 * 
	  * 【处理交易结果(胖胖服务订单)】
	  * @param orderId             订单id
      * @param threeSerialNumber    第三方流水号  
      * @param receivePrice         收到货款
	  * @param payType              支付类型
	  * @throws ServiceException  
	  * @time:2015年11月26日 上午10:16:45
	  * @author 涂鑫
	  * @version
	 */
	public void updateDealPayResultByPpService(Long orderId,String threeSerialNumber,double receivePrice,Integer payType)  throws ServiceException;

	/**
	 * 
	  * 【钱包支付】
	  * @param userId
	  * @param orderId
	  * @param pwd  
	  * @time:2015年9月24日 上午9:10:09
	  * @author 涂鑫
	  * @version
	 */
	public void saveWalletPayment(Long userId,Long orderId,String pwd) throws ServiceException;
	
	
	
}
