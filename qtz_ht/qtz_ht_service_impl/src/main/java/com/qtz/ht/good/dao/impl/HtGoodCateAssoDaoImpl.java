package com.qtz.ht.good.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.ht.good.dao.HtGoodCateAssoDao;
import com.qtz.ht.good.vo.HtGoodCateAsso;

/**
 * <p>Title:HtGoodCateAssoDaoImpl</p>
 * <p>Description:商家商品与分类关联DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-10
 */
@Repository("htGoodCateAssoDaoImpl")
public class HtGoodCateAssoDaoImpl extends MyBaitsDaoImpl<HtGoodCateAsso,Long> implements HtGoodCateAssoDao {
	/**MYBatis命名空间名*/
	private static String preName = HtGoodCateAssoDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public Integer queryGoodsCount() throws DaoException {
		return getMyBaitsTemplate().getSqlSession().selectOne(getPreName()+".queryGoodsCount");
	}
	
}