package com.qtz.ht.wallet.count.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;

/**
 * <p>Title:DayWithdrawCountService</p>
 * <p>Description:日提现服务接口类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 聂恒
 * @version v1.0 2015-12-17
 */
public interface DayWithdrawCountService extends BaseService<com.qtz.ht.wallet.count.vo.DayWithdrawCount, Long> {
	
	/**
	 * 统计每天提现数据
	 */
	void savaDayWithdrawCount()throws ServiceException;
}
