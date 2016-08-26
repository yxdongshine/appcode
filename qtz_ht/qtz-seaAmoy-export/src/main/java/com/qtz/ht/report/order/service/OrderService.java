package com.qtz.ht.report.order.service;

import java.util.List;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.report.order.model.BulkShippingModel;
import com.qtz.ht.report.order.model.HtOrderResponseModel;

/**
 * <p>Title:OrderService</p>
 * <p>Description:(订单导入导出service接口类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月7日
 */
public interface OrderService {

	public void modOrderIdCard()throws ServiceException;
	/**
	* 【批量修改订单发货信息】
	* @param busiId
	* @param list  
	*/
	int modBulkShipping(Long busiId, List<BulkShippingModel> list)throws ServiceException;
	/**
	* 【根据商户信息查询商户公司名称】
	* @param busiId
	* @return
	* @throws ServiceException  
	*/
	String getBusinessName(Long busiId)throws ServiceException;
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
	List<HtOrderResponseModel> findHtOrderResponseModels(Long busiId, Integer status, String detailAddr,Long dmId,String consignee,String mphoneNo,Long startCrtime,Long endCrtime)throws ServiceException;
	
	/**
	 * 转换数据
	 * transHtOrderResponseModels:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param htOrderResponseModelList
	 * @return
	 * @throws ServiceException
	 */
    List<HtOrderResponseModel> transHtOrderResponseModels(List<HtOrderResponseModel> htOrderResponseModelList)throws ServiceException;

}