package com.qtz.ht.wallet.service;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.wallet.vo.HtStaffPaymentFlow;

/**
 * <p>Title:HtStaffPaymentFlowService</p>
 * <p>Description:商户货款流水服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
public interface HtStaffPaymentFlowService extends BaseService<HtStaffPaymentFlow,Long> {
	
	public void delDataByCondition(HtStaffPaymentFlow t)throws ServiceException;

	/**
	* 【根据orderId 与 供应商id 获取供应商钱包流水】
	* @param orderId
	* @param staffCode
	* @return  
	*/
	public HtStaffPaymentFlow getEntityByOrderId(Long orderId, Long staffCode)throws ServiceException;
}