package com.qtz.ht.wallet.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.wallet.dao.HtStaffPaymentFlowDao;
import com.qtz.ht.wallet.service.HtStaffPaymentFlowService;
import com.qtz.ht.wallet.vo.HtStaffPaymentFlow;

/**
 * <p>Title:HtStaffPaymentFlowServiceImpl</p>
 * <p>Description:商户货款流水服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
@Service("htStaffPaymentFlowServiceImpl")
public class HtStaffPaymentFlowServiceImpl extends BaseServiceImpl<HtStaffPaymentFlow,Long> implements HtStaffPaymentFlowService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtStaffPaymentFlowServiceImpl.class);
	/**注入商户货款流水DAO接口类*/
	@Resource(name="htStaffPaymentFlowDaoImpl")
    private HtStaffPaymentFlowDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtStaffPaymentFlow,Long> getDao() {
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
	///service 层删除条件记录
	@Override
	public void delDataByCondition(HtStaffPaymentFlow t) throws ServiceException {
			try {
				dao.delDataByCondition(t);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
	}
	@Override
	public HtStaffPaymentFlow getEntityByOrderId(Long orderId, Long staffCode) throws ServiceException{
		
		HtStaffPaymentFlow vo = new HtStaffPaymentFlow();
		vo.setOrderId(orderId);
		vo.setBusiId(staffCode);
		List<HtStaffPaymentFlow> list = super.findList(vo);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}