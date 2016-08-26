package com.qtz.ht.order.service;

import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.rpc.RpcException;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.order.model.OrderModel;

/**
 * <p>Title:HtOrderService</p>
 * <p>Description:商户订单表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-02-20
 */
public interface HtOrderService{
	
	/**
	 * 
	* 【计算订单】
	* @param goodS  
	* 				商品ID，以及商品购买数量
	* @return
	* @throws ServiceException
	 */
	List<OrderModel> calculateOrder(Map<Long, Integer> goodS) throws ServiceException,RpcException;
	
	/**
	* 【订单付款成功后修改库存】
	* @param goodS
	* 			商品ID以及购买数量
	* @throws ServiceException  
	*/
	void modStock(List<OrderModel> list)throws ServiceException;
	/**
	 * 【添加对账】
	 * @param goodS
	 * 			商品ID以及购买数量
	 * @throws ServiceException  
	 */
	void addReconciliation(List<OrderModel> list)throws ServiceException;
	
	
	/**
	 * 增加库存方法
	 * addStock:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param list
	 * @throws ServiceException
	 */
	void addStock(List<OrderModel> list)throws ServiceException;

}