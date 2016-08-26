//package com.qtz.ht.manage.controller.callback;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mall.core.common.Arith;
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ServiceException;
//import com.qtz.dm.order.service.OrderService;
//import com.qtz.dm.pay.service.PaymentService;
//import com.qtz.dm.pay.service.UnionpayService;
//import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
//import com.qtz.ht.controller.BaseController;
//import com.unionpay.acp.sdk.SDKConstants;
//
///**
// * 
// * <p>Title:Unionpay</p>
// * <p>Description:(银联支付)</p>
// * <p>Copyright: Copyright (c) 2015</p>
// * <p>Company: 深圳市擎天柱信息科技有限公司</p>
// * @author 涂鑫 - changbo.li
// * @version v1.0 2015年10月12日
// */
//@RestController
//@RequestMapping(value="/v1.0/unionpay/")
//public class UnionpayController extends BaseController{
//	@Autowired
//	private OrderService orderService;
//	
//	@Autowired
//	private PaymentService paymentService;
//	
//	@Autowired
//	private UnionpayService unionpayService;
//	
//	//TODO 线上产品还没有弄 签约申请没有下来
//	/**
//	 * 
//	  * 【推送银联订单信息】
//	  * @param sid						session
//	  * @param orderId					订单id
//	  * @param response  
//	  * @time:2015年10月12日 下午4:21:07
//	  * @author 涂鑫
//	  * @version
//	 */
//	@RequestMapping(value="pushOrderInfo",method=RequestMethod.POST)
//	public void pushOrderInfo(@RequestHeader("token") String sid,@RequestParam(required=true) Long orderId,HttpServletResponse response){
//		try {
//			User user = getUser(sid);
//			if(user==null){
//				RespHandler.respError(401, RespMsg.user_not_login, response);
//				return;
//			}
//			Order order = orderService.findVo(orderId, null);
//			if(order==null){
//				RespHandler.respError(RespMsg.order_null, response);
//				return;
//			}
//			if(order.getOrderStatus().intValue()==OrderStatus.unpay.getId()){
//				String pushOrderInfo = unionpayService.pushOrderInfo(order.getDmId(), order.getPaymentPrice());
//				log.debug("银联返回数据"+pushOrderInfo);
//				JSONObject parseObject = JSONObject.parseObject(pushOrderInfo);
//				JSONObject data=parseObject;
//				if(!data.get("respCode").equals("00")){
//					RespHandler.respError(RespMsg.push_unionpay_order_into_error(Integer.valueOf(data.get("respCode")+"")), response);
//					return;
//				}
//				Object object = data.get("tn");
//				JSONObject returnData=new JSONObject();
//				returnData.put("tn", object);
//				RespHandler.respOK(returnData, response);
//				return;
//			}else{
//				RespHandler.respError(RespMsg.order_pay_error, response);
//				return;
//			}
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.push_unionpay_order_into_error(e.getErrorType()),response);
//		}catch (Exception e) {
//			log.error(e);
//			RespHandler.respServerError(response);
//		}
//	}
//	/**
//	 * 
//	  * 【银联结果通知】
//	  * @param request
//	  * @param response
//	  * @throws UnsupportedEncodingException
//	  * @throws NumberFormatException
//	  * @throws ServiceException  
//	  * @time:2015年10月13日 上午9:53:17
//	  * @author 涂鑫
//	  * @version
//	 */
//	@RequestMapping("unionpayNotifyResult")
//	public void unionpayNotifyResult(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, NumberFormatException, ServiceException{
//		log.debug("BackRcvResponse接收后台通知开始");
//	request.setCharacterEncoding("ISO-8859-1");
//	String encoding = request.getParameter(SDKConstants.param_encoding);
//	// 获取请求参数中所有的信息
//	Map<String, String> reqParam = getAllRequestParam(request);
//	// 打印请求报文
//	log.debug(reqParam);
//	Map<String, String> valideData = null;
//	if (null != reqParam && !reqParam.isEmpty()) {
//		Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
//		valideData = new HashMap<String, String>(reqParam.size());
//		while (it.hasNext()) {
//			Entry<String, String> e = it.next();
//			String key = (String) e.getKey();
//			String value = (String) e.getValue();
//			value = new String(value.getBytes("ISO-8859-1"), encoding);
//			valideData.put(key, value);
//		}
//	}
//	// 验证签名
//	if (!unionpayService.validate(valideData, encoding)) {
//		log.debug("验证签名结果[失败].");
//	} else {
//		System.out.println(valideData.get("orderId")); //其他字段也可用类似方式获取
//		log.debug("验证签名结果[成功].");
//		double parseDouble = Double.parseDouble(valideData.get("txnAmt"));
//		paymentService.updateDealPayResult(Long.parseLong(valideData.get("orderId")), valideData.get("queryId"), Arith.div(parseDouble, 100),PaymentMethodEnum.UNIONPAY.getValue());
//		
//	}
//	}
//	
//	/**
//	 * 
//	  * 【获取请求参数中所有的信息】
//	  * @param request
//	  * @return  
//	  * @time:2015年10月13日 上午9:33:42
//	  * @author 涂鑫
//	  * @version
//	 */
//	private static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
//		Map<String, String> res = new HashMap<String, String>();
//		Enumeration<?> temp = request.getParameterNames();
//		if (null != temp) {
//			while (temp.hasMoreElements()) {
//				String en = (String) temp.nextElement();
//				String value = request.getParameter(en);
//				res.put(en, value);
//				//在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
//				//System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
//				if (null == res.get(en) || "".equals(res.get(en))) {
//					res.remove(en);
//				}
//			}
//		}
//		return res;
//	}
//}
