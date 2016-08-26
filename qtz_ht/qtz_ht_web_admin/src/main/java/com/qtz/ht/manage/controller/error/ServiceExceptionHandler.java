//package com.qtz.ht.controller.error;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.exception.ServiceException;
//
//public class ServiceExceptionHandler implements HandlerExceptionResolver{
//
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex) {
//		if (ex instanceof ServiceException) {
//				ServiceException e=(ServiceException)ex;
//				if (e.getErrorType()==1) {
//					RespHandler.resp400(response);
//					ModelAndView mv=new ModelAndView();
//					
//					return ExceptionHandlerFactory;
//				}
//		}
//		return null;
//	}
//
//}
