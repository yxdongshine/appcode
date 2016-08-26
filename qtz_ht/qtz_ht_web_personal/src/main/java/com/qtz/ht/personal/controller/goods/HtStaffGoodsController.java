package com.qtz.ht.personal.controller.goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.Constants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.enums.CommodityStatusEnum;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.service.HtGoodDescService;
import com.qtz.ht.good.service.HtGoodsAlbumService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtGoodDesc;
import com.qtz.ht.good.vo.HtGoodsAlbum;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.personal.controller.BaseController;

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
	@Resource(name="htGoodDescServiceImpl")
	private HtGoodDescService htGoodDescService; 
	@Resource(name="htGoodsAlbumServiceImpl")
	private HtGoodsAlbumService htGoodsAlbumService; 
	
	/** 
	* 【分页】
	* @param req
	* @param page
	* @return
	* @throws ActionException  
	*/
	@RequestMapping(value="page")
	public void query(HtStaffGoodsPage page,HttpServletRequest request, HttpServletResponse response){
		try {
			//默认设置商品的审核状态通过 上架
			page.setAuditStatus(3);
			page.setStatus(1);
			RespJsonPHandler.respOK(htStaffGoodsService.query(page,null),response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	/**
	* 【查询购物车商品是否过期】
	* @param sid
	* @param goodsId
	* @param request
	* @param response  
	*/
	@RequestMapping(value="queryFailure")
	public void queryFailure(@RequestParam("goodsId") String goodsId,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<String> array = new ArrayList<>();
			Collections.addAll(array, goodsId.split(","));
			Set<Long> idS = new HashSet<>();
			for (int i = 0; i < array.size(); i++) {
				idS.add(NumberUtils.toLong(array.get(i)));
			}
			List<HtStaffGoods> list = htStaffGoodsService.findListByGoodsId(idS);
			JSONArray result = new JSONArray();
			JSONObject o = null;
			if (null != list && list.size() > 0) {
				for (HtStaffGoods g : list) {
					if (CommodityStatusEnum.on.getValue() == g.getStatus().intValue() && Constants.THREE == g.getAuditStatus().intValue()) {
						o = new JSONObject();
						o.put("goodId", g.getDmId());
						result.add(o);
					}
				}
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
	public void input(@RequestParam("dmId")Long id,
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtStaffGoods vo = htStaffGoodsService.findVo(id,null);
			HtGoodDesc desc = this.htGoodDescService.findVo(vo.getDmId(), null);
			HtGoodsAlbum obj = new HtGoodsAlbum();
			obj.setGoodId(vo.getDmId());
			List<HtGoodsAlbum> list = this.htGoodsAlbumService.findList(obj);
			JSONObject result = new JSONObject();
			result.put("dmId", vo.getDmId());
			result.put("goodName", vo.getGoodName());
//			result.put("simpleName", vo.getSimpleName());
			result.put("price", vo.getPrice());
			result.put("goodCode", vo.getGoodCode());
			result.put("sales", vo.getSales());
			result.put("mainPicture", vo.getMainPicture());
			result.put("goodDesc", desc.getGoodDesc());
			result.put("albums", list);
			RespJsonPHandler.respOK(result,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	/**
	 * 获取热门搜索的商品名称
	 * hotSearch:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="hotSearch")
	public void hotSearch(HttpServletRequest request, HttpServletResponse response){
		try {
            List<String> hotGoodsName = new ArrayList<String>();
			JSONObject result = new JSONObject();
			List<HtStaffGoods> htStaffGoodsList=htStaffGoodsService.findHotSearchGoods();
			if(htStaffGoodsList!=null){
				for (Iterator<HtStaffGoods> iterator = htStaffGoodsList.iterator(); iterator.hasNext();) {
					HtStaffGoods htStaffGoods = iterator.next();
					hotGoodsName.add(htStaffGoods.getGoodName());
				}
			}
			result.put("hotGoodsName",hotGoodsName);
			RespJsonPHandler.respOK(result,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}