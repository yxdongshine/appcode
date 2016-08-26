package com.qtz.ht.stat.service;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
/**
 * <p>Title:DayStatService</p>
 * <p>Description:每天营业统计服务接口类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 -xin.tu
 * @version v1.0 2015-11-07
 */
public interface DayStatService extends BaseService<com.qtz.ht.stat.vo.DayStat,Long> {
	
	
	/**
	 * 
	  * 【正在执行统计】
	  * @param time				需要统计那天
	  * @throws ServiceException  
	  * @time:2015年11月7日 下午2:37:36
	  * @author 涂鑫
	  * @version
	 */
	public void saveStartStat(String time) throws ServiceException;
	
}