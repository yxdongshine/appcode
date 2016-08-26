//package com.qtz.ht.manage.controller.callback;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mall.core.log.LogTool;
//import com.qtz.dm.pay.service.AlipayService;
//import com.qtz.dm.pay.service.PaymentService;
//import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
//
//
///**
// * 
// * <p>Title:AlliPayController</p>
// * <p>Description:(支付宝)</p>
// * <p>Copyright: Copyright (c) 2015</p>
// * <p>Company: 深圳市擎天柱信息科技有限公司</p>
// * @author 涂鑫 - changbo.li
// * @version v1.0 2015年9月24日
// */
//@RestController
//@RequestMapping(value="v1.0/aliPay/")
//public class AlliPayController {
//	
//	protected static LogTool log = LogTool.getInstance(AlliPayController.class);
//	
//	@Autowired
//	private PaymentService paymentService;
//	
//	@Autowired
//	private AlipayService  alipayService;
//	/**
//	 * 
//	  * 【接受支付宝回调】
//	  * @param out_trade_no				商户订单号
//	  * @param trade_no					支付宝交易号
//	  * @param trade_status				支付宝交易状态
//	  * @param price  					付款金额
//	  * 
//	  * @time:2015年9月24日 上午10:22:26
//	  * @author 涂鑫
//	  * @version
//	 * @throws IOException 
//	 */
//	@RequestMapping(value="aliPayNotifyResult",method=RequestMethod.POST)
//	public void aliPayNotifyResult( Long out_trade_no,String trade_no,String trade_status,Double price,HttpServletResponse response ,HttpServletRequest request) throws IOException{
//		try {
//		log.debug("收到支付宝异步通知正在验证支付...");
//		Map<String,String> params = new HashMap<String,String>();
//		Map requestParams = request.getParameterMap();
//		for (Iterator iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
//			String name = (String) iterator.next();
//			String[] values = (String[]) requestParams.get(name);//获取名字集合 值
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i]
//						: valueStr + values[i] + ",";
//			}
//			log.debug("name="+name+"       values="+valueStr);
//			params.put(name, valueStr);
//		}
//		if(alipayService.verify(params)){//支付宝url验证
//			log.debug("支付验证成功正在验证状态订单号="+out_trade_no+"...交易状态为="+trade_status);
//			if(trade_status.equals("TRADE_FINISHED")){
//				paymentService.updateDealPayResult(out_trade_no, trade_no, price,PaymentMethodEnum.ALIPAY.getValue());
//			} else if (trade_status.equals("TRADE_SUCCESS")){
//				paymentService.updateDealPayResult(out_trade_no, trade_no, price,PaymentMethodEnum.ALIPAY.getValue());
//			log.debug("高级到账....trane_success");;
//			}
//			response.getWriter().print("success");
//		}else{
//			log.error("验证支付宝失败错误的支付...");
//			response.getWriter().print("fail");
//		}
//		} catch (Exception e) {
//			log.error("商户处理错误"+e.getMessage(),e);
//			response.getWriter().print("fail");
//		}
//	}
//	
//	
//	
//	
//	/**
//     * 
//      * 【接受支付宝回调】
//      * @param out_trade_no             商户订单号
//      * @param trade_no                 支付宝交易号
//      * @param trade_status             支付宝交易状态
//      * @param price                    付款金额
//      * 
//      * @time:2015年9月24日 上午10:22:26
//      * @author 涂鑫
//      * @version
//     * @throws IOException 
//     */
//    @RequestMapping(value="aliPayNotifyResultByPpService",method=RequestMethod.POST)
//    public void aliPayNotifyResultByPpService( Long out_trade_no,String trade_no,String trade_status,Double price,HttpServletResponse response ,HttpServletRequest request) throws IOException{
//        try {
////        paymentService.updateDealPayResultByPpService(out_trade_no, trade_no, price,PayTypeEnum.ALIPAY.value());	
//        log.debug("收到支付宝异步通知正在验证支付...");
//        Map<String,String> params = new HashMap<String,String>();
//        Map requestParams = request.getParameterMap();
//        for (Iterator iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
//            String name = (String) iterator.next();
//            String[] values = (String[]) requestParams.get(name);//获取名字集合 值
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                        : valueStr + values[i] + ",";
//            }
//            log.debug("name="+name+"       values="+valueStr);
//            params.put(name, valueStr);
//        }
//        if(alipayService.verify(params)){//支付宝url验证
//            log.debug("支付验证成功正在验证状态订单号="+out_trade_no+"...交易状态为="+trade_status);
//            if(trade_status.equals("TRADE_FINISHED")){
//                paymentService.updateDealPayResultByPpService(out_trade_no, trade_no, price,PaymentMethodEnum.ALIPAY.getValue());
//            } else if (trade_status.equals("TRADE_SUCCESS")){
//                paymentService.updateDealPayResultByPpService(out_trade_no, trade_no, price,PaymentMethodEnum.ALIPAY.getValue());
//            log.debug("高级到账....trane_success");;
//            }
//            response.getWriter().print("success");
//        }else{
//            log.error("验证支付宝失败错误的支付...");
//            response.getWriter().print("fail");
//        }
//        } catch (Exception e) {
//            log.error("商户处理错误"+e.getMessage(),e);
//            response.getWriter().print("fail");
//        }
//    }
//}
