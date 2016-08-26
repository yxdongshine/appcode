package com.qtz_ht_timer.task.page;
import com.mall.core.vo.Pager;
import com.qtz_ht_timer.task.vo.TQuartzTaskInfo;


/**
 * 任务分页模型
 * ClassName: TQuartzTaskInfoPage <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月6日 上午9:18:51 <br/> 
 * 
 * @author yxd 
 * @version
 */
public class TQuartzTaskInfoPage extends Pager<TQuartzTaskInfo,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1244611013904385L;

		/** 任务名称 */	private java.lang.String taskName;	/** 具体业务ID */	private java.lang.Long buId;	/** 创建任务消息ID */	private java.lang.Integer crTaskMsgId;	/** 终止任务消息ID（多个逗号分隔） */	private java.lang.String abendTaskMsgIds;	/** 创建时间 */	private java.lang.Long crttm;	/** 任务开始执行时间 */	private java.lang.Long exeStartTime;	/** 任务执行结束时间 */	private java.lang.Long exeEndTime;	/** 任务终止执行时间 */	private java.lang.Long exeAbendTime;	/** 执行状态，1待执行/未执行，2已执行，3终止执行（杀掉任务）； */	private java.lang.Integer exeStatus;	/** 执行结果，1执行成功，2执行失败 */	private java.lang.Integer exeResult;	/** 执行日志（多次执行append日志） */	private java.lang.String exeLog;	/** 执行beanId */	private java.lang.String exeBeanId;	public java.lang.Long getId() {	    return this.id;	}	public void setId(java.lang.Long id) {	    this.id=id;	}	public java.lang.String getTaskName() {	    return this.taskName;	}	public void setTaskName(java.lang.String taskName) {	    this.taskName=taskName;	}	public java.lang.Long getBuId() {	    return this.buId;	}	public void setBuId(java.lang.Long buId) {	    this.buId=buId;	}	public java.lang.Integer getCrTaskMsgId() {	    return this.crTaskMsgId;	}	public void setCrTaskMsgId(java.lang.Integer crTaskMsgId) {	    this.crTaskMsgId=crTaskMsgId;	}	public java.lang.String getAbendTaskMsgIds() {	    return this.abendTaskMsgIds;	}	public void setAbendTaskMsgIds(java.lang.String abendTaskMsgIds) {	    this.abendTaskMsgIds=abendTaskMsgIds;	}	public java.lang.Long getCrttm() {	    return this.crttm;	}	public void setCrttm(java.lang.Long crttm) {	    this.crttm=crttm;	}	public java.lang.Long getExeStartTime() {	    return this.exeStartTime;	}	public void setExeStartTime(java.lang.Long exeStartTime) {	    this.exeStartTime=exeStartTime;	}	public java.lang.Long getExeEndTime() {	    return this.exeEndTime;	}	public void setExeEndTime(java.lang.Long exeEndTime) {	    this.exeEndTime=exeEndTime;	}	public java.lang.Long getExeAbendTime() {	    return this.exeAbendTime;	}	public void setExeAbendTime(java.lang.Long exeAbendTime) {	    this.exeAbendTime=exeAbendTime;	}	public java.lang.Integer getExeStatus() {	    return this.exeStatus;	}	public void setExeStatus(java.lang.Integer exeStatus) {	    this.exeStatus=exeStatus;	}	public java.lang.Integer getExeResult() {	    return this.exeResult;	}	public void setExeResult(java.lang.Integer exeResult) {	    this.exeResult=exeResult;	}	public java.lang.String getExeLog() {	    return this.exeLog;	}	public void setExeLog(java.lang.String exeLog) {	    this.exeLog=exeLog;	}	public java.lang.String getExeBeanId() {	    return this.exeBeanId;	}	public void setExeBeanId(java.lang.String exeBeanId) {	    this.exeBeanId=exeBeanId;	}	public String toString() {	    return "[" + "id:" + getId() +"," + "taskName:" + getTaskName() +"," + "buId:" + getBuId() +"," + "crTaskMsgId:" + getCrTaskMsgId() +"," + "abendTaskMsgIds:" + getAbendTaskMsgIds() +"," + "crttm:" + getCrttm() +"," + "exeStartTime:" + getExeStartTime() +"," + "exeEndTime:" + getExeEndTime() +"," + "exeAbendTime:" + getExeAbendTime() +"," + "exeStatus:" + getExeStatus() +"," + "exeResult:" + getExeResult() +"," + "exeLog:" + getExeLog() +"," + "exeBeanId:" + getExeBeanId() +"]";	}
}