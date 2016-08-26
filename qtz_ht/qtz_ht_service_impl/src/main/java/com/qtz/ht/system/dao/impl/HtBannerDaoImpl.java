package com.qtz.ht.system.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.ht.system.dao.HtBannerDao;
import com.qtz.ht.system.vo.HtBanner;
/**
 * <p>Title:HtBannerDaoImpl</p>
 * <p>Description:横幅管理表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-15
 */
@Repository("htBannerDaoImpl")
public class HtBannerDaoImpl extends MyBaitsDaoImpl<HtBanner,Long> implements HtBannerDao {
	/**MYBatis命名空间名*/
	private static String preName = HtBannerDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public List<HtBanner> findSpecifyFieldList(HtBanner obj) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().selectList(getPreName()+".findSpecifyFieldList", obj);
	}
}