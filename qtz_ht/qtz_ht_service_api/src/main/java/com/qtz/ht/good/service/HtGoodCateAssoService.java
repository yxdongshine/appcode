package com.qtz.ht.good.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.page.HtGoodCateAssoPage;
import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtStaffGoods;

/**
 * <p>Title:HtGoodCateAssoService</p>
 * <p>Description:商家商品与分类关联服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-10
 */
public interface HtGoodCateAssoService extends BaseService<HtGoodCateAsso,Long> {

	/**
	* 【获取商品所有关联分类】
	* @param goodId
	* @return
	* @throws ServiceException  
	*/
	List<HtGoodCateAsso> findList(Long goodId)throws ServiceException;

	/**
	* 【转移商品分类】
	* @param goodId
	* 				商品id
	* @param cateId
	* 				分类id
	* @param type
	* 				操作类型	1：检查是否有此分类;2:转移分类
	* @throws ServiceException  
	*/
	void modGoodCate(Long goodId, Long cateId, Integer type)throws ServiceException;
	
	/**
	* 【根据第三级分类ID，查分类下所有商品】
	* @param htGoodCateAsso
	* @return
	* @throws ServiceException  
	*/
	Pager<HtStaffGoods, Long> findAllByCategory(HtGoodCateAssoPage page)throws ServiceException;
	
	/**
	* 【去重查询已类的商品】
	* @return
	* @throws DaoException  
	*/
	Integer queryGoodsCount()throws ServiceException;
	
}