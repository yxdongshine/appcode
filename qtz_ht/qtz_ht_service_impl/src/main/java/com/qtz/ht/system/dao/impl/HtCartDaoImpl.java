package com.qtz.ht.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.ht.system.dao.HtCartDao;
import com.qtz.ht.system.vo.HtCart;

/**
 * <p>Title:HtCartDaoImpl</p>
 * <p>Description:购物车DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 刘宝平 - liubaoping
 * @version v1.0 2016年8月12日
 */
@Repository("htCartDaoImpl")
public class HtCartDaoImpl extends MyBaitsDaoImpl<HtCart,Long> implements HtCartDao {
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	/**MYBatis命名空间名*/
	private static String preName = HtCartDao.class.getName();
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public void delAllbyUserIdAndGoodId(HtCart vo) throws DaoException {
		getMyBaitsTemplate().getSqlSession().delete(getPreName()+".delAllbyUserIdAndGoodId", vo);
	}

}
