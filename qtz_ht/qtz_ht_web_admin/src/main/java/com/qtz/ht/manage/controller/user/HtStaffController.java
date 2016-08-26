package com.qtz.ht.manage.controller.user;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.encryption.SHA256Encryption;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.sendmessage.model.SmsCode;
import com.qtz.dm.sendmessage.service.SendMessageService;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.StaffModel;
import com.qtz.ht.user.page.HtStaffPage;
import com.qtz.ht.user.service.HtStaffService;
import com.qtz.ht.user.service.HtUserService;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.ht.util.BeanUtils;
import com.qtz.ht.util.ProcessingCheckExceptionUtil;

/**
 * <p>Title:HtStaffAction</p>
 * <p>Description:海淘员工表Action类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-06
 */
@RestController
@RequestMapping("v1.0/staff")
public class HtStaffController extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(HtStaffController.class);
	/**注入海淘员工表Service类*/
	@Resource(name="htStaffServiceImpl")
	private HtStaffService htStaffService; 
	@Autowired
	private SendMessageService sendMessageService;
	/** 
	* 【保存】
	* @param req
	* @param vo
	* @return
	*/
	//@RandomCodeInter(remove=true)
	@RequestMapping(value="add")
	public void add(@RequestParam("token") String sid,@Valid @ModelAttribute StaffModel vo,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				HtStaff obj = new HtStaff();
				obj.setMphone(vo.getMphone());
				List<HtStaff> list = this.htStaffService.findList(obj);
				if (list != null && list.size() > 0) {
					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "手机已经添加过", response, request);
					return;
				}
				HtStaff entity = new HtStaff();
				BeanUtils.copyProperties(vo, entity);
				entity.setPwd(SHA256Encryption.encodeToSHA256(vo.getMphone().substring(vo.getMphone().length() - 6)));
				entity.setCrtime(System.currentTimeMillis());
				entity = htStaffService.addVo(entity);
				RespJsonPHandler.respOK(response,request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}

	/**
	* 【重置密码】
	* 
	* 重置管理员的密码 默认123456
	* @param id
	* @param request
	* @param response  
	*/
	@RequestMapping(value="resetPassword")
	public void resetPassword(@RequestParam("token") String sid,
			@RequestParam("dmId") Long dmId,
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtStaff session = getUser(sid);
			if(session ==null){
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "请重新登录", response, request);
				return;
			}
			HtStaff vo = this.htStaffService.findVo(dmId, null);
			if (vo == null || vo.getMphone() == null || vo.getMphone().length() < 6) {
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7, "入参错误", response, request);
				return;
			}
			HtStaff staff = new HtStaff();
			staff.setPwd(SHA256Encryption.encodeToSHA256(HtUserService.HTSTAFF_RESET_PASSWORD));
			staff.setDmId(vo.getDmId());
			htStaffService.modVoNotNull(staff);
			RespJsonPHandler.respOK(response,request);
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
	public void page(@RequestParam(value="token") String sid,HtStaffPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			RespJsonPHandler.respOK(htStaffService.query(page,null),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}

	/**
	* 【编辑回显数据】
	* @param dmId
	* @param request
	* @param response  
	*/
	@RequestMapping(value="input")
	public void input(@RequestParam Long dmId,HttpServletRequest request, HttpServletResponse response){
		HtStaff vo = new HtStaff();
		try {
			vo = htStaffService.findVo(dmId,null);
			RespJsonPHandler.respOK(vo,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}

	
	/**
	 * 获取我的信息
	 * getMyInfo:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getMyInfo")
	public void getMyInfo(@RequestParam(value="token") String sid,HttpServletRequest request, HttpServletResponse response){
		HtStaff vo = new HtStaff();
		try {
			 vo = getUser(sid);
			RespJsonPHandler.respOK(vo,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	* 【编辑保存】
	* @param sid
	* @param vo
	* @param result
	* @param request
	* @param response  
	*/
	@RequestMapping(value="edit")
	public void edit(@RequestParam("token") String sid,@Valid @ModelAttribute StaffModel vo,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				if (null == vo.getDmId()) {
					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7, "dmId不能为空", response, request);
					return;
				}
				HtStaff entity = new HtStaff();
				BeanUtils.copyProperties(vo, entity);
				this.htStaffService.modVoNotNull(entity);
				RespJsonPHandler.respOK(response,request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	
	/**
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
			RespHandler.respError(999, "手机号格式不正确", response);
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
		HtStaff htStaffSession;
		HtStaff htStaffInDB;
		Long dmid = null;
		try {
			
			htStaffSession = getUser(sid);
			if(htStaffSession ==null){
				RespJsonPHandler.respError( ExceptionConstants.ERRORCODE_7,"请重新登录",response,request);
				return;
			}
			dmid = htStaffSession.getDmId();
			htStaffInDB = htStaffService.findVo(htStaffSession.getDmId(),null);
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
		
		HtStaff staff = new HtStaff();
		staff.setPwd(confireNewPasswod);
		staff.setDmId(dmid);
		try {
			htStaffService.modVoNotNull(staff);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		RespJsonPHandler.respOK(response,request);
	}	
	
}