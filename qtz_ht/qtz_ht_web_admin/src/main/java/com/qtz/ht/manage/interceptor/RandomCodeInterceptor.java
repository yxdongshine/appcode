
package com.qtz.ht.manage.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.qtz.ht.manage.annotinter.RandomCodeInter;
import com.qtz.ht.manage.controller.system.RandomCodeController;

/** 
 * ClassName:RandomCodeInterceptor <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年6月14日 下午5:46:46 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class RandomCodeInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RandomCodeInter annotation = method.getAnnotation(RandomCodeInter.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
            	String sid = request.getParameter("token");
                if (needSaveSession) {
        			if (StringUtils.isEmpty(sid)) {//不存在 提示登录
        				RespJsonPHandler.respError(RespMsg.session_overdue, response, request);// session
        				return false;
        			}
        			String uuid = RandomCodeController.RandomCode.get(sid);
        			if(uuid!=null){
                        request.getSession(false).setAttribute("randomCode", uuid);
        			}else {
        				return false;
					}
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false;
                    }
                    request.getSession(false).removeAttribute("randomCode");
                    RandomCodeController.remodeRandomCode(sid);
                }
            }
            return true;
        } else {
        	 return true;
        }
	}

	/**
	 * 
	 * isRepeatSubmit:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param request
	 * @return
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("randomCode");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("randomCode");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
  