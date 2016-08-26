package com.qtz.ht.personal.controller.order;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespHandler;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.dm.user.vo.User;
import com.qtz.ht.personal.controller.BaseController;
import com.qtz.orig.common.Result;
import com.qtz.orig.message.service.MessageService;
import com.qtz.orig.message.vo.Message;

@RestController
@RequestMapping(value="/v1.0/message/")
public class MessageController extends BaseController{

	protected static LogTool log = LogTool.getInstance(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="getMessageByUserId",method=RequestMethod.GET)
	public void getMessageByUserId(@RequestHeader("token") String sid,@RequestParam(value = "nowPage", required = false) Integer nowPage,HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		try {
			
			User user=getUser(sid);
			
			Result<Message> result = messageService.getMessageListByUserId(user.getDmId(), nowPage);
			
			if(result.isSuccess()){
				RespHandler.respOK(result.getPager(), result.getPager().getList(), response);
			}else {
				RespHandler.respError(result.getCode(), result.getFailMessage(), response);
			}
			
		} catch (ServiceException e) {
			RespHandler.respError(e.getErrorType(), e.getErrorTitle(), response);
		}
	}
	
}