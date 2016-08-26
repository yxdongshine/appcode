//package com.qtz.ht.controller.system;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.log.LogTool;
//import com.qtz.ht.controller.BaseController;
//import com.qtz.ht.system.service.ClientExceptionService;
//import com.qtz.ht.system.vo.ClientException;
//import com.qtz.ht.system.vo.ClientException.ClientType;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//@RestController
//@RequestMapping(value="/v1.0/exception")
//public class ClientExceptionController extends BaseController{
//	
//	protected static LogTool log = LogTool.getInstance(ClientExceptionController.class);
//	@Autowired
//	private ClientExceptionService clientExceptionService;
//	
//	@ApiOperation(value="提交异常",notes="1 ios 2 android 错误级别 1 应用类接口调用错误 2 系统奔溃")
//	@RequestMapping(value="/subException",method=RequestMethod.POST)
//	public void subException(@ApiParam(required=false) @ModelAttribute ClientException clientException,HttpServletResponse response){
//		if(clientException==null){
//			RespHandler.respError(400, RespMsg.request_parameter_error, response);
//			return;
//		}
//		if(clientException.getClientType()==null ){
//				RespHandler.respError(400, RespMsg.request_parameter_error, response);
//			return ;
//		}else{
//			if(clientException.getClientType().intValue()!=ClientType.Android.value()&&clientException.getClientType().intValue()!=ClientType.IOS.value()){
//				RespHandler.respError(400, RespMsg.request_parameter_error, response);
//				return ;
//			}
//		}
//		try{
//		clientExceptionService.save(clientException);
//		RespHandler.respOK(response);
//		}catch(Exception e){
//			log.error(e.getMessage());
//			RespHandler.respServerError(response);
//		}
//	}
//}
