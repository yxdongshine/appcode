package com.qtz.ht.manage.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.DdmSession;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.user.service.HtStaffService;

/**
 * <p>Title:LoginControll</p>
 * <p>Description:(登录控制器)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年1月27日
 */
@RestController
@RequestMapping("/v1.0/")
public class LoginControll extends BaseController {

	protected static final String htSession_cookie_path = "/";
	
	@Resource(name="htStaffServiceImpl")
	private HtStaffService htStaffService = null;
	
	/** 
	* 【登录】ajax
	* @param req
	* @param res
	 * @throws IOException 
	* @throws ActionException  
	*/
	@RequestMapping(value="login")
	public void login(@RequestParam String account,@RequestParam String pwd,HttpServletResponse resp,HttpServletRequest req) throws IOException {
		try {
			DdmSession s = htStaffService.login(account, pwd, new DdmSession());//登录后生成新session
			JSONObject result = new JSONObject();
			result.put("sid", s.getId());
			result.put(Constants.SESSION_USER, s.getSessionObject(Constants.SESSION_USER));
			RespJsonPHandler.respOK(result, resp,req);
		}catch (ServiceException e) {
			log.error("网络错误", e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), resp,req);
		}catch(RpcException e){
			log.error("网络错误", e);
			RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "连接异常，请重试", resp,req);
		}
	}
	
	/**
	 * 
	* 【登出】(这里用一句话描述这个方法的作用)
	* @param req
	* @return
	 *@throws IOException 
	 */
	@RequestMapping(value="logout")
	public void logout(@RequestParam(value="token") String sid,HttpServletResponse resp,HttpServletRequest req) throws IOException{
		log.info(sid + ">>>>>>>>登出");
		try {
			if(this.htStaffService.logout(null, sid));
			RespJsonPHandler.respOK(resp,req);
		} catch (RpcException e) {
			log.error("网络错误", e);
			RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "连接异常，请重试", resp,req);
		} catch (ServiceException e) {
			log.error(e.getErrorTitle(), e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), resp,req);
		}
	}
}
