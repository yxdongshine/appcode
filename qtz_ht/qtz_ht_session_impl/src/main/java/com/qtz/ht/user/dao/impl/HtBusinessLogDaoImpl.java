package com.qtz.ht.user.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.user.dao.HtBusinessLogDao;
import com.qtz.ht.user.vo.HtBusinessLog;
/**
 * <p>Title:HtBusinessLogDaoImpl</p>
 * <p>Description:商户信息日志表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-11
 */
@Repository("htBusinessLogDaoImpl")
public class HtBusinessLogDaoImpl extends MyBaitsDaoImpl<HtBusinessLog,Long> implements HtBusinessLogDao {
	/**MYBatis命名空间名*/
	private static String preName = HtBusinessLogDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}