
package com.qtz.ht.personal.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.personal.controller.BaseController;
import com.qtz.ht.system.page.HtActivityPage;
import com.qtz.ht.system.service.HtActivityService;
import com.qtz.ht.system.vo.HtActivity;

/** 
 * ClassName:HtActivityController <br/> 
 * Function: TODO (app 端活动控制类). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年8月10日 下午2:51:24 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
@RestController
@RequestMapping("v1.0/activity")
public class HtActivityController extends BaseController {
	
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(HtActivityController.class);
	/**注入活动管理表Service类*/
	@Resource(name="htActivityServiceImpl")
	private HtActivityService htActivityService;
	
	
	/**
	 * 请求所有活动而页面列表
	 * page:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param page
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="page")
	public void page(HtActivityPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			//默认设置活动是上架的
			page.setIsValid(1);
			RespJsonPHandler.respOK(htActivityService.query(page,null),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 具体活动的编号的 活动详细信息
	 * input:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="input")
	public void input(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response){
		HtActivity vo = new HtActivity();
		try {
			if(null != id)
				vo = htActivityService.findVo(id,null);
			RespJsonPHandler.respOK(vo,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}
  