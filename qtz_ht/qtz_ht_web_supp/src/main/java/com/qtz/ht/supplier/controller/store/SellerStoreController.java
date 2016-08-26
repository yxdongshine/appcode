//package com.qtz.ht.controller.store;
//
//import java.io.IOException;
//import java.io.Serializable;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ExceptionCode;
//import com.mall.core.exception.ServiceException;
//import com.qtz.ht.controller.BaseController;
//import com.qtz.ht.model.request.goods.SellerStoreRequestModel;
//import com.qtz.ht.store.service.SellerStoreService;
//import com.qtz.ht.store.vo.SellerStore;
//import com.qtz.ht.store.vo.SellerStore.IsSend;
//import com.qtz.ht.user.vo.User;
//import com.qtz.ht.userwallet.enums.UserType;
//import com.wordnik.swagger.annotations.ApiOperation;
//
///**
// * 
// * <p>
// * Title:SellerStoreController
// * </p>
// * <p>
// * Description:(商家店铺)
// * </p>
// * <p>
// * Copyright: Copyright (c) 2015 
// * </p>
// * <p>
// * Company: 深圳市擎天柱信息科技有限公司
// * </p>
// * 
// * @author 涂鑫 - changbo.li
// * @version v1.0 2015年8月26日
// */
//@RestController
//@RequestMapping(value = "/v1.0/store/")
//public class SellerStoreController extends BaseController implements Serializable {
//  /** () */
//  private static final long serialVersionUID = -4401961371563845425L;
//  @Autowired
//  private SellerStoreService sellerStoreService;
//
//  /**
//   * 
//   * 【获取店铺信息】
//   * 
//   * @param userId 店铺用户 商家
//   * @param response
//   * @time:2015年9月9日 下午4:29:29
//   * @author 涂鑫
//   * @version
// * @throws IOException 
//   */
//  @ApiOperation("获取店铺信息")
//  @RequestMapping(value = "getStoreInfo/{userId}", method = RequestMethod.GET)
//  public void getStoreInfo(@PathVariable("userId") Long userId, HttpServletResponse response) throws IOException {
//    try {
//      SellerStore sellerStore = sellerStoreService.getSellerStore(userId);
//      if (sellerStore != null) {
//        sellerStore.setUserId(null);
//      }
//
//      RespHandler.respOK(sellerStore, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      switch (e.getErrorType()) {
//	case ExceptionCode.SELLER__STORE_ARTIFICIAL_CLOSED:
//		RespHandler.respError(RespMsg.seller__store_artificial_closed, response);
//		break;
//	case ExceptionCode.SELLER__STORE_CLOSE:
//		RespHandler.respError(RespMsg.seller__store_close, response);
//		break;
//	case ExceptionCode.SELLER__STORE_DATA_EXCEPTION:
//		RespHandler.respError(RespMsg.seller__store_data_exception, response);
//		break;
//	default:
//		  RespHandler.respError(RespMsg.getStoreInfo_error(e.getErrorType()),response);
//	}
//    } 
//  }
//  
//
//
//	/**
//	* 【获取店铺信息】
//	* @param sid
//	* @param response
//	* @throws IOException  
//	*/
//	@ApiOperation("获取店铺信息")
//	@RequestMapping(value = "getMyStoreInfo", method = RequestMethod.GET)
//	public void getMyStoreInfo(@RequestHeader("token") String sid, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//		    if (user == null) {
//		      RespHandler.respError( RespMsg.user_not_login, response);
//		      return;
//		    }
//		  JSONObject json = sellerStoreService.getMyStoreInfo(user.getDmId());
//		
//		  RespHandler.respOK(json, response);
//		} catch (ServiceException e) {
//		  log.error(e);
//		  switch (e.getErrorType()) {
//		case ExceptionCode.SELLER__STORE_ARTIFICIAL_CLOSED:
//			RespHandler.respError(RespMsg.seller__store_artificial_closed, response);
//			break;
//		case ExceptionCode.SELLER__STORE_CLOSE:
//			RespHandler.respError(RespMsg.seller__store_close, response);
//			break;
//		case ExceptionCode.SELLER__STORE_DATA_EXCEPTION:
//			RespHandler.respError(RespMsg.seller__store_data_exception, response);
//			break;
//		default:
//			  RespHandler.respError(RespMsg.getStoreInfo_error(e.getErrorType()),response);
//		}
//		} 
//	}
//
//
//  @RequestMapping(value = "myStore", method = RequestMethod.GET)
//  public void myStore(@RequestHeader("token") String sid, HttpServletResponse response) throws IOException {
//    try {
//      User user = getUser(sid);
//      if (user == null) {
//        RespHandler.respError( RespMsg.user_not_login, response);
//        return;
//      }
//      try {
//        JSONObject myStore = sellerStoreService.getMyStore(user.getDmId());
//        RespHandler.respOK(myStore, response);
//      } catch (ServiceException e) {
//        switch (e.getErrorType()) {
//          case ExceptionCode.USER_NO_AUTHEN:
//            RespHandler.respMapOK(RespMsg.account_no_auth, response);
//            break;
//          case ExceptionCode.SELLER_NO_OPEN_STORE:
//            RespHandler.respMapOK(RespMsg.seller_no_store, response);
//            break;
//          default:
//            throw new ServiceException(e);
//        }
//      }
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(RespMsg.myStore_error(e.getErrorType()), response);
//    }
//  }
//
//  /**
//   * 
//   * 【更新店铺】
//   * 
//   * @param sid
//   * @param sellerStoreRequestModel
//   * @param br
//   * @param response
//   * @time:2015年9月14日 上午10:09:34
//   * @author 涂鑫
//   * @version
// * @throws IOException 
//   */
//  @ApiOperation("更新店铺")
//  @RequestMapping(value = "updateStore", method = RequestMethod.POST)
//  public void updateStore(@RequestHeader("token") String sid,
//      @ModelAttribute @Valid SellerStoreRequestModel sellerStoreRequestModel, BindingResult br,
//      HttpServletResponse response) throws IOException {
//    if (br.hasErrors()) {
//      log.debug(br.getFieldError().getDefaultMessage());
//      return;
//    }
//    if (!sellerStoreRequestModel.getIsSend()) {
//      sellerStoreRequestModel.setSendFee(null);
//      sellerStoreRequestModel.setSendOutUpFee(null);
//    } else {
//      if (sellerStoreRequestModel.getSendOutUpFee() == null
//          || sellerStoreRequestModel.getSendFee() == null) {
//        RespHandler.respError(RespMsg.sendOutUpFee_or_sendFee_param_null, response);
//        return;
//      }
//    }
//    try {
//      User user = getUser(sid);
//      if (user == null) {
//        RespHandler.respError(RespMsg.user_not_login, response);
//        return;
//      }
//      if (user.getUserType().intValue() == UserType.PERSON.value()) {
//        RespHandler.respError(RespMsg.userType_Dont_match, response);
//        return;
//      }
//      SellerStore sellerStore = new SellerStore();
//      sellerStore.setDmId(sellerStoreRequestModel.getDmId());
//      sellerStore.setIsSend(sellerStoreRequestModel.getIsSend() ? IsSend.OK.value() : IsSend.NO
//          .value());
//      sellerStore.setMealFee(sellerStoreRequestModel.getMealFee());
//      sellerStore.setSendFee(sellerStoreRequestModel.getSendFee());
//      sellerStore.setSendOutUpFee(sellerStoreRequestModel.getSendOutUpFee());
//      sellerStore.setStoreType(sellerStoreRequestModel.getStoreType());
//      JSONObject updateStore = sellerStoreService.updateStore(sellerStore, user.getDmId());
//      RespHandler.respOK(updateStore, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      switch (e.getErrorType()) {
//        case ExceptionCode.PRICE_ERROR:
//          RespHandler.respError(RespMsg.price_error, response);
//          break;
//        case ExceptionCode.SELLER_NO_OPEN_STORE:
//          RespHandler.respError(RespMsg.seller_no_store, response);
//          break;
//        case ExceptionCode.SELLER__STORE_DATA_EXCEPTION:
//    		RespHandler.respError(RespMsg.seller__store_data_exception, response);
//    		break;
//        default:
//          RespHandler.respError(RespMsg.updateStore_error(e.getErrorType()),response);
//          break;
//      }
//    }
//
//  }
//
////  /**
////   * 
////   * 【开通店铺】
////   * 
////   * @param isSend
////   * @param sendFee
////   * @param sendOutUpFee
////   * @param response
////   * @time:2015年8月26日 下午2:48:05
////   * @author 涂鑫
////   * @version
//// * @throws IOException 
////   */
////  @ApiOperation("开通店铺")
////  @RequestMapping(value = "openStore", method = RequestMethod.POST)
////  public void openStore(@RequestHeader("token") String sid,
////      @ModelAttribute @Valid SellerStoreRequestModel sellerStoreRequestModel, BindingResult br,
////      HttpServletResponse response) throws IOException {
////    if (br.hasErrors()) {
////      log.debug(br.getFieldError().getDefaultMessage());
////      return;
////    }
////    if (!sellerStoreRequestModel.getIsSend()) {
////      sellerStoreRequestModel.setSendFee(null);
////      sellerStoreRequestModel.setSendOutUpFee(null);
////    } else {
////      if (sellerStoreRequestModel.getSendOutUpFee() == null
////          || sellerStoreRequestModel.getSendFee() == null) {
////    	  RespHandler.respError(RespMsg.sendOutUpFee_or_sendFee_param_null, response);
////        return;
////      }
////    }
////    try {
////      User user = getUser(sid);
////      if (user == null) {
////        RespHandler.respError( RespMsg.user_not_login, response);
////        return;
////      }
////      if (user.getUserType().intValue() == UserType.person.value()) {
////        RespHandler.respError( RespMsg.userType_Dont_match, response);
////        return;
////      }
////      SellerStore sellerStore =
////          new SellerStore(user.getDmId().longValue(), sellerStoreRequestModel.getIsSend() ? 0 : 1,
////              sellerStoreRequestModel.getSendFee(), sellerStoreRequestModel.getSendOutUpFee(),
////              sellerStoreRequestModel.getMealFee(), sellerStoreRequestModel.getStoreType());
////      JSONObject json =
////          sellerStoreService.saveOpenStore(sellerStore, new CallbackService<SellerStore>() {
////
////            /** () */
////            private static final long serialVersionUID = -6417651603397116557L;
////
////            @Override
////            public JSONObject returnData(SellerStore vo) {
////              JSONObject json = JSON.parseObject(JSON.toJSONString(vo));
////              json.remove("userId");
////              return json;
////            }
////
////            @Override
////            public JSONObject returnData(SellerStore vo, Object... objs) {
////              return null;
////            }
////          });
////      RespHandler.respOK(json, response);
////    } catch (ServiceException e) {
////      log.error(e);
////      if (e.getErrorType() == ExceptionCode.PRICE_ERROR) {
////        RespHandler.respError( RespMsg.price_error, response);
////      } else if (e.getErrorType() == ExceptionCode.SELLER_STORE_EXIST_VALID) {
////        RespHandler.respError( RespMsg.seller_store_exist_valid, response);
////      } else {
////        RespHandler.respError(RespMsg.openStore_error(e.getErrorType()),response);
////      }
////    } 
////  }
//}
