package com.qtz.ht.personal.controller.order;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.authentication.service.AuthenService;
import com.qtz.dm.authentication.vo.UserAuthen;
import com.qtz.dm.user.vo.User;
import com.qtz.ht.order.model.PaymentModel;
import com.qtz.ht.order.service.TransferActivityService;
import com.qtz.ht.personal.controller.BaseController;

/**
 * <p>Title:TransferActivityController</p>
 * <p>Description:()</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月18日
 */
@RestController
@RequestMapping(value="/v1.0/transfer/")
public class TransferActivityController extends BaseController {
	@Autowired
	private TransferActivityService transferActivityService;
	@Resource(name="authenServiceImpl")
	private AuthenService authenService;

	/**
	* 【提交订单并支付】
	* @param sid
	* @param goodId
	* @param addressId
	* @param payPassword
	* @param response
	* @param request
	* @throws IOException  
	*/
	@RequestMapping(value="subOrder",method=RequestMethod.POST)
	public void subOrder(@RequestParam("token") String sid, @RequestParam("goodId") Long goodId,
			Long addressId, @RequestParam("payPassword") String payPassword,
			HttpServletResponse response,HttpServletRequest request) throws IOException{
		try {
			
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			//获取认证信息
			UserAuthen userAuthen = authenService.findUserAuthen(user.getDmId());
			if (null == userAuthen) {
				RespHandler.respException(10005,"用户未认证或者认证未通过",response);
				return;
			}
			if (null == userAuthen.getApplyStatus() || 1 != userAuthen.getApplyStatus().intValue()) {
				RespHandler.respException(10006,"用户未认证或者认证未通过",response);
				return;
			}
			transferActivityService.addOrder(user.getDmId(), userAuthen.getName(), goodId, userAuthen.getPno(), payPassword,user.getMphonenum());
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respException(e.getErrorType(),e.getErrorTitle(),response);
		}
	}
	@RequestMapping(value="confirmOrder",method=RequestMethod.POST)
	public void confirmOrder(@RequestParam("token") String sid, @RequestParam("goodId") Long goodId,
			HttpServletResponse response,HttpServletRequest request) throws IOException{
		try {
			
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			//查看余额是否足够
			PaymentModel model = transferActivityService.getSubsidy(user.getDmId(), goodId);
			//获取认证信息
			UserAuthen userAuthen = authenService.findUserAuthen(user.getDmId());
			if (null == userAuthen) {
				RespHandler.respException(10005,"用户未认证或者认证未通过",response);
				return;
			}
			if (null == userAuthen.getApplyStatus() || 1 != userAuthen.getApplyStatus().intValue()) {
				RespHandler.respException(10006,"用户未认证或者认证未通过",response);
				return;
			}
			JSONObject result = JSONObject.parseObject(JSONObject.toJSONString(model));
			String idCard = userAuthen.getPno();
			idCard = idCard.substring(0, 4) + "**********" + idCard.substring(idCard.length() -4);
			result.put("idCard", idCard);
			result.put("phone", user.getMphonenum());
			result.put("name", userAuthen.getName());
			RespHandler.respOK(result , response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respException(e.getErrorType(),e.getErrorTitle(),response);
		}
	}
}