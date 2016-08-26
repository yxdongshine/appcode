package com.qtz.ht.user.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.user.dao.HtRoleDao;
import com.qtz.ht.user.vo.HtRole;
/**
 * <p>Title:HtRoleDaoImpl</p>
 * <p>Description:商户角色DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
@Repository("htRoleDaoImpl")
public class HtRoleDaoImpl extends MyBaitsDaoImpl<HtRole,Long> implements HtRoleDao {
	/**MYBatis命名空间名*/
	private static String preName = HtRoleDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}