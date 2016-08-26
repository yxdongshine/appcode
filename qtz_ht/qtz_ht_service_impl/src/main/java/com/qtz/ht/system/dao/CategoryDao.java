package com.qtz.ht.system.dao;

import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.ht.system.page.HtCategoryPage;
import com.qtz.ht.system.vo.HtCategory;

public interface CategoryDao extends BizDao<HtCategory,Long>  {
		
	/**
	 * 查询海淘在后台的二级唯一分类
	 * @param mark
	 * @return
	 * @throws DaoException
	 */
	HtCategory findVoByMark(String mark)throws DaoException;
	
	/**
	* 【根据parentID查询所有】
	* @param parentId
	* @return
	* @throws DaoException  
	*/
	List<HtCategory> findAllByparentId(Long parentId,Integer status,Integer show)throws DaoException;
	
	/**
	* 【分页查询】
	* @param page
	* @return
	* @throws DaoException  
	*/
	Pager<HtCategory, Long> queryPage(HtCategoryPage page) throws DaoException;
	
	/**
	* 【删除】
	* @param dmId
	* @throws DaoException  
	*/
	void delete(Long dmId)throws DaoException;
	
	/**
	* 【查询序号】
	* @param parentId
	* @return
	* @throws DaoException  
	*/
	public List<HtCategory> queryOrder(Long parentId) throws DaoException;
}
