package com.qtz.ht.supplier.controller.wallet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.ht.supplier.controller.BaseController;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.ht.wallet.service.HtWalletService;
import com.qtz.ht.wallet.vo.HtWallet;

/**
 * <p>Title:HtWalletAction</p>
 * <p>Description:钱包表Action类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 唐礼军 - 1309025893@qq.com
 * @version v1.0 2016-05-20
 */
@Controller
@RequestMapping("v1.0/wallet")
public class HtWalletController extends BaseController {
	/**注入钱包表Service类*/
	@Resource(name="htWalletServiceImpl")
	private HtWalletService htWalletService;
	
	/**
	* 【查看平台钱包】
	* @param request
	* @param response
	* @param id  
	*/
	@RequestMapping(value="show")
	public void show(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("token") String sid){
		try {
			HtUser user = getUser(sid);
			RespJsonPHandler.respOK(htWalletService.getWalletByUser(user.getBusiId(),UserType.BUSINESS.value()), response, request);
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	/**
	* 【查看】
	* @param request
	* @param response
	* @param id  
	*/
	@RequestMapping(value="input")
	public void input(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("token") String sid, @RequestParam("dmId") Long id){
		HtWallet vo = new HtWallet();
		try {
			if(null != id)
				vo = htWalletService.findVo(id,null);
			RespJsonPHandler.respOK(vo, response, request);
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}