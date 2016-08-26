package com.qtz.ht.manage.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.RegexUtil;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.request.order.OrderResponseModel;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.util.BeanUtils;
import com.qtz.ht.wallet.service.HtStaffPaymentFlowService;
import com.qtz.ht.wallet.vo.HtStaffPaymentFlow;
import com.qtz.orig.common.Result;
import com.qtz.orig.order.page.HtOrderPage;
import com.qtz.orig.order.service.OrigOrderService;
import com.qtz.orig.order.vo.HtOrder;



/**
 * <p>Title:HtOrderController</p>
 * <p>Description:(订单操作表控制类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年5月25日
 */
@RestController
@RequestMapping(value="/v1.0/order/")
public class HtOrderController extends BaseController{
		
	@Autowired
	private OrigOrderService origOrderService;
	/**注入商户货款流水Service类*/
	@Resource(name="htStaffPaymentFlowServiceImpl")
	private HtStaffPaymentFlowService htStaffPaymentFlowService;
	@Autowired
	private HtBusinessService htBusinessService;
	
	/** 
	* 【分页】
	* 
	* 根据HtOrderPage 的状态值 分页查出订单列表 和  售后订单列表
	* @param req
	* @param page
	* @return
	* @throws ActionException  
	*/
	@RequestMapping(value="page")
	public void page(@RequestParam("token") String sid,HtOrderPage page,String mobilePhone,
			HttpServletRequest request, HttpServletResponse response){
		try {
			page.setOrderField("crtime");
			page.setOrderDirection(true);
			if (null != mobilePhone && RegexUtil.isMobile(mobilePhone)) {
				HtBusiness b = new HtBusiness();
				b.setMobilePhone(mobilePhone);
				List<HtBusiness> list = htBusinessService.findList(b);
				if (null != list && list.size() > 0) {
					page.setStaffCode(list.get(0).getDmId());
				}
			}
			page =origOrderService.query(page);
			
			Pager<OrderResponseModel, Long> result = new Pager<>();	//返回结果
			List<HtOrder> list = page.getList();
			if (null != list && list.size() > 0) {	//添加流水信息及供应商手机号
				OrderResponseModel model = null;
				List<OrderResponseModel> listOrder = new ArrayList<>();
				for (HtOrder order : list) {
					model = new OrderResponseModel();
					BeanUtils.copyProperties(order, model);
					
					model = this.getSuppMObilePhone(model, order.getDmId(), order.getStaffCode(), request, response);
					if(order.getPaymentWay()!=null){
						String payTypeName = PaymentMethodEnum.getTextByValue(Integer.valueOf(order.getPaymentWay()));
						model.setPaymentTypeName(payTypeName);
					}

					listOrder.add(model);
				}
				BeanUtils.copyProperties(page, result);
				result.setList(listOrder);
			}
			RespJsonPHandler.respOK(result,response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
	}
	
	/**
	* 【获取供应商手机号及平台扣点分利等】
	* @param model
	* @param orderId
	* @param suppId
	* @param request
	* @param response
	* @return  
	*/
	private OrderResponseModel getSuppMObilePhone(OrderResponseModel model, Long orderId, Long suppId,
		HttpServletRequest request, HttpServletResponse response)
	{
		HtStaffPaymentFlow pf = null;	//商家流水记录
		HtBusiness b = null;	//商家信息
		try {
			pf = htStaffPaymentFlowService.getEntityByOrderId(orderId, suppId);
			b = htBusinessService.findVo(suppId, null);
			if (null != pf) {
				model.setPlatformPoints(pf.getPlatformPoints());
				model.setPlatformRevenue(pf.getPlatformRevenue());
				model.setStaffRevenue(pf.getStaffRevenue());
			}
			if (b != null) {
				model.setSupplierPhone(b.getMobilePhone());
				model.setIDCard(b.getLegalIdCard());
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
		return model;
	}
	
	/**
	 * 得到某一条订单详细  里面包含商品列表 供应商列表
	 * input:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param dmId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="input")
	public void input(@RequestParam("token") String sid, @RequestParam("dmId") Long dmId,
			HttpServletRequest request, HttpServletResponse response){
		HtOrder order = null;
		//调用带订单号码下  有多少种商品 以及商家（应该只有一个）
		Result<HtOrder> resultHtOrderList = origOrderService.backstageOrderInfo(dmId);
		if(resultHtOrderList.isSuccess())
		{
			order = resultHtOrderList.getCarrier();
			OrderResponseModel model = new OrderResponseModel();
			BeanUtils.copyProperties(order, model);
			model = this.getSuppMObilePhone(model, order.getDmId(), order.getStaffCode(), request, response);
			if(order.getPaymentWay()!=null){
				String payTypeName = PaymentMethodEnum.getTextByValue(Integer.valueOf(order.getPaymentWay()));
				model.setPaymentTypeName(payTypeName);
			}
			RespJsonPHandler.respOK(model,response,request);
		}else{
			RespJsonPHandler.respError(resultHtOrderList.getCode(), resultHtOrderList.getFailMessage(), response, request);
		}
	}
	
	
	
	/**
	 * 管理员退货  （前提是已经接到货）
	 * adminRefundOrder:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid 
	 * @param dmId 订单编号
	 * @param address
	 * @param tel
	 * @param consignee
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="adminRefundOrder")
	public void adminRefundOrder(@RequestParam("token") String sid, 
			@RequestParam("dmId") Long dmId,
/*			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("consignee") String consignee,*/
			HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		/**
		 * 需要再修改
		 */
		Result<HtOrder> result=origOrderService.adminReturnGoods(dmId, null, null, null);
		if(result.isSuccess()){
			RespJsonPHandler.respOK(response,request);	
		}else{
			RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
		}
	}
	
	
	
	/**
	 * 管理员退款操作
	 * adminRefundMoeny:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param dmId 订单编号
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="adminRefundMoeny")
	public void adminRefundMoeny(@RequestParam("token") String sid, 
			@RequestParam("dmId") Long dmId,
			HttpServletRequest request, HttpServletResponse response){
		//需要二次修改
		Result<HtOrder> result=origOrderService.adminRefund(dmId);
		if(result.isSuccess()){
			RespJsonPHandler.respOK(response,request);	
		}else{
			RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
		}
	}
	
	/**
	 * 管理员仲裁操作
	 * adminArbitration:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param dmId
	 * @param content
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="adminArbitration")
	public void adminArbitration(@RequestParam("token") String sid, 
			@RequestParam("dmId") Long dmId,
			@RequestParam("content") String content,
			HttpServletRequest request, HttpServletResponse response){
		Result<HtOrder> result=origOrderService.adminArbitration(dmId, content);
		if(result.isSuccess()){
			RespJsonPHandler.respOK(response,request);	
		}else{
			RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
		}
	}
}
