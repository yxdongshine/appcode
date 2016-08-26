package com.qtz.ht.supplier.controller.goods;

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
import com.mall.core.exception.ServiceException;
import com.qtz.ht.good.service.HtGoodCateService;
import com.qtz.ht.good.vo.HtGoodCate;
import com.qtz.ht.supplier.controller.BaseController;

/**
 * <p>Title:HtGoodCateController</p>
 * <p>Description:商户商品分类表Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-08
 */
@RestController
@RequestMapping("v1.0/goodCate")
public class HtGoodCateController extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(HtGoodCateController.class);
	/**注入商户商品分类表Service类*/
	@Resource(name="htGoodCateServiceImpl")
	private HtGoodCateService htGoodCateService; 
	
	/** 
	* 【编辑】
	* @param req
	* @param id
	* @return
	*/
	@RequestMapping(value="list")
	public void list(@RequestParam("token") String sid, Long pId,
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtGoodCate vo = new HtGoodCate();
			if (null == pId) {
				pId = 0l;
			}
			vo.setPid(pId);
			vo.setStatus(Constants.ONE);
			List<HtGoodCate> list = this.htGoodCateService.findList(vo);
			RespJsonPHandler.respOK(list,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}