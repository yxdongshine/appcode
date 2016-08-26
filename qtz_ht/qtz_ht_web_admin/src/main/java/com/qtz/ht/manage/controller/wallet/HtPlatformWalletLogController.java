package com.qtz.ht.manage.controller.wallet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.response.PagerDm;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.dm.userwallet.model.EnumResultOut;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.wallet.page.HtPlatformWalletLogPage;
import com.qtz.ht.wallet.service.HtPlatformWalletLogService;
import com.qtz.ht.wallet.vo.HtPlatformWalletLog;
import com.qtz.orig.common.Result;
import com.qtz.orig.order.service.OrigOrderService;
import com.qtz.orig.order.vo.HtOrder;


/**
 * <p>Title:HtPlatformWalletLogController</p>
 * <p>Description:(平台钱包流水控制类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年6月1日
 */
@RestController
@RequestMapping("v1.0/platform/paymentFlow")
public class HtPlatformWalletLogController extends BaseController {
	/**注入商户货款流水Service类*/
	@Resource(name="htPlatformWalletLogServiceImpl")
	private HtPlatformWalletLogService htPlatformWalletLogService;
	@Autowired
	private OrigOrderService origOrderService;
	
	/** 
	* 【分页】
	* @param req
	* @param page
	* @return
	*/
	@RequestMapping(value="page")
	public void page(@RequestParam("token") String sid, @ModelAttribute HtPlatformWalletLogPage page,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<EnumResultOut> list = PaymentMethodEnum.getTypesByUserType(UserType.PERSON.value());
			page = (HtPlatformWalletLogPage) htPlatformWalletLogService.query(page,null);
			// 分页数据
			PagerDm dmpage = null;
			JSONObject result = new JSONObject();
			if (null != page) {
				int nextPage = 0;
				if (page.getPageCount() > page.getNowPage())
					nextPage = 1;

				dmpage = new PagerDm(page.getNowPage(), page.getPageSize(), page.getRowCount(), nextPage);
				
				JSONObject json = new JSONObject();
				json.put("list", page.getList());
				json.put("page", dmpage);
				result.put("data", json);
			}
			result.put("paymentList", list);
			RespJsonPHandler.respOK(result, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/** 
	* 【查看】
	* @param req
	* @param dmId
	* @return
	*/
	@RequestMapping(value="input")
	public void input(@RequestParam("token") String sid,@RequestParam("dmId")Long dmId,
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtPlatformWalletLog vo = htPlatformWalletLogService.findVo(dmId,null);
			Result<HtOrder> r = origOrderService.backstageOrderInfo(vo.getOrderId());
			HtOrder order = null;
			if (r.isSuccess() ) {
				order =  r.getCarrier();
			}
			
			JSONObject result = new JSONObject();
			result.put("detailsData", vo);
			result.put("order", order);
			RespJsonPHandler.respOK(result,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}