package com.qtz_ht_timer.drive;

import com.mall.core.exception.ServiceException;
import com.qtz_ht_timer.model.TaskResult;
import com.qtz_ht_timer.task.vo.TQuartzTaskInfo;


/**
 * <p>Title:QuartzScan</p>
 * <p>Description:(时效任务扫描类)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2015-7-23
 */
public interface QuartzScan {
	
	/** 
	* 【启动初始化】
	* @throws ServiceException  
	*/
	public void addInit() throws ServiceException;
	
	/** 
	* 【添加执行】
	* @param info  
	*/
	public void addExe(TQuartzTaskInfo info) throws ServiceException;
	
	/** 
	* 【时效任务处理完成回调方法】
	* @param qtId
	* 			时效任务id
	* @param r
	* 			执行结果
	*/
	public void addNotfiy(long qtId,TaskResult r) throws ServiceException;
	
	/** 
	* 【追加日志】(info)
	* @param tInfo
	* @param log
	* @return
	* @throws ServiceException  
	*/
	//public JSONArray appendExeLog(TQuartzTaskInfo tInfo , String log) throws ServiceException;
}