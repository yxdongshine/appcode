//package com.qtz.ht.manage.controller.callback;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.TreeMap;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mall.core.common.Arith;
//import com.mall.core.common.pay.weChatPay.common.XMLParser;
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ExceptionCode;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.log.LogTool;
//import com.qtz.dm.pay.service.PaymentService;
//import com.qtz.dm.pay.service.WeChatPayService;
//import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
//import com.qtz.ht.controller.BaseController;
///**
// * 
// * <p>Title:WeChatPayController</p>
// * <p>Description:(微信支付)</p>
// * <p>Copyright: Copyright (c) 2015</p>
// * <p>Company: 深圳市擎天柱信息科技有限公司</p>
// * @author 涂鑫 - changbo.li
// * @version v1.0 2015年10月10日
// */
//@RestController
//@RequestMapping(value="v1.0/weChatPay/")
//public class WeChatPayController extends BaseController{
//	protected static LogTool log = LogTool.getInstance(WeChatPayController.class);
//	
//	@Autowired
//	private WeChatPayService weChatPayService;
//	
//	@Autowired
//	private PaymentService paymentService;
//	/**
//	 * 
//	  * 【推送微信订单】
//	  * @param sid
//	  * @param orderId				订单id
//	  * @param response
//	  * @throws IOException  
//	  * @time:2015年11月6日 上午9:37:29
//	  * @author 涂鑫
//	  * @version
//	 */
//	@RequestMapping(value="pushOrderInfo",method=RequestMethod.POST)
//	public void pushOrderInfo(@RequestHeader("token") String sid, @RequestParam(required=true) Long orderId,HttpServletResponse response) throws IOException{
//		try {
//			User user = getUser(sid);
//			if(user==null){
//				RespHandler.respError(401, RespMsg.user_not_login, response);
//				return;
//			}
//			String pushOrderInfo = weChatPayService.pushOrderInfo(orderId);
//			JSONObject parseObject = JSONObject.parseObject(pushOrderInfo);
//			RespHandler.respOK(parseObject, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			switch (e.getErrorType()) {
//			case ExceptionCode.ORDER_PAY_ERROR:
//				RespHandler.respError(RespMsg.order_pay_error, response);
//				break;
//			default:
//				RespHandler.respError(RespMsg.push_wechatpay_order_into_error(e.getErrorType()),response);
//				break;
//			}
//			
//		}
//	}
//	
//	
//	/**
//	 * 
//	  * 【接受微信支付异步通知】
//	  * @param return_code			返回状态码
//	  * @param return_msg  			返回信息
//	  * @time:2015年10月10日 下午1:09:45
//	  * @author 涂鑫
//	  * @version
//	 * @throws IOException 
//	 */
//	@RequestMapping(value="weChatPayNotifyResult",method=RequestMethod.POST)
//	public void weChatPayNotifyResult(@RequestBody  String requestBody ,HttpServletResponse response,HttpServletRequest request) throws IOException{
//		try{
//			log.debug(requestBody);
//			Map<String, Object> mapFromXML = XMLParser.getMapFromXML(requestBody);
//				Map<String, Object> returnMap=new TreeMap<String, Object>();
//				log.debug("收到微信异步通知正在验证支付...");
//				if(StringUtils.isEmpty(mapFromXML.get("return_code"))){
//					log.error("校验错误.异步通知服务 return_code为空.");
//					returnMap.put("return_code", "FAIL");
//					RespHandler.respXml(response, "FAIL");
//					return;
//				}
//				log.debug("return_code"+mapFromXML.get("return_code") +"return_msg"+mapFromXML.get("return_msg"));
//				if(mapFromXML.get("return_code").equals("SUCCESS")){
//					//签名验证
//					if(!weChatPayService.checkIsSignValidFromResponseStringByGoodsOrder(requestBody)){
//						log.debug("签名认证失败.");
//						return;
//					}
//					//成功处理逻辑  微信还没有写
//					log.debug("成功.");
//					double parseDouble = Double.parseDouble(mapFromXML.get("cash_fee").toString());
//					parseDouble=Arith.div(parseDouble, 100);
//					paymentService.updateDealPayResult(Long.valueOf(mapFromXML.get("out_trade_no").toString()),
//							mapFromXML.get("transaction_id").toString(),
//							parseDouble,
//							PaymentMethodEnum.WEIXIN.getValue());
//					returnMap.put("return_code", "SUCCESS");
//					RespHandler.respXml(response, returnMap);
//				}
//			}catch(Exception e){
//				log.error("处理异常"+e.getMessage(),e);
//				Map<String, Object> returnMap=new TreeMap<String, Object>();
//				returnMap.put("return_code", "FAIL");
//				RespHandler.respXml(response, returnMap);
//			}
//			
//			
//	}
//	
//	
//	/**
//     * 
//      * 【接受微信支付异步通知】
//      * @param return_code          返回状态码
//      * @param return_msg           返回信息
//      * @time:2015年10月10日 下午1:09:45
//      * @author 涂鑫
//      * @version
//     * @throws IOException 
//     */
//    @RequestMapping(value="weChatPayNotifyResultByPpService",method=RequestMethod.POST)
//    public void weChatPayNotifyResultByPpService(@RequestBody  String requestBody ,HttpServletResponse response,HttpServletRequest request) throws IOException{
//        try{
//            log.debug(requestBody);
//            Map<String, Object> mapFromXML = XMLParser.getMapFromXML(requestBody);
//                Map<String, Object> returnMap=new TreeMap<String, Object>();
//                log.debug("收到微信异步通知正在验证支付...");
//                if(StringUtils.isEmpty(mapFromXML.get("return_code"))){
//                    log.error("校验错误.异步通知服务 return_code为空.");
//                    returnMap.put("return_code", "FAIL");
//                    RespHandler.respXml(response, "FAIL");
//                    return;
//                }
//                log.debug("return_code"+mapFromXML.get("return_code") +"return_msg"+mapFromXML.get("return_msg"));
//                if(mapFromXML.get("return_code").equals("SUCCESS")){
//                    //签名验证
//                    if(!weChatPayService.checkIsSignValidFromResponseStringByPpOrder(requestBody)){
//                        log.debug("签名认证失败.");
//                        return;
//                    }
//                    //成功处理逻辑  微信还没有写
//                    log.debug("成功.");
//                    double parseDouble = Double.parseDouble(mapFromXML.get("cash_fee").toString());
//                    parseDouble=Arith.div(parseDouble, 100);
//                    paymentService.updateDealPayResultByPpService(Long.valueOf(mapFromXML.get("out_trade_no").toString()),
//                    		mapFromXML.get("transaction_id").toString(),
//                    		parseDouble,
//                    		PaymentMethodEnum.WEIXIN.getValue());
//                    returnMap.put("return_code", "SUCCESS");
//                    RespHandler.respXml(response, returnMap);
//                }
//            }catch(Exception e){
//                log.error("处理异常"+e.getMessage(),e);
//                Map<String, Object> returnMap=new TreeMap<String, Object>();
//                returnMap.put("return_code", "FAIL");
//                RespHandler.respXml(response, returnMap);
//            }
//            
//            
//    }
//
//}
