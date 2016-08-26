package com.qtz_ht_timer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.common.Arith;
import com.mall.core.common.FifteenLongId;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz_ht_timer.drive.TaskHandle;
import com.qtz_ht_timer.enums.ExeResultEnum;
import com.qtz_ht_timer.enums.UserType;
import com.qtz_ht_timer.model.TaskResult;
import com.qtz_ht_timer.task.dao.HtPlatformWalletLogDao;
import com.qtz_ht_timer.task.dao.HtStaffPaymentFlowDao;
import com.qtz_ht_timer.task.dao.HtWalletDao;
import com.qtz_ht_timer.task.vo.HtPlatformWalletLog;
import com.qtz_ht_timer.task.vo.HtWallet;
import com.qtz_ht_timer.util.TimeConfig;

/**
 * 
 * 这里是自动解冻实现类 的回调操作类  
 * 
 * 对流水线的  平台的钱 打进 钱包 对象内 操作
 * ClassName: RefundAutoHandleImpl <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月3日 下午5:53:19 <br/> 
 * 
 * @author yxd 
 * @version
 */
@Service("paymentPlatformThawImpl")
public class PaymentPlatformThawImpl implements TaskHandle{

	private static LogTool log = LogTool.getInstance(PaymentPlatformThawImpl.class);
	
	@Resource(name="htPlatformWalletLogDaoImpl")
	private HtPlatformWalletLogDao htPlatformWalletLogDao;
	@Resource(name="htStaffPaymentFlowDaoImpl")
	private HtStaffPaymentFlowDao htStaffPaymentFlowDao;
	@Resource(name="htWalletDaoImpl")
	private HtWalletDao htWalletDao;
	@Resource(name="fifteenLongIdImpl")
	private FifteenLongId fifteenLongId;
	/** 平台钱包为信息id*/
	public final long ADMIN_WALLET_ID = 1000l;

	@Override
	public TaskResult modExecute(long qtId, Object vo) throws ServiceException {
		
		TaskResult tResult = new TaskResult();
		
		
		this.modThawForzenCorpaction(tResult, qtId, vo);
		
		return tResult;
	}

	/**
	 * 根据不同的值 不同的流水账处理
	 * thawForzenCorpaction:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param tResult
	 * @param qtId
	 * @param buId
	 * @return
	 * @throws Exception 
	 */
	private TaskResult modThawForzenCorpaction(TaskResult tResult,long qtId, Object vo) throws ServiceException{
		if (vo instanceof List) {
			@SuppressWarnings("unchecked")
			List<HtPlatformWalletLog> list = (List<HtPlatformWalletLog>) vo;
			Double amount = 0d;
			try {
//				平台对账金额
				for (HtPlatformWalletLog p : list) {
					if (p != null && p.getAmount() != null && p.getDmId() != null) {
						amount = Arith.add(amount, p.getAmount());
						//修改该记录的数据库
	                	p.setRecoStatus(TimeConfig.RECONCILIACTION_STATE_FINISHED);
	                	htPlatformWalletLogDao.modVoNotNull(p);
						
						tResult.setExeResult(ExeResultEnum.handleFinished);
	        			tResult.setLog("任务ID已经处理了=" + p.toString());
					}else{
						tResult.setExeResult(ExeResultEnum.FAIL);
	        			tResult.setLog("失败,任务ID不存在=" + p.toString());
	        			log.info("失败,任务ID不存在=" + p.toString());
					}
				}
				//再继续对钱包操作
				HtWallet htWallet = this.findWallet(this.ADMIN_WALLET_ID, UserType.PPUSER.value());
				if(htWallet!=null){
					//修改用户的钱包
					synchronized (htWallet) {
						//对账减少 货款增加
						//原来的对账总额
						double oldTotalReconciliatio= htWallet.getTotalReconciliation() ==null?0:htWallet.getTotalReconciliation();
						
						if(oldTotalReconciliatio >= amount){
							double newTotalReconciliatio = Arith.sub(oldTotalReconciliatio, amount) ;
							htWallet.setTotalReconciliation(newTotalReconciliatio);
							//原来的货款总额
							double oldTotalPaymentGoods = htWallet.getTotalPaymentGoods() ==null?0:htWallet.getTotalPaymentGoods();
							double newTotalPaymentGoods = Arith.add(oldTotalPaymentGoods,amount);
							
							htWallet.setTotalPaymentGoods(newTotalPaymentGoods);
							
							htWalletDao.modVoNotNull(htWallet);
						}else{//不满足事物 回退
							log.error(qtId + "货款数小于退款数");
						}
						
					}
				}		
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
			
		return tResult;
	}
	
	
	
	/**
	 * 根据用户编号 用户类型获取钱包
	 * findWallet:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param userId
	 * @param utype
	 * @return
	 * @throws ServiceException 
	 */
	private HtWallet findWallet (Long userId, int utype) throws ServiceException{
		HtWallet htWallet = null ;
		try {
			htWallet = htWalletDao.getWalletByUser(userId, utype);
			if(htWallet == null){
				htWallet = new HtWallet();
				Long dmId = this.fifteenLongId.nextId();
				htWallet.setDmId(dmId);
				htWallet.setBusiId(userId);
				htWallet.setUserType(utype);
				htWallet.setCreateBy(System.currentTimeMillis());
				htWallet=htWalletDao.addVo(htWallet);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return htWallet;
	}
}