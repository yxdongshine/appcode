package com.qtz.ht.manage.interceptor;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mall.core.common.Constants;
import com.mall.core.common.SpringContextHolder;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.vo.DdmSession;
import com.qtz.ht.user.service.HtUserService;
import com.qtz.ht.user.vo.HtUser;
import com.qtz.session.service.SessionService;
/**
 * 
 * <p>Title:StepsInterceptor</p>
 * <p>Description:(步奏拦截器)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月21日
 */
public class StepsInterceptor implements HandlerInterceptor{
	SessionService sessionService = (SessionService) SpringContextHolder.getBean("sessionService");
	
	HtUserService usersService = (HtUserService) SpringContextHolder.getBean("htUserService");
	//排除
	private List<String> excludeUrls;

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (excludeUrls.contains(url)) {
			return true;
		} else {
			for (String regex : excludeUrls) {
				//String subN = StringUtil.subN(url, "/", 2);
				if(Pattern.matches(regex.toString(), url.toString())){
					return true;
				}
				
			}
		}	
		String sid = request.getHeader("token");
		DdmSession appSession = sessionService.getSession(sid);
		if(appSession!=null){
			HtUser user = (HtUser) appSession.getSessionObject(Constants.SESSION_USER);
			if (user != null) {
				return true;
			}else{
				RespJsonPHandler.respError(RespMsg.session_overdue, response, request);
				return false;
			}
		}else{
			RespJsonPHandler.respError(RespMsg.session_overdue, response, request);
			return false; 
		}
	}
}
