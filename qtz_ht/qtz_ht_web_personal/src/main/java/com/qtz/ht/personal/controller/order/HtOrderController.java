package com.qtz.ht.personal.controller.order;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.RespKey;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.Pager;
import com.qtz.dm.user.vo.User;
import com.qtz.ht.personal.controller.BaseController;
import com.qtz.ht.personal.model.request.order.OrderRequestModel;
import com.qtz.orig.common.Result;
import com.qtz.orig.order.service.OrigOrderService;
import com.qtz.orig.order.vo.HtOrder;

@RestController
@RequestMapping(value="/v1.0/htOrder/")
public class HtOrderController extends BaseController{

	protected static LogTool log = LogTool.getInstance(HtOrderController.class);
	
	@Autowired
	private OrigOrderService origOrderService;
	
	/**
	 *
	  * 【提交订单】
	  * @param sid
	  * @param orderRequestModel
	  * @param response
	  * @time:2015年9月6日 上午9:44:01
	  * @author 涂鑫
	  * @version
	 * @throws IOException
	 */
	@RequestMapping(value="subOrder",method=RequestMethod.POST, produces = "application/json")
	public void subOrder(@RequestHeader("token") String sid, @RequestBody OrderRequestModel orderRequestModel,BindingResult br, HttpServletResponse response,HttpServletRequest request) throws IOException{
		try {
			if(br.hasErrors()){
				log.debug(br.getFieldError().getDefaultMessage());
				return;
			}
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			
			Result<HtOrder> result = origOrderService.saveSubOrder(orderRequestModel.getGoodsMaps(), orderRequestModel.getReceivingInfoId(), user, orderRequestModel.getNote(),orderRequestModel.getCliType());
			
			if(result.isSuccess()){
				JSONObject reJsonObject=new JSONObject();
				reJsonObject.put(RespKey.dmId, result.getCarrierObject());
				RespHandler.respOK(reJsonObject,response);
			}else {
				RespHandler.respError(result.getCode(), result.getFailMessage(), response);
			}
			
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respException(e.getErrorType(),e.getErrorTitle(),response);
			
		}
	}
	
	/**
	 *
	  * 【计算订单价格】
	  * @param orderId					订单id
	  * @param couponId					优惠卷id(如果支持)
	  * @param response
	  * @time:2015年9月6日 上午11:41:35
	  * @author 涂鑫
	  * @version
	 * @throws IOException
	 */
	@RequestMapping(value="calculateHtOrderPrice/{htOrderId}",method=RequestMethod.GET)
	public void calculateHtOrderPrice(@RequestHeader("token") String sid,@PathVariable(value= "htOrderId") Long htOrderId, HttpServletResponse response) throws IOException{
		try {
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError( RespMsg.user_not_login, response);
				return;
			}
		
			Result<HtOrder> result = origOrderService.calculatePaymentPrice(htOrderId);
			
			if(result.isSuccess()){
				JSONObject reJsonObject=new JSONObject();
				reJsonObject.put(RespKey.dmId, htOrderId);
				reJsonObject.put(RespKey.goodsCount, result.getCarrierMap().get("goodsNum"));
				reJsonObject.put(RespKey.paymentPrice, result.getCarrierMap().get("payMoney"));//返回支付金额
				reJsonObject.put(RespKey.orderPrice, result.getCarrierMap().get("orderPrice"));//返回订单金额
				reJsonObject.put("rule", result.getCarrierMap().get("rule"));//规则
				RespHandler.respOK(reJsonObject,response);
			}
			
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.calculateOrderPrice_error(e.getErrorType()),response);
		}
	}
	
	/**
	 * 获得海淘订单列表
	 * 
	 * @param sid
	 * @param orderStatus
	 * @param pageIndex
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="getHtOrderList",method=RequestMethod.GET)
	public void  getHtOrderList(@RequestHeader("token") String sid, @RequestParam(value="orderStatus",required = false) Integer orderStatus,@RequestParam(value="pageIndex") Integer pageIndex,HttpServletResponse response) throws IOException{
		
		try {
			User user = getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
			}
			
			Result<HtOrder> result = origOrderService.getHtOrderList(user.getDmId(), orderStatus, pageIndex);
			
			if(result.isSuccess()){
				Pager<HtOrder, Long> pager = result.getPager();
				RespHandler.respOK(pager,pager.getList2(), response);
			}else{
				RespHandler.respError(result.getCode(),result.getFailMessage(), response);
			}
			
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getOrderList_error(e.getErrorType()), response);
		}
		
	}
	
	/**
	 *
	  * 【取消海淘订单】
	  * @param sid
	  * @param orderId				订单id
	  * @param response
	  * @time:2015年9月17日 下午3:05:18
	  * @author 涂鑫
	  * @version
	 * @throws IOException
	 */
	@RequestMapping(value="cancelHtOrder",method=RequestMethod.POST)
	public void cancelHtOrder(@RequestHeader("token") String sid,@RequestParam("htOrderId") Long htOrderId,HttpServletResponse response,HttpServletRequest request) throws IOException{
		try {
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			Result<HtOrder> result = origOrderService.updateCancelOrder(htOrderId, user.getDmId());
			
			if (result.isSuccess()) {
				RespHandler.respOK(response);
			} else {
				RespHandler.respError(result.getCode(), result.getFailMessage(), response);
			}
			
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.cancelOrder_error(e.getErrorType()), response);
		}
	}
	
	/**
	 * 获得订单详情 
	 * 
	 * @param sid
	 * @param orderId
	 * @param orderFrom
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value="getHtOrderInfo/{htOrderId}",method=RequestMethod.GET)
	public void getHtOrderInfo(@RequestHeader("token") String sid,@PathVariable("htOrderId") Long htOrderId,HttpServletResponse response,HttpServletRequest request) throws IOException{
		try {
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			
			Result<HtOrder> result = origOrderService.getHtOrderInfo(htOrderId);
			
			if(result.isSuccess()){
				RespHandler.respOK(result.getCarrierObject(), response);
			}
			
		} catch (ServiceException e) {
			log.error(e);
			if (e.getErrorType()==ExceptionCode.ORDERS_STATUS_CANNOT_BE_CANCELLED) {
				RespHandler.respError(RespMsg.orders_status_cannot_be_cancelled, response);
				return;
			}
			RespHandler.respError(RespMsg.cancelOrder_error(e.getErrorType()), response);
		}
	}
	
	
	/**
	 * 用户申请退款
	 *  
	 * @param sid 
	 * @param htOrderId  订单ID
	 * @param choice　  0 未发货退款  1 未收货退款  2 已收货退款退款
	 * @param reason   退款理由
	 * @param goodsImg  退款图片
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="applyRefund",method=RequestMethod.POST)
	public void applyRefund(@RequestHeader("token") String sid, @RequestParam("htOrderId")Long htOrderId,
			@RequestParam("choice")Integer choice, @RequestParam("reason")String reason,
			@RequestParam(value = "goodsImg" , required = false)String goodsImg,
			HttpServletResponse response,HttpServletRequest request){
		try {
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			
			Result<HtOrder> result = origOrderService.applyRefund(user.getDmId(), htOrderId, choice, reason, goodsImg);
			
			if(result.isSuccess()){
				RespHandler.respOK(response);
			}else {
				RespHandler.respError(result.getCode(), result.getFailMessage(), response);
			}
			
		} catch (Exception e) {
			log.error(e);
			RespHandler.respError(RespMsg.cancelOrder_error(-1), response);
		}
	} 
	
	/**
	 * 商家同意 退款后用户提交发货信息
	 * 
	 * @param sid
	 * @param express 快递公司名称
	 * @param expressCode 快递公司邮编号
	 * @param deliverTime 发货时间
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="customerDelivery",method=RequestMethod.POST)
	public void customerDelivery(@RequestHeader("token") String sid,@RequestParam("htOrderId")Long htOrderId, @RequestParam("express")String express, @RequestParam("expressCode")String expressCode, @RequestParam("deliverTime")Long deliverTime, HttpServletResponse response,HttpServletRequest request){
		try {
			
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			
			Result<HtOrder> result = origOrderService.customerDelivery(htOrderId, express, expressCode, deliverTime);
			
			if(result.isSuccess()){
				RespHandler.respOK(response);
			}else {
				RespHandler.respError(result.getCode(), result.getFailMessage(), response);
			}
			
		} catch (Exception e) {
			log.error(e);
			RespHandler.respError(RespMsg.cancelOrder_error(-1), response);
		}
	}
	
	/**
	 * 根据申请ID获得申请信息
	 * 
	 * @param sid
	 * @param orderApplyId
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="getOrderApplyById",method=RequestMethod.GET)
	public void getOrderApplyById(@RequestHeader("token") String sid,@RequestParam("orderApplyId")Long orderApplyId,HttpServletResponse response,HttpServletRequest request){
		try {
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			
			Result<HtOrder> result = origOrderService.getOrderApplyById(orderApplyId);
			
			if(result.isSuccess()){
				RespHandler.respOK(result.getCarrierObject(), response);
			}else {
				RespHandler.respError(result.getCode(), result.getFailMessage(), response);
			}
			
		} catch (Exception e) {
			log.error(e);
			RespHandler.respError(RespMsg.cancelOrder_error(-1), response);
		}
	}
	
	
	/**
	 * 
	 * 用户确认收货
	 * 
	 * @param sid
	 * @param htOrderId
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="userConfirmReceipt",method=RequestMethod.POST)
	public void userConfirmReceipt(@RequestHeader("token") String sid,@RequestParam("htOrderId")Long htOrderId,HttpServletResponse response,HttpServletRequest request){
		
		try {
			
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			
			Result<HtOrder> result = origOrderService.userConfirmReceipt(htOrderId);
			
			if(result.isSuccess()){
				RespHandler.respOK(response);
			}else {
				RespHandler.respError(result.getCode(), result.getFailMessage(), response);
			}
			
		} catch (Exception e) {
			log.error(e);
			RespHandler.respError(RespMsg.cancelOrder_error(-1), response);
		}
	}
	
	
	
	/**
	* 【获取订单日志信息】
	* @param sid
	* @param htOrderId
	* 				订单id
	* @param response
	* @param request  
	*/
	@RequestMapping(value="getOrderstatusList/{htOrderId}",method=RequestMethod.GET)
	public void getOrderstatusList(@RequestHeader("token") String sid,@PathVariable("htOrderId")Long htOrderId,HttpServletResponse response,HttpServletRequest request){
		
		try {
			
			User user=getUser(sid);
			if(user==null){
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			
			Result<HtOrder> result = origOrderService.getOrderstatusList(htOrderId);
			
			if(result.isSuccess()){
				RespHandler.respOK(result.getCarrierObject(), response);
			}else {
				RespHandler.respError(result.getCode(), result.getFailMessage(), response);
			}
			
		} catch (Exception e) {
			log.error(e);
			RespHandler.respError(RespMsg.cancelOrder_error(-1), response);
		}
	}
	
	
	/**
	 * app付款下单时候请求显示该订单总价格
	 * appDispalycalculatePaymentPrice:(). <br/> 
	 * @author yxd 
	 * @param sid 玩家的token
	 * @param dmId  该订单的批次编号
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="dispalycalculatePaymentPrice")
	public void dispalycalculatePaymentPrice(@RequestHeader("token") String sid,
			@RequestParam("dmId") Long dmId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			User user= getUser(sid);
			if(user != null){
				Result<HtOrder> result=origOrderService.calculatePaymentPrice(dmId);
				if(result.isSuccess()){
					RespHandler.respOK(result.getCarrierMap(),response);	
				}else{
					RespHandler.respError(result.getCode(), result.getFailMessage(), response);
				}
			}else{
				RespHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "请您先登录！", response);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(e.getErrorType(), e.getErrorMessage(), response);
		}catch (IOException e) {
			RespHandler.respServerError(response);
		}
	}
	
	/**
	* 【保存订单实付金额】
	* @param sid
	* @param orderId
	* @param constantKey
	* @param constantName
	* @param deductionPrice
	* @param discountPercent
	* @param request
	* @param response  
	*/
	@RequestMapping("replenishmentOrder")
	public void replenishmentOrder(@RequestHeader("token") String sid,Long orderId,
			String constantKey, String constantName, Double ductionPrice, Double discountPercentByNow,
			HttpServletRequest request, HttpServletResponse response)
	{
		try {
			User user= getUser(sid);
			if(user != null){
//				Integer discountercent = null;
//				if (null != discountPercentByNow) {
//					discountercent = discountPercentByNow.intValue();
//				}
				Result<HtOrder> result=origOrderService.replenishmentOrder(orderId, constantKey);
				if(result.isSuccess()){
					RespHandler.respOK(result.getCarrierMap(),response);	
				}else{
					RespHandler.respError(result.getCode(), result.getFailMessage(), response);
				}
			}else{
				RespHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "请您先登录！", response);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(e.getErrorType(), e.getErrorMessage(), response);
		}catch (IOException e) {
			RespHandler.respServerError(response);
		}
	}
	/**
	* 【根据订单ID查询订单商品】
	* @param sid
	* @param orderId
	* 				订单ID
	* @param request
	* @param response  
	*/
	@RequestMapping("queryOrderGoodsByOrderId")
	public void queryOrderGoodsByOrderId(@RequestHeader("token") String sid,@RequestParam Long orderId,
			HttpServletRequest request, HttpServletResponse response)
	{
		try {
			User user= getUser(sid);
			if(user != null){
				Result<HtOrder> result=origOrderService.queryOrderGoodsByOrderId(orderId);
				if(result.isSuccess()){
					RespHandler.respOK(result.getCarrierObject(),response);	
				}else{
					RespHandler.respError(result.getCode(), result.getFailMessage(), response);
				}
			}else{
				RespHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "请您先登录！", response);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(e.getErrorType(), e.getErrorMessage(), response);
		}catch (IOException e) {
			RespHandler.respServerError(response);
		}
	}
}
