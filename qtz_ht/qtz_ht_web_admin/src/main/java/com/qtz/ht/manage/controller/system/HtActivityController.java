package com.qtz.ht.manage.controller.system;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.system.page.HtActivityPage;
import com.qtz.ht.system.service.HtActivityService;
import com.qtz.ht.system.vo.HtActivity;
import com.qtz.ht.user.vo.HtStaff;

/**
 * <p>Title:HtActivityController</p>
 * <p>Description:活动管理表Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
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
	* 【添加】
	* @param req
	* @param vo
	* @return
	*/
	@RequestMapping(value="add")
	public void add(@RequestParam("token") String sid, @RequestParam String activityName,
			@RequestParam String mainPicture,@RequestParam String linkUrl,
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtStaff user = getUser(sid);
			HtActivity htActivity = new HtActivity();
			htActivity.setActivityName(activityName);
			htActivity.setMainPicture(mainPicture);
			htActivity.setLinkUrl(linkUrl);
			htActivity.setCrUserId(user.getDmId());
			htActivity.setCrtime(System.currentTimeMillis());
			htActivity.setIsValid(Constants.TWO);//默认为下架状态
			htActivityService.addVo(htActivity);
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
		/** 
	* 【修改保存】
	* @param req
	* @param vo
	* @return
	*/
	@RequestMapping(value="mod")
	public void mod(@RequestParam("token") String sid, HtActivity vo,HttpServletRequest request, HttpServletResponse response){
		try {
			htActivityService.modVoNotNull(vo);
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/** 
	* 【删除】
	* @param req
	* @return
	*/
	@RequestMapping(value="delete")
	public void delete(@RequestParam("token") String sid,Long id,HttpServletRequest request, HttpServletResponse response){
		try {
			htActivityService.delId(id);
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
	public void page(@RequestParam("token") String sid,HtActivityPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			RespJsonPHandler.respOK(htActivityService.query(page,null),response,request);
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
	public void input(@RequestParam("token") String sid,Long id,HttpServletRequest request, HttpServletResponse response){
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
	
	/**
	* 【活动状态更改】
	* @param id
	* @param isValid
	* @param request
	* @param response  
	*/
	@RequestMapping(value="stateChanges")
	public void stateChanges(@RequestParam("token") String sid,Long id,int isValid,HttpServletRequest request, HttpServletResponse response){
		try {
			HtActivity vo=new HtActivity();
			vo.setIsValid(isValid);
			if(isValid==Constants.ONE){
				List<HtActivity> list=htActivityService.findList(vo);
				if(list!=null&&list.size()>0){
					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7,"已经有一个活动正在进行中，请先将其下架，才可以上架新活动哦~", response, request);
				}else{
					vo.setDmId(id);
					htActivityService.modVoNotNull(vo);
					RespJsonPHandler.respOK(response, request);
				}
			}else if(isValid==Constants.TWO){
				vo.setDmId(id);
				htActivityService.modVoNotNull(vo);
				RespJsonPHandler.respOK(response, request);
			}else{
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7,"传入的参数错误", response, request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		
	}
}