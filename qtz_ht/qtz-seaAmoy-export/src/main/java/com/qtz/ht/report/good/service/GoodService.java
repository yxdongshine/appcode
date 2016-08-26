package com.qtz.ht.report.good.service;

import java.util.List;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.report.good.model.GoodStockModel;

/**
 * <p>Title:GoodService</p>
 * <p>Description:(商品导入导出service接口类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月7日
 */
public interface GoodService {

	/**
	* 【查询指定商家商品名称及库存】
	* @param busiId
	* 				商家ID
	* @param status
	* 				上架状态
	* @param auditStatus
	* 				审核状态
	* @return
	* @throws DaoException  
	*/
	List<GoodStockModel> findListGoods(Long busiId, Integer status, Integer auditStatus)throws ServiceException;
}
