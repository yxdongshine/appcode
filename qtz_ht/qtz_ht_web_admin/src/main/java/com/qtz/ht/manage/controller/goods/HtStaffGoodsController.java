package com.qtz.ht.manage.controller.goods;

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

import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.ht.enums.CommodityStatusEnum;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.service.HtGoodCateAssoService;
import com.qtz.ht.good.service.HtGoodCateService;
import com.qtz.ht.good.service.HtGoodDescService;
import com.qtz.ht.good.service.HtGoodsAlbumService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtGoodDesc;
import com.qtz.ht.good.vo.HtGoodsAlbum;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.GoodModel;
import com.qtz.ht.manage.model.HtStaffGoodsResponseModel;
import com.qtz.ht.user.vo.HtStaff;
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
	@Resource(name="htGoodCateServiceImpl")
	private HtGoodCateService htGoodCateService;
	@Resource(name="htGoodCateAssoServiceImpl")
	private HtGoodCateAssoService htGoodCateAssoService;
	  
	@RequestMapping(value="mod")
	public void mod(@RequestParam("token") String sid, @Valid @ModelAttribute GoodModel vo,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response)
	{
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
			}else{
				HtStaff staff = getUser(sid);
				HtStaffGoods good = new HtStaffGoods();
				BeanUtils.copyProperties(vo, good);
				this.htStaffGoodsService.mod(good,vo.getCateS(), vo.getGoodDesc(), vo.getAlbums(), staff.getDmId(), staff.getName(), UserType.PPUSER.value());
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
			HtStaff staff = getUser(sid);
			this.htStaffGoodsService.modUpOrDownGood(staff.getDmId(),staff.getName(),dmId,status);
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
	public void page(HtStaffGoodsPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			page.setOrderField("crtime");
			Pager<HtStaffGoods, Long> pageList =htStaffGoodsService.query(page,null);
			Pager<HtStaffGoodsResponseModel, Long> result = new Pager<>();	//返回结果
			BeanUtils.copyProperties(pageList, result);
			List<HtStaffGoods> list = pageList.getList();
			HtStaffGoodsResponseModel model = null;
			if (null != list && list.size() > 0) {	
				List<HtStaffGoodsResponseModel> listOrder = new ArrayList<>();
				for (HtStaffGoods htStaffGoods : list) {
					model = new HtStaffGoodsResponseModel();
					if(htStaffGoods.getFinalPrice()==null){
						htStaffGoods.setFinalPrice(0d);
					}
					BeanUtils.copyProperties(htStaffGoods, model);
					//如果为下架状态不显示上架时间
					if(model.getStatus()>=CommodityStatusEnum.under.getValue()){
						model.setShelvesTime(null);
					}
					//根据商家编号 商品编号找到分类信息
					model.setCateS(htGoodCateAssoService.findList(htStaffGoods.getDmId()));
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
	* 【查看】
	* @param sid
	* 			令牌
	* @param dmId
	* 			商品id
	* @param result
	* @param request
	* @param response  
	*/
	@RequestMapping(value="input")
	public void input(@RequestParam("token") String sid, @RequestParam("dmId") Long dmId,
			HttpServletRequest request, HttpServletResponse response)
	{
		try {
			HtStaffGoods vo = htStaffGoodsService.findVo(dmId,null);
			//商品相册
			HtGoodsAlbum obj = new HtGoodsAlbum();
			obj.setGoodId(vo.getDmId());
			List<HtGoodsAlbum> list = this.htGoodsAlbumService.findList(obj);
			//商品详情
			HtGoodDesc desc = this.htGoodDescService.findVo(vo.getDmId(), null);
			
			if(vo.getFinalPrice()==null){
				vo.setFinalPrice(0d);
			}
			GoodModel model = new GoodModel();
			//商品分类
			BeanUtils.copyProperties(vo, model);
			model.setGoodDesc(desc.getGoodDesc());
			//根据商家编号 商品编号找到分类信息
			model.setCateS(htGoodCateAssoService.findList(vo.getDmId()));
			model.setAlbums(list);
			
			RespJsonPHandler.respOK(model,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}