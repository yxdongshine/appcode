//package com.qtz.ht.controller.store;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mall.core.common.StringUtil;
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
//@RestController
//@RequestMapping(value="v1.1/store/")
//public class SellerStoreControllerV1_1 extends BaseController{
//  @Autowired
//  private SellerStoreService sellerStoreService;
//  /**
//   * 
//    * 【更新店铺】
//    * @param sid
//    * @param sellerStoreRequestModel
//    * @param br
//    * @param response  
//    * @time:2015年9月14日 上午10:09:34
//    * @author 涂鑫
//    * @version
// * @throws IOException 
//   */
//  @ApiOperation("更新店铺")
//  @RequestMapping(value="updateStore",method=RequestMethod.POST)
//  public void  updateStore(@RequestHeader("token") String sid,Integer isSend,Integer isStop,@ModelAttribute @Valid SellerStoreRequestModel sellerStoreRequestModel ,BindingResult br,HttpServletResponse response) throws IOException{
//    log.info("正在更新店铺"); 
//    if(br.hasErrors()){
//          log.debug(br.getFieldError().getDefaultMessage());
//          return;
//      }
//      if(isSend!=0){
//          sellerStoreRequestModel.setSendFee(null);
//          sellerStoreRequestModel.setSendOutUpFee(null);
//      }else{
////          if(sellerStoreRequestModel.getSendOutUpFee()==null || sellerStoreRequestModel.getSendFee()==null){
////              RespHandler.respError( RespMsg.request_parameter_error, response);
////              return;
////          }
//      }
//      try {
//          User user = getUser(sid);
//          if(user==null){
//              RespHandler.respError( RespMsg.user_not_login, response);
//              return;
//          }
//          if(user.getUserType().intValue()==UserType.PERSON.value()){
//              RespHandler.respError( RespMsg.userType_Dont_match, response);
//              return;
//          }
//          SellerStore sellerStore=new SellerStore();
//          sellerStore.setDmId(sellerStoreRequestModel.getDmId());
//          sellerStore.setFreeMealFee(sellerStoreRequestModel.getFreeMealFee());
//          sellerStore.setFreeSendFee(sellerStoreRequestModel.getFreeSendFee());
//          sellerStore.setIsSend(isSend==0?IsSend.OK.value():IsSend.NO.value());
//          sellerStore.setIsStop(isStop==0?IsSend.OK.value():IsSend.NO.value());
//          sellerStore.setMealFee(sellerStoreRequestModel.getMealFee());
//          sellerStore.setSendFee(sellerStoreRequestModel.getSendFee());
//          sellerStore.setSendOutUpFee(sellerStoreRequestModel.getSendOutUpFee());
//          if(null != sellerStoreRequestModel.getcBEndTime() && !StringUtil.isEmpty(sellerStoreRequestModel.getcBEndTime())){
//            sellerStore.setcBEndTime(Long.valueOf(sellerStoreRequestModel.getcBEndTime()));
//          }
//          if(null != sellerStoreRequestModel.getcBStartTime() && !StringUtil.isEmpty(sellerStoreRequestModel.getcBStartTime())){
//            sellerStore.setcBStartTime(Long.valueOf(sellerStoreRequestModel.getcBStartTime()));
//          }
//          if (sellerStore.getEndTime()!=null && sellerStore.getStartTime()!=null) {
//        	  if (sellerStore.getcBEndTime().longValue()<sellerStore.getcBStartTime().longValue()) {
//                  RespHandler.respError(RespMsg.startTime_gt_endTime,response);
//                  return;
//                }
//		}
//          
//          //sellerStore.setStoreType(sellerStoreRequestModel.getStoreType());
//          JSONObject updateStore = sellerStoreService.updateStore(sellerStore , user.getDmId());
//          RespHandler.respOK(updateStore, response);
//      }catch(ServiceException e){
//          log.error(e);
//          switch (e.getErrorType()) {
//          case ExceptionCode.PRICE_ERROR:
//                  RespHandler.respError( RespMsg.price_error, response);
//              break;
//          case ExceptionCode.SELLER_NO_OPEN_STORE:
//                  RespHandler.respError( RespMsg.seller_no_store, response);
//                  break;
//          default:
//                  RespHandler.respError(RespMsg.updateStore_error(e.getErrorType()), response);
//                  break;
//          }
//      }
//      
//  }
//}
