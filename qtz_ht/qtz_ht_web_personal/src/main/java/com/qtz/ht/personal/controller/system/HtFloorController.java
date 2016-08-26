
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
import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.personal.controller.BaseController;
import com.qtz.ht.system.service.HtFloorGoodsService;
import com.qtz.ht.system.service.HtFloorService;
import com.qtz.ht.system.vo.HtFloor;
import com.qtz.ht.system.vo.HtFloorGoods;

/** 
 * ClassName:HtFloorController <br/> 
 * Function: TODO (app 请求楼层控制类). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年8月10日 下午3:46:17 <br/> 
 * @author   yxd 
 * @version   
 * @see       
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
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	/**
	 * 
	 * 
	 * page:(). <br/> 
	 * TODO(根据分类编号 查询出楼层列表).<br/> 
	 * @author yxd 
	 * @param htFloor
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="list")
	public void page(@RequestParam Long categoryId,HttpServletRequest request, HttpServletResponse response){
		try {
			HtFloor htFloor =new HtFloor();
			htFloor.setCategoryId(categoryId);
			htFloor.setIsValid(1);
			RespJsonPHandler.respOK(htFloorService.findList(htFloor),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 根据分类查询出所有的楼层 及楼层的前九个商品
	 * view:(). <br/> 
	 * TODO().<br/> 
	 * @author yxd 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="viewFloorGoodsIbdexShow")
	public void view(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response){
		try {
			JSONObject result = new JSONObject();
			//获取所有的楼层
			HtFloor htFloor =new HtFloor();
			htFloor.setCategoryId(id);
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
							htStaffGoods=htStaffGoodsService.findVo(htStaffGoods.getDmId(), null);//查询商品
							if(!StringUtil.isEmpty(htStaffGoods))
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
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
	}
	
	
	
	
	/**
	 * 根据楼层编号查询楼层 及关联的商品 全部商品(包含首页推介的商品）
	 * view:(). <br/> 
	 * TODO().<br/> 
	 * 		List<HtStaffGoods> listG = this.htStaffGoodsService.findListByGoodsId(goodS.keySet());
	 * @author yxd 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="viewFloorGoods")
	public void viewFloorGoods(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response){
		try {
			HtFloor htFloor=new HtFloor();
			htFloor.setDmId(id);
			HtFloor floor=htFloorService.findVo(id, htFloor); 
			List<HtFloorGoods> lastFloorGoodsList = new ArrayList<HtFloorGoods>();//最后返回的数据
			//查询全部显示的记录数据
			HtFloorGoods vo=new HtFloorGoods();
			vo.setLinkUrl(id);
			List<HtFloorGoods> ibdexShowHtFloorGoodsList=htFloorGoodsService.findList(vo);//查询条件楼层下所有商品
			HtStaffGoods ibdexShowHtStaffGoods=new HtStaffGoods();
			Set<Long> idS =new HashSet<Long>();
			if(ibdexShowHtFloorGoodsList!=null&&ibdexShowHtFloorGoodsList.size()>0){
				for (HtFloorGoods ibdexShowHtFloorGoods : ibdexShowHtFloorGoodsList) {
					if(ibdexShowHtFloorGoods.getIsIbdexShow()==1){//首页显示的话
						ibdexShowHtStaffGoods.setDmId(ibdexShowHtFloorGoods.getGoodId());
						ibdexShowHtStaffGoods=htStaffGoodsService.findVo(ibdexShowHtStaffGoods.getDmId(),null);//查询商品
						if(!StringUtil.isEmpty(ibdexShowHtStaffGoods)){
							ibdexShowHtFloorGoods.setHtStaffGoods(ibdexShowHtStaffGoods);
						    lastFloorGoodsList.add(ibdexShowHtFloorGoods);
						}
					}else if (ibdexShowHtFloorGoods.getIsIbdexShow()==2){//不显示的则按照排序查询
						idS.add(ibdexShowHtFloorGoods.getGoodId());
					}
					
				}
			}
			//根据剩下的编号按照创建时间逆序查询
			List<HtStaffGoods> noShowHtStaffGoodsList= htStaffGoodsService.findListByGoodsIdAndCrtime(idS);
			if(noShowHtStaffGoodsList!=null&&noShowHtStaffGoodsList.size()>0){
				for (Iterator<HtStaffGoods> iterator = noShowHtStaffGoodsList.iterator(); iterator.hasNext();) {
					HtStaffGoods noShowHtStaffGoods =  iterator.next();
					for (Iterator<HtFloorGoods> iterator2 = ibdexShowHtFloorGoodsList.iterator(); iterator2.hasNext();) {
						HtFloorGoods htFloorGoods = iterator2.next();
						if(noShowHtStaffGoods.getDmId()==htFloorGoods.getGoodId()){
							htFloorGoods.setHtStaffGoods(noShowHtStaffGoods);
							//添加到最后列表中
							lastFloorGoodsList.add(htFloorGoods);
						}
					}
				}
			}
			
			if(lastFloorGoodsList!=null&&lastFloorGoodsList.size()>0)
				floor.setFloorGoods(lastFloorGoodsList);
			RespJsonPHandler.respOK(floor, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
	}
}
  