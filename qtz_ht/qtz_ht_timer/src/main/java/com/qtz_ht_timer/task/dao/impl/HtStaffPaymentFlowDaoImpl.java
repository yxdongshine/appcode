package com.qtz_ht_timer.task.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz_ht_timer.task.dao.HtStaffPaymentFlowDao;
import com.qtz_ht_timer.task.vo.HtStaffPaymentFlow;
/**
 * <p>Title:HtStaffPaymentFlowDaoImpl</p>
 * <p>Description:商户货款流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
@Repository("htStaffPaymentFlowDaoImpl")
public class HtStaffPaymentFlowDaoImpl extends MyBaitsDaoImpl<HtStaffPaymentFlow,Long> implements HtStaffPaymentFlowDao {
	/**MYBatis命名空间名*/
	private static String preName = HtStaffPaymentFlowDao.class.getName();
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
		getMyBaitsTemplate().del(getPreName(), ".delDataByCondition", HtStaffPaymentFlow.class);		
	}
}