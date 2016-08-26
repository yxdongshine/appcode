package com.qtz_ht_timer.util;

import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import com.mall.core.exception.ServiceException;



/**
 * 定时器 管理类
 * ClassName: QuartzUtils <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月6日 上午8:58:29 <br/> 
 * 
 * @author yxd 
 * @version
 */
public class QuartzUtils {
	
	
	private static SchedulerFactory gSchedulerFactory = null;
	private static String JOB_GROUP_NAME = "HT_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "HT_TRIGGERGROUP_NAME";

	/*
	 * 变成单列模式
	 */
	private QuartzUtils() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 单利提供接口
	 * getSchedulerFactory:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @return
	 */
	public static SchedulerFactory getSchedulerFactory(){
		if(gSchedulerFactory==null){
			gSchedulerFactory = new StdSchedulerFactory();
		}
		return gSchedulerFactory;
	}
	/**
	 * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * @param jobName
	 *            任务名
	 * @param cls
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @param dataMap
	 *            数据map，可为null
	 *            
	 *            @author yxd 
	 * @throws ServiceException 
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void addJob(String jobName, Class<?> cls, String time,Map<String,Object> dataMap) throws ServiceException {
		try {
			Scheduler sched = getSchedulerFactory().getScheduler();
			JobDetail jobDetail = new JobDetailImpl(jobName, JOB_GROUP_NAME, (Class<? extends Job>) cls);// 任务名，任务组，任务执行类
			
			if(null != dataMap && dataMap.size() > 0)
				jobDetail.getJobDataMap().putAll(dataMap);
			
			// 触发器
			CronTrigger trigger = new CronTriggerImpl(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
			((CronTriggerImpl) trigger).setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Description: 添加一个定时任务
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            任务
	 * @param time
	 * @author yxd 
	 *            时间设置，参考quartz说明文档
	 * @throws ServiceException 
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<?> jobClass, String time) throws ServiceException {
		try {
			Scheduler sched = getSchedulerFactory().getScheduler();
			JobDetail jobDetail = new JobDetailImpl(jobName, jobGroupName, (Class<? extends Job>) jobClass);// 任务名，任务组，任务执行类
			// 触发器
			CronTrigger trigger = new CronTriggerImpl(triggerName, triggerGroupName);// 触发器名,触发器组
			((CronTriggerImpl) trigger).setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName
	 * @param time
	 * @author yxd 
	 * @throws ServiceException 
	 */
	public static void modifyJobTime(String jobName, String time) throws ServiceException {
		try {
			Scheduler sched = getSchedulerFactory().getScheduler();
			TriggerKey tk = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(tk);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				JobDetail jobDetail = sched.getJobDetail(trigger.getJobKey());
				Class<?> objJobClass = jobDetail.getJobClass();
				removeJob(jobName);
				addJob(jobName, objJobClass, time,null);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 * @author yxd 
	 * @throws ServiceException 
	 */
	public static void modifyJobTime(String triggerName, String triggerGroupName, String time) throws ServiceException {
		try {
			Scheduler sched = getSchedulerFactory().getScheduler();
			TriggerKey tk = new TriggerKey(triggerName,triggerGroupName);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(tk);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTrigger ct = (CronTrigger) trigger;
				// 修改时间
				((CronTriggerImpl) ct).setCronExpression(time);
				// 重启触发器
				sched.resumeTrigger(tk);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName
	 * @author yxd 
	 * @throws ServiceException 
	 */
	public static void removeJob(String jobName) throws ServiceException {
		try {
			Scheduler sched = getSchedulerFactory().getScheduler();
			TriggerKey tk = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(tk);
			if (trigger == null) {
				return;
			}
			sched.pauseTrigger(tk);// 停止触发器
			sched.unscheduleJob(tk);// 移除触发器
			sched.deleteJob(trigger.getJobKey());// 删除任务
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【获得一个任务】(使用默认的任务组名)
	* @param jobName  
	* @author yxd 
	 * @throws ServiceException 
	*/
	public static JobDetail getJobDetail(String jobName) throws ServiceException {
		try {
			JobDetail s =null;
			Scheduler sched = getSchedulerFactory().getScheduler();
			TriggerKey tk = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(tk);
			if (trigger == null) {
				return s;
			}
			 s= sched.getJobDetail(trigger.getJobKey());
			if(null != s){
//				System.out.println(s.getFullName() + s.getDescription());
			}
			return s;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Description: 移除一个任务
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @throws ServiceException 
	 * @author yxd 
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) throws ServiceException {
		try {
			Scheduler sched = getSchedulerFactory().getScheduler();
			TriggerKey tk = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(tk);
			if (trigger == null) {
				return;
			}
			sched.pauseTrigger(tk);// 停止触发器
			sched.unscheduleJob(tk);// 移除触发器
			sched.deleteJob(trigger.getJobKey());// 删除任务
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Description:启动所有定时任务
	 * 
	 * @author yxd 
	 * @throws ServiceException 
	 */
	public static void startJobs() throws ServiceException {
		try {
			Scheduler sched = getSchedulerFactory().getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Description:关闭所有定时任务
	 * @author yxd 
	 * @throws ServiceException 
	 */
	public static void shutdownJobs() throws ServiceException {
		try {
		Scheduler sched = getSchedulerFactory().getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
