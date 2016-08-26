
package com.qtz.ht.personal.controller.shoppingcar;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.user.vo.User;
import com.qtz.ht.personal.controller.BaseController;
import com.qtz.ht.system.service.HtCartService;
import com.qtz.ht.system.vo.HtCart;

/** 
 * ClassName:ShoppingCarController <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年8月16日 上午9:51:11 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
@RestController
@RequestMapping("v1.0/shoppingCar")
public class ShoppingCarController extends BaseController {
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(ShoppingCarController.class);
	
	/**
	 * 引入购物车
	 */
	@Resource(name="htCartServiceImpl")
	private HtCartService htCartService;
	
	/**
	 * 非购物车页面添加商品进购物车
	 * shoppingInCar:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param htCart
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="shoppingInCar")
	public void shoppingInCar(@RequestParam("token") String sid,@RequestParam Long goodId,HttpServletRequest request, HttpServletResponse response){
		try {
			HtCart htCart =new HtCart();
			User user=getUser(sid);
			if(user!=null&&goodId>0){
				htCart.setUserId(user.getDmId());
				htCart.setGoodId(goodId);
               //添加进购物车
               htCartService.add(htCart);
			RespJsonPHandler.respOK(response,request);
			}else{
				RespJsonPHandler.respError(RespMsg.user_not_login, response, request);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	/**
	 * 修改购物车界面的购物记录数量
	 * modGoodsNumInCar:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param htCart
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="modGoodsNumInCar")
	public void modGoodsNumInCar(HtCart htCart,HttpServletRequest request, HttpServletResponse response){
		try {
            if(htCart!=null){
            	Long dmId=htCart.getDmId();//购物车记录编号
            	Integer nums=htCart.getGoodNumber();//货物数量
            	if(dmId!=null&&dmId>0&&nums!=null&&nums>0){
            		//添加进购物车
            		htCartService.modGoodsNum(dmId, nums);
            	}
            }		
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 删除购物车里面的记录  当记录数为0时候调用这个删除接和直接删除接口
	 * deleteRecordInCar:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param htCart
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deleteRecordInCar")
	public void deleteRecordInCar(@RequestParam("token") String sid,@RequestParam String goodId,HttpServletRequest request, HttpServletResponse response){
		try {
			User user=getUser(sid);
			if(user!=null&&goodId!=null&&goodId.trim().length()>0){
				String[] goodIdStrArr = goodId.split(",");
				if(goodIdStrArr!=null&&goodIdStrArr.length>0){
					List<Long> goodsIdList = new ArrayList<Long>();
                    for (int i = 0; i < goodIdStrArr.length; i++) {
						Long goodIdSingle=Long.parseLong(goodIdStrArr[i]);
						goodsIdList.add(goodIdSingle);
					}
                    if(goodsIdList.size()>0){
                       	//删除记录
                    	htCartService.delete(goodsIdList, user.getDmId());
                    }
				}
 
            }else{
				RespJsonPHandler.respError(RespMsg.user_not_login, response, request);
			}		
			RespJsonPHandler.respOK(response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 查看购物车该用户的所有商品 接口
	 * listInCar:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param htCart
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="listInCar")
	public void listInCar(@RequestParam("token") String sid,HttpServletRequest request, HttpServletResponse response){
		try {
			User user=getUser(sid);
			if(user!=null){
        	  RespJsonPHandler.respOK(htCartService.list(user.getDmId()),response,request);
            }else{
				RespJsonPHandler.respError(RespMsg.user_not_login, response, request);
			}		
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
}
  