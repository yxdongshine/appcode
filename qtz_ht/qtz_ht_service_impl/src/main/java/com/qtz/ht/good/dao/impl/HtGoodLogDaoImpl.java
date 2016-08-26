package com.qtz.ht.good.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.good.dao.HtGoodLogDao;
import com.qtz.ht.good.vo.HtGoodLog;
/**
 * <p>Title:HtGoodLogDaoImpl</p>
 * <p>Description:商户商品操作记录DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
@Repository("htGoodLogDaoImpl")
public class HtGoodLogDaoImpl extends MyBaitsDaoImpl<HtGoodLog,Long> implements HtGoodLogDao {
	/**MYBatis命名空间名*/
	private static String preName = HtGoodLogDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}