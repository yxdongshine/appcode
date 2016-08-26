package com.qtz.ht.rule.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.qtz.ht.rule.vo.RuleDeduct;
import com.qtz.ht.rule.vo.RuleGold;
import com.qtz.ht.rule.vo.RuleProfit;
import com.qtz.ht.rule.vo.RuleRebate;

public interface RuleService {
	
	///////////////////////////////////////////////////////////////////
	/**行业扣点规则*/
	////////////////////////////////////////////////////////////////////
	/**
	 * 
	* 【添加行业扣点规则】(这里用一句话描述这个方法的作用)
	* @param rg
	* @throws ServiceException
	 */
	void addRuleDeduct(RuleDeduct rg) throws ServiceException;
	/**
	 * 
	* 【更改行业扣点规则】(这里用一句话描述这个方法的作用)
	* @param rg
	* @throws ServiceException
	 */
	void modRuleDeduct(RuleDeduct rg)throws ServiceException;
	/**
	 * 
	* 【删除行业扣点规则】(这里用一句话描述这个方法的作用)
	* @param id
	* @throws ServiceException
	 */
	void delRuleDeduct(Long id)throws ServiceException;
	/**
	 * 
	* 【所有行业扣点规则】(这里用一句话描述这个方法的作用)
	* @return
	* @throws ServiceException
	 */
	List<RuleDeduct> findRuleDeduct()throws ServiceException;
	/**
	* 【查询行业扣点规则】(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	* @throws ServiceException
	 */
	RuleDeduct findRuleDeduct(Long id)throws ServiceException;
	/**
	 * 
	* 【查询行业扣点规则】(这里用一句话描述这个方法的作用)
	* @param typeid  行业ID
	* @return
	* @throws ServiceException
	 */
	RuleDeduct findRuleDeductByTypeId(Long typeid)throws ServiceException;
	
	
	
	
	
	///////////////////////////////////////////////////////////////////
	/**行业返点规则*/
	////////////////////////////////////////////////////////////////////
	/**
	 * 
	* 【添加行业返点规则】(这里用一句话描述这个方法的作用)
	* @param rg
	* @throws ServiceException
	 */
	void addRuleRebate(RuleRebate rg) throws ServiceException;
	/**
	 * 
	* 【更改行业返点规则】(这里用一句话描述这个方法的作用)
	* @param rg
	* @throws ServiceException
	 */
	void modRuleRebate(RuleRebate rg)throws ServiceException;
	/**
	 * 
	* 【删除行业返点规则】(这里用一句话描述这个方法的作用)
	* @param id
	* @throws ServiceException
	 */
	void delRuleRebate(Long id)throws ServiceException;
	/**
	* 【查询行业返点规则】(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	* @throws ServiceException
	 */
	RuleRebate findRuleRebate(Long id)throws ServiceException;
	/**
	 * 
	* 【行业ID 行业返点规则】(这里用一句话描述这个方法的作用)
	* @param typeid
	* @return
	* @throws ServiceException
	 */
	List<RuleRebate> findRuleRebateByTypeid(Long typeid)throws ServiceException;
	///////////////////////////////////////////////////////////////////
	/**利润分配规则*/
	////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	* 【添加利润分配规则】(这里用一句话描述这个方法的作用)
	* @param rg
	* @throws ServiceException
	 */
	void addRuleProfit(RuleProfit rg) throws ServiceException;
	/**
	 * 
	* 【更改利润分配规则】(这里用一句话描述这个方法的作用)
	* @param rg
	* @throws ServiceException
	 */
	void modRuleProfit(RuleProfit rg)throws ServiceException;
	/**
	 * 
	* 【删除利润分配规则】(这里用一句话描述这个方法的作用)
	* @param id
	* @throws ServiceException
	 */
	void delRuleProfit(Long id)throws ServiceException;
	/**
	 * 
	* 【所有利润分配规则】(这里用一句话描述这个方法的作用)
	* @return
	* @throws ServiceException
	 */
	List<RuleProfit> findRuleProfit()throws ServiceException;
	/**
	* 【查询利润分配规则】(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	* @throws ServiceException
	 */
	RuleProfit findRuleProfit(Long id)throws ServiceException;
	/**
	 * 
	* 【查询利润分配规则】(这里用一句话描述这个方法的作用)
	* @param code
	* @return
	* @throws ServiceException
	 */
	RuleProfit findRuleProfitByCode(String code)throws ServiceException;
	
	///////////////////////////////////////////////////////////////////
	/**金币规则*/
	////////////////////////////////////////////////////////////////////
	/**
	 * 
	* 【添加金币规则】(这里用一句话描述这个方法的作用)
	* @param rg
	* @throws ServiceException
	 */
	void addRuleGold(RuleGold rg) throws ServiceException;
	/**
	 * 
	* 【更改金币规则】(这里用一句话描述这个方法的作用)
	* @param rg
	* @throws ServiceException
	 */
	void modRuleGold(RuleGold rg)throws ServiceException;
	/**
	 * 
	* 【删除金币规则】(这里用一句话描述这个方法的作用)
	* @param id
	* @throws ServiceException
	 */
	void delRuleGold(Long id)throws ServiceException;
	/**
	 * 
	* 【所有金币规则】(这里用一句话描述这个方法的作用)
	* @return
	* @throws ServiceException
	 */
	List<RuleGold> findRuleGold()throws ServiceException;
	/**
	* 【查询金币规则】(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	* @throws ServiceException
	 */
	RuleGold findRuleGoldById(Long id)throws ServiceException;
	/**
	 * 
	* 【查询金币规则】(这里用一句话描述这个方法的作用)
	* @param code
	* @return
	* @throws ServiceException
	 */
	RuleGold findRuleGoldByCode(String code)throws ServiceException;
	
}
