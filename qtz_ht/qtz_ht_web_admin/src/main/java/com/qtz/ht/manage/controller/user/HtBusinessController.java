package com.qtz.ht.manage.controller.user;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.encryption.SHA256Encryption;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.OperationModel;
import com.qtz.ht.manage.model.UserSaveModel;
import com.qtz.ht.user.page.HtBusinessPage;
import com.qtz.ht.user.service.HtBusinessLogService;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.service.HtUserService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.ht.util.BeanUtils;
import com.qtz.ht.util.ProcessingCheckExceptionUtil;
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
	@Resource(name="htBusinessLogServiceImpl")
	private HtBusinessLogService htBusinessLogService;
	@Resource(name="htStaffGoodsServiceImpl")
    private HtStaffGoodsService htStaffGoodsService;
	private static String USER_ID = "userId";//用户ID
	private static String BANK_ID = "bankId";//银行卡id
	
	
	/** 
	* 【保存】
	* @param req
	* @param vo
	* @return
	* @throws ActionException  
	 * @throws IOException 
	*/
	@RequestMapping(value="save")
	public void save(@RequestParam(value="token") String sid,
			@Valid @ModelAttribute UserSaveModel userModel,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request)throws IOException{
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				HtBusiness vo = new HtBusiness();
				HtUser user = new HtUser();
				HtBankCard bank = new HtBankCard();
				BeanUtils.copyProperties(userModel, vo);
				BeanUtils.copyProperties(userModel, user);
				BeanUtils.copyProperties(userModel, bank);
				htBusinessService.addOrMod(getUserId(sid, response), vo, user, bank);
				RespJsonPHandler.respOK(response,request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	@RequestMapping(value="modMerchantInfo")
	public void modMerchantInfo(@RequestParam(value="token") String sid,
			@Valid @ModelAttribute UserSaveModel userModel,
			@RequestParam("userId") Long userId,
			@RequestParam("bankId") Long bankId,
			BindingResult result,
			HttpServletResponse response,
			HttpServletRequest request)throws IOException{
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				if (null == userModel.getDmId()) {//修改时，id不能为空
					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7, "入参错误,dmId不能为空", response, request);
					return;
				}
				HtBusiness vo = new HtBusiness();
				HtUser user = new HtUser();
				HtBankCard bank = new HtBankCard();
				BeanUtils.copyProperties(userModel, vo);
				BeanUtils.copyProperties(userModel, user);
				BeanUtils.copyProperties(userModel, bank);
				vo.setDmId(userModel.getDmId());
				user.setAccount(userModel.getMobilePhone());
				user.setDmId(userId);
				bank.setDmId(bankId);
				htBusinessService.modMerchantInfo(getUserId(sid, response), vo, user, bank);
				RespJsonPHandler.respOK(response,request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	/**
	* 【批量屏蔽或者冻结商家信息】
	* @param sid
	* @param list
	* @param result
	* @param req
	* @param resp  
	*/
	@RequestMapping(value="batchMod")
	public void batchMod(@RequestParam(value="token") String sid,
			@Valid @ModelAttribute OperationModel model,
			BindingResult result,
			HttpServletRequest req,HttpServletResponse resp)
	{
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), resp, req);
			}else{
				HtStaff staff = getUser(sid);
				List<HtBusiness> list = model.getList();
				htStaffGoodsService.modBatchSupplier(staff, list,model.getOperateContent(), model.getVoucherAddr());
				RespJsonPHandler.respOK(resp,req);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), resp, req);
		}
	}
	
	/** 
	* 【根据手机号查询商家】
	* @param req
	* @param page
	* @return
	*/
	@RequestMapping(value="findByPhone", method = RequestMethod.GET)
	public void findByPhone(@RequestParam(value="token") String sid,
			@RequestParam("phone") String phone,
			HttpServletRequest req,HttpServletResponse resp){
		try {
			HtBusiness b = new HtBusiness();
			b.setMobilePhone(phone);
			List<HtBusiness> list = this.htBusinessService.findList(b, null);
			if (list != null && list.size() == 1) {
				HtBusiness business = list.get(0);
				JSONObject result = new JSONObject();
				result.put(HtBusiness.DMID, business.getDmId());
				result.put(HtBusiness.FULLNAME, business.getFullName());
				result.put(HtBusiness.MOBILEPHONE, business.getMobilePhone());
				result.put(HtBusiness.LEGALPERSON, business.getLegalPerson());
				RespJsonPHandler.respOK(result, resp, req);
			}else{
				RespJsonPHandler.respError(-1, "数据错误", resp, req);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), resp, req);
		}
	}
	/**
	* 【分页列表】
	* @param sid
	* @param request
	* @param page
	* @param response
	* @throws ActionException  
	*/
	@RequestMapping(value="page", method = RequestMethod.GET)
	public void page(@RequestParam("token") String sid, HttpServletRequest request,HtBusinessPage page,HttpServletResponse response) throws ActionException
	{
		try {
			page.setOrderField("crtime");
			Pager<HtBusiness, Long> p = htBusinessService.query(page,HtBusiness.class);
			List<HtBusiness> list = p.getList();
			if (null != list && !list.isEmpty()) {
				HtUser user = null;
				for (HtBusiness b : list) {
					HtUser obj = new HtUser();
					obj.setBusiId(b.getDmId());
					List<HtUser> list1 = this.htUserService.findList(obj);
					user = list1.get(0);
					b.setLastLogin(user.getLastLogin());
				}
			}
			RespJsonPHandler.respOK(p, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	* 【根据商家信息id获取商家详情】
	* @param sid
	* @param id
	* @param result
	* @param request
	* @param response  
	*/
	@RequestMapping(value="findById")
	public void findById(@RequestParam("token") String sid, 
			@RequestParam("id") @NotNull(message="id不能为空") Long id,
			HttpServletRequest request, HttpServletResponse response)
	{ 
		try {
			UserSaveModel model = new UserSaveModel();
			HtUser user = new HtUser();
			HtBankCard bank = new HtBankCard();
			HtBusiness vo = this.htBusinessService.findVo(id, null);
			if (vo != null) {
				//商家用户信息
				HtUser obj = new HtUser();
				obj.setBusiId(vo.getDmId());
				List<HtUser> list1 = this.htUserService.findList(obj);
				//商家银行卡信息
				HtBankCard obj2 = new HtBankCard();
				obj2.setBusiId(vo.getDmId());
				List<HtBankCard> list2 = this.htBankCardService.findList(obj2);
				
				//此处因目前设计是一个商家一条用户信息一条银行卡信息，如有需求变动，再行修改
				if (list1 != null && list1.size() > 0) {
					user = list1.get(0);
				}
				if (list2 != null && list2.size() > 0) {
					bank = list2.get(0);
					
				}
				BeanUtils.copyProperties(user, model);
				BeanUtils.copyProperties(bank, model);
				BeanUtils.copyProperties(vo, model);
				if (null != vo.getLegalProvince()) {
					
				}
				model.setDmId(vo.getDmId());
			}
			JSONObject resul = JSONObject.parseObject(JSONObject.toJSONString(model));
			resul.put(USER_ID, user.getDmId());
			resul.put(BANK_ID, bank.getDmId());
			resul.putAll(this.htBusinessService.getText(vo));
            resul.putAll(this.htBankCardService.getText(bank));
			RespJsonPHandler.respOK(resul, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	
	/**
	* 【重置密码】
	* 
	* 重置商家的密码 默认123456
	* @param id
	* @param request
	* @param response  
	*/
	@RequestMapping(value="resetPassword")
	public void resetPassword(@RequestParam("token") String sid,@RequestParam Long dmId,HttpServletRequest request, HttpServletResponse response){
		try {
			//根据商家编号 找到商家
			HtBusiness htb=htBusinessService.findVo(dmId, null);
			if(htb ==null){
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7, "入参错误", response, request);
				return;
			}
			//根据商家编号 找到用户
			HtUser htuQuery= new HtUser();
			htuQuery.setBusiId(htb.getDmId());
			List<HtUser> listHtUser = this.htUserService.findList(htuQuery);
			if (listHtUser == null ||listHtUser.size()==0) {
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7, "入参错误", response, request);
				return;
			}
			HtUser vo = listHtUser.get(0);
			HtUser htUser = new HtUser();
			htUser.setPassword(SHA256Encryption.encodeToSHA256(HtUserService.HTSTAFF_RESET_PASSWORD));
			htUser.setDmId(vo.getDmId());
			htUserService.modVoNotNull(htUser);
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
}