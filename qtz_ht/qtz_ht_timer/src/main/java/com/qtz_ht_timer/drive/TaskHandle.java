package com.qtz_ht_timer.drive;

import com.mall.core.exception.ServiceException;
import com.qtz_ht_timer.model.TaskResult;

/**
 * <p>Title:TaskHandle</p>
 * <p>Description:(时效任务处理接口)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年6月23日
 */
public interface TaskHandle {

	/** 
	* 【时效任务执行】(任务执行完毕，return执行结果)
	* @param qtId
	* 			任务Id
	* @param buId
	* 			具体业务数据ID
	* @throws ServiceException  
	*/
	TaskResult modExecute(long qtId , Object vo) throws ServiceException;
	
}
