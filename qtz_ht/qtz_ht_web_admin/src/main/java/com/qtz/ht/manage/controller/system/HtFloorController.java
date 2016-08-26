package com.qtz.ht.manage.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.Constants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.HtFloorModel;
import com.qtz.ht.manage.model.Second;
import com.qtz.ht.system.page.HtCategoryPage;
import com.qtz.ht.system.page.HtFloorPage;
import com.qtz.ht.system.service.CategoryService;
import com.qtz.ht.system.service.HtFloorGoodsService;
import com.qtz.ht.system.service.HtFloorService;
import com.qtz.ht.system.vo.HtFloor;
import com.qtz.ht.system.vo.HtFloorGoods;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.ht.util.BeanUtils;

/**
 * <p>Title:HtFloorController</p>
 * <p>Description:楼层管理表Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author 刘宝平
 * @version v1.0 2016-08-08
 */
/**
 * <p>Title:HtFloorController</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 刘宝平 - liubaoping
 * @version v1.0 2016年8月12日
 */
@RestController
@RequestMapping("v1.0/floor")
public class HtFloorController extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(HtFloorController.class);
	/**注入楼层管理表Service类*/
	@Resource(name="htFloorServiceImpl")
	private HtFloorService htFloorService; 
	@Resource(name="htFloorGoodsServiceImpl")
	private HtFloorGoodsService htFloorGoodsService;
	@Resource(name="categoryServiceImpl")
	private CategoryService categoryService;
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService; 
	
	/** 
	* 【添加】
	* @param req
	* @param vo
	* @return
	*/
	@RequestMapping(value="add")
	public void add(@RequestParam("token") String sid,@Validated({Second.class})@ModelAttribute HtFloor htFloor,
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtStaff user = getUser(sid);  
			htFloor.setGoodNumber(Constants.ZERO);
			htFloor.setCrUserId(user.getDmId());
			htFloor.setCrtime(System.currentTimeMillis());
			int count=htFloorService.add(htFloor);
			categoryService.modFloorNumber(htFloor.getCategoryId(),count);
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	* 【分类楼层查看】
	* @param sid
	* @param page
	* @param vo
	* @param request
	* @param response  
	*/
	@RequestMapping(value="categoryView")
	public void category(@RequestParam("token") String sid,HtCategoryPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			page.setParentID(null);
			page.setOrderField("order");
			page.setOrderDirection(true);
			RespJsonPHandler.respOK(categoryService.queryPage(page),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
	}
	
		/** 
	* 【修改保存】
	* @param req
	* @param vo
	* @return
	*/
	@RequestMapping(value="mod")
	public void mod(@RequestParam("token") String sid,@Validated({Second.class})@ModelAttribute HtFloorModel vo,HttpServletRequest request, HttpServletResponse response){
		try {
			HtStaff user = getUser(sid);
			HtFloor htFloor = new HtFloor();
			BeanUtils.copyProperties(vo, htFloor);
			htFloor.setCrUserId(user.getDmId());
			htFloor.setCrtime(System.currentTimeMillis());
			htFloorService.mod(htFloor);
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
	public void delete(Long id,HttpServletRequest request, HttpServletResponse response){
		try {
			HtFloor floor=htFloorService.findVo(id, new HtFloor());
			HtFloorGoods floorGoods=new HtFloorGoods();
			floorGoods.setLinkUrl(id);
			List<HtFloorGoods> floorGoodsList=htFloorGoodsService.findList(floorGoods);
			if(floorGoodsList!=null&&floorGoodsList.size()>0){
				for (HtFloorGoods floorGoods2 : floorGoodsList) {
					htFloorGoodsService.delId(floorGoods2.getDmId());
				}
			}
			htFloorService.delId(id);
			HtFloor htFloor=new HtFloor();
			htFloor.setCategoryId(floor.getCategoryId());
			List<HtFloor> fList=htFloorService.findList(htFloor);
			int num=0;
			if(fList!=null){
				num=fList.size();
			}
			categoryService.modFloorNumber(htFloor.getCategoryId(),num);//楼层数量
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
	public void page(@RequestParam("token") String sid,HtFloorPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			page.setOrderField("is_valid,sort_number");
			page.setOrderDirection(true);
			RespJsonPHandler.respOK(htFloorService.query(page,null),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	* 【查看楼层】
	* @param sid
	* @param id
	* @param request
	* @param response  
	*/
	@RequestMapping(value="view")
	public void view(@RequestParam("token") String sid,Long id,HttpServletRequest request, HttpServletResponse response){
		try {
			HtFloor htFloor=new HtFloor();
			htFloor.setDmId(id);
			HtFloor floor=htFloorService.findVo(id, htFloor); 
			RespJsonPHandler.respOK(floor, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
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
		HtFloor vo = new HtFloor();
		try {
			if(null != id){
				vo = htFloorService.findVo(id,null);
			}
			RespJsonPHandler.respOK(vo,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	/**
	* 【楼层状态更改】
	* @param id
	* @param isValid
	* @param request
	* @param response  
	*/
	@RequestMapping(value="stateChanges")
	public void stateChanges(Long id,int isValid,HttpServletRequest request, HttpServletResponse response){
		try {
			HtFloor htFloor=new HtFloor();
			htFloor.setDmId(id);
			htFloor.setIsValid(isValid);
			htFloorService.statesMov(htFloor);
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
	}
	
	/**
	* 【分类楼层状态更改 1 启用，2禁用】
	* @param id
	* @param show
	* @param request
	* @param response  
	*/
	@RequestMapping(value="categoryStateChanges")
	public void categoryStateChanges(Long id,int show,HttpServletRequest request, HttpServletResponse response){
		try {
			categoryService.modCategoryStatus(id, show);
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
	}
	
	/**
	* 【楼层商品管理】
	* @param floorId
	* @param request
	* @param response  
	*/
	@RequestMapping(value="floorGoodsManager")
	public void floorGoodsManager(Long floorId,HttpServletRequest request, HttpServletResponse response){
		try {
			List<HtFloorGoods> list=htFloorService.findFloorGoodsByFloorId(floorId);
			RespJsonPHandler.respOK(list,response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
	}
	
}