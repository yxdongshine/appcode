package com.qtz.ht.system.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.system.vo.HtFloor;
import com.qtz.ht.system.vo.HtFloorGoods;

/**
 * <p>Title:HtFloorService</p>
 * <p>Description:楼层管理表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
 */
public interface HtFloorService extends BaseService<HtFloor,Long> {
	
	/**
	* 【新增楼层】
	* @param htFloor
	* @param htFloorGoods
	* @throws Exception  
	*/
	public int add(HtFloor htFloor)throws ServiceException;
	
	/**
	* 【编辑保存】
	* @param htFloor
	* @param htFloorGoods
	* @throws ServiceException  
	*/
	public void mod(HtFloor htFloor)throws ServiceException;
	
	/**
	* 【楼层状态更改】
	* @param htFloor
	* @throws ServiceException  
	*/
	public void statesMov(HtFloor htFloor)throws ServiceException;
	
	/**
	* 【根据楼层Id查找楼层下的商品】
	* @param floorId
	* @return
	* @throws ServiceException  
	*/
	public List<HtFloorGoods> findFloorGoodsByFloorId(Long floorId)throws ServiceException;
}