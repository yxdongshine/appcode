package com.qtz.ht.manage.controller.goods;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.good.page.HtGoodCatePage;
import com.qtz.ht.good.service.HtGoodCateService;
import com.qtz.ht.good.vo.HtGoodCate;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.GoodCateModel;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.ht.util.BeanUtils;
import com.qtz.ht.util.ProcessingCheckExceptionUtil;

/**
 * <p>Title:HtGoodCateController</p>
 * <p>Description:商户商品分类表Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-08
 */
@RestController
@RequestMapping("v1.0/goodCate")
public class HtGoodCateController extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(HtGoodCateController.class);
	/**注入商户商品分类表Service类*/
	@Resource(name="htGoodCateServiceImpl")
	private HtGoodCateService htGoodCateService; 
	
	/** 
	* 【添加】
	* @param req
	* @param vo
	* @return
	*/
	@RequestMapping(value="add")
	public void add(@RequestParam("token") String sid,
			@Valid @ModelAttribute GoodCateModel parameter,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				HtGoodCate cate = new HtGoodCate();
				cate.setCataName(parameter.getCataName());
				List<HtGoodCate> list = this.htGoodCateService.findList(cate);
				if (list != null && list.size() > 0) {
					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "分类名称已添加过", response, request);
					return;
				}
				HtStaff staff = getUser(sid);
				HtGoodCate vo = new HtGoodCate();
				BeanUtils.copyProperties(parameter, vo);
				vo.setStatus(Constants.ONE);
				vo.setPid(0l);
				vo.setCruser(staff.getDmId());
				vo.setCrtime(System.currentTimeMillis());
				vo.setCataLevel(Constants.ONE);
				htGoodCateService.addVo(vo);
				RespJsonPHandler.respOK(response,request);
			}
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
	public void mod(@RequestParam("token") String sid,@NotNull @ModelAttribute HtGoodCate vo,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response)
	{
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				htGoodCateService.modVoNotNull(vo);
				RespJsonPHandler.respOK(response,request);
			}
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
	public void page(@RequestParam("token") String sid,HtGoodCatePage page,
			HttpServletRequest request, HttpServletResponse response){
		try {
			RespJsonPHandler.respOK(htGoodCateService.query(page,null),response,request);
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
	public void input(@RequestParam("dmId") Long id,HttpServletRequest request, HttpServletResponse response){
		HtGoodCate vo = new HtGoodCate();
		try {
			vo = htGoodCateService.findVo(id,null);
			RespJsonPHandler.respOK(vo,response,request);	
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
	@RequestMapping(value="list")
	public void list(@RequestParam("token") String sid, Long pId,
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtGoodCate vo = new HtGoodCate();
			if (null == pId) {
				pId = 0l;
			}
			vo.setPid(pId);
			vo.setStatus(Constants.ONE);
			List<HtGoodCate> list = this.htGoodCateService.findList(vo);
			RespJsonPHandler.respOK(list,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}