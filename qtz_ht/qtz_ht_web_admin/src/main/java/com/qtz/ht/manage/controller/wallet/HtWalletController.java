package com.qtz.ht.manage.controller.wallet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.wallet.page.HtWalletPage;
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
	@Autowired
	private HtBusinessService htBusinessService;
	
	
	/**
	* 【分页】
	* @param request
	* @param response
	* @param page  
	*/
	@RequestMapping(value="page")
	public void page(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("token") String sid, HtWalletPage page){
		try {
			page.setUserType(UserType.BUSINESS.value());
			String BusiName =page.getBusiName();
			if(BusiName!=null&&BusiName.trim().length()>0){
				HtBusiness htBusiness = new HtBusiness();
				htBusiness.setFullName(BusiName);
				List<HtBusiness> htBusinessList= this.htBusinessService.findList(htBusiness);
				if(htBusinessList!=null&&htBusinessList.size()>0){
					HtBusiness newHtBusiness = htBusinessList.get(0);
					if(newHtBusiness!=null){
						page.setBusiId(newHtBusiness.getDmId());
					}
				}
			}
			
			String mobilePhone =page.getMobilePhone();
			if(mobilePhone!=null&&mobilePhone.trim().length()>0){
				HtBusiness htBusiness = new HtBusiness();
				htBusiness.setMobilePhone(mobilePhone);;
				List<HtBusiness> htBusinessList= this.htBusinessService.findList(htBusiness);
				if(htBusinessList!=null&&htBusinessList.size()>0){
					HtBusiness newHtBusiness = htBusinessList.get(0);
					if(newHtBusiness!=null){
						page.setBusiId(newHtBusiness.getDmId());
					}
				}
			}
			page = (HtWalletPage) htWalletService.query(page,null);
			List<HtWallet> list = page.getList();
			if (null != list && !list.isEmpty()) {
				HtBusiness b = null;
				for (HtWallet w : list) {
					b = this.htBusinessService.findVo(w.getBusiId(), null);
					if(b !=null){
						w.setSimpleName(b.getSimpleName()==null?"":b.getSimpleName());
						w.setTrademark(b.getTrademark()==null?"":b.getTrademark());
						w.setMobilePhone(b.getMobilePhone()==null?" ":b.getMobilePhone());
					}else{
						log.error("不存在该商户，钱包编号："+w.getDmId()+"  商家编号："+w.getBusiId());
						continue;
					}
					
				}
				page.setList(list);
			}
			RespJsonPHandler.respOK(page, response, request);
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
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
			RespJsonPHandler.respOK(htWalletService.findPlatformWallet(), response, request);
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