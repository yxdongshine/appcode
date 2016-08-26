package com.qtz.ht.user.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.user.dao.HtUserRoleDao;
import com.qtz.ht.user.vo.HtUserRole;
/**
 * <p>Title:HtUserRoleDaoImpl</p>
 * <p>Description:商户用户与角色关联DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
@Repository("htUserRoleDaoImpl")
public class HtUserRoleDaoImpl extends MyBaitsDaoImpl<HtUserRole,Long> implements HtUserRoleDao {
	/**MYBatis命名空间名*/
	private static String preName = HtUserRoleDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}