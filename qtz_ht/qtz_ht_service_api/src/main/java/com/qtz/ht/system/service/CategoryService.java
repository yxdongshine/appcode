package com.qtz.ht.system.service;

import java.util.List;
import java.util.Map;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.page.HtGoodCateAssoPage;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.system.page.HtCategoryPage;
import com.qtz.ht.system.vo.HtCategory;

public interface CategoryService extends BaseService<HtCategory,Long>  {
		
	/**
	 * 查询海淘在后台的二级唯一分类
	 * @return
	 * @throws DaoException
	 */
	HtCategory findVoByMark()throws ServiceException;

	/**
	* 【添加商品分类】
	* @param parentID
	* 				父类id
	* @param name
	* 				分类名称
	* @param mainPicture
	* 				分类图片  
	*/
	void addVo(Long parentID, String name, String mainPicture)throws ServiceException;

	/**
	* 【根据父级id查询下级所有分类】
	* @param parentID
	* 					父级id 可为空，为空时默认获取 第一级分类下的所有
	* @return
	* @throws ServiceException  
	*/
	List<HtCategory> findList(Long parentID)throws ServiceException;

	
	/**
	 * 根据htCategory条件查询符合条件的分类
	 */
	List<HtCategory> findListStatusAndPra(HtCategory htCategory)throws ServiceException;
	/**
	* 【编辑分类】
	* @param id
	* 			分类id
	* @param name
	* 			分类名称
	* @param mainPicture
	* 			分类图片
	*/
	void modVoNotNull(Long id, String name, String mainPicture)throws ServiceException;

	/**
	* 【根据id删除分类】
	* @param id
	* @throws ServiceException  
	*/
	void delCategoryById(Long id)throws ServiceException;

	/**
	* 【上下架分类】
	* @param id
	* 			分类ID
	* @param status 
	* 			上下架状态 
	*/
	void modStatus(Long id, Boolean status)throws ServiceException;

	/**
	* 【修改分类的排序】
	* @param sortStr
	* 				
	* @throws ServiceException  
	*/
	void modSort(String sortStr)throws ServiceException;
	/**
	* 【设置分类楼层数】
	* @param dmId
	* 				分类id
	* @param number
	* 				楼层数
	* @throws ServiceException  
	*/
	void modFloorNumber(Long dmId, Integer number) throws ServiceException;
	
	/**
	* 【分页查询】--刘宝平 2016.8.11
	* @param page
	* @return
	* @throws ServiceException  
	*/
	Pager<HtCategory, Long> queryPage(HtCategoryPage page)throws ServiceException;
	
	/**
	* 【楼层分类启用1和禁用2】--刘宝平 2016.8.11
	* @param id
	* @param show
	* @throws ServiceException  
	*/
	void modCategoryStatus(Long id, int show) throws ServiceException;
	
	/**
	* 【根据分类id查找该分类下所有商品】
	* @param id
	* @return
	* @throws ServiceException  
	*/
	Pager<HtStaffGoods, Long> findAllByCategory(HtGoodCateAssoPage page)throws ServiceException;
	/**
	* 【分类查询商品】
	* @return
	* @throws ServiceException  
	*/
	List<Map<String, Object>>  categoryGoods(Long floorId)throws ServiceException;
}
