package com.qtz.ht.user.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.ht.user.dao.HtBusinessDao;
import com.qtz.ht.user.vo.HtBusiness;
/**
 * <p>Title:HtBusinessDaoImpl</p>
 * <p>Description:商户信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
@Repository("htBusinessDaoImpl")
public class HtBusinessDaoImpl extends MyBaitsDaoImpl<HtBusiness,Long> implements HtBusinessDao {
	/**MYBatis命名空间名*/
	private static String preName = HtBusinessDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public List<HtBusiness> findListByBusinessesId(Set<Long> idS) throws DaoException {
		if (idS == null || idS.isEmpty()) {
			return null;
		}
		List<Long> list = new ArrayList<>();
		list.addAll(idS);
		return getMyBaitsTemplate().getSqlSession().selectList(getPreName() + ".findListByBusinessesId", list.toArray());
	}
}