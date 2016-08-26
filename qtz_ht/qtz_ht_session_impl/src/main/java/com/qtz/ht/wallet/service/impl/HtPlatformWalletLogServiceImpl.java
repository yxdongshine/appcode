package com.qtz.ht.wallet.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.wallet.dao.HtPlatformWalletLogDao;
import com.qtz.ht.wallet.service.HtPlatformWalletLogService;
import com.qtz.ht.wallet.vo.HtPlatformWalletLog;

/**
 * <p>Title:HtPlatformWalletLogServiceImpl</p>
 * <p>Description:平台钱包流水服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
@Service("htPlatformWalletLogServiceImpl")
public class HtPlatformWalletLogServiceImpl extends BaseServiceImpl<HtPlatformWalletLog,Long> implements HtPlatformWalletLogService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtPlatformWalletLogServiceImpl.class);
	/**注入平台钱包流水DAO接口类*/
	@Resource(name="htPlatformWalletLogDaoImpl")
        private HtPlatformWalletLogDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtPlatformWalletLog,Long> getDao() {
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
	public <T> void delDataByCondition(T vo) throws DaoException {
		// TODO Auto-generated method stub
						dao.delDataByCondition(vo);
			
	}
}