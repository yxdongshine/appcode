package com.qtz_ht_timer.drive.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.JobDetail;
import org.springframework.stereotype.Service;

import com.mall.core.common.DateUtil;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz_ht_timer.drive.QuartzScan;
import com.qtz_ht_timer.job.QuartzTaskJob;
import com.qtz_ht_timer.model.TaskResult;
import com.qtz_ht_timer.task.dao.HtPlatformWalletLogDao;
import com.qtz_ht_timer.task.dao.HtStaffPaymentFlowDao;
import com.qtz_ht_timer.task.dao.HtWalletDao;
import com.qtz_ht_timer.task.vo.TQuartzTaskInfo;
import com.qtz_ht_timer.util.QuartzUtils;
import com.qtz_ht_timer.util.TimeConfig;



/**
 *查询出已经过或者 刚好等于当前时间的 流水
 * ClassName: QuartzScanImpl <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月3日 下午6:13:12 <br/> 
 * 
 * @author yxd 
 * @version
 */
@Service
public class QuartzScanImpl implements QuartzScan {
	
	private static LogTool log = LogTool.getInstance(QuartzScanImpl.class);
	
	@Resource(name="htPlatformWalletLogDaoImpl")
	private HtPlatformWalletLogDao htPlatformWalletLogDao;
	@Resource(name="htStaffPaymentFlowDaoImpl")
	private HtStaffPaymentFlowDao htStaffPaymentFlowDao;
	@Resource(name="htWalletDaoImpl")
	private HtWalletDao htWalletDao;
	
	public void addInit() throws ServiceException{
		log.info("-------------------初始化quartz容器job开始--------------");
		String exeTimeStr ="";
		//测试
		exeTimeStr = TimeConfig.EXE_TIME_CONFIG_TEST;
		//实际
		/*Task task =TaskUtils.getTaskByBeanId(TimeConfig.THAW_FORZEN_RECONCILIACTION);
		if(task!=null){
			String time = task.getExeTimeStr();
			if(time!=null &&time.trim().length()>0){
				exeTimeStr = TimeConfig.PRIFIX_EXE_TIME_STR+time.trim()+TimeConfig.SUFFIX_EXE_TIME_STR;
			}
		}*/
		TQuartzTaskInfo info = new TQuartzTaskInfo(TimeConfig.MAIN_START_RECONCILIACTION, new Object(), TimeConfig.MAIN_START_NO, exeTimeStr);
		this.addExe(info);
		log.info("-------------------初始化quartz容器job结束--------------");
	}
	
	/**
	 * 添加任务到调度器
	 */
	public void addExe(TQuartzTaskInfo info) throws ServiceException{
		if(null == info || null == info.getId() || null == info.getObj() || null == info.getExeBeanId() || (null == info.getExeStartTime()&&info.getExeTimeStr()==null)){
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误");
		}
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put(QuartzTaskJob.BEAN_ID, info.getExeBeanId());
		dataMap.put(QuartzTaskJob.BU_ID, info.getObj());
		dataMap.put(QuartzTaskJob.QT_ID, info.getId());
		
		String timeStr ="";
		if(info.getExeTimeStr()!=null&&info.getExeTimeStr().trim().length()>0){
			timeStr  = info.getExeTimeStr();
		}else if(null != info.getExeStartTime()){
			Long exeStartTime = info.getExeStartTime();
			Date date = DateUtil.UnixTimeToDate(exeStartTime);
			timeStr = DateUtil.dateToStr(date,"ss mm HH dd MM ? yyyy");
		}
		String jobName = info.getId()+"";
		
		JobDetail jd = QuartzUtils.getJobDetail(jobName);
		if(null != jd){
			QuartzUtils.removeJob(jobName);
		}
		QuartzUtils.addJob(jobName, QuartzTaskJob.class,timeStr, dataMap);
	}
	
	
	/**
	 * 任务调度器 完成任务后 回调具体任务执行
	 */
	public void addNotfiy(long qtId,TaskResult r) throws ServiceException{
		if(null == r){
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "回调执行结果为空");
		}
	}
	
	
	
	public static void main(String[] args) {
		String timeStr = DateUtil.dateToStr(new Date(),"ss mm HH dd MM ? yyyy");
		System.out.println(timeStr);
	}
}