package com.qtz.ht.user.service;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.ht.wallet.vo.HtBankCard;

/**
 * <p>Title:HtBusinessService</p>
 * <p>Description:商户信息服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
public interface HtBusinessService extends BaseService<HtBusiness,Long> {
	

	/**
	* 【保存或者修改商户信息】
	* @param operator
	* 				操作人
	* @param vo
	* 				商户信息
	* @param user
	* 				商户用户
	* @param bank
	* 				商户银行卡
	* @return
	* @throws ServiceException  
	*/
	HtBusiness addOrMod(Long operatorId, HtBusiness vo, HtUser user, HtBankCard bank) throws ServiceException;

	/**
	* 【修改商家信息】
	* @param userId
	* 				操作人ID
	* @param vo
	* 			商家信息
	* @param user
	* 			商家用户
	* @param bank 
	* 			商家银行卡 
	*/
	void modMerchantInfo(Long userId, HtBusiness vo, HtUser user, HtBankCard bank)throws ServiceException;

	/**
	* 【获取商户信息省市县镇等名字】
	* @param vo
	* 			商户对象
	* @return
	* @throws ServiceException  
	*/
	JSONObject getText(HtBusiness vo)throws ServiceException;

	/**
	* 【根据商家信息ID集合查询商家信息列表】
	* @param idS
	* @return
	* @throws ServiceException  
	*/
	List<HtBusiness> findListByBusinessesId(Set<Long> idS) throws ServiceException;

	
}