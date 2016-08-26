package com.qtz.ht.good.dao;
import java.util.List;
import java.util.Set;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.vo.HtStaffGoods;
/**
 * <p>Title:HtStaffGoodsDao</p>
 * <p>Description:商户商品DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
public interface HtStaffGoodsDao extends BizDao<HtStaffGoods,Long> {

	/**
	* 【首页显示查询列表方法】
	* @param obj
	* @return 
	* @throws DaoException  
	*/ 
	List<HtStaffGoods> findSpecifyFieldList(HtStaffGoods obj)throws DaoException;
	
	/**
	 * 【获取抢购商品列表方法】
	 * @param obj
	 * @return 
	 * @throws DaoException  
	 */ 
	List<HtStaffGoods> getPanicBuyingGoodsList(HtStaffGoods obj)throws DaoException;

	/**
	* 【根据商品id集合查询商品列表】
	* @param idS
	* @return
	* @throws DaoException  
	*/
	List<HtStaffGoods> findListByGoodsId(Set<Long> idS)throws DaoException;

	/**
	 * 根据创建时间逆序查新
	 * findListByGoodsIdAndCrtime:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param idS
	 * @return
	 * @throws DaoException
	 */
	List<HtStaffGoods> findListByGoodsIdAndCrtime(Set<Long> idS)throws DaoException;
	/**
	* 【扣除商品库存】
	* @param dmId
	* @param buyNumber
	* @throws DaoException  
	*/
	void modstock(Long dmId, Integer buyNumber)throws DaoException;
	
	/**
	 * 增加库存
	 * addstock:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param dmId
	 * @param buyNumber
	 * @throws ServiceException
	 */
	public void addstock(Long dmId, Integer buyNumber)throws DaoException;
	
	
	/**
	* 【首页显示上架时间最新的5条】
	* @return 
	* @throws DaoException  
	*/ 
	List<HtStaffGoods> findShelvesTop5List()throws DaoException;
	/**
	* 【查询未分类的商品】
	* @return
	* @throws DaoException  
	*/
	Pager<HtStaffGoods, Long> unclassifiedGoods(HtStaffGoodsPage page)throws DaoException;
}