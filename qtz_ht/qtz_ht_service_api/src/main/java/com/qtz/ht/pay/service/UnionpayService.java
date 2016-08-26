package com.qtz.ht.pay.service;

import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.pay.vo.Unionpay;

/**
 * 
 * <p>Title:UnionpayService</p>
 * <p>Description:(银联)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年10月13日
 */
public interface UnionpayService extends BaseService<Unionpay, Long>{
	/**
	 * 
	  * 【推送银联服务】
	  * @param orderId				订单
	  * @param paymentPrice			价格
	  * @return  
	  * @time:2015年10月13日 上午10:47:31
	  * @author 涂鑫
	  * @version
	 */
	public  String pushOrderInfo(Long orderId,Double paymentPrice) throws ServiceException;
	/**
	 * 
	  * 【银联查询订单信息】
	  * @param orderId
	  * @return
	  * @throws ServiceException  
	  * @time:2015年10月13日 上午10:56:28
	  * @author 涂鑫
	  * @version
	 */
	public String query(Long orderId) throws ServiceException;
	
	/**
	 * 
	  * 【银联验证】
	  * @param valideData
	  * @param encoding
	  * @return  
	  * @time:2015年10月13日 下午7:07:16
	  * @author 涂鑫
	  * @version
	 */
	public boolean  validate(Map<String, String> valideData,String encoding);

}
