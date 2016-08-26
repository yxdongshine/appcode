package com.qtz.ht.wallet.service;
import com.alibaba.fastjson.JSONObject;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.wallet.vo.HtBankCard;
/**
 * <p>Title:HtBankCardService</p>
 * <p>Description:商户银行卡信息表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 唐礼军 - 1309025893@qq.com
 * @version v1.0 2016-06-21
 */
public interface HtBankCardService extends BaseService<com.qtz.ht.wallet.vo.HtBankCard,java.lang.Long> {
	
	/**
	* 【获取商户信息省市县镇等名字】
	* @param vo
	* 			商户对象
	* @return
	* @throws ServiceException  
	*/
	JSONObject getText(HtBankCard bank)throws ServiceException;
}