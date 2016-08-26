package com.qtz.ht.system.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.system.vo.HtCart;

/**
 * <p>Title:HtCartService</p>
 * <p>Description:(购物车服务接口类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 刘宝平 - liubaoping
 * @version v1.0 2016年8月12日
 */
public interface HtCartService extends BaseService<HtCart, Long> {

	/**
	* 【新增 ，默认数量为1】
	* @param htCart
	* @throws ServiceException  
	*/
	public void add(HtCart htCart)throws ServiceException;
	
	
	/**
	* 【查看购物车列表】
	* @param userId
	* @return
	* @throws ServiceException  
	*/
	public List<HtCart> list(Long userId)throws ServiceException;
	
	/**
	* 【根据Id修改商品数量】
	* @param dmId
	* @param goodsNumber
	* @throws ServiceException  
	*/
	public void modGoodsNum(Long dmId,int goodsNumber)throws ServiceException;
	
	/**
	* 【订单生成后，根据用户Id和商品Id删除购物车列表】
	* @param goodsId
	* @param userId
	* @throws ServiceException  
	*/
	public void delete(List<Long> goodsId,Long userId)throws ServiceException;

}
