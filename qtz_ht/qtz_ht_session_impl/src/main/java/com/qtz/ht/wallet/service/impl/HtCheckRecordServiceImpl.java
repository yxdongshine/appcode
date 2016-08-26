package com.qtz.ht.wallet.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.FifteenLongId;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.ht.enums.PresentStateEnum;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.ht.util.BeanUtils;
import com.qtz.ht.wallet.dao.HtCheckRecordDao;
import com.qtz.ht.wallet.service.HtBankCardService;
import com.qtz.ht.wallet.service.HtCheckRecordService;
import com.qtz.ht.wallet.service.HtWalletService;
import com.qtz.ht.wallet.vo.HtBankCard;
import com.qtz.ht.wallet.vo.HtCheckRecord;
import com.qtz.ht.wallet.vo.HtWallet;

import cache.BaseProperties;

/**
 * <p>Title:HtCheckRecordServiceImpl</p>
 * <p>Description:商户提现记录表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
@Service("htCheckRecordServiceImpl")
public class HtCheckRecordServiceImpl extends BaseServiceImpl<HtCheckRecord,Long> implements HtCheckRecordService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtCheckRecordServiceImpl.class);
	/**注入商户提现记录表DAO接口类*/
	@Resource(name="htCheckRecordDaoImpl")
    private HtCheckRecordDao dao;
	@Resource(name="htBusinessServiceImpl")
	private HtBusinessService htBusinessService;
	@Resource(name="htBankCardServiceImpl")
	private HtBankCardService htBankCardService;
	@Resource(name="fifteenLongIdImpl")
	private FifteenLongId fifteenLongId;
	@Autowired
	private HtWalletService htWalletService;
    
	
	
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtCheckRecord,Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	@Override
	public HtCheckRecord addVo(HtUser user, Double amount,Long bankId) throws ServiceException{
		if (user == null || null == amount || amount.doubleValue() <= 0) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入參错误");
		}
		//一天只能提现一次 .测试时去掉
//		long endTime = System.currentTimeMillis();
//		int count = this.findCount(user.getBusiId(),DateUtil.getStartOfTheDay(endTime),endTime);
//		if (count > 0) {
//			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"一天只能提现一次");
//		}
		HtBusiness b = this.htBusinessService.findVo(user.getBusiId(), null);
		if (Constants.TWO != b.getIsFrozen().intValue()) {	//是否冻结(1：冻结；2：不冻结)
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"您正在冻结中，暂时不能提现");
		}
		if (Constants.TWO != b.getIsShield().intValue()) {//是否屏蔽(1：屏蔽；2：不屏蔽)
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"您正在屏蔽中，暂时不能提现");
		}
		HtWallet wallet =htWalletService.getWalletByUser(user.getBusiId(),UserType.BUSINESS.value());
		if (wallet == null || wallet.getTotalPaymentGoods() == null || amount.doubleValue() > wallet.getTotalPaymentGoods().doubleValue()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"您的可提现金额暂时不足以支持本次提现！");
		}
		htWalletService.modWalletTotalPaymentGoods(wallet.getDmId(), amount);
		HtBankCard bankCard = htBankCardService.findVo(bankId, null);
		if (bankCard == null) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入參错误");
		}
		Long dmId = this.fifteenLongId.nextId(),currentTime = System.currentTimeMillis();
		//冻结时间 配置参数单位为 小时
		long releasedate = currentTime.longValue() + Integer.valueOf(BaseProperties.getBaseProperties("FREEZING_TIME")) * 24 * 60 * 60 * 1000l;
		//添加提现
		HtCheckRecord r = new HtCheckRecord(dmId, user.getBusiId(), currentTime, user.getDmId(),
				amount, bankCard.getBankName(), bankCard.getBankBranch(), bankCard.getCardNum(),
				bankCard.getAccountType(), bankCard.getCardholder(), PresentStateEnum.APPLY.getValue(),
				dmId, user.getDmId(), currentTime, null, null, null, null, null, releasedate);
		r = super.addVo(r);
		HtCheckRecord result = new HtCheckRecord();
		BeanUtils.copyProperties(r, result);
		result.setBankno(result.getBankno().substring(result.getBankno().length() - 4));
		return result;
	}
	
	
	@Override
	public int findCount(Long busiId, long startTime, long endTime) throws ServiceException{
		try {
			return dao.findCount(busiId,startTime,endTime);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"查询失败",e);
		}
	}
	@Override
	public void addOnceMore(HtCheckRecord htCheckRecord) throws ServiceException {
		// TODO Auto-generated method stub
		Long dmId = this.fifteenLongId.nextId();
		htCheckRecord.setDmId(dmId);
		//存进数据库
		super.addVo(htCheckRecord);
	}
}