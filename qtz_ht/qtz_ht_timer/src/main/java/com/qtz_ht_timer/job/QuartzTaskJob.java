package com.qtz_ht_timer.job;


import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.mall.core.common.SpringContextHolder;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz_ht_timer.drive.QuartzScan;
import com.qtz_ht_timer.drive.TaskHandle;
import com.qtz_ht_timer.enums.ExeResultEnum;
import com.qtz_ht_timer.model.TaskResult;
/**
 * 任务类型 调度 底层的多线程实现会调用该接口
 * ClassName: QuartzTaskJob <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月3日 下午7:19:35 <br/> 
 * 
 * @author yxd 
 * @version
 */
public class QuartzTaskJob extends QuartzJobBean {

	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(QuartzTaskJob.class);
	
	public final static String BEAN_ID = "beanId"; //大任务的类型实现名称  spring中的name
	public final static String BU_ID = "data";  //任务的类型  1;//商家流水线  2. 平台流水
	public final static String QT_ID = "qtId";  //具体类型的记录的唯一编号 
	
	protected void executeInternal(JobExecutionContext context) {
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String beanId = dataMap.getString(BEAN_ID);
		Object buId = dataMap.get(BU_ID);
		Long qtId = dataMap.getLong(QT_ID);
		
		if(null == beanId || beanId.trim().length() == 0){
			log.error("beanId无效，终止执行！");
			return;
		}
		log.debug(" 时效任务Job开始。qtId="+qtId+",buId="+buId+",beanId="+beanId);
		
		TaskHandle th = SpringContextHolder.getBean(beanId);
		QuartzScan quartzScan = SpringContextHolder.getBean("quartzScanImpl");
		TaskResult r = null;
		try {
			 r = th.modExecute(qtId,buId);//调用具体业务
			 //设置r的任务类型
			 r.setTask_type(buId);
		} catch (ServiceException e) {
			r = new TaskResult();
			r.setExeResult(ExeResultEnum.FAIL);
			r.setLog("任务执行异常");
			log.error("执行任务异常！qtId="+qtId+",buId="+buId+",beanId="+beanId,e);
		}
		try {
			quartzScan.addNotfiy(qtId, r);//回调通知
		}
		catch (ServiceException e) {
			log.error("回调通知异常", e);
		}
		
		log.debug(" 时效任务Job结束。qtId="+qtId+",buId="+buId+",beanId="+beanId);
	} 
}