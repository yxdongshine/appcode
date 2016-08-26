package com.qtz.ht.user.dao.impl;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.ht.user.dao.HtStaffDao;
import com.qtz.ht.user.vo.HtStaff;
/**
 * <p>Title:HtStaffDaoImpl</p>
 * <p>Description:海淘员工表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-06
 */
@Repository("htStaffDaoImpl")
public class HtStaffDaoImpl extends MyBaitsDaoImpl<HtStaff,Long> implements HtStaffDao {
	/**MYBatis命名空间名*/
	private static String preName = HtStaffDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public HtStaff getLoginVo(String account, String password, int jobOn, int status) throws DaoException {
		Map<String, Object> params = new HashMap<>();
		params.put("account", account);
		params.put("password", password);
		params.put("jobOn", jobOn);
		params.put("status", status);
		return getMyBaitsTemplate().getSqlSession().selectOne(getPreName() + ".getLoginVo", params);
	}
}