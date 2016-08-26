package com.qtz.ht.stat.service;
import javax.sql.rowset.serial.SerialException;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
/**
 * <p>Title:MonthStatService</p>
 * <p>Description:每月营业统计服务接口类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 -xin.tu
 * @version v1.0 2015-11-07
 */
public interface MonthStatService extends BaseService<com.qtz.ht.stat.vo.MonthStat,Long> {
	/**
	 * 
	  * 【统计执行 每月1执行】
	  * @throws SerialException  
	  * @time:2015年11月9日 下午4:52:55
	  * @author 涂鑫
	  * @version
	 */
	public void  saveStartStat()throws ServiceException;
	
}