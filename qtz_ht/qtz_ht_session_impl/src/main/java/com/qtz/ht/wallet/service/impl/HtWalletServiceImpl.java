package com.qtz.ht.wallet.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.enums.UserType;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.ht.wallet.dao.HtWalletDao;
import com.qtz.ht.wallet.service.HtWalletService;
import com.qtz.ht.wallet.vo.HtWallet;

/**
 * <p>Title:HtWalletServiceImpl</p>
 * <p>Description:钱包服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
@Service("htWalletServiceImpl")
public class HtWalletServiceImpl extends BaseServiceImpl<HtWallet,Long> implements HtWalletService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtWalletServiceImpl.class);
	/**注入钱包DAO接口类*/
	@Resource(name="htWalletDaoImpl")
    private HtWalletDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtWallet,Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	@Override
	public HtWallet findPlatformWallet() throws ServiceException {
		HtWallet wallet = this.getWalletByUser(HtStaff.ADMIN_WALLET_ID, UserType.PPUSER.value());
		if (wallet != null) {
			//未付供应商货款
			wallet.setUnpaidPayment(dao.getUnpaidPayment(UserType.BUSINESS.value()));
			//已付供应商货款
			wallet.setPaidPayment(dao.getPaidPayment(UserType.BUSINESS.value()));
		}
		return wallet;
	}
	@Override
	public HtWallet getWalletByUser(Long busiId, int userType) throws ServiceException {
		try {
			return dao.getWalletByUser(busiId,userType);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void modSuppReconciliation(Long busiId, Double staffRevenue) throws ServiceException {
		try {
			dao.modWallet(busiId,staffRevenue);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void modPlatformReconciliation(double platformRevenue)throws ServiceException {
		try {
			dao.modWallet(HtStaff.ADMIN_WALLET_ID,platformRevenue);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void countWalletTotalRefund(Long buessId,double amount) throws ServiceException {
		try {
			dao.countWalletTotalRefund(buessId,amount);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void modWalletTotalPaymentGoods(Long buessId, double amount) throws ServiceException {
		try {
			dao.modWalletTotalPaymentGoods(buessId,amount);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}