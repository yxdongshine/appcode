package com.qtz.ht.personal.controller.wallet;
//package com.qtz.ht.controller.userwallet;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mall.core.common.DateUtil;
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ExceptionCode;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.vo.Pager;
//import com.qtz.ht.controller.BaseController;
//import com.qtz.ht.user.vo.User;
//import com.qtz.ht.userwallet.enums.CashBackFlowActionTypeEnum;
//import com.qtz.ht.userwallet.enums.ExtractApplyActionTypeEnum;
//import com.qtz.ht.userwallet.enums.ExtractApplyStatusEnum;
//import com.qtz.ht.userwallet.enums.PaymentFlowActionTypeEnum;
//import com.qtz.ht.userwallet.enums.PaymentMethodEnum;
//import com.qtz.ht.userwallet.enums.ReconciliationActionTypeEnum;
//import com.qtz.ht.userwallet.enums.ReimburseFlowActionTypeEnum;
//import com.qtz.ht.userwallet.enums.RunSubFlowActionTypeEnum;
//import com.qtz.ht.userwallet.enums.RunSubFlowStatusEnum;
//import com.qtz.ht.userwallet.enums.UserType;
//import com.qtz.ht.userwallet.enums.UserWalletActionTypeEnum;
//import com.qtz.ht.userwallet.model.EnumResultOut;
//import com.qtz.ht.userwallet.model.ReconciliationQuery;
//import com.qtz.ht.userwallet.model.ReconciliationRecordGroupOut;
//import com.qtz.ht.userwallet.model.UserConsumptionFlowGroupOut;
//import com.qtz.ht.userwallet.model.UserConsumptionFlowQuery;
//import com.qtz.ht.userwallet.page.BusinessRunSubFlowPage;
//import com.qtz.ht.userwallet.page.CashBackFlowPage;
//import com.qtz.ht.userwallet.page.ExtractApplyPage;
//import com.qtz.ht.userwallet.page.PaymentFlowPage;
//import com.qtz.ht.userwallet.page.PersonalRunSubFlowPage;
//import com.qtz.ht.userwallet.page.ProxyRunSubFlowPage;
//import com.qtz.ht.userwallet.page.ReconciliationRecordPage;
//import com.qtz.ht.userwallet.page.ReimburseFlowPage;
//import com.qtz.ht.userwallet.page.RunSubOperatePage;
//import com.qtz.ht.userwallet.page.UserConsumptionFlowPage;
//import com.qtz.ht.userwallet.service.BusinessRunSubFlowService;
//import com.qtz.ht.userwallet.service.CashBackFlowCountService;
//import com.qtz.ht.userwallet.service.CashBackFlowService;
//import com.qtz.ht.userwallet.service.ExtractApplyService;
//import com.qtz.ht.userwallet.service.PaymentFlowCountService;
//import com.qtz.ht.userwallet.service.PaymentFlowService;
//import com.qtz.ht.userwallet.service.PersonalRunSubFlowService;
//import com.qtz.ht.userwallet.service.ProxyRunSubFlowService;
//import com.qtz.ht.userwallet.service.ReconciliationRecordService;
//import com.qtz.ht.userwallet.service.ReimburseFlowCountService;
//import com.qtz.ht.userwallet.service.ReimburseFlowService;
//import com.qtz.ht.userwallet.service.RunSubFlowCountService;
//import com.qtz.ht.userwallet.service.RunSubService;
//import com.qtz.ht.userwallet.service.UserConsumptionFlowService;
//import com.qtz.ht.userwallet.service.UserWalletService;
//import com.qtz.ht.userwallet.vo.BusinessRunSubFlow;
//import com.qtz.ht.userwallet.vo.CashBackFlow;
//import com.qtz.ht.userwallet.vo.CashBackFlowCount;
//import com.qtz.ht.userwallet.vo.ExtractApply;
//import com.qtz.ht.userwallet.vo.PaymentFlow;
//import com.qtz.ht.userwallet.vo.PaymentFlowCount;
//import com.qtz.ht.userwallet.vo.PersonalRunSubFlow;
//import com.qtz.ht.userwallet.vo.ProxyRunSubFlow;
//import com.qtz.ht.userwallet.vo.ReconciliationRecord;
//import com.qtz.ht.userwallet.vo.ReimburseFlow;
//import com.qtz.ht.userwallet.vo.ReimburseFlowCount;
//import com.qtz.ht.userwallet.vo.RunSubFlow;
//import com.qtz.ht.userwallet.vo.RunSubFlowCount;
//import com.qtz.ht.userwallet.vo.UserConsumptionFlow;
//import com.qtz.ht.userwallet.vo.UserWallet;
//import com.qtz.ht.wallet.service.BankCardInfoService;
//import com.qtz.ht.wallet.vo.BankCardInfo;
//
///**
// * 会员钱包APP接口
// * 
// * <p>
// * Title:GoodsCategory
// * </p>
// * <p>
// * Description:(类描述)
// * </p>
// * <p>
// * Copyright: Copyright (c) 2016
// * </p>
// * <p>
// * Company: 深圳市擎天柱信息科技有限公司
// * </p>
// * 
// * @author 谭林清 - tanlinqingaction@126.com
// * @version v1.0 2016年3月2日
// */
//@RestController
//@RequestMapping(value = "v1.0/userwallet/")
//public class UserWalletController extends BaseController {
//
//	@Autowired
//	private UserWalletService userWalletService;
//	@Autowired
//	private ReconciliationRecordService reconciliationRecordService;
//	@Autowired
//	private ExtractApplyService extractApplyService;
//	@Autowired
//	private RunSubService runSubService;
//	@Autowired
//	private ReimburseFlowService reimburseFlowService;
//	@Autowired
//	private UserConsumptionFlowService userConsumptionFlowService;
//	@Autowired
//	private CashBackFlowService cashBackFlowService;
//	@Autowired
//	private PaymentFlowService paymentFlowService;
//	@Autowired
//	private RunSubFlowCountService runSubFlowCountService;
//	@Autowired
//	private PersonalRunSubFlowService personalRunSubFlowService;
//	@Autowired
//	private BusinessRunSubFlowService businessRunSubFlowService;
//	@Autowired
//	private PaymentFlowCountService paymentFlowCountService;
//	@Autowired
//	private ReimburseFlowCountService reimburseFlowCountService;
//	@Autowired
//	private CashBackFlowCountService cashBackFlowCountService;
//	@Autowired
//	private BankCardInfoService bankCardInfoService;
//	@Autowired
//	private ProxyRunSubFlowService proxyRunSubFlowService;
//
//	// /////////////////////////用户钱包业务//////////////////////////////////////////
//
//	/**
//	 * 获取用户钱包信息
//	 * 
//	 * @param sid
//	 *            用户登录票据
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "getUserWalletByUserId", method = RequestMethod.POST)
//	public void getUserWalletByUserId(@RequestHeader("token") String sid, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			Long userId = user.getDmId();
//			// 查询用户钱包
//			UserWallet wallet = userWalletService.findUserWallet(userId, user.getUserType());
//			if (wallet == null) {
//				return;
//			}
////			JSONObject result = new JSONObject();
////			result.put("runSubTotalAmount", wallet.getRunSubTotalAmount());
////			result.put("reconciliationTotalAmount", wallet.getReconciliationTotalAmount());
////			result.put("goodsTotalAmount", wallet.getGoodsTotalAmount());
////			result.put("totalAmount", wallet.getTotalAmount());
////			result.put("extractTotalAmount", wallet.getExtractTotalAmount());
//			JSONObject result = userWalletService.findWallerToJson(userId, user.getUserType());
//			if (null == result) {
//				RespHandler.respError(RespMsg.get_user_wallet_fail, response);
//			}else{
//				RespHandler.respOK(result, response);
//			}
////			JSONObject w = new JSONObject();
////			w.put("runSubTotalAmount", wallet.getRunSubTotalAmount());
////			w.put("reimburseTotalAmount", wallet.getReimburseTotalAmount());
////			w.put("cashBackTotalAmount", wallet.getCashBackTotalAmount());
////			w.put("goodsTotalAmount", wallet.getGoodsTotalAmount());
////			w.put("reconciliationTotalAmount", wallet.getReconciliationTotalAmount());
////			w.put("totalAmount", wallet.getTotalAmount());
////			w.put("extractTotalAmount", wallet.getExtractTotalAmount());
////			w.put("goldTotalAmount", wallet.getGoldTotalAmount());
////			w.put("hasPayPwd", wallet.getPayPwd() == null ? false : true);
////			switch (user.getUserType()) {
////			case 1:
////				w.put("questionurl", "http://topic.yw01.com/wallet_user.html");
////				break;
////			case 2:
////				w.put("questionurl", "http://topic.yw01.com/wallet_seller.html");
////				break;
////
////			default:
////				break;
////			}
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_fail, response);
//		}
//	}
//
//	/**
//	 * 【获取用户钱包及我的银行卡】
//	 * 
//	 * @param sid
//	 *            token 票据
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "getMyWalletAndBankCard", method = RequestMethod.GET)
//	public void getMyWalletAndBankCard(@RequestHeader("token") String sid, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			Long userId = user.getDmId();
//			// 查询用户钱包
////			UserWallet wallet = userWalletService.findUserWallet(userId, user.getUserType());
//			BankCardInfo b = new BankCardInfo();
//			b.setUserId(user.getDmId());
//			List<BankCardInfo> list = this.bankCardInfoService.findList(b);
//
//			JSONObject json = new JSONObject();
////			if (null != result) {
////				JSONObject w = new JSONObject();
////				w.put("runSubTotalAmount", wallet.getRunSubTotalAmount());
////				w.put("reimburseTotalAmount", wallet.getReimburseTotalAmount());
////				w.put("cashBackTotalAmount", wallet.getCashBackTotalAmount());
////				w.put("goodsTotalAmount", wallet.getGoodsTotalAmount());
////				w.put("goldTotalAmount", wallet.getGoldTotalAmount());
////				w.put("reconciliationTotalAmount", wallet.getReconciliationTotalAmount());
////				w.put("totalAmount", wallet.getTotalAmount());
////				w.put("extractTotalAmount", wallet.getExtractTotalAmount());
////				w.put("hasPayPwd", wallet.getPayPwd() == null ? false : true);
////				switch (user.getUserType()) {
////				case 1:
////					w.put("questionurl", "http://topic.yw01.com/wallet_user.html");
////					break;
////				case 2:
////					w.put("questionurl", "http://topic.yw01.com/wallet_seller.html");
////					break;
////
////				default:
////					break;
////				}
////			}
//			json.put("wallet", userWalletService.findWallerToJson(userId, user.getUserType()));
//			json.put("bankList", list);
//			RespHandler.respOK(json, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.sendPayPwdCode_error(e.getErrorType()), response);
//		}
//	}
//	/**
//	* 【检查验证码是否正确】
//	* @param sid
//	* @param code
//	* 			验证码
//	* @param mphone
//	* 			手机号
//	* @param response
//	* @throws IOException  
//	*/
//	@RequestMapping(value = "verificationCode", method = RequestMethod.POST)
//	public void verificationCode(@RequestHeader("token") String sid,@RequestParam String code , HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			boolean result = this.userWalletService.verificationCode(code, user.getMphonenum());
//			if (result) {
//				RespHandler.respOK(response);
//			}else{
//				RespHandler.respError(RespMsg.user_auth_code_error, response);
//			}
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.user_auth_code_error, response);
//		}
//	}
//	
//	/**
//	 * 检查支付密码是否设置
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param response
//	 */
//	@RequestMapping(value = "checkExistPayPwd", method = RequestMethod.POST)
//	public void checkExistPayPwd(@RequestHeader("token") String sid, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			Long userId = user.getDmId();
//			UserWallet wallet = userWalletService.findUserWallet(userId, user.getUserType());
//			if (wallet == null) {
//				RespHandler.respError(RespMsg.get_user_wallet_fail, response);
//				return;
//			}
//			String payPwd = wallet.getPayPwd();
//			if (!StringUtils.isEmpty(payPwd)) {
//				RespHandler.respOK(response);
//			}else{
//				RespHandler.respError(RespMsg.wallet_pay_pwd_null_error, response);
//			}
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 获取钱包业务筛选类型
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param type
//	 *            业务类型 1对账类型 2分润类型 3退款类型 4消费类型 5返现类型 6货款类型
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "getQueryTypes", method = RequestMethod.POST)
//	public void getQueryTypes(@RequestHeader("token") String sid, @RequestParam Integer type, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			Integer userType = user.getUserType();
//			if (UserWalletActionTypeEnum.RECONCILIATION.getValue() == type) {
//				List<EnumResultOut> result = ReconciliationActionTypeEnum.getTypesByUserType(userType);
//				RespHandler.respOK(result, response);
//			} else if (UserWalletActionTypeEnum.RUNSUB.getValue() == type) {
//				List<EnumResultOut> result = RunSubFlowActionTypeEnum.getTypesByUserType(userType);
//				RespHandler.respOK(result, response);
//			} else if (UserWalletActionTypeEnum.REIMBURSE.getValue() == type) {
//				List<EnumResultOut> result = ReimburseFlowActionTypeEnum.getTypesByUserType(userType);
//				RespHandler.respOK(result, response);
//			} else if (UserWalletActionTypeEnum.CONSUMPTION.getValue() == type) {
//				List<EnumResultOut> result = PaymentMethodEnum.getTypesByUserType(userType);
//				RespHandler.respOK(result, response);
//			} else if (UserWalletActionTypeEnum.CASHBACK.getValue() == type) {
//				List<EnumResultOut> result = CashBackFlowActionTypeEnum.getTypesByUserType(userType);
//				RespHandler.respOK(result, response);
//			} else if (UserWalletActionTypeEnum.PAYMENT.getValue() == type) {
//				List<EnumResultOut> result = PaymentFlowActionTypeEnum.getTypesByUserType(userType);
//				RespHandler.respOK(result, response);
//			}
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	// /////////////////////////用户钱包业务//////////////////////////////////////////\\
//
//	// /////////////////////////钱包对账中业务//////////////////////////////////////////
//
//	/**
//	 * 获取某月每一天对账笔数列表
//	 * 
//	 * @param sid
//	 *            用户登录票据
//	 * @param dateQuery
//	 *            查询月份 格式：2016-03
//	 * @param response
//	 */
//	@RequestMapping(value = "listReconciliationsByGroup", method = RequestMethod.POST)
//	public void listReconciliationsByGroup(@RequestHeader("token") String sid, @RequestParam(required = true) String dateQuery,
//			HttpServletResponse response) throws IOException {
//		try {
//			Long userId = getUserId(sid, response);
//			ReconciliationQuery query = new ReconciliationQuery();
//			query.setUserId(userId);
//			query.setDateQuery(dateQuery);
//
//			List<ReconciliationRecordGroupOut> groups = reconciliationRecordService.listReconciliationsByGroup(query);
//			RespHandler.respOK(groups, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 获取对某天对账记录详情列表
//	 * 
//	 * @param sid
//	 *            用户登录票据
//	 * @param type
//	 *            0全部对账 1货款对账 2分润(平台)对账 3分润(代理商)对账 4分润(商家)对账 5分润(个人)对账 6提现对账
//	 *            7返现对账
//	 * @param date
//	 *            查询日期 格式：2016-3-1
//	 * @param pageIndex
//	 *            当前页
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listReconciliations", method = RequestMethod.POST)
//	public void listReconciliations(@RequestHeader("token") String sid, @RequestParam(required = false) Integer type,
//			@RequestParam(required = true) String dateQuery, @RequestParam Integer pageIndex, HttpServletResponse response) throws IOException {
//		try {
//			Long userId = getUserId(sid, response);
//
//			ReconciliationRecordPage page = new ReconciliationRecordPage();
//			if(type != ReconciliationActionTypeEnum.ALL.getValue()){
//				page.setSourceType(type);
//			}
//			page.setUserId(userId);
//			page.setDateQuery(dateQuery);
//			page.setNowPage(pageIndex);
//			page.setOrderField(ReconciliationRecord.STATISTICSDATE);
//			page.setOrderDirection(false);
//
//			Pager<ReconciliationRecord, Long> records = reconciliationRecordService.query(page, null);
//			RespHandler.respOK(records, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	// /////////////////////////钱包对账中业务//////////////////////////////////////////\\
//
//	// /////////////////////////提现业务//////////////////////////////////////////
//	/**
//	 * 获取提现记录列表
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param date
//	 *            查询日期 格式：2016-3
//	 * @param pageIndex
//	 *            当前页
//	 * @param response
//	 */
//	@RequestMapping(value = "listExtractApply", method = RequestMethod.POST)
//	public void listExtractApply(@RequestHeader("token") String sid, @RequestParam String dateQuery, HttpServletResponse response) throws IOException {
//		if (StringUtils.isEmpty(dateQuery)) {
//			RespHandler.respError(RespMsg.extract_apply_date_cannot_null, response);
//			return;
//		}
//		try {
//			User user = getUser(sid);
//			int userType = user.getUserType();
//			Long userId = user.getDmId();
//			
//			ExtractApply apply = new ExtractApply();
//			apply.setUserId(userId);
//			apply.setUserType(userType);
//			apply.setDateQuery(dateQuery);//按月查询
//
//			List<ExtractApply> applys = extractApplyService.findList(apply);
//			
//			//接口转换DMID
//			if(applys != null && applys.size()>0){
//				for (int i = 0; i < applys.size(); i++) {
//					ExtractApply a = applys.get(i);
//					Integer status = a.getStatus();
//					a.setSaleOrdersId(a.getSingleSourceId()==null?"":a.getSingleSourceId().toString());
//					//不显示预期到账
//					if(ExtractApplyStatusEnum.APPLY.getValue() != status){
//						a.setPredictDate(0L);
//					}
//				}
//			}
//			RespHandler.respOK(applys, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 提交提现
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param sourceType
//	 *            源单类型(1：表示货款提现，2：平台分润提现，3：表示代理商分润提现，4：商家分润提现，
//	 *            5：个人分润提现，6：个人返现提现，7：个人退款提现)
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "createExtractApply", method = RequestMethod.POST)
//	public void createExtractApply(@RequestHeader("token") String sid, @RequestParam Long bankId, @RequestParam Double price,
//			@RequestParam int sourceType, @RequestParam String pwd, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			if (user == null) {
//				RespHandler.respError(RespMsg.user_not_login, response);
//				return;
//			}
//			if (StringUtils.isEmpty(pwd)) {
//				RespHandler.respError(RespMsg.applyDrawing_pwd_param_null, response);
//				return;
//			}
//			if (bankId == null) {
//				RespHandler.respError(RespMsg.applyDrawing_bankId_param_null, response);
//				return;
//			}
//			if (price == null) {
//				RespHandler.respError(RespMsg.applyDrawing_price_param_null, response);
//				return;
//			}
//			userWalletService.modWithdrawals(user.getDmId(), bankId, price, pwd, sourceType);
//			RespHandler.respOK(response);
//		} catch (ServiceException e) {
//			log.error(e);
//			switch (e.getErrorType()) {
//			case ExceptionCode.WALLET_PAY_PWD_ERROR:
//				RespHandler.respError(RespMsg.wallet_pay_pwd_error, response);
//				break;
//			case ExceptionCode.WALLET_PAY_PWD_NULL_ERROR:
//				RespHandler.respError(RespMsg.wallet_pay_pwd_null_error, response);
//				break;
//			case ExceptionCode.EXIST_WITHDRAWAL_APPLY:
//				RespHandler.respError(RespMsg.exist_withdrawal_apply, response);
//				break;
//			case ExceptionCode.DRAWING_LT_PRICE_BUSINESS:
//				RespHandler.respError(RespMsg.drawing_lt_price_business(e.getErrorTitle()), response);
//				break;
//			case ExceptionCode.PERSION_ONLY_2_DRAWING:
//				RespHandler.respError(RespMsg.persion_only_2_drawing, response);
//				break;
//			default:
//				RespHandler.respError(RespMsg.applyDrawing_error(e.getErrorType()), response);
//				break;
//			}
//		}
//	}
//	@RequestMapping(value = "calcWithdrawals")
//	public void calcWithdrawals(@RequestHeader("token") String sid, @RequestParam Long bankId, @RequestParam Double price,
//			@RequestParam int sourceType, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			if (user == null) {
//				RespHandler.respError(RespMsg.user_not_login, response);
//				return;
//			}
//			if (bankId == null) {
//				RespHandler.respError(RespMsg.applyDrawing_bankId_param_null, response);
//				return;
//			}
//			if (price == null) {
//				RespHandler.respError(RespMsg.applyDrawing_price_param_null, response);
//				return;
//			}
//			RespHandler.respOK(userWalletService.calcWithdrawals(user.getDmId(), bankId, price, sourceType),response);
//		} catch (ServiceException e) {
//			log.error(e);
//			switch (e.getErrorType()) {
//			case ExceptionCode.WALLET_PAY_PWD_ERROR:
//				RespHandler.respError(RespMsg.wallet_pay_pwd_error, response);
//				break;
//			case ExceptionCode.WALLET_PAY_PWD_NULL_ERROR:
//				RespHandler.respError(RespMsg.wallet_pay_pwd_null_error, response);
//				break;
//			case ExceptionCode.EXIST_WITHDRAWAL_APPLY:
//				RespHandler.respError(RespMsg.exist_withdrawal_apply, response);
//				break;
//			case ExceptionCode.DRAWING_LT_PRICE_BUSINESS:
//				RespHandler.respError(RespMsg.drawing_lt_price_business(e.getErrorTitle()), response);
//				break;
//			case ExceptionCode.PERSION_ONLY_2_DRAWING:
//				RespHandler.respError(RespMsg.persion_only_2_drawing, response);
//				break;
//			default:
//				RespHandler.respError(RespMsg.applyDrawing_error(e.getErrorType()), response);
//				break;
//			}
//		}
//	}
//
//	// /////////////////////////提现业务//////////////////////////////////////////\\
//
//	// /////////////////////////分润业务//////////////////////////////////////////
//	/**
//	 * 获取某月每一天分润汇总列表
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param dateQuery
//	 *            查询月份，格式：2016-3
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listRunFlowsByGroup", method = RequestMethod.POST)
//	public void listRunFlowsByGroup(@RequestHeader("token") String sid, @RequestParam String dateQuery, HttpServletResponse response)
//			throws IOException {
//		try {
//			User user = getUser(sid);
//			Integer userType = user.getUserType();
//			Long userId = getUserId(sid, response);
//
//			RunSubFlowCount flow = new RunSubFlowCount();
//			flow.setUserId(userId);
//			flow.setDateQuery(dateQuery);
//			flow.setUserType(userType);
//
//			List<RunSubFlowCount> counts = runSubFlowCountService.findList(flow);
//			RespHandler.respOK(counts, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 获取分润详情
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param date
//	 *            查询日期 格式：2016-3-3
//	 * @param type
//	 *            0表示全部明细， 1表示分润收入， 2表示分润提现， 3表示分润消费， 4表示分润退款
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listRunFlows", method = RequestMethod.POST)
//	public void listRunFlows(@RequestHeader("token") String sid, @RequestParam String dateQuery, @RequestParam Integer type,
//			@RequestParam Integer pageIndex, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			Long userId = user.getDmId();
//			int userType = user.getUserType();
//			// 全部明细
//			if (RunSubFlowActionTypeEnum.ALL.getValue() == type) {
//				RunSubOperatePage page = new RunSubOperatePage();
//				page.setUserId(userId);
//				page.setNowPage(pageIndex);
//				page.setUserType(userType);
//				page.setOrderDirection(false);
//				page.setOrderField(RunSubFlow.CREATEON);
//				page.setSourceType(type);
//				
//				Date dateQueryTmp = DateUtil.strToDate(dateQuery);
//				Map<String, Long> dateQueryMap = DateUtil.getOneDayStartAndEndTime(dateQueryTmp);
//				page.setCreateOnStart(dateQueryMap.get("start"));
//				page.setCreateOnEnd(dateQueryMap.get("end"));
//				
//				// 分润表+消费表+提现表+退款表
//				//Pager<RunSubFlow, Long> flows = runSubService.findRunSubActionDetail(page);
//				Pager<RunSubFlow, Long> flows = runSubService.queryListByDay(page);//过程调用
//				RespHandler.respOK(flows, response);
//			} else if (RunSubFlowActionTypeEnum.RUNSUB.getValue() == type) {// 分润收入
//				// 按用户类型查询
//				listRunSubFlowsByUserType(dateQuery, pageIndex, response, userId, userType);
//			} else if (RunSubFlowActionTypeEnum.EXTRACTAPPLY.getValue() == type) {// 分润提现
//				// 按用户类型查询
//				listExtractApplysByUserType(dateQuery, pageIndex, response, userId, userType);
//			} else if (RunSubFlowActionTypeEnum.CONSUMPTION.getValue() == type) {// 查询分润消费
//				// 只有个人客户有消费流水
//				UserConsumptionFlowPage page = new UserConsumptionFlowPage();
//				page.setUserId(userId);
//				page.setDateQuery(dateQuery);
//				page.setSourceType(PaymentMethodEnum.RUNSUB.getValue());
//				page.setNowPage(pageIndex);
//				page.setOrderDirection(false);
//				page.setOrderField(UserConsumptionFlow.STATISTICSDATE);
//				Pager<UserConsumptionFlow, Long> flows = userConsumptionFlowService.query(page, null);
//				RespHandler.respOK(flows, response);
//			} else if (RunSubFlowActionTypeEnum.REIMBURSE.getValue() == type) {// 查询分润退款
//				// 只有个人客户有退款流水
//				ReimburseFlowPage page = new ReimburseFlowPage();
//				page.setUserId(userId);
//				page.setDateQuery(dateQuery);
//				page.setPaymentMethod(PaymentMethodEnum.RUNSUB.getValue());
//				page.setNowPage(pageIndex);
//				page.setOrderDirection(false);
//				page.setOrderField(ReimburseFlow.STATISTICSDATE);
//				Pager<ReimburseFlow, Long> flows = reimburseFlowService.query(page, null);
//				RespHandler.respOK(flows, response);
//			}
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 查询用户的分润提现流水
//	 * 
//	 * @param dateQuery
//	 *            查询日期 2016-3-15
//	 * @param pageIndex
//	 *            当前页
//	 * @param response
//	 * @param userId
//	 *            用户ID
//	 * @param userType
//	 *            用户类型
//	 * @throws ServiceException
//	 * @throws IOException
//	 */
//	private void listExtractApplysByUserType(String dateQuery, Integer pageIndex, HttpServletResponse response, Long userId, int userType)
//			throws ServiceException, IOException {
//		ExtractApplyPage page = new ExtractApplyPage();
//		page.setUserId(userId);
//		//page.setDateQuery(dateQuery);
//		page.setDateQueryDay(dateQuery);//按天查询
//		//page.setStatus(ExtractApplyStatusEnum.APPLIED.getValue());
//		page.setNowPage(pageIndex);
//		page.setOrderDirection(false);
//		page.setOrderField(ExtractApply.STATISTICSDATE);
//		page.setUserType(userType);
//		if (UserType.PERSON.value() == userType) {
//			page.setSourceType(ExtractApplyActionTypeEnum.PERSON_RUNSUB.getValue());// 个人分润提现
//		} else if (UserType.BUSINESS.value() == userType) {
//			page.setSourceType(ExtractApplyActionTypeEnum.BUSINESS_RUNSUB.getValue());// 商家分润提现
//		} else if (UserType.PROXY.value() == userType) {
//			page.setSourceType(ExtractApplyActionTypeEnum.PROXY_RUNSUB.getValue());// 代理商分润提现
//		}
//		Pager<ExtractApply, Long> flows = extractApplyService.query(page, null);
//		
//		//解决分润提现筛选sourceType不正确的情况
//		if(flows != null){
//			List<ExtractApply> applys = flows.getList();
//			if(applys != null && applys.size()>0){
//				for (int i = 0; i < applys.size(); i++) {
//					ExtractApply a = applys.get(i);
//					a.setSourceType(RunSubFlowActionTypeEnum.EXTRACTAPPLY.getValue());
//					Integer status = a.getStatus();
//					//不显示预期到账
//					if(ExtractApplyStatusEnum.APPLY.getValue() != status){
//						a.setPredictDate(0L);
//					}
//				}
//			}
//		}
//		RespHandler.respOK(flows, response);
//	}
//
//	/**
//	 * 查询用户的分润收入流水
//	 * 
//	 * @param dateQuery
//	 *            查询日期 2016-3-15
//	 * @param pageIndex
//	 *            当前页
//	 * @param response
//	 * @param userId
//	 *            用户ID
//	 * @param userType
//	 *            用户类型
//	 * @throws ServiceException
//	 * @throws IOException
//	 */
//	private void listRunSubFlowsByUserType(String dateQuery, Integer pageIndex, HttpServletResponse response, Long userId, int userType)
//			throws ServiceException, IOException {
//		if (UserType.PERSON.value() == userType) {// 个人分润收入
//			PersonalRunSubFlowPage page = new PersonalRunSubFlowPage();
//			page.setUserId(userId);
//			page.setNowPage(pageIndex);
//			page.setOrderDirection(false);
//			page.setOrderField(RunSubFlow.STATISTICSDATE);
//			
//			Date dateQueryTmp = DateUtil.strToDate(dateQuery);
//			Map<String, Long> dateQueryMap = DateUtil.getOneDayStartAndEndTime(dateQueryTmp);
//			page.setCreateOnStart(dateQueryMap.get("start"));
//			page.setCreateOnEnd(dateQueryMap.get("end"));
//			
//			//Pager<PersonalRunSubFlow, Long> flows = personalRunSubFlowService.listFlowsByPage(page);
//			Pager<PersonalRunSubFlow, Long> flows = personalRunSubFlowService.queryListByDay(page);
//			RespHandler.respOK(flows, response);
//		} else if (UserType.BUSINESS.value() == userType) {// 商家分润收入
//			BusinessRunSubFlowPage page = new BusinessRunSubFlowPage();
//			page.setUserId(userId);
//			page.setDateQuery(dateQuery);
//			page.setNowPage(pageIndex);
//			page.setOrderDirection(false);
//			page.setOrderField(RunSubFlow.STATISTICSDATE);
//			Pager<BusinessRunSubFlow, Long> flows = businessRunSubFlowService.query(page,null);
//			RespHandler.respOK(flows, response);
//		} else if (UserType.PROXY.value() == userType) {// 代理商分润收入
//			ProxyRunSubFlowPage page = new ProxyRunSubFlowPage();
//			page.setUserId(userId);
//			page.setDateQuery(dateQuery);
//			page.setNowPage(pageIndex);
//			page.setOrderDirection(false);
//			page.setOrderField(RunSubFlow.STATISTICSDATE);
//			Pager<ProxyRunSubFlow, Long> flows = proxyRunSubFlowService.query(page, null);
//			RespHandler.respOK(flows, response);
//		}
//	}
//
//	// /////////////////////////分润业务//////////////////////////////////////////\\
//
//	// /////////////////////////分润业务//////////////////////////////////////////\\
//
//	// //////////////////////////////////退款业务/////////////////////////////////////////////
//
//	/**
//	 * 获取退款明细列表
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param date
//	 *            查询日期
//	 * @param response
//	 */
//	@RequestMapping(value = "listReimburseFlowsByGroup", method = RequestMethod.POST)
//	public void listReimburseFlowsByGroup(@RequestHeader("token") String sid, @RequestParam String dateQuery, HttpServletResponse response)
//			throws IOException {
//		try {
//			Long userId = getUserId(sid, response);
//			int userType = getUser(sid).getUserType();
//
//			ReimburseFlowCount flow = new ReimburseFlowCount();
//			flow.setDateQuery(dateQuery);
//			flow.setUserId(userId);
//			flow.setUserType(userType);
//
//			List<ReimburseFlowCount> flows = reimburseFlowCountService.findList(flow);
//			RespHandler.respOK(flows, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 获取退款详情
//	 * 
//	 * @param sid
//	 * @param dateQuery
//	 * @param type
//	 *            0全部明细 1退款收入 2退款消费 3退款提现
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listReimburseFlows", method = RequestMethod.POST)
//	public void listReimburseFlows(@RequestHeader("token") String sid, @RequestParam String dateQuery, @RequestParam Integer type,
//			@RequestParam Integer pageIndex, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			Integer userType = user.getUserType();
//			Long userId = user.getDmId();
//			ReimburseFlowPage page = new ReimburseFlowPage();
//			page.setNowPage(pageIndex);
//			//if (ReimburseFlowActionTypeEnum.ALL.getValue() != type) {
//				page.setSourceType(type);
//			//}
//			//page.setDateQuery(dateQuery);
//			page.setUserId(userId);
//			page.setUserType(userType);
//			page.setOrderDirection(false);
//			page.setOrderField(ReimburseFlow.CREATEON);
//			Date dateQueryTmp = DateUtil.strToDate(dateQuery);
//			Map<String, Long> dateQueryMap = DateUtil.getOneDayStartAndEndTime(dateQueryTmp);
//			page.setCreateOnStart(dateQueryMap.get("start"));
//			page.setCreateOnEnd(dateQueryMap.get("end"));
//			//Pager<ReimburseFlow, Long> flows = reimburseFlowService.listReimburseFlows(page);
//			Pager<ReimburseFlow, Long> flows = reimburseFlowService.queryListByDay(page);
//			RespHandler.respOK(flows, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	// //////////////////////////////////退款业务/////////////////////////////////////////////\\
//
//	// //////////////////////////////////消费业务/////////////////////////////////////////////
//
//	/**
//	 * 获取某月每一天消费笔数明细
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param dateQuery
//	 *            查询日期 格式：2016-3
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listUserConsumptionFlowsByGroup", method = RequestMethod.POST)
//	public void listUserConsumptionFlowsByGroup(@RequestHeader("token") String sid, @RequestParam String dateQuery, HttpServletResponse response)
//			throws IOException {
//		try {
//			Long userId = getUserId(sid, response);
//
//			UserConsumptionFlowQuery flow = new UserConsumptionFlowQuery();
//			flow.setUserId(userId);
//			flow.setDateQuery(dateQuery);
//
//			List<UserConsumptionFlowGroupOut> groups = userConsumptionFlowService.listUserConsumptionFlowsByGroup(flow);
//			RespHandler.respOK(groups, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 获取某天消费详情列表
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param date
//	 *            查询日期 格式：2016-3-3
//	 * @param type
//	 *            支付方式 ALL(0, "全部明细"), WEIXIN(1, "微信(现金)消费"), ALIPAY(2,
//	 *            "支付宝(现金)消费"), RUNSUB(3, "分润消费"), CASHBACK(4, "返现消费"),
//	 *            REIMBURSE(5, "退款消费");
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listUserConsumptionFlows", method = RequestMethod.POST)
//	public void listUserConsumptionFlows(@RequestHeader("token") String sid, @RequestParam String dateQuery, @RequestParam Integer type,
//			@RequestParam Integer pageIndex, HttpServletResponse response) throws IOException {
//		try {
//			Long userId = getUserId(sid, response);
//
//			UserConsumptionFlowPage page = new UserConsumptionFlowPage();
//			page.setUserId(userId);
//			if (PaymentMethodEnum.ALL.getValue() != type) {
//				page.setSourceType(type);
//			}
//			page.setDateQuery(dateQuery);
//			page.setNowPage(pageIndex);
//			page.setOrderDirection(false);
//			page.setOrderField(UserConsumptionFlow.CREATEON);
//
//			Pager<UserConsumptionFlow, Long> flows = userConsumptionFlowService.query(page, null);
//			RespHandler.respOK(flows, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	// //////////////////////////////////消费业务/////////////////////////////////////////////\\
//
//	// //////////////////////////////////返现业务/////////////////////////////////////////////
//	/**
//	 * 获取某月每一天返现明细
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param date
//	 *            查询日期
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listCashBackFlowsByGroup", method = RequestMethod.POST)
//	public void listCashBackFlowsByGroup(@RequestHeader("token") String sid, @RequestParam String dateQuery, HttpServletResponse response)
//			throws IOException {
//		try {
//			User user = getUser(sid);
//			Long userId = user.getDmId();
//			int userType = user.getUserType();
//
//			CashBackFlowCount flow = new CashBackFlowCount();
//			flow.setUserId(userId);
//			flow.setDateQuery(dateQuery);
//			flow.setUserType(userType);
//
//			List<CashBackFlowCount> counts = cashBackFlowCountService.findList(flow);
//			RespHandler.respOK(counts, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 获取返现详情列表
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param date
//	 *            查询日期
//	 * @param type
//	 *            业务类型 ALL(0, "全部明细"), CASHBACK(1, "返现收入"), CONSUMPTION(2,
//	 *            "返现消费"), EXTRACT(3, "返现提现");
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listCashBackFlows", method = RequestMethod.POST)
//	public void listCashBackFlows(@RequestHeader("token") String sid, @RequestParam String dateQuery, @RequestParam Integer type,
//			@RequestParam Integer pageIndex, HttpServletResponse response) throws IOException {
//		try {
//			Long userId = getUserId(sid, response);
//
//			CashBackFlowPage page = new CashBackFlowPage();
//			//if (type != CashBackFlowActionTypeEnum.ALL.getValue()) {
//				page.setSourceType(type);
//			//}
//			page.setUserId(userId);
//			page.setNowPage(pageIndex);
//			page.setOrderDirection(false);
//			page.setOrderField(CashBackFlow.CREATEON);
//			Date dateQueryTmp = DateUtil.strToDate(dateQuery);
//			Map<String, Long> dateQueryMap = DateUtil.getOneDayStartAndEndTime(dateQueryTmp);
//			page.setCreateOnStart(dateQueryMap.get("start"));
//			page.setCreateOnEnd(dateQueryMap.get("end"));
//			Pager<CashBackFlow, Long> flows = cashBackFlowService.queryListByDay(page);
//			RespHandler.respOK(flows, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	// //////////////////////////////////返现业务/////////////////////////////////////////////\\
//
//	// /////////////////////////////////货款业务///////////////////////////////////
//
//	/**
//	 * 获取某月每一天货款列表
//	 * 
//	 * @param sid
//	 *            票据
//	 * @param dateQuery
//	 *            查询月份 格式:2016-3
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listPaymentFlowsByGroup", method = RequestMethod.POST)
//	public void listPaymentFlowsByGroup(@RequestHeader("token") String sid, @RequestParam String dateQuery, HttpServletResponse response)
//			throws IOException {
//		try {
//			Long userId = getUserId(sid, response);
//			int userType = getUser(sid).getUserType();
//
//			PaymentFlowCount flow = new PaymentFlowCount();
//			flow.setUserId(userId);
//			flow.setDateQuery(dateQuery);
//			flow.setUserType(userType);
//
//			List<PaymentFlowCount> groups = paymentFlowCountService.findList(flow);
//			RespHandler.respOK(groups, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//	}
//
//	/**
//	 * 获取货款明细
//	 * 
//	 * @param sid
//	 * @param dateQuery
//	 * @param type
//	 * @param pageIndex
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "listPaymentFlows", method = RequestMethod.POST)
//	public void listPaymentFlows(@RequestHeader("token") String sid, @RequestParam String dateQuery, @RequestParam Integer type,
//			@RequestParam Integer pageIndex, HttpServletResponse response) throws IOException {
//
//		try {
//			User user = getUser(sid);
//			Long userId = user.getDmId();
//			PaymentFlowPage page = new PaymentFlowPage();
//			//if (type != PaymentFlowActionTypeEnum.ALL.getValue()) {// 查询全部
//				page.setSourceType(type);
//			//}
//			page.setUserId(userId);
//			page.setNowPage(pageIndex);
//			page.setOrderDirection(false);
//			page.setOrderField(PaymentFlow.CREATEON);
//			page.setUserType(user.getUserType());
//			
//			Date dateQueryTmp = DateUtil.strToDate(dateQuery);
//			Map<String, Long> dateQueryMap = DateUtil.getOneDayStartAndEndTime(dateQueryTmp);
//			page.setCreateOnStart(dateQueryMap.get("start"));
//			page.setCreateOnEnd(dateQueryMap.get("end"));
//			
//			//Pager<PaymentFlow, Long> flows = paymentFlowService.query(page, null);
//			Pager<PaymentFlow, Long> flows = paymentFlowService.queryListByDay(page);
//			RespHandler.respOK(flows, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.get_user_wallet_msg_fail, response);
//		}
//
//	}
//	// /////////////////////////////////货款业务///////////////////////////////////\\
//
//}
