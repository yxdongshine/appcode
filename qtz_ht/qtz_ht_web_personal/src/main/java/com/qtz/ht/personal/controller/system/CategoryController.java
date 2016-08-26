
package com.qtz.ht.personal.controller.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.good.service.HtGoodCateAssoService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.personal.controller.BaseController;
import com.qtz.ht.system.service.CategoryService;
import com.qtz.ht.system.vo.HtCategory;

/** 
 * ClassName:CategoryController <br/> 
 * Function: TODO (app 端查看分类接口). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年8月10日 上午11:04:55 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
@RestController
@RequestMapping("v1.0/category")
public class CategoryController  extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(CategoryController.class);
	/**注入活动管理表Service类*/
	@Resource(name="categoryServiceImpl")
	private CategoryService categoryService;
	@Resource(name="htGoodCateAssoServiceImpl")
	private HtGoodCateAssoService htGoodCateAssoService;
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;

	/**
	 * 
	 * list:(). <br/> 
	 * TODO(app 端查看分类借口   parentid 不带参数值表示查询一级分类).<br/> 
	 * 
	 * @author yxd 
	 * @param parentID
	 * @param isIndex 1 是首页 0 查询分类
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="list")
	public void list(Long parentID,Integer isIndex ,HttpServletRequest request, HttpServletResponse response){
		try {
			HtCategory htCategory = new HtCategory();
			htCategory.setParentID(parentID);
			if(isIndex!=null&&isIndex.intValue()==1){
				htCategory.setShow(1);
			}else{
				htCategory.setStatus(0);
			}
			RespJsonPHandler.respOK(categoryService.findListStatusAndPra(htCategory),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 查询含有二级分类的一级分类列表
	 * includeScelist:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param oneParentID
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value="includeScelist")
//	public void includeScelist(Long oneParentID,HttpServletRequest request, HttpServletResponse response){
//		try {
//			JSONObject result = new JSONObject();
//			HtCategory oneHtCategory = new HtCategory();
//			oneHtCategory.setStatus(0);
//			List<HtCategory> oneHtCategoryList=categoryService.findListStatusAndPra(oneHtCategory);
//			result.put("oneHtCategoryList", oneHtCategoryList);
//			if(oneHtCategoryList!=null&&oneHtCategoryList.size()>0){
//				if(oneParentID==null){//查询第一个一级分类
//					oneParentID = oneHtCategoryList.get(0).getDmId();
//				}
//				//根据分类编号查询二级分类信息
//				HtCategory twoHtCategory = new HtCategory();
//				twoHtCategory.setStatus(0);
//				twoHtCategory.setParentID(oneParentID);
//				List<HtCategory> twoHtCategoryList=categoryService.findListStatusAndPra(twoHtCategory);
//                if(twoHtCategoryList!=null){
//        			result.put("twoHtCategoryList", twoHtCategoryList);
//                }
//			}
//			RespJsonPHandler.respOK(result,response,request);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
//		}
//	}
	

	/**
	 * 
	 * input:(). <br/> 
	 * TODO(根据分类编号查询具体分类的信息).<br/> 
	 * 
	 * @author yxd 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="input")
	public void input(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response){
		HtCategory vo = new HtCategory();
		try {
			if(null != id)
				vo = categoryService.findVo(id,vo);
			JSONObject result = new JSONObject();
			if (null != vo) {
				result.put("dmId", vo.getDmId());
				result.put("sysHeadImg", vo.getSysHeadImg());
				result.put("name", vo.getName());
			}
			RespJsonPHandler.respOK(result,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 获取三级分类及三级分类下的所有商品 
	 * getHtStuffGoods:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param secCateId  表示是二级标签查询全部的商 一定是必须带上的
	 * @param threeCateId 三级分类id的商品 不带表示查询全部  带上表示查询特定三级的标签
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getHtStuffGoodsByCate")
	public void getHtStuffGoodsByCate(@RequestParam Long secCateId, Long threeCateId,HttpServletRequest request, HttpServletResponse response){
		JSONObject result = new JSONObject();
		try {
			//返回二级分类编号 查询出二级分类对象
			HtCategory secHtCategory = new HtCategory();
			secHtCategory = categoryService.findVo(secCateId,secHtCategory);
			if(secHtCategory!=null){
				result.put("secHtCategory", secHtCategory);
			}
			
			//查询所有的三级分类列表
			HtCategory htCategory = new HtCategory();
			htCategory.setParentID(secCateId);
			htCategory.setStatus(0);
			List<HtCategory> htCategoryList =categoryService.findListStatusAndPra(htCategory);
			result.put("threeHtCategoryList", htCategoryList);
			//所有满足条件的对应关系记录
			List<HtGoodCateAsso> htGoodCateAssoList =new ArrayList<HtGoodCateAsso>();
			//jihe编号集合
			Set<Long> idS =new HashSet<Long>();
			//查询商品
			if(threeCateId==null){//表示查询全部
				//带上二级标签查询全部关联的记录
				HtGoodCateAsso secHtGoodCateAsso=new HtGoodCateAsso();
				secHtGoodCateAsso.setTwoCate(secCateId);
				htGoodCateAssoList=htGoodCateAssoService.findList(secHtGoodCateAsso);
				
			}else if(threeCateId>0){//表示查询特定的三级分类下的商品
				//带上三级标签查询全部关联的记录
				HtGoodCateAsso thrHtGoodCateAsso=new HtGoodCateAsso();
				thrHtGoodCateAsso.setThreeCate(threeCateId);;
				htGoodCateAssoList=htGoodCateAssoService.findList(thrHtGoodCateAsso);
			}
			//查询出全部关系编号
			for (Iterator<HtGoodCateAsso> iterator = htGoodCateAssoList.iterator(); iterator.hasNext();) {
				HtGoodCateAsso htGoodCateAsso = iterator.next();
				idS.add(htGoodCateAsso.getGoodId());
			}
			//根据集合查询出全部的商品
			List<HtStaffGoods> htStaffGoodsList= htStaffGoodsService.findListByGoodsIdAndCrtime(idS);
			result.put("htStaffGoodsList", htStaffGoodsList);
			RespJsonPHandler.respOK(result,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		
	}
	
}
  