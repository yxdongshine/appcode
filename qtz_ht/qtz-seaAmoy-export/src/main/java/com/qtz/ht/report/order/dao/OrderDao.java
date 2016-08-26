package com.qtz.ht.report.order.dao;

import java.util.List;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.report.order.entity.OrderLog;
import com.qtz.ht.report.order.model.BulkShippingModel;
import com.qtz.ht.report.order.model.HtOrderResponseModel;
import com.qtz.ht.report.order.model.ModOrderModel;
import com.qtz.ht.report.order.model.OrderModel;

public interface OrderDao {
	
	/**
	* 【獲取所有】
	* @return
	* @throws DaoException  
	*/
	List<ModOrderModel> getOrderList()throws DaoException;

	/**
	* 【批量修改订单进行发货处理】
	* @param dataSet
	* @return  
	*/
	int[] batchUpdateAudit(List<BulkShippingModel> dataSet)throws DaoException;
	/**
	* 【根据商户信息查询商户公司名称】
	* @param busiId
	* @return
	* @throws ServiceException  
	*/
	String getBusinessName(Long busiId)throws  DaoException;
	/**
	 * 根据条件找出订单列表
	 * findHtOrderModes:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param busiId  商家编号
	 * @param status 订单状态
	 * @param detailAddr 收货人详细地址 模糊查询
	 * @param dmId 订单编号
	 * @param consignee 收货人姓名
	 * @param mphoneNo 手机号码
	 * @param startCrtime 订单创建开始时间
	 * @param endCrtime 订单创建结束时间
	 * @return
	 * @throws DaoException
	 */
	List<HtOrderResponseModel> findHtOrderResponseModels(Long busiId, Integer status, String detailAddr,Long dmId,String consignee,String mphoneNo,Long startCrtime,Long endCrtime)throws DaoException;

	/**
	* 【批量修改订单身份证号】
	* @param list  
	*/
	int batchUpdateIdCard(List<ModOrderModel> list)throws DaoException;

	/**
	* 【查询未发货订单】
	* @param subList
	* @return  
	*/
	List<OrderModel> getOrderList(List<BulkShippingModel> subList)throws DaoException;

	/**
	* 【批量添加订单日志】
	* @param logList  
	*/
	void addBatchOrderLogRecord(List<OrderLog> logList);

}
