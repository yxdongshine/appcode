package com.qtz.ht.personal.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.Constants;
import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.system.page.HtActivityPage;
import com.qtz.ht.system.service.CategoryService;
import com.qtz.ht.system.service.HtActivityService;
import com.qtz.ht.system.service.HtBannerService;
import com.qtz.ht.system.service.HtFloorGoodsService;
import com.qtz.ht.system.service.HtFloorService;
import com.qtz.ht.system.vo.HtCategory;
import com.qtz.ht.system.vo.HtFloor;
import com.qtz.ht.system.vo.HtFloorGoods;


/**
 * <p>Title:MainControll</p>
 * <p>Description:(管理页面主界面)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市爱免费信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016-1-28
 */
@RestController
@RequestMapping(value="/")
public class IndexControll extends BaseController {
	
	/**
	 * 商品信息服务
	 */
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	
	
	@Resource(name="htBannerServiceImpl")
	private HtBannerService htBannerService; 
	
	
	/**注入活动管理表Service类*/
	@Resource(name="htActivityServiceImpl")
	private HtActivityService htActivityService;
	
	@Resource(name="categoryServiceImpl")
	private CategoryService categoryService;
	
	/**注入楼层管理表Service类*/
	@Resource(name="htFloorServiceImpl")
	private HtFloorService htFloorService; 
	
	@Resource(name="htFloorGoodsServiceImpl")
	private HtFloorGoodsService htFloorGoodsService;
	/**
	* 【海淘首页】
	* @param versionType
	* 					版本类型  1：商家，2：个人
	* @param clientType
	* 					客户端类型(1：安卓,2：IOS,3:所有)
	* @param response
	* @param request
	* @throws IOException  
	*/
	@RequestMapping(value="index")
	public void index(@RequestParam("versionType")Integer versionType, @RequestParam("clientType")Integer clientType,
			HttpServletResponse response,HttpServletRequest request) throws IOException {
		try {
			JSONObject result = new JSONObject();
			result.put("bannerS", this.htBannerService.findSpecifyFieldList(versionType, clientType, Constants.ONE));
			//活动信息添加
			HtActivityPage page = new HtActivityPage();
			page.setIsValid(1);
			result.put("activity",htActivityService.query(page,null));
            //首页只展示精选一级分类楼层
			HtCategory htCategory = new HtCategory();
			htCategory.setShow(1);
			List<HtCategory> htCategoryList= categoryService.findListStatusAndPra(htCategory);
			if(!htCategoryList.isEmpty()&&htCategoryList.size()>0){
				htCategory=htCategoryList.get(0);//精选类
			}
			Long categoryId =htCategory.getDmId();
			//获取所有的楼层
			HtFloor htFloor =new HtFloor();
			htFloor.setCategoryId(categoryId);
			htFloor.setIsValid(1);
			List<HtFloor> htFloorLis=htFloorService.findList(htFloor);
			if(htFloorLis!=null&&htFloorLis.size()>0){
				for (Iterator<HtFloor> iterator = htFloorLis.iterator(); iterator.hasNext();) {
					HtFloor everyOneHtFloor =  iterator.next();//每一层
					HtFloorGoods vo=new HtFloorGoods();
					vo.setLinkUrl(everyOneHtFloor.getDmId());
					vo.setIsIbdexShow(1);
					List<HtFloorGoods> htFloorGoodsList=htFloorGoodsService.findList(vo);//查询条件楼层下所有商品
					HtStaffGoods htStaffGoods=new HtStaffGoods();
					if(htFloorGoodsList!=null&&htFloorGoodsList.size()>0){
						for (HtFloorGoods htFloorGoods : htFloorGoodsList) {
							htStaffGoods.setDmId(htFloorGoods.getGoodId());
							htStaffGoods=htStaffGoodsService.findVo(htStaffGoods.getDmId(), htStaffGoods);//查询商品
							if(!StringUtil.isEmpty(htFloorGoods))
								htFloorGoods.setHtStaffGoods(htStaffGoods);
						}
					}
					if(htFloorGoodsList!=null&&htFloorGoodsList.size()>0)
						everyOneHtFloor.setFloorGoods(htFloorGoodsList);
				}
			}
			result.put("htFloorLis",htFloorLis);
			RespJsonPHandler.respOK(result, response, request);
		} catch (ServiceException e) {
			log.error(e.getErrorTitle(), e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response,request);
		}
	}
	/**
	* 【活动页获取商品列表】
	* @param response
	* @param request
	* @throws IOException  
	*/
	@RequestMapping(value="v1.0/getPanicBuyingGoodsList")
	public void getPanicBuyingGoodsList(
			HttpServletResponse response,HttpServletRequest request) throws IOException {
		try {
			List<HtStaffGoods> list = this.htStaffGoodsService.getPanicBuyingGoodsList(Constants.ONE);
			List<HtStaffGoods> oneCars = null;
			List<HtStaffGoods> twoCars = null;
			if (null != list && list.size() > 4) {
				oneCars = list.subList(0, 4);
				twoCars = list.subList(4, list.size());
			}else{
				oneCars = list;
			}
			JSONObject result = new JSONObject();
			result.put("oneCars", oneCars);
			result.put("twoCars", twoCars);
			RespJsonPHandler.respOK(result, response, request);
		} catch (ServiceException e) {
			log.error(e.getErrorTitle(), e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response,request);
		}
	}
	
	
	/**
	 * 返回上架时间排在前面的5条记录
	 * findShelvesTop5List:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value="v1.0/findShelvesTop5List")
	public void findShelvesTop5List(HttpServletResponse response,HttpServletRequest request) throws IOException {
		try {
			JSONObject result = new JSONObject();
			result.put("goodS", this.htStaffGoodsService.findShelvesTop5List());
			RespJsonPHandler.respOK(result, response, request);
		
		} catch (ServiceException e) {
			log.error(e.getErrorTitle(), e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response,request);
		}
	}
	
}
