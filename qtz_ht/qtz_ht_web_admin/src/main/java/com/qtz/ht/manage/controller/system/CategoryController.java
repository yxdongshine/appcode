package com.qtz.ht.manage.controller.system;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.Constants;
import com.mall.core.common.response.PagerDm;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.page.HtGoodCateAssoPage;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.service.HtGoodCateAssoService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.HtStaffGoodsResponseModel;
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
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	@Resource(name="htGoodCateAssoServiceImpl")
	private HtGoodCateAssoService htGoodCateAssoService;
	

	/**
	* 【添加商品分类】
	* @param sid
	* 				token
	* @param parentID
	* 				父级id
	* @param name
	* 				分类名称
	* @param mainPicture
	* 				分类图片
	* @param request
	* @param response  
	*/
	@RequestMapping(value="add")
	public void add(@RequestParam("token") String sid, Long parentID,
			@RequestParam String name, String mainPicture,
			HttpServletRequest request, HttpServletResponse response){
		try {
//			HtStaff user = getUser(sid);
			categoryService.addVo(parentID,name,mainPicture);
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
	public void mod(@RequestParam("token") String sid, @RequestParam("dmId")Long id,
			@RequestParam("name")String name, String mainPicture,
			HttpServletRequest request, HttpServletResponse response){
		try {
			categoryService.modVoNotNull(id,name,mainPicture);
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	/**
	* 【上下架分类】
	* @param sid
	* @param id
	* 				分类id
	* @param status
	* 				Y:上架;N：下架
	* @param request
	* @param response  
	*/
	@RequestMapping(value="modStatus")
	public void modStatus(@RequestParam("token") String sid, @RequestParam("dmId")Long id,
			@RequestParam("status")Boolean status,
			HttpServletRequest request, HttpServletResponse response){
		try {
			categoryService.modStatus(id, status);
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	* 【设置分类排序,对应上移下移】
	* @param sid
	* 				token
	* @param sortStr
	* 				jsonarray字符串
	* @param request
	* @param response  
	*/
	@RequestMapping(value="setSort")
	public void setSort(@RequestParam("token") String sid, @RequestParam("sortStr")String sortStr,
			HttpServletRequest request, HttpServletResponse response){
		try {
			categoryService.modSort(sortStr);
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
	public void delete(@RequestParam("token") String sid,@RequestParam("dmId")Long id,HttpServletRequest request, HttpServletResponse response){
		try {
			categoryService.delCategoryById(id);
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}

	/**
	* 【根据父级id查询下级所有分类】
	* @param sid
	* 				token
	* @param parentID
	* 				父id 可为空
	* @param request
	* @param response  
	*/
	@RequestMapping(value="list")
	public void list(@RequestParam("token") String sid,Long parentID,HttpServletRequest request, HttpServletResponse response){
		try {
			RespJsonPHandler.respOK(categoryService.findList(parentID),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}

	/**
	* 【根据id查看商品分类】
	* @param sid
	* @param id
	* @param request
	* @param response  
	*/
	@RequestMapping(value="input")
	public void input(@RequestParam("token") String sid, @RequestParam("dmId")Long id, HttpServletRequest request, HttpServletResponse response){
		HtCategory vo = new HtCategory(); 
		try {
			if(null != id)
				vo = categoryService.findVo(id, new HtCategory());
			JSONObject result = new JSONObject();
			if (null != vo) {
				result.put("dmId", vo.getDmId());
				result.put("sysHeadImg", vo.getSysHeadImg());
				result.put("name", vo.getName());
				result.put("goodNumber",vo.getGoodNumber());
				result.put("status", vo.getStatus());
			}
			RespJsonPHandler.respOK(result,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	/**
	* 【转移商品分类】
	* @param sid
	* 				token
	* @param goodId
	* 				商品id
	* @param cateId
	* 				分类id
	* @param type
	* 				操作类型 1：检查是否有此分类;2:转移分类
	* @param request
	* @param response  
	*/
	@RequestMapping(value="transferCate")
	public void transferCate(@RequestParam("token") String sid, @RequestParam("goodId")Long goodId,
			@RequestParam("cateId")Long cateId, @RequestParam("type")Integer type,
			HttpServletRequest request, HttpServletResponse response){
		try {
			htGoodCateAssoService.modGoodCate(goodId,cateId,type);
			RespJsonPHandler.respOK(response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/** 
	* 【查看分类下的商品及所有三级分类】
	* @param req
	* @param page
	* @return
	* @throws ActionException  
	*/
	@RequestMapping(value="page")
	public void page(@RequestParam("token") String sid,HtStaffGoodsPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			page.setOrderField("crtime");
			page =(HtStaffGoodsPage) htStaffGoodsService.query(page,null);
			JSONObject result = new JSONObject();
			PagerDm dmpage = null;
			if (null != page) {
				int nextPage = 0;
				if (page.getPageCount() > page.getNowPage()) nextPage = 1;

				dmpage = new PagerDm(page.getNowPage(), page.getPageSize(), page.getRowCount(), nextPage);
				
				List<HtStaffGoodsResponseModel> list = new ArrayList<>();
				HtStaffGoodsResponseModel g = null;
				for (HtStaffGoods htStaffGoods : page.getList()) {
					g = new HtStaffGoodsResponseModel();
					g.setDmId(htStaffGoods.getDmId());
					g.setStatus(htStaffGoods.getStatus());
					g.setGoodName(htStaffGoods.getGoodName());
					g.setCateS(htGoodCateAssoService.findList(htStaffGoods.getDmId()));
					list.add(g);
				}
				result.put("list", list);
				result.put("page", dmpage);
			}
			//查询所有三级分类
			HtCategory obj = new HtCategory();
			obj.setMark(SeaAmoConstants.THREE_HT_MARK);
			obj.setStatus(Constants.ZERO);
			List<HtCategory> cateS = categoryService.findList(obj);
			result.put("cateList", cateS);
			RespJsonPHandler.respOK(result,response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	/**
	* 【根据分类id查找该分类下所有商品】
	* @param sid
	* @param dmId
	* @param request
	* @param response  
	*/
	@RequestMapping(value="catetoryGoods")
	public void catetoryGoods(@RequestParam("token") String sid,HtGoodCateAssoPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			Pager<HtStaffGoods, Long> pager=categoryService.findAllByCategory(page);
			JSONObject result = new JSONObject(); 
			PagerDm dmpage = null;
			if (null != pager) {
				int nextPage = 0;
				if (pager.getPageCount() > pager.getNowPage()) nextPage = 1;
				dmpage = new PagerDm(pager.getNowPage(), pager.getPageSize(), pager.getRowCount(), nextPage); 
				List<HtStaffGoodsResponseModel> list = new ArrayList<>();
				HtStaffGoodsResponseModel g = null;
				for (HtStaffGoods htStaffGoods : pager.getList()) {
					g = new HtStaffGoodsResponseModel();
					g.setDmId(htStaffGoods.getDmId());
					g.setStatus(htStaffGoods.getStatus());
					g.setGoodName(htStaffGoods.getGoodName());
					g.setPrice(htStaffGoods.getPrice());
					g.setMainPicture(htStaffGoods.getMainPicture());
					g.setSales(htStaffGoods.getSales());
					g.setSettDiscount(htStaffGoods.getSettDiscount());
					List<HtGoodCateAsso> htList=htGoodCateAssoService.findList(htStaffGoods.getDmId());
					if(htList!=null&&htList.size()>0){
						g.setCateS(htList);
					}else{
						g.setCateS(null);
					}
					list.add(g);
				}
				result.put("list", list);
				result.put("page", dmpage);
			}
			RespJsonPHandler.respOK(result,response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	* 【分类商品树】
	* @param sid
	* @param request
	* @param response  
	*/
	@RequestMapping(value="categoryGoodsTree")
	public void categoryGoodsTree(@RequestParam("token") String sid,Long floorId,HttpServletRequest request, HttpServletResponse response){
		try {
			RespJsonPHandler.respOK(categoryService.categoryGoods(floorId), response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
}