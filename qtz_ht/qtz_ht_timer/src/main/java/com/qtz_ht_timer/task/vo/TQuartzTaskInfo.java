package com.qtz_ht_timer.task.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * 任务信息封装  类
 * ClassName: TQuartzTaskInfo <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月6日 上午9:19:11 <br/> 
 * 
 * @author yxd 
 * @version
 */
public class TQuartzTaskInfo extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1244611013904384L;

	
	/**
	 * 周期性任务运行表达式
	 */
	private String exeTimeStr;
	private Long exeEndTime;
	
	
	public TQuartzTaskInfo() {
		super();
	}
	/**
	 * 
	 * Creates a new instance of TQuartzTaskInfo. 
	 * 
	 * @param exeBeanId 执行的名称
	 * @param buId 任务类型
	 * @param qt_id 任务记录编号 唯一
	 * @param exeStartTime 执行时间
	 */
	public TQuartzTaskInfo(String exeBeanId,Object obj,long qt_id,Long exeStartTime){
		super();
		this.exeBeanId =  exeBeanId;
		this.obj = obj;
		this.dmId = qt_id;
		this.exeStartTime = exeStartTime;
	}

	/**
	 * 
	 * Creates a new instance of TQuartzTaskInfo. 
	 * 
	 * @param exeBeanId 执行的名称
	 * @param buId 任务类型
	 * @param qt_id 任务记录编号 唯一
	 * @param String exeTimeStr
	 */
	public TQuartzTaskInfo(String exeBeanId,Object obj,long qt_id,String exeTimeStr){
		super();
		this.exeBeanId =  exeBeanId;
		this.obj = obj;
		this.dmId = qt_id;
		this.exeTimeStr = exeTimeStr;
	}

	
	public TQuartzTaskInfo(String taskName, Object obj, Integer crTaskMsgId,
			String abendTaskMsgIds, Long crttm, Long exeStartTime,
			Long exeEndTime, Long exeAbendTime, Integer exeStatus,
			Integer exeResult, String exeLog, String exeBeanId) {
		super();
		this.taskName = taskName;
		this.obj = obj;
		this.crttm = crttm;
		this.exeStartTime = exeStartTime;
		this.exeStatus = exeStatus;
		this.exeResult = exeResult;
		this.exeBeanId = exeBeanId;
	}
	public Long getId() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Long getCrttm() {

	public Long getExeEndTime() {
		return exeEndTime;
	}
	public void setExeEndTime(Long exeEndTime) {
		this.exeEndTime = exeEndTime;
	}
	
	public String getExeTimeStr() {
		return exeTimeStr;
	}
	public void setExeTimeStr(String exeTimeStr) {
		this.exeTimeStr = exeTimeStr;
	}
	
	public String toString() {
}