package com.qtz.ht.manage.controller.error;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespHandler;
import com.qtz.ht.manage.controller.BaseController;

@RestController
@RequestMapping(value="error/")
public class ErrorController extends BaseController{
	
	
	@RequestMapping(value="404")
	public void _404(HttpServletResponse response){
		RespHandler.resp404(response);
	}
	
	@RequestMapping(value="405")
	public void _405(HttpServletResponse response){
		RespHandler.resp405(response);
	}

	@RequestMapping(value="400")
	public void _400(HttpServletResponse response){
		RespHandler.resp400(response);
	}
	
	@RequestMapping(value="500")
	public void _500(HttpServletResponse response){
		RespHandler.respServerError(response);
	}

	@RequestMapping(value="timeout")
	public void _timeout(HttpServletResponse response){
		RespHandler.respServerTimeout(response);
	}
//	@RequestMapping(value="serviceException")
//	public void _serviceException(HttpServletResponse response){
//		RespHandler.respServerTimeout(response);
//	}

}
