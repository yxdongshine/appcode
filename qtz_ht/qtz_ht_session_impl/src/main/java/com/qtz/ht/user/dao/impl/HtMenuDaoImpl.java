package com.qtz.ht.user.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.user.dao.HtMenuDao;
import com.qtz.ht.user.vo.HtMenu;
/**
 * <p>Title:HtMenuDaoImpl</p>
 * <p>Description:商户员工菜单DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
@Repository("htMenuDaoImpl")
public class HtMenuDaoImpl extends MyBaitsDaoImpl<HtMenu,Long> implements HtMenuDao {
	/**MYBatis命名空间名*/
	private static String preName = HtMenuDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}