package com.qtz.ht.supplier.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.Arith;
import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
import com.qtz.ht.supplier.controller.BaseController;
import com.qtz.ht.supplier.model.OrderResponseModel;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtUser;
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
	@Resource(name="htStaffPaymentFlowServiceImpl")
	private HtStaffPaymentFlowService htStaffPaymentFlowService;
	@Autowired
	private HtBusinessService htBusinessService;
	/** 
	 * 供应商查询自己的订单列表
	* 【分页】
	* @param req
	* @param page
	* @return
	* @throws ActionException  
	*/
	@RequestMapping(value="page")
	public void page(@RequestParam("token") String sid,HtOrderPage page,
			HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user = getUser(sid);
			page.setStaffCode(user.getBusiId());//需要修改
			page.setPayType(Constants.TWO);
			page.setOrderField("crtime");
			page.setOrderDirection(false);
			//订单商品待处理
			page = origOrderService.query(page);
			Pager<OrderResponseModel, Long> result = new Pager<>();	//返回结果
			List<HtOrder> list = page.getList();
			if (null != list && list.size() > 0) {	//添加流水信息
				OrderResponseModel model = null;
				List<OrderResponseModel> listOrder = new ArrayList<>();
				for (HtOrder order : list) {
					model = new OrderResponseModel();
					for (int i = 0; i < order.getHtOrderGoodsList().size(); i++) {
						if(order.getHtOrderGoodsList().get(i).getGoodSpec()==null){
							order.getHtOrderGoodsList().get(i).setGoodSpec(" ");
						}
					}
					BeanUtils.copyProperties(order, model);
					model = this.getSuppMObilePhone(model, order.getDmId(), order.getStaffCode(), request, response);
					listOrder.add(model);
				}
				BeanUtils.copyProperties(page, result);
				result.setList(listOrder);
			}
			RespJsonPHandler.respOK(result, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
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
		try {
			pf = htStaffPaymentFlowService.getEntityByOrderId(orderId, suppId);
			if (null == pf) {
				if (null != suppId) {
					HtBusiness b = htBusinessService.findVo(suppId, null);
					if (null != b && null != b.getSettDiscount()) {
						model.setStaffRevenue(Arith.div(Arith.mul(model.getGoodtMoney(), b.getSettDiscount()), 10));
					}
				}
			}else{
				model.setStaffRevenue(pf.getStaffRevenue());
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
		return model;
	}	
	
	
	/**
	 * 查询某个指点的订单  返回商品列表  商检列表 详细信息
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
		try {
			HtUser user = getUser(sid);
			Result<HtOrder> result = origOrderService.backstageOrderInfo(dmId);
			if (result.isSuccess()) {
				HtOrder order = result.getCarrier();
				if (user.getBusiId().longValue() == order.getStaffCode().longValue()) {
					OrderResponseModel model = new OrderResponseModel();
					BeanUtils.copyProperties(order, model);
					
					if(order.getPaymentWay()!=null){
						String payTypeName = PaymentMethodEnum.getTextByValue(Integer.valueOf(order.getPaymentWay()));
						model.setPaymentTypeName(payTypeName);
					}
					//添加商家收入
					HtStaffPaymentFlow pf = htStaffPaymentFlowService.getEntityByOrderId(order.getDmId(), user.getBusiId());
					if (null != pf) {
						model.setStaffRevenue(pf.getStaffRevenue());
					}
					RespJsonPHandler.respOK(model,response,request);
				}else{
					RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "只能查看自己的订单", response, request);
				}
			}else{
				RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	
	/**
	 * 商家发货操作 
	 * shopDeliverGoodsOperation:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param dmId 订单编号
	 * @param express 快递公司名称
	 * @param expressCode 快递公司邮编
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="shopDeliverGoodsOperation")
    public void shopDeliverGoodsOperation(@RequestParam("token") String sid, 
    		@RequestParam("dmId") Long dmId,
    		@RequestParam("express") String express,
    		@RequestParam("expressCode") String expressCode,
			HttpServletRequest request, HttpServletResponse response){
		
		Result<HtOrder> result=origOrderService.shopDeliverGoods(dmId, express, expressCode);
		if(result.isSuccess()){
			RespJsonPHandler.respOK(response,request);	
		}else{
			RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
		}
		
    }
	

	/**
	 * 供应商还没有发货同意退款
	 * shopConfirmRefuneMoeny:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid 
	 * @param dmId 订单编号
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="notShippedAgreedRefundMoeny")
	public void notShippedAgreedRefundMoeny(@RequestParam("token") String sid, 
			@RequestParam("dmId") Long dmId,
    		HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user = getUser(sid);
			Result<HtOrder> result=origOrderService.alreadyShippedAgreedRefund(user.getBusiId(), dmId,null,null,null);
			if(result.isSuccess()){
				RespJsonPHandler.respOK(response,request);	
			}else{
				RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
			}
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		
	}
	
	/**
	 * 供应商已经发货用户未收到货供应商同意退款
	 * shopConfirmRefuneMoeny:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid 
	 * @param dmId 订单编号
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="agreeRefund")
	public void agreeRefund(@RequestParam("token") String sid, 
			@RequestParam("dmId") Long dmId,
    		HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user = getUser(sid);
			Result<HtOrder> result=origOrderService.notShippedAgreedRefund(user.getBusiId(), dmId);
			if(result.isSuccess()){
				RespJsonPHandler.respOK(response,request);	
			}else{
				RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
			}
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		
	}	
	/**
	 *  已经发货的商家退款操作
	 * alreadyShippedAgreedRefundMoeny:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param dmId 订单编号
	 * @param address 地址
	 * @param tel 电话
	 * @param consignee 收货人
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="alreadyShippedAgreedRefundMoeny")
	public void alreadyShippedAgreedRefundMoeny(@RequestParam("token") String sid, 
			@RequestParam("dmId") Long dmId,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("consignee")  String consignee,
    		HttpServletRequest request, HttpServletResponse response){
		try {
//			HtOrder order = origOrderService.findVo(dmId, null);
			HtUser user = getUser(sid);
			Result<HtOrder> result=origOrderService.alreadyShippedAgreedRefund(user.getBusiId(), dmId, address, tel, consignee);
			if(result.isSuccess()){
				RespJsonPHandler.respOK(response,request);	
			}else{
				RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
			}
//			if(order!=null &&order.getVipCode()>0){
//			}else {
//				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "该订单存在异常，联系客户退款", response, request);
//			}
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		
	}
	
	
	
	/**
	 * 供应商收到货后同意退款
	 * shopConfirmRefuneMoeny:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param dmId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="shopConfirmRefuneMoeny")
	public void shopConfirmRefuneMoeny(@RequestParam("token") String sid, 
    		@RequestParam("dmId") Long dmId,
    		HttpServletRequest request, HttpServletResponse response){
		try {
			HtUser user = getUser(sid);
			Result<HtOrder> result=origOrderService.shopConfirmOrder(user.getBusiId(), dmId);
			if(result.isSuccess()){
				RespJsonPHandler.respOK(response,request);
			}else{
				RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
			}
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
		
	}
	
	
	
	/**
	 * 商家不同意退款
	 * 
	 * shopNotRefundMoeny:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param dmId 订单编号
	 * @param messag 不同意退款的理由
	 * @param deliver 是否已经收到退货
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="shopNotRefundMoeny")
	public void shopNotRefundMoeny(@RequestParam("token") String sid, 
    		@RequestParam("dmId") Long dmId,
    		@RequestParam("messag") String messag,
    		HttpServletRequest request, HttpServletResponse response){

		try {
			HtUser user = getUser(sid);
//			HtOrder order = origOrderService.findVo(dmId, null);
			Result<HtOrder> result=origOrderService.shopNotRefund(user.getBusiId(), dmId,messag,null);
			if(result.isSuccess()){
				RespJsonPHandler.respOK(response,request);	
			}else{
				RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
			}
//			if (user.getBusiId().longValue() == order.getStaffCode().longValue()) {
//			}else{
//				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "该订单商家不匹配", response, request);
//			}
//			if(user != null && order != null){
//			}else{
//				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "该订单存在异常，联系客户退款", response, request);
//			}
			
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	* 【修改运单号】
	* @param sid
	* @param id
	* 				订单id
	* @param express
	* 				物流公司名
	* @param expressCode
	* 				运单号
	* @param request
	* @param response  
	*/
	@RequestMapping(value="modWaybill")
    public void modWaybill(@RequestParam("token") String sid, @RequestParam("dmId") Long id,
    		@RequestParam("express") String express, @RequestParam("expressCode") String expressCode,
			HttpServletRequest request, HttpServletResponse response){
		HtUser user;
		try {
			user = getUser(sid);
			Result<HtOrder> result=null;//origOrderService.modifyExpressCore(id, user.getBusiId(), express, expressCode);
			if(result.isSuccess()){
				RespJsonPHandler.respOK(response,request);	
			}else{
				RespJsonPHandler.respError(result.getCode(), result.getFailMessage(), response, request);
			}
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
    }
}
