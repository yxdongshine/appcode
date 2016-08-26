
package com.qtz_ht_timer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz_ht_timer.drive.QuartzScan;
import com.qtz_ht_timer.drive.TaskHandle;
import com.qtz_ht_timer.enums.ExeResultEnum;
import com.qtz_ht_timer.model.TaskResult;
import com.qtz_ht_timer.task.dao.HtPlatformWalletLogDao;
import com.qtz_ht_timer.task.dao.HtStaffPaymentFlowDao;
import com.qtz_ht_timer.task.vo.HtPlatformWalletLog;
import com.qtz_ht_timer.task.vo.HtStaffPaymentFlow;
import com.qtz_ht_timer.task.vo.TQuartzTaskInfo;
import com.qtz_ht_timer.util.TimeConfig;

/** 
 * ClassName:MainAutoHandleImpl <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年6月7日 下午12:00:04 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
@Service("mainAutoHandleImpl")
public class MainAutoHandleImpl implements TaskHandle {
	
	private static LogTool log = LogTool.getInstance(MainAutoHandleImpl.class);
	@Resource(name="htPlatformWalletLogDaoImpl")
	private HtPlatformWalletLogDao htPlatformWalletLogDao;
	@Resource(name="htStaffPaymentFlowDaoImpl")
	private HtStaffPaymentFlowDao htStaffPaymentFlowDao;
//	@Resource(name="htWalletDaoImpl")
//	private HtWalletDao htWalletDao;
//	@Resource(name="fifteenLongIdImpl")
//	private FifteenLongId fifteenLongId;
	@Resource(name="quartzScanImpl")
	private QuartzScan quartzScanImpl;
	@Override
	public TaskResult modExecute(long qtId, Object obj) throws ServiceException {
		// TODO Auto-generated method stub
			//查询解冻的记录
		TaskResult tResult = new TaskResult();


		log.info("-------------------初始化quartz容器job开始--------------");
		long nowTime = System.currentTimeMillis();
		
		//查询平台流水记录表
		HtPlatformWalletLog hpw = new HtPlatformWalletLog();
		hpw.setRecoStatus(TimeConfig.RECONCILIACTION_STATE_FORZEN);
		hpw.setReleaseTime(nowTime);
//		hpw.setReleaseTime(1467388800999l);
		List<HtPlatformWalletLog> hpwlist =null;
		try {
			hpwlist = htPlatformWalletLogDao.findList(hpw);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		//查询商户流水记录表
		HtStaffPaymentFlow hsf = new HtStaffPaymentFlow();
		hsf.setRecoStatus(TimeConfig.RECONCILIACTION_STATE_FORZEN);
		hsf.setReleaseTime(nowTime);
//		hsf.setReleaseTime(1467388800999l);
		List<HtStaffPaymentFlow> hsfList =null;
		try {
			hsfList = htStaffPaymentFlowDao.findList(hsf);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		//添加平台流水任务统计
		quartzScanImpl.addExe(new TQuartzTaskInfo(TimeConfig.PLATFORM_THAW_FORZEN_RECONCILIACTION, hpwlist, TimeConfig.TASK_TYPE_OF_ADMIN, 2+nowTime));
		
		//添加商户流水线统计
		TQuartzTaskInfo info = new TQuartzTaskInfo(TimeConfig.SUPP_THAW_FORZEN_RECONCILIACTION, hsfList, TimeConfig.TASK_TYPE_OF_HTSTUFF, 1+nowTime);
		quartzScanImpl.addExe(info);
		log.info("-------------------初始化quartz容器job结束--------------");
		
		tResult.setExeResult(ExeResultEnum.SUCCESS);
		tResult.setLog("处理成功,任务ID="+qtId);
		log.info("任务ID已经处理="+qtId);
		
		return tResult;
	}

}
  