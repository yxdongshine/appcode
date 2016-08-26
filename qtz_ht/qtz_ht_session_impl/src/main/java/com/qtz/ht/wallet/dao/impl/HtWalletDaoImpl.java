package com.qtz.ht.wallet.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.ht.wallet.dao.HtWalletDao;
import com.qtz.ht.wallet.vo.HtWallet;
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
	@Override
	public Double getUnpaidPayment(int userType) {
		return getMyBaitsTemplate().getSqlSession().getMapper(HtWalletDao.class).getUnpaidPayment(userType);
	}
	@Override
	public Double getPaidPayment(int userType) {
		return getMyBaitsTemplate().getSqlSession().getMapper(HtWalletDao.class).getPaidPayment(userType);
	}
	@Override
	public void modWallet(Long busiId, Double amount) throws DaoException {
		if (null == busiId || null == amount) {
			throw new DaoException("入参错误");
		}
		HtWallet wallet = new HtWallet();
		wallet.setBusiId(busiId);
		wallet.setTotalReconciliation(amount);
		getMyBaitsTemplate().getSqlSession().update(getPreName() + ".modWallet", wallet);
	}
	@Override
	public void countWalletTotalRefund(Long busiId, Double amount) throws DaoException {
		// TODO Auto-generated method stub
		if (null == busiId || null == amount) {
			throw new DaoException("入参错误");
		}
		HtWallet wallet = new HtWallet();
		wallet.setBusiId(busiId);
		wallet.setTotalRefund(amount);
		getMyBaitsTemplate().getSqlSession().update(getPreName() + ".countWalletTotalRefund", wallet);
	}
	@Override
	public void modWalletTotalPaymentGoods(Long buessId, Double amount) throws DaoException {
		// TODO Auto-generated method stub
		if (null == buessId || null == amount) {
			throw new DaoException("入参错误");
		}
		HtWallet wallet = new HtWallet();
		wallet.setDmId(buessId);;
		wallet.setTotalPaymentGoods(-amount);
		wallet.setTotalCash(amount);
		getMyBaitsTemplate().getSqlSession().update(getPreName() + ".modWalletTotalPaymentGoods", wallet);
	}
}