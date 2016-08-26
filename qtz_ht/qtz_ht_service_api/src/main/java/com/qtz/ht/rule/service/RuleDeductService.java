package com.qtz.ht.rule.service;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.rule.vo.RuleDeduct;
/**
 * <p>Title:RuleDeductService</p>
 * <p>Description:行业扣点规则服务接口类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015-09-23
 */
public interface RuleDeductService extends BaseService<com.qtz.ht.rule.vo.RuleDeduct,Long> {
	/**
	 * 
	* 【行业ID 查询规则】(这里用一句话描述这个方法的作用)
	* @param typeid
	* @return
	* @throws ServiceException
	 */
	RuleDeduct findByTypeId(Long typeid) throws ServiceException;
}