package com.qtz.ht.wallet.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.ht.wallet.dao.HtPlatformWalletLogDao;
import com.qtz.ht.wallet.vo.HtPlatformWalletLog;
/**
 * <p>Title:HtPlatformWalletLogDaoImpl</p>
 * <p>Description:平台钱包流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
@Repository("htPlatformWalletLogDaoImpl")
public class HtPlatformWalletLogDaoImpl extends MyBaitsDaoImpl<HtPlatformWalletLog,Long> implements HtPlatformWalletLogDao {
	/**MYBatis命名空间名*/
	private static String preName = HtPlatformWalletLogDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	
	@Override
	public <T> void delDataByCondition(T vo) throws DaoException {
		// TODO Auto-generated method stub
		getMyBaitsTemplate().del(getPreName(), "delDataByCondition", vo);				
	}
}