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

		/** 任务模块的名称  比如现在的帐水流水解冻 */	private String taskName;	//任务的数据	private Object obj;		/** 创建时间 */	private Long crttm;	/** 任务开始执行时间 */	private Long exeStartTime;
	/**
	 * 周期性任务运行表达式
	 */
	private String exeTimeStr;	/** 任务执行结束时间 */
	private Long exeEndTime;	/** 执行状态，0执行中，1待执行/未执行，2已执行，3终止执行（杀掉任务） */	private Integer exeStatus;	/** 执行结果，1执行成功，2执行失败，3延时执行 */	private Integer exeResult;		//大任务的类型实现名称  spring中的name	private String exeBeanId;
	
	
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
	public Long getId() {	    return this.dmId;	}	public void setId(Long id) {	    this.dmId=id;	}	public String getTaskName() {	    return this.taskName;	}	public void setTaskName(String taskName) {	    this.taskName=taskName;	}	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Long getCrttm() {	    return this.crttm;	}	public void setCrttm(Long crttm) {	    this.crttm=crttm;	}	public Long getExeStartTime() {	    return this.exeStartTime;	}	public void setExeStartTime(Long exeStartTime) {	    this.exeStartTime=exeStartTime;	}	public Integer getExeStatus() {	    return this.exeStatus;	}	public void setExeStatus(Integer exeStatus) {	    this.exeStatus=exeStatus;	}	public Integer getExeResult() {	    return this.exeResult;	}	public void setExeResult(Integer exeResult) {	    this.exeResult=exeResult;	}	public String getExeBeanId() {	    return this.exeBeanId;	}	public void setExeBeanId(String exeBeanId) {	    this.exeBeanId=exeBeanId;	}

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
	
	public String toString() {	    return ToStringBuilder.reflectionToString(this);	}
}