package com.qtz.ht.manage.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.system.page.HtFloorGoodsPage;
import com.qtz.ht.system.service.HtFloorGoodsService;
import com.qtz.ht.system.service.HtFloorService;
import com.qtz.ht.system.vo.HtFloor;
import com.qtz.ht.system.vo.HtFloorGoods;

/**
 * <p>Title:HtFloorGoodsController</p>
 * <p>Description:楼层关联商品Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
 */
@RestController
@RequestMapping("v1.0/floorGoods")
public class HtFloorGoodsController extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(HtFloorGoodsController.class);
	/**注入楼层关联商品Service类*/
	@Resource(name="htFloorGoodsServiceImpl")
	private HtFloorGoodsService htFloorGoodsService; 
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	@Resource(name="htFloorServiceImpl")
	private HtFloorService htFloorService; 
	
	
	/** 
	* 【添加】
	* @param req
	* @param vo
	* @return
	*/
	@RequestMapping(value="add")
	public void add(@RequestParam("token") String sid,@RequestParam String floorGoods,HttpServletRequest request, HttpServletResponse response){
		try {
			List<HtFloorGoods> list=JSON.parseArray(floorGoods, HtFloorGoods.class); 
			int count=0;
			if(list!=null&&list.size()>0){
				HtFloorGoods obj=new HtFloorGoods();
				obj.setLinkUrl(list.get(0).getLinkUrl());
				List<HtFloorGoods> htFloorGoods=htFloorGoodsService.findList(obj);
				int size=0;
				if(htFloorGoods!=null&&htFloorGoods.size()>0){
					size=htFloorGoods.get(htFloorGoods.size()-1).getSortNumber();
				}
				for(int i=1;list!=null&&i<=list.size();i++){
					if(size>0){
						list.get(i-1).setSortNumber(size+i);
					}else{
						list.get(i-1).setSortNumber(i);
					}
				}
				htFloorGoodsService.addList(list);
			}
			HtFloorGoods vo=new HtFloorGoods();
			vo.setLinkUrl(list.get(0).getLinkUrl());
			List<HtFloorGoods> list1=htFloorGoodsService.findList(vo);
			if(list1!=null&&list1.size()>0){
				count=list1.size();
			}
			HtFloor htFloor=new HtFloor();
			htFloor.setDmId(list.get(0).getLinkUrl());
			htFloor.setGoodNumber(count);
			htFloorService.modVoNotNull(htFloor);
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/** 
	* 【是否首页推荐】
	* @param req
	* @param vo
	* @return
	*/
	@RequestMapping(value="isIbdexShow")
	public void mod(@RequestParam("token") String sid,Long dmId,int isIbdexShow,HttpServletRequest request, HttpServletResponse response){
		try {
			HtFloorGoods vo=new HtFloorGoods();
			vo.setDmId(dmId);
			vo.setIsIbdexShow(isIbdexShow);
			htFloorGoodsService.modVoNotNull(vo);
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
			int count=0;
			htFloorGoodsService.delId(id);
			HtFloorGoods htFloorGoods=htFloorGoodsService.findVo(id, null);
			HtFloorGoods vo=new HtFloorGoods();
			vo.setLinkUrl(htFloorGoods.getLinkUrl());
			List<HtFloorGoods> list=htFloorGoodsService.findList(vo);
			if(list!=null&&list.size()>0){
				count=list.size();
			}
			HtFloor htFloor=new HtFloor();
			htFloor.setDmId(vo.getDmId());
			htFloor.setGoodNumber(count);
			htFloorService.modVoNotNull(htFloor);
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
	public void page(HtFloorGoodsPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			page.setOrderField("is_ibdex_show,sort_number");
			page.setOrderDirection(true);
			Pager<HtFloorGoods,Long> pages=htFloorGoodsService.query(page,null);
			HtStaffGoods htStaffGoods=null;
			if(pages.getList()!=null&&pages.getList().size()>0){
				for (HtFloorGoods vo : pages.getList()) {
					htStaffGoods=htStaffGoodsService.findVo(vo.getGoodId(), new HtStaffGoods());
					if(htStaffGoods!=null&&!"".equals(htStaffGoods.getGoodName())){
						vo.setGoodName(htStaffGoods.getGoodName());
					}
				}
			}
			
			RespJsonPHandler.respOK(pages,response,request);
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
	public void input(Long id,HttpServletRequest request, HttpServletResponse response){
		HtFloorGoods vo = new HtFloorGoods();
		try {
			if(null != id)
				vo = htFloorGoodsService.findVo(id,null);
			RespJsonPHandler.respOK(vo,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
}