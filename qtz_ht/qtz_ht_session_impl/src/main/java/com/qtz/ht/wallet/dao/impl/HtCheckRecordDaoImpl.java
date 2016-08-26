package com.qtz.ht.wallet.dao.impl;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.ht.wallet.dao.HtCheckRecordDao;
import com.qtz.ht.wallet.vo.HtCheckRecord;
/**
 * <p>Title:HtCheckRecordDaoImpl</p>
 * <p>Description:商户提现记录表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
@Repository("htCheckRecordDaoImpl")
public class HtCheckRecordDaoImpl extends MyBaitsDaoImpl<HtCheckRecord,Long> implements HtCheckRecordDao {
	/**MYBatis命名空间名*/
	private static String preName = HtCheckRecordDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public int findCount(Long busiId, long startTime, long endTime) throws DaoException {
		Map<String, Long> map = new HashMap<>();
		map.put("busiId", busiId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return getMyBaitsTemplate().getSqlSession().selectOne(getPreName() + ".findCountByTime", map);
	}
}