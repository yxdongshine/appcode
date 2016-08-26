package com.qtz.ht.wallet.service;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.ht.wallet.vo.HtCheckRecord;

/**
 * <p>Title:HtCheckRecordService</p>
 * <p>Description:商户提现记录表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-28
 */
public interface HtCheckRecordService extends BaseService<HtCheckRecord,Long> {

	/**
	* 【申请提现】
	* @param user
	* 				申请人
	* @param amount
	* 				申请金额 
	* @param bankname
	* 				收款银行 
	* @param bankno
	* 				收款卡号 
	* @param cardholdername
	* 				持卡人名字 
	*/
	HtCheckRecord addVo(HtUser user, Double amount,Long bankId)throws ServiceException;
	/**
	* 【查询指定时间当天记录数】
	* @param busiId
	* 					商家信息ID
	* @param startTime
	* 					开始时间
	* @param endTime
	* 					结束时间
	* @return
	* @throws ServiceException  
	*/
	int findCount(Long busiId, long startTime, long endTime)throws ServiceException;
	
	/**
	 * 
	 * 
	 * 修改状态后再次添加一条状态的记录
	 * addOnceMore:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param htCheckRecord
	 * @throws ServiceException
	 */
	void addOnceMore(HtCheckRecord htCheckRecord)throws ServiceException;
}