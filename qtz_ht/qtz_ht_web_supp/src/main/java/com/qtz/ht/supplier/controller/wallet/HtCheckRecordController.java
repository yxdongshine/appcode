package com.qtz.ht.supplier.controller.wallet;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.ht.supplier.controller.BaseController;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.ht.wallet.page.HtCheckRecordPage;
import com.qtz.ht.wallet.service.HtBankCardService;
import com.qtz.ht.wallet.service.HtCheckRecordService;
import com.qtz.ht.wallet.service.HtWalletService;
import com.qtz.ht.wallet.vo.HtBankCard;
import com.qtz.ht.wallet.vo.HtCheckRecord;
import com.qtz.ht.wallet.vo.HtWallet;

/**
 * <p>Title:HtCheckRecordController</p>
 * <p>Description:商户提现记录表Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-29
 */
@RestController
@RequestMapping("v1.0/withdrawals")
public class HtCheckRecordController extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(HtCheckRecordController.class);
	/**注入商户提现记录表Service类*/
	@Resource(name="htCheckRecordServiceImpl")
	private HtCheckRecordService htCheckRecordService;
	@Resource(name="htBusinessServiceImpl")
	private HtBusinessService htBusinessService;
	@Resource(name="htBankCardServiceImpl")
	private HtBankCardService htBankCardService;
	/**注入钱包表Service类*/
	@Resource(name="htWalletServiceImpl")
	private HtWalletService htWalletService;
	/** 
	* 【申请提现】
	* @param request
	* @param response
	* @param amount
	* 					提现金额
	* @return
	*/
	@RequestMapping(value="add")
	public void add(@RequestParam("token") String sid,@RequestParam("amount") Double amount,
			@RequestParam("bankId")Long bankId, HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user= getUser(sid);
			HtCheckRecord result = htCheckRecordService.addVo(user,amount,bankId);
			RespJsonPHandler.respOK(result,response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/** 
	* 【分页】
	* @param req
	* @param page
	* @return
	*/
	@RequestMapping(value="page")
	public void page(@RequestParam("token") String sid,HtCheckRecordPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user = getUser(sid);
			page.setBusiId(user.getBusiId());
			RespJsonPHandler.respOK(htCheckRecordService.query(page, null),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/** 
	* 【编辑】
	* @param req
	* @param id
	* @return
	*/
	@RequestMapping(value="input")
	public void input(@RequestParam("token") String sid,@RequestParam("dmId")Long dmId,
			HttpServletRequest request, HttpServletResponse response){
		HtCheckRecord vo = new HtCheckRecord();
		try {
			if(null != dmId)
				vo = htCheckRecordService.findVo(dmId,null);
			RespJsonPHandler.respOK(vo,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	/** 
	* 【编辑】
	* @param req
	* @param id
	* @return
	*/
	@RequestMapping(value="show")
	public void show(@RequestParam("token") String sid,HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user = getUser(sid);
			HtBusiness b = this.htBusinessService.findVo(user.getBusiId(), null);
			if (Constants.TWO != b.getIsFrozen().intValue()) {	//是否冻结(1：冻结；2：不冻结)
				throw new ServiceException(ExceptionConstants.ERRORCODE_7,"您正在冻结中，暂时不能提现");
			}
			if (Constants.TWO != b.getIsShield().intValue()) {//是否屏蔽(1：屏蔽；2：不屏蔽)
				throw new ServiceException(ExceptionConstants.ERRORCODE_7,"您正在屏蔽中，暂时不能提现");
			}
			HtBankCard obj = new HtBankCard();
			obj.setBusiId(user.getBusiId());
			HtBankCard c = this.htBankCardService.findList(obj).get(0);
			String cardnum = c.getCardNum();
			if (null != cardnum && cardnum.length() >= 4) {
				cardnum = cardnum.substring(cardnum.length() - 4);
			}
			JSONObject result = new JSONObject();
			result.put("bankname", c.getBankName());
			result.put("cardnum", cardnum);
			result.put("cardholdername", c.getCardholder());
			result.put("bankId", c.getDmId());
			HtWallet htWallet =htWalletService.getWalletByUser(user.getBusiId(),UserType.BUSINESS.value());
			if(htWallet !=null){
				result.put("canWithdrawalsMoney", htWallet.getTotalPaymentGoods());
			}
			RespJsonPHandler.respOK(result,response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}