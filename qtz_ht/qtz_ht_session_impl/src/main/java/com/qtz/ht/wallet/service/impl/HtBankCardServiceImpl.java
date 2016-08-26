package com.qtz.ht.wallet.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.dm.system.service.RegionService;
import com.qtz.dm.system.vo.Region;
import com.qtz.ht.wallet.dao.HtBankCardDao;
import com.qtz.ht.wallet.service.HtBankCardService;
import com.qtz.ht.wallet.vo.HtBankCard;
/**
 * <p>Title:HtBankCardServiceImpl</p>
 * <p>Description:商户银行卡信息表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 唐礼军 - 1309025893@qq.com
 * @version v1.0 2016-06-21
 */
@Service("htBankCardServiceImpl")
public class HtBankCardServiceImpl extends BaseServiceImpl<com.qtz.ht.wallet.vo.HtBankCard,java.lang.Long> implements HtBankCardService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtBankCardServiceImpl.class);
	/**注入商户银行卡信息表DAO接口类*/
	@Resource(name="htBankCardDaoImpl")
    private HtBankCardDao dao;
    
	@Resource(name="regionServiceImpl")
	private RegionService regionService;
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.ht.wallet.vo.HtBankCard,java.lang.Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	@Override
	public JSONObject getText(HtBankCard bank) throws ServiceException {
		JSONObject result = new JSONObject();
		Region r = null;
		if (null != bank.getAccountProvince()) {
			r = this.regionService.findRegions(bank.getAccountProvince());
			result.put(HtBankCard.BANK_PROVINCE_TEXT, r.getName());
		}
		if (null != bank.getAccountCity()) {
			r = this.regionService.findRegions(bank.getAccountCity());
			result.put(HtBankCard.BANK_CITY_TEXT, r.getName());
		}
		
		return result;
	}
}