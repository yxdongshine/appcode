package com.qtz.ht.manage.controller.wallet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.HtStaffPaymentFlowResponseModel;
import com.qtz.ht.util.BeanUtils;
import com.qtz.ht.wallet.page.HtStaffPaymentFlowPage;
import com.qtz.ht.wallet.service.HtStaffPaymentFlowService;
import com.qtz.ht.wallet.vo.HtStaffPaymentFlow;
import com.qtz.orig.common.Result;
import com.qtz.orig.order.service.OrigOrderService;
import com.qtz.orig.order.vo.HtOrder;

/**
 * <p>Title:HtStaffPaymentFlowController</p>
 * <p>Description:商户货款流水Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-29
 */
@RestController
@RequestMapping("v1.0/payment")
public class HtStaffPaymentFlowController extends BaseController {
	/**注入商户货款流水Service类*/
	@Resource(name="htStaffPaymentFlowServiceImpl")
	private HtStaffPaymentFlowService htStaffPaymentFlowService;
	@Autowired
	private OrigOrderService origOrderService;
	
	/** 
	* 【分页】
	* @param req
	* @param page
	* @return
	*/
	@RequestMapping(value="page")
	public void page(@RequestParam("token") String sid, @ModelAttribute HtStaffPaymentFlowPage page,
			HttpServletRequest request, HttpServletResponse response){
		try {
			if (null == page) {
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "入参错误！", response, request);
				return;
			}
			Pager<HtStaffPaymentFlow, Long> pageList =htStaffPaymentFlowService.query(page,null);
			Pager<HtStaffPaymentFlowResponseModel, Long> result = new Pager<>();	//返回结果
			BeanUtils.copyProperties(pageList, result);
			List<HtStaffPaymentFlow> list = pageList.getList();
			HtStaffPaymentFlowResponseModel model = null;
			if (null != list && list.size() > 0) {	
				List<HtStaffPaymentFlowResponseModel> listOrder = new ArrayList<>();
				for (HtStaffPaymentFlow htStaffPaymentFlow : list) {
					model = new HtStaffPaymentFlowResponseModel();
					BeanUtils.copyProperties(htStaffPaymentFlow, model);
					String payTypeName = PaymentMethodEnum.getTextByValue(htStaffPaymentFlow.getPaymentType());
					model.setPaymentTypeName(payTypeName);
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
	 * 供应商账户流水 详情列表
	 * suppAccountFlow:(). <br/> 
	 * @param sid
	 * @param dmId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="input")
	public void input(@RequestParam("token") String sid,@RequestParam("dmId")Long dmId,
			HttpServletRequest request, HttpServletResponse response){
			try {
				if (StringUtils.isEmpty(sid)) {
					log.error("账户的sid为空");
					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "入参错误！", response, request);
					return;
				}
				if(StringUtils.isEmpty(dmId)){
					log.error("供应商编号为空");
					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "入参错误！", response, request);
					return;
				}
				HtStaffPaymentFlow ht = htStaffPaymentFlowService.findVo(dmId, null);
				Result<HtOrder> r = origOrderService.getHtOrderInfo(ht.getOrderId());
				JSONObject result = JSONObject.parseObject(JSONObject.toJSONString(ht));
				JSONObject r1 = JSONObject.parseObject(JSONObject.toJSONString(r.getCarrierObject()));
				result.putAll(r1);
				RespJsonPHandler.respOK(result,response,request);
			} catch (ServiceException e) {
				log.error(e);
				RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
			}
	}
}