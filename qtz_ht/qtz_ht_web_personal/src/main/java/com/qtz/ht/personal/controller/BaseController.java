package com.qtz.ht.personal.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.dubbo.rpc.RpcException;
import com.mall.core.common.Constants;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.DdmSession;
import com.qtz.dm.session.service.SessionService;
import com.qtz.dm.user.vo.User;
import com.qtz.dm.user.vo.UserShop;
@Component
public abstract class BaseController {
	
	protected static LogTool log = LogTool.getInstance(BaseController.class);
	
//	public static final String REQUEST_DATA = "dm_data";//请求数据
	@Autowired
	protected SessionService sessionService;
	
	/**
	 * 
	* 取得用户id  如果返回为null则表示未登录
	* @param sessionid
	* @return
	* @throws ServiceException
	 */
	protected User getUser(String sid) throws ServiceException {
		User user = null;
		if (!StringUtils.isEmpty(sid)) {
			DdmSession appSession = sessionService.getAppSession(sid);
			if (null != appSession) {
				Object obj = appSession
						.getSessionObject(Constants.SESSION_USER);
				if (obj instanceof UserShop) {
					return (UserShop) obj;
				}
				if (obj instanceof User) {
					return (User) obj;
				}
			}
		}
		return user;
	}
	@ExceptionHandler
    public void exp(HttpServletRequest request, Exception ex,HttpServletResponse response) {
        // 记录日志
		StringBuffer sb = new StringBuffer();  
        StackTraceElement[] stackArray = ex.getStackTrace();  
        for (int i = 0; i < stackArray.length; i++) {  
            StackTraceElement element = stackArray[i];  
            sb.append(element.toString() + "\n");  
        }
        log.error(""+sb.toString()+""+ex.getMessage(), ex);
        // 根据不同错误转向不同页面
        if (ex instanceof ServiceException) {
        	ServiceException e=(ServiceException)ex;
        	switch (e.getErrorType()) {
			case ExceptionCode.GOODSCATEGORY_NULL_EXCEPTION:
				RespHandler.respError(RespMsg.goodsCategory_dont_exist, response);
				break;
			case ExceptionCode.PRICE_ERROR:
				RespHandler.respError(RespMsg.price_error, response);
				break;
			case ExceptionCode.USER_NO_LOGIN:
				RespHandler.respApiLoginError(response);
				break;
			case ExceptionCode.DEL_GOODSCATEGORY_EXIST_PRODUCT:
				RespHandler.respError(RespMsg.goodsCategory_product_error, response);
				break;
			case ExceptionCode.USER_NO_AUTHEN:
				RespHandler.respError(RespMsg.account_no_auth, response);
				break;
			case ExceptionCode.SELLER_NO_OPEN_STORE:
				RespHandler.respError(RespMsg.seller_no_store,response);
				break;
			case ExceptionCode.EXIST_BUSINESSLICENSENAME:
				RespHandler.respError(RespMsg.exist_businesslicensename, response);
				break;
			case ExceptionCode.BUSINESSLICENSENAME_LENGTH_GT_30:
				RespHandler.respError(RespMsg.businessLicenseName_length_gt_30, response);
				break;
			case ExceptionCode.BUSINESSLICENSENAME_LENGTH_LT_5:
				RespHandler.respError(RespMsg.businessLicenseName_length_lt_5, response);
				break;
			default:
				RespHandler.respServerError(response);
				break;
        	}
        	return;
        } if (ex instanceof RpcException ) {
				RespHandler.respServerTimeout(response);
		}else {
				RespHandler.respServerError(response);
        }
    }
	
	/**
	 * 根据票据，获取用户ID
	 * @param sid 票据
	 * @param response
	 * @return
	 * @throws ServiceException
	 */
	public Long getUserId(String sid,HttpServletResponse response) throws ServiceException {
		User user = getUser(sid);
		if(user == null){
			RespHandler.respError(RespMsg.user_not_login, response);
			return null;
		}
		return user.getDmId();
	}
}