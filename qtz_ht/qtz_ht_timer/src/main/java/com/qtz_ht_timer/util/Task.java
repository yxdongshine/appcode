package com.qtz_ht_timer.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 任务加载初始化信息类  
 * 
 * 暂时只有名称 和 编号  
 * 
 * 
 * 编号是重点
 * ClassName: Task <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月6日 上午9:56:22 <br/> 
 * 
 * @author yxd 
 * @version
 */
public class Task implements Serializable,Cloneable {
	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 5863398682816321701L;
	/**任务名称*/
	private String name;
	/**处理任务beanId*/
	private String beanId;
	/**
	 * 每天执行的小时刻 数字
	 */
	private String exeTimeStr;
	
	public Task() {
		super();
	}
	
	public Task(String name, int triggerMsgId, int crOrAbend,
			String beanId, long sleepTime, String buIdPropertyName,
			int crMsgId, Map<Integer, Integer> abendMsgIds) {
		super();
		this.name = name;
		
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBeanId() {
		return beanId;
	}
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException	    {
	    return super.clone();
	}

	
    public String getExeTimeStr() {
		return exeTimeStr;
	}

	public void setExeTimeStr(String exeTimeStr) {
		this.exeTimeStr = exeTimeStr;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "task:[ beanid ="+ beanId +" ,name = "+name;
	}
   
}