package com.qtz.ht.supplier.controller.goods;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.service.HtGoodCateAssoService;
import com.qtz.ht.good.service.HtGoodDescService;
import com.qtz.ht.good.service.HtGoodsAlbumService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtGoodDesc;
import com.qtz.ht.good.vo.HtGoodsAlbum;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.supplier.controller.BaseController;
import com.qtz.ht.supplier.model.GoodModel;
import com.qtz.ht.supplier.model.HtStaffGoodsResponseModel;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.ht.util.BeanUtils;
import com.qtz.ht.util.ProcessingCheckExceptionUtil;

/**
 * <p>Title:HtStaffGoodsController</p>
 * <p>Description:商户商品Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
@RestController
@RequestMapping("v1.0/good")
public class HtStaffGoodsController extends BaseController {
	/**注入商户商品Service类*/
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService; 
	@Resource(name="htGoodsAlbumServiceImpl")
	private HtGoodsAlbumService htGoodsAlbumService; 
	@Resource(name="htGoodDescServiceImpl")
	private HtGoodDescService htGoodDescService; 
	@Resource(name="htGoodCateAssoServiceImpl")
	private HtGoodCateAssoService htGoodCateAssoService; 
	@Resource(name="htBusinessServiceImpl")
	private HtBusinessService htBusinessService;

	/**
	* 【添加商品】
	* @param sid
	* @param entryModel
	* @param result
	* @param request
	* @param response  
	*/
	@RequestMapping(value="add")
	public void add(@RequestParam("token") String sid,@Valid @ModelAttribute GoodModel entryModel,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				HtUser user = getUser(sid);
				HtStaffGoods vo = new HtStaffGoods();
				BeanUtils.copyProperties(entryModel, vo);
				vo.setBusiId(user.getBusiId());
				log.debug(entryModel.getAlbums());
				vo = htStaffGoodsService.add(vo, entryModel.getCateS(), entryModel.getGoodDesc(), JSON.parseArray(entryModel.getAlbums(), HtGoodsAlbum.class),user.getDmId(),user.getRealName(),UserType.BUSINESS.value());
				RespJsonPHandler.respOK(response,request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (Exception e) {
			log.error(e);
			RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "入參错误!", response, request);
		}
	}
	/**
	* 【编辑保存商品】
	* @param sid
	* @param entryModel
	* @param result
	* @param request
	* @param response  
	*/
	@RequestMapping(value="mod")
	public void mod(@RequestParam("token") String sid,@Valid @ModelAttribute GoodModel entryModel,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				HtUser user = getUser(sid);
				HtStaffGoods vo = new HtStaffGoods();
				BeanUtils.copyProperties(entryModel, vo);
				log.debug(entryModel.getAlbums());
				vo = htStaffGoodsService.mod(vo,entryModel.getCateS(), entryModel.getGoodDesc(), JSON.parseArray(entryModel.getAlbums(), HtGoodsAlbum.class),user.getDmId(),user.getRealName(),UserType.BUSINESS.value());
				RespJsonPHandler.respOK(response,request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}

	/**
	* 【上架或者下架单个商品】
	* @param sid
	* @param dmId
	* @param status
	* @param request
	* @param response  
	*/
	@RequestMapping(value="upOrDownGood")
	public void upOrDownGood(@RequestParam("token") String sid,
			@RequestParam("dmId") Long dmId,@RequestParam("status") Integer status, 
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user = getUser(sid);
			Long busiId=user.getBusiId();
		    if(busiId!=null){
		        HtBusiness  htBusiness =htBusinessService.findVo(busiId, null);
		    	if(htBusiness!=null){
		    		if(htBusiness.getIsFrozen() == 1||htBusiness.getIsShield()==1){//冻结 提示：
		    			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1, user.getRealName()+"处于不合法状态，禁止当前操作");
		    		}
		    	}
		    }
			this.htStaffGoodsService.modUpOrDownGood(user.getDmId(),user.getRealName(),dmId,status);
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
	* @throws ActionException  
	*/
	@RequestMapping(value="page")
	public void query(@RequestParam("token") String sid,
			@ModelAttribute HtStaffGoodsPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user = getUser(sid);
			page.setBusiId(user.getBusiId());
			page.setOrderField("shelves_time");
			
			Pager<HtStaffGoods, Long> pageList =htStaffGoodsService.query(page,null);
			Pager<HtStaffGoodsResponseModel, Long> result = new Pager<>();	//返回结果
			BeanUtils.copyProperties(pageList, result);
			List<HtStaffGoods> list = pageList.getList();
			HtStaffGoodsResponseModel model = null;
			if (null != list && list.size() > 0) {	
				List<HtStaffGoodsResponseModel> listOrder = new ArrayList<>();
				for (HtStaffGoods htStaffGoods : list) {
					model = new HtStaffGoodsResponseModel();
					BeanUtils.copyProperties(htStaffGoods, model);
//					//根据商家编号 商品编号找到分类信息
//					model.setCateS(htGoodCateAssoService.findList(htStaffGoods.getDmId()));
//					//设置成本价补位null
//					if(model.getFinalPrice()==null){
//						model.setFinalPrice(0d);
//					}
					listOrder.add(model);
				}	
				result.setList(listOrder);
				
			}
			RespJsonPHandler.respOK(result,response,request);
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
	* @throws ActionException  
	*/
	@RequestMapping(value="input")
	public void input(@RequestParam("token") String sid, @RequestParam("dmId") Long dmId,
			HttpServletRequest request, HttpServletResponse response)
	{
		HtStaffGoods vo = null;
		try {
			HtUser user = getUser(sid);
			vo = htStaffGoodsService.findVo(dmId,null);
			if (user.getBusiId().longValue() != vo.getBusiId().longValue()) {
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7, "只能查看自己的商品", response, request);
				return;
			}
			HtGoodsAlbum obj = new HtGoodsAlbum();
			obj.setGoodId(vo.getDmId());
			List<HtGoodsAlbum> list = this.htGoodsAlbumService.findList(obj);//商品相册
			HtGoodDesc desc = this.htGoodDescService.findVo(vo.getDmId(), null);//商品详情
			GoodModel model = new GoodModel();
			
			//商品分类
			List<HtGoodCateAsso> cateS = this.htGoodCateAssoService.findList(vo.getDmId());
			model.setCateS(cateS);
			//设置成本价不为null
			if(vo.getFinalPrice()==null){
				vo.setFinalPrice(0d);
			}
			BeanUtils.copyProperties(vo, model);
			model.setGoodDesc(desc.getGoodDesc());
			model.setAlbums(JSONArray.toJSONString(list));
			RespJsonPHandler.respOK(model,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}