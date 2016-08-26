package com.qtz.ht.wallet.dao;
import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.ht.wallet.vo.HtWallet;
/**
 * <p>Title:HtWalletDao</p>
 * <p>Description:钱包DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
public interface HtWalletDao extends BizDao<HtWallet,Long> {

	/**
	* 【获取商家钱包】
	* @param busiId
	* @param userType
	* @return  
	*/
	HtWallet getWalletByUser(@Param("busiId")Long busiId, @Param("userType")int userType) throws DaoException;

	/**
	* 【未付供应商货款】
	* @param userType
	* @return  
	*/
	Double getUnpaidPayment(@Param("userType")int userType);

	/**
	* 【已付供应商货款】
	* @param userType
	* @return  
	*/
	Double getPaidPayment(@Param("userType")int userType);

	/**
	* 【修改对账金额】
	* @param busiId
	* @param amount
	* @throws DaoException  
	*/
	void modWallet(Long busiId, Double amount)throws DaoException;
	
	/**
	* 【根据商家信息id，统计退款总额】
	* @param busiId
	* @param amount
	* @throws DaoException  
	*/
	void countWalletTotalRefund(Long busiId, Double amount)throws DaoException;
	
	/**
	* 修改钱包货款总额
	* @param amount
	*/
	void modWalletTotalPaymentGoods(Long buessId,Double amount)throws DaoException;
}