package com.qtz.ht.report.order.dao;

import java.util.List;

import com.mall.core.exception.DaoException;
import com.qtz.ht.report.order.entity.HtBusiness;
import com.qtz.ht.report.order.entity.HtPlatformWalletLog;
import com.qtz.ht.report.order.entity.HtStaffPaymentFlow;
import com.qtz.ht.report.order.model.OrderModel;

public interface HtBusinessDao {
	
	/**
	* 【获取商家折扣比例与对账期】
	* @param busiId
	* @return
	* @throws DaoException  
	*/
	HtBusiness getEntityById(Long busiId)throws DaoException;
	/**
	* 【修改对账金额】
	* @param busiId
	* @param amount
	* @throws DaoException  
	*/
//	void modWallet(Long busiId, Double amount)throws DaoException;
	/**
	* 【添加商品对账记录】
	* @param suppList
	* @throws DaoException  
	*/
	int addSuppBatchRecord(List<HtStaffPaymentFlow> suppList)throws DaoException;
	/**
	* 【添加平台对账数据】
	* @param platList
	* @throws DaoException  
	*/
	int addPlatBatchRecord(List<HtPlatformWalletLog> platList)throws DaoException;
	/**
	* 【批量修改钱包】
	* @param reconciliationList  
	*/
	int modBatchWallet(List<OrderModel> reconciliationList)throws DaoException;
}
