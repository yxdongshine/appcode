package com.qtz_ht_timer.model;

import java.io.Serializable;

import com.qtz_ht_timer.enums.ExeResultEnum;

/**
 * 任务执行后返回结果类
 * ClassName: TaskResult <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月3日 下午7:15:52 <br/> 
 * 
 * @author yxd 
 * @version
 */
public class TaskResult implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 1244611013904385L;
	/**执行结果，1执行成功，2执行失败，3延时执行*/
	private ExeResultEnum exeResult;
	/**执行日志*/
	private String log;
	/**
	 * 任务类型
	 */
	private Object task_type;
	
	public TaskResult() {
		super();
	}
	public TaskResult(ExeResultEnum exeResult, String log) {
		super();
		this.exeResult = exeResult;
		this.log = log;
	}
	public ExeResultEnum getExeResult() {
		return exeResult;
	}
	public void setExeResult(ExeResultEnum exeResult) {
		this.exeResult = exeResult;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public Object getTask_type() {
		return task_type;
	}
	public void setTask_type(Object task_type) {
		this.task_type = task_type;
	}
	
}