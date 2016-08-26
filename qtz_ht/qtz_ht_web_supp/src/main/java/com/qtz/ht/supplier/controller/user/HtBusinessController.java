package com.qtz.ht.supplier.controller.user;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.sendmessage.model.SmsCode;
import com.qtz.dm.sendmessage.service.SendMessageService;
import com.qtz.ht.supplier.controller.BaseController;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.service.HtUserService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.ht.wallet.service.HtBankCardService;
import com.qtz.ht.wallet.vo.HtBankCard;


/**
 * <p>Title:HtBusinessAction</p>
 * <p>Description:(商户信息Action类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年3月31日
 */
@RestController
@RequestMapping("/v1.0/user/")
public class HtBusinessController extends BaseController {
	/**注入商户信息Service类*/
	@Autowired
	private HtBusinessService htBusinessService;
	@Resource(name="htUserServiceImpl")
	private HtUserService htUserService;
	@Resource(name="htBankCardServiceImpl")
	private HtBankCardService htBankCardService;
	@Autowired
	private SendMessageService sendMessageService;

	/**
	* 【获取商家信息详情】
	* @param sid
	* @param result
	* @param request
	* @param response  
	*/
	@RequestMapping(value="findBusiness")
	public void findBusiness(@RequestParam("token") String sid,
			HttpServletRequest request, HttpServletResponse response)
	{
		try {
			HtUser user = getUser(sid);
			HtBankCard bank = null;
			HtBusiness vo = this.htBusinessService.findVo(user.getBusiId(), null);
			//隐藏手机号中间5位
			String phone = vo.getMobilePhone();
			//phone = phone.replace(phone.subSequence(5, phone.length() - 2), "*****");
			vo.setMobilePhone(phone);
			
			//商家银行卡信息
			HtBankCard obj = new HtBankCard();
			obj.setBusiId(vo.getDmId());
			List<HtBankCard> list = this.htBankCardService.findList(obj);
			//此处因目前设计是一个商家一条用户信息一条银行卡信息，如有需求变动，再行修改
			if (list != null && list.size() > 0) {
				bank = list.get(0);
				
			}
			JSONObject r = new JSONObject();
			JSONObject r1 = JSONObject.parseObject(JSONObject.toJSONString(vo));
			JSONObject r2 = JSONObject.parseObject(JSONObject.toJSONString(bank));
			r.putAll(r1);
			r.putAll(r2);
			r.putAll(this.htBusinessService.getText(vo));
			r.putAll(this.htBankCardService.getText(bank));
			RespJsonPHandler.respOK(r, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}

	/**
	* 【修改密码，旧密码改新密码】
	* @param sid
	* @param model
	* @param result
	* @param request
	* @param response  
	*/
	@RequestMapping(value="modPassword")
	public void modPassword(@RequestParam("token") String sid,@RequestParam("oldPwd") String oldPwd,
			@RequestParam("newPwd") String newPwd,@RequestParam("confirmPwd") String confirmPwd,
			HttpServletRequest request, HttpServletResponse response)
	{
		try {
			HtUser user = getUser(sid);
			user = this.htUserService.findVo(user.getDmId());
			if (!oldPwd.equals(user.getPassword())) {
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "旧密码输入错误", response, request);
				return;
			}
			if (newPwd.equals(user.getPassword())) {
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "新密码与旧密码一致错误", response, request);
				return;
			}
			if (!newPwd.equals(confirmPwd)) {
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "新密码与确认密码不一致", response, request);
				return;
			}
			
			HtUser vo = new HtUser();
			vo.setDmId(user.getDmId());
			vo.setPassword(newPwd);
			this.htUserService.modVoNotNull(vo);
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
//	/**
//	* 【修改密码】
//	* @param sid
//	* @param model
//	* @param result
//	* @param request
//	* @param response  
//	*/
//	@RequestMapping(value="modPassword")
//	public void modPassword(@RequestParam("token") String sid,
//			@Valid @ModelAttribute modPasswordModel model,
//			BindingResult result,
//			HttpServletRequest request, HttpServletResponse response)
//	{
//		try {
//			if (result.hasErrors()) {
//				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
//			}else{
//				HtUser user = getUser(sid);
//				if (model.getNewPassword().equals(user.getPassword())) {
//					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "新密码与旧密码一致错误", response, request);
//					return;
//				}
//				if (!model.getNewPassword().equals(model.getConfirmPassword())) {
//					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "新密码与确认密码不一致", response, request);
//					return;
//				}
//				if (true) {
//					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "验证码错误", response, request);
//					return;
//				}
//				HtUser vo = new HtUser();
//				vo.setDmId(user.getDmId());
//				vo.setPassword(model.getNewPassword());
//				this.htUserService.modVoNotNull(vo);
//				RespJsonPHandler.respOK(response, request);
//			}
//		} catch (ServiceException e) {
//			log.error(e);
//			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
//		}
//	}
//	@RequestMapping(value="sendVerificationCode")
//	public void sendVerificationCode(@RequestParam("token") String sid,
//			@RequestParam("mobilePhone") @NotBlank(message="手机号不能为空") String mobilePhone,
//			BindingResult result,
//			HttpServletRequest request, HttpServletResponse response)
//	{
//		try {
//			if (result.hasErrors()) {
//				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
//			}else{
//				HtUser user = getUser(sid);
//				HtBusiness b = this.htBusinessService.findVo(user.getBusiId(), null);
//				if (!mobilePhone.equals(b.getMobilePhone())) {
//					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_19, "手机号错误", response, request);
//					return;
//				}
//				//发送验证码
//				RespJsonPHandler.respOK(response, request);
//			}
//		} catch (ServiceException e) {
//			log.error(e);
//			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
//		}
//	}
	
	
	
	/**
	 * 
	 * 获取短信验证码,2分钟有效
	 * @param phoneNo
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="getPhoneCode")
	public void getPhoneCode(@RequestParam(value="token") String sid,
			@RequestParam(required=true)String phoneNo,
			HttpServletRequest request, HttpServletResponse response){
		Pattern p = Pattern.compile("^((1[0-9][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(phoneNo);  
		if(!m.matches()){
			RespHandler.respError(ExceptionConstants.ERRORCODE_7, "手机号格式不正确", response);
			return;
		}	  
        try {
        	SmsCode smscode=sendMessageService.sendSmsCode(phoneNo);
        	log.info("手机验证码:"+smscode.getCode());
        	RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}			  
	}	
	
	
	
	/**
	 * 验证码根据  修改商家的用户密码
	 * updatePasswordByPhoneCode:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param phoneNo  手机号码
	 * @param verificationCode 验证码
	 * @param newPasswod 新密码
	 * @param confireNewPasswod 确认新密码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="updatePasswordByPhoneCode")
	public void updatePasswordByPhoneCode(@RequestParam(value="token") String sid,
			@RequestParam(required=true)String phoneNo,
			@RequestParam(required=true)String verificationCode,
			@RequestParam(required=true)String newPasswod,
			@RequestParam(required=true)String confireNewPasswod,
			HttpServletRequest request, HttpServletResponse response){
		if(newPasswod==null||confireNewPasswod==null||!(confireNewPasswod.equals(newPasswod))){
			 RespJsonPHandler.respError( ExceptionConstants.ERRORCODE_7,"两次密码不相同",response,request);
			 return;
		}
		
		
		Pattern p = Pattern.compile("^((1[0-9][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(phoneNo);  
		if(!m.matches()){
			RespJsonPHandler.respError( ExceptionConstants.ERRORCODE_7,"手机号格式不正确",response,request);
			return;
		}	
		HtUser htStaffSession;
		HtUser htStaffInDB;
		Long dmid = null;
		try {
			
			htStaffSession = getUser(sid);
			if(htStaffSession ==null){
				RespJsonPHandler.respError( ExceptionConstants.ERRORCODE_7,"请重新登录",response,request);
				return;
			}
			dmid = htStaffSession.getDmId();
			htStaffInDB = htUserService.findVo(htStaffSession.getDmId(),null);
			if(htStaffInDB==null){
				RespJsonPHandler.respError( ExceptionConstants.ERRORCODE_7,"数据异常",response,request);
				return;
			}
		} catch (ServiceException e1) {
			log.error(e1);
			RespJsonPHandler.respError(e1.getErrorType(), e1.getErrorTitle(), response, request);
	
		}
		
		//验证是否是有效验证码
		try {
			if(!sendMessageService.regSmsCode(phoneNo,verificationCode)){
				 RespJsonPHandler.respError( ExceptionConstants.ERRORCODE_7,"验证码不正确",response,request);
				 return;
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		
		//继续修改密码
		
		HtUser htUser = new HtUser();
		htUser.setPassword(confireNewPasswod);
		htUser.setDmId(dmid);
		try {
			htUserService.modVoNotNull(htUser);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		RespJsonPHandler.respOK(response,request);
	}	
}