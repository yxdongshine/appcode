package com.qtz.ht.supplier.controller.system;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.Constants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.supplier.controller.BaseController;
import com.qtz.ht.system.service.CategoryService;
import com.qtz.ht.system.vo.HtCategory;
import com.qtz.ht.util.SeaAmoConstants;


/**
 * <p>Title:CategoryController</p>
 * <p>Description:(商品分类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年8月9日
 */
@RestController
@RequestMapping("v1.0/category")
public class CategoryController extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(CategoryController.class);
	/**注入活动管理表Service类*/
	@Resource(name="categoryServiceImpl")
	private CategoryService categoryService;
//	@Resource(name="htStaffGoodsServiceImpl")
//	private HtStaffGoodsService htStaffGoodsService;
//	@Resource(name="htGoodCateAssoServiceImpl")
//	private HtGoodCateAssoService htGoodCateAssoService;
	
	/** 
	* 【查看所有三级分类】
	* @param req
	* @param page
	* @return
	* @throws ActionException  
	*/
	@RequestMapping(value="list")
	public void list(@RequestParam("token") String sid, HttpServletRequest request, HttpServletResponse response){
		try {
			//查询所有三级分类
			HtCategory obj = new HtCategory();
			obj.setMark(SeaAmoConstants.THREE_HT_MARK);
			obj.setStatus(Constants.ZERO);
			List<HtCategory> cateS = categoryService.findList(obj);
			RespJsonPHandler.respOK(cateS,response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}