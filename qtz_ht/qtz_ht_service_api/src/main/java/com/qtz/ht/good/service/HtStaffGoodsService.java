package com.qtz.ht.good.service;
import java.util.List;
import java.util.Set;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtGoodsAlbum;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtStaff;


/**
 * <p>Title:HtStaffGoodsService</p>
 * <p>Description:(商户商品服务接口类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月8日
 */
public interface HtStaffGoodsService extends BaseService<HtStaffGoods,Long> {

	/**
	* 【添加商品】
	* @param vo
	* 			商品
	* @param goodDesc
	* 			商品详情
	* @param albums
	* 			商品相册图片地址
	* @param operatorId
	* 			操作人用户id
	* @param operatorName
	* 			操作人姓名
	* @param operaType
	* 			操作人用户类型
	* @return  
	*/
	HtStaffGoods add(HtStaffGoods vo,List<HtGoodCateAsso> cateS, String goodDesc, List<HtGoodsAlbum> albums,Long operatorId, String operatorName, Integer operaType)throws ServiceException;

	/**
	* 【供应商上下架商品】
	* @param operatorId
	* 				操作人ID
	* @param operatorName
	* 				操作名字
	* @param dmId
	* 				商品id
	* @param status  
	* 				上，下架状态
	*/
	void modUpOrDownGood(Long operatorId, String operatorName, Long dmId, Integer status)throws ServiceException;


	/**
	* 【编辑商品保存】
	* @param vo
	* 			商品
	* @param cataId
	* 			分类关联
	* @param goodDesc
	* 			商品详情
	* @param albums
	* 			商品相册
	* @param operatorId
	* 			操作人ID
	* @param operatorName
* 				操作人名字
	* @param operaType
	* 			操作人用户类型
	* @return
	* @throws ServiceException  
	*/
	HtStaffGoods mod(HtStaffGoods vo,List<HtGoodCateAsso> cateS, String goodDesc, List<HtGoodsAlbum> albums, Long operatorId, String operatorName,int operaType)throws ServiceException;

	/**
	* 【首页显示查询列表方法】
	* @param status
	* 			1：上架；2：下架；3：强制下架 findShelvesTop5List
	* @return
	* @throws ServiceException  
	*/
	List<HtStaffGoods> findSpecifyFieldList(Integer status)throws ServiceException;
	
	/**
	 * 【获取抢购商品列表方法】
	 * @param status
	 * 			1：上架；2：下架；3：强制下架 findShelvesTop5List
	 * @return
	 * @throws ServiceException  
	 */
	List<HtStaffGoods> getPanicBuyingGoodsList(Integer status)throws ServiceException;

	/**
	* 【根据商品ID集合查询商品列表】
	* @param keySet
	* @return
	* @throws ServiceException  
	*/
	List<HtStaffGoods> findListByGoodsId(Set<Long> idS)throws ServiceException;

	/**
	* 【扣除商品库存】
	* @param dmId
	* @param buyNumber
	* @throws DaoException  
	*/
	void modstock(Long dmId, Integer buyNumber)throws ServiceException;
	
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
	public void addstock(Long dmId, Integer buyNumber)throws ServiceException;
	
	/**
	* 【首页显示商品上架时间前面5条列表方法】
	* 			1：上架；2：下架；3：强制下架 
	* @return
	* @throws ServiceException  
	*/
	List<HtStaffGoods> findShelvesTop5List()throws ServiceException;
	
	/**
	* 【批量屏蔽或者冻结商家】
	* @param staff
	* @param list  
	*/
	void modBatchSupplier(HtStaff staff, List<HtBusiness> list, String operateContent, String voucherAddr)throws ServiceException;
	
	/**
	 * 根据创建时间 逆序 排列
	 * findListByGoodsIdAndCrtime:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param idS
	 * @return
	 * @throws ServiceException
	 */
	List<HtStaffGoods> findListByGoodsIdAndCrtime(Set<Long> idS)throws ServiceException;
	
	/**
	 *收索热门商品
	 * findHotSearchGoods:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @return
	 * @throws ServiceException
	 */
	List<HtStaffGoods> findHotSearchGoods()throws ServiceException;
	

	/**
	* 【查询未分类的商品】
	* @return
	* @throws DaoException  
	*/
	Pager<HtStaffGoods, Long> unclassifiedGoods(HtStaffGoodsPage page)throws ServiceException;
}