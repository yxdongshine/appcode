package com.qtz.ht.manage.interceptor;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mall.core.common.Constants;
import com.mall.core.common.SpringContextHolder;
import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.DdmSession;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.session.service.SessionService;


/**
 * 实现拦截器 
 * ClassName: SecurityInterceptor <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年5月27日 上午10:39:31 <br/> 
 * 
 * @author yxd 
 * @version
 */
public class SecurityInterceptor implements HandlerInterceptor{
	private static LogTool log=LogTool.getInstance(SecurityInterceptor.class);
	
	SessionService sessionService = (SessionService) SpringContextHolder.getBean("sessionService");
	
	private static final String strHtml= ".html";
	//排除
	private List<String> excludeUrls;

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}
	
	/**
	 * 判断该路径是否在排除之外 合法
	 * isExcludeUrls:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param excludeUrls
	 * @param url
	 * @return
	 */
	public boolean isExcludeUrls(List<String> excludeUrls,String url){
		boolean isExcludeUrls = false ;
		for (String regex : excludeUrls) {
			String subN = StringUtil.subN(regex, "/", 1);
			if(subN.equals("logout") && Pattern.matches(regex.toString(), url.toString())){
				isExcludeUrls= true;
				return isExcludeUrls;
			}else if(subN.equals("login") && Pattern.matches(regex.toString(), url.toString())){
				isExcludeUrls= true;
				return isExcludeUrls;
			}else if(url.contains(strHtml)){
				isExcludeUrls= true;
				return isExcludeUrls;
			}
			
		}
		return isExcludeUrls;
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws ServiceException, IOException{
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (excludeUrls.contains(url)) {
	        return true;
		} else {
			if(isExcludeUrls(excludeUrls, url))return true;
			
			String sid = request.getParameter("token");
			if (StringUtils.isEmpty(sid)) {//不存在 提示登录
				RespJsonPHandler.respError(RespMsg.session_overdue, response, request);// session
				return false;
			}else {
				DdmSession appSession = sessionService.getSession(sid);	
				if (appSession == null) {
					RespJsonPHandler.respError(RespMsg.session_overdue, response, request);// session
					return false;
				}
				Object obj=appSession.getSessionObject(Constants.SESSION_USER);
				if(obj instanceof HtStaff){
					HtStaff user = (HtStaff)obj;
					log.debug("+++++++++++++++++++++++");
					log.debug("拦截到请求  token  :"+sid+", USERID: [ " + user.getDmId()+ " ]"+",NAME: [ "+user.getName()+"]"+ ", IP: " + request.getRemoteAddr() + ", REQUESTURL: "+request.getRequestURL() + ",  USER-AGENT: " + request.getHeader("User-Agent") + ",  SESSIONID: "+appSession.getId() );
					log.debug("+++++++++++++++++++++++");
				}else{
					RespJsonPHandler.respError(RespMsg.session_overdue, response, request);// session
					return false;
				}
				
			}
			
			return true;
		}
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		
		if(url.contains("logout")){//登出 不用再次保存会话session
			return;
		}
		
		
		String sid = request.getHeader("token");
		if (!StringUtils.isEmpty(sid)) {
			DdmSession appSession = sessionService.getSession(sid);
			if (appSession != null) {
				sessionService.saveSession(appSession);
			}
		}
	}
	
	
}
