package com.qtz.ht.user.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.user.dao.HtRoleMenuDao;
import com.qtz.ht.user.vo.HtRoleMenu;
/**
 * <p>Title:HtRoleMenuDaoImpl</p>
 * <p>Description:商户角色菜单DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
@Repository("htRoleMenuDaoImpl")
public class HtRoleMenuDaoImpl extends MyBaitsDaoImpl<HtRoleMenu,Long> implements HtRoleMenuDao {
	/**MYBatis命名空间名*/
	private static String preName = HtRoleMenuDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}