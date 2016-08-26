package com.qtz.ht.wallet.service;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.wallet.vo.HtWallet;

/**
 * <p>Title:HtWalletService</p>
 * <p>Description:钱包服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
public interface HtWalletService extends BaseService<HtWallet,Long> {

	/**
	* 【查询平台系统钱包】
	* @return  
	*/
	HtWallet findPlatformWallet()throws ServiceException;

	/**
	* 【获取商家钱包】
	* @param busiId
	* 				商家id
	* @param userType
	* 				用户类型
	* @return
	* @throws ServiceException  
	*/
	HtWallet getWalletByUser(Long busiId, int userType)throws ServiceException;

	/**
	* 【修改商家冻结金额】
	* @param dmId
	* @param staffRevenue
	* @throws ServiceException  
	*/
	void modSuppReconciliation(Long busiId, Double staffRevenue)throws ServiceException;

	/**
	* 【修改平台对账金额】countWalletTotalRefund
	* @param platformRevenue  
	*/
	void modPlatformReconciliation(double platformRevenue)throws ServiceException;
	
	/**
	* 【根据商家信息id，统计退款总额】
	* @param amount
	*/
	void countWalletTotalRefund(Long buessId,double amount)throws ServiceException;
	
	/**
	* 修改钱包货款总额
	* @param amount
	*/
	void modWalletTotalPaymentGoods(Long buessId,double amount)throws ServiceException;
}