package com.qtz.ht.system.dao;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.ht.system.vo.HtCart;

/**
 * <p>Title:HtCartDao</p>
 * <p>Description:购物车DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 刘宝平 - liubaoping
 * @version v1.0 2016年8月12日
 */
public interface HtCartDao extends BizDao<HtCart,Long>{
	
	/**
	* 【根据用户Id,商品id删除】
	* @param vo
	* @throws DaoException  
	*/
	void delAllbyUserIdAndGoodId(HtCart vo)throws DaoException;

}
