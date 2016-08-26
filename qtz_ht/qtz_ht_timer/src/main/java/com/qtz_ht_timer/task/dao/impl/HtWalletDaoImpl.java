package com.qtz_ht_timer.task.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz_ht_timer.task.dao.HtWalletDao;
import com.qtz_ht_timer.task.vo.HtWallet;
/**
 * <p>Title:HtWalletDaoImpl</p>
 * <p>Description:钱包DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
@Repository("htWalletDaoImpl")
public class HtWalletDaoImpl extends MyBaitsDaoImpl<HtWallet,Long> implements HtWalletDao {
	/**MYBatis命名空间名*/
	private static String preName = HtWalletDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public HtWallet getWalletByUser(Long busiId, int userType) throws DaoException {
		
		return getMyBaitsTemplate().getSqlSession().getMapper(HtWalletDao.class).getWalletByUser(busiId, userType);
	}
}