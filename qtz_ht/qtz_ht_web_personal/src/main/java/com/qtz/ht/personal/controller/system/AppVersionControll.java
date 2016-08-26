//package com.qtz.ht.controller.system;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.exception.ActionException;
//import com.mall.core.exception.ServiceException;
//import com.qtz.dm.system.service.AppVersionService;
//import com.qtz.dm.system.vo.AppVersion;
//import com.qtz.dm.userwallet.enums.AppType;
//import com.qtz.ht.controller.BaseController;
//import com.wordnik.swagger.annotations.ApiOperation;
///**
// * app 应用版本管理
// * <p>Title:AppVersionControll</p>
// * <p>Description:(用一句话描述该文件做什么)</p>
// * <p>Copyright: Copyright (c) 2015</p>
// * <p>Company: 深圳市擎天柱信息科技有限公司</p>
// * @author 李昌波 - changbo.li
// * @version v1.0 2015年4月7日
// */
//@RestController
//@RequestMapping(value="/v1.0/version" ,produces = {"application/json;charset=UTF-8"})
//public class AppVersionControll extends BaseController {
//	@Autowired
//	private AppVersionService service;
//	
//	private static final String APP_KEY = "app";
//	private static final String APP_NAME = "amf";
//	/**
//	 * 
//	* 【查询渠道对应的最新版本】(这里用一句话描述这个方法的作用)
//	* @param channel
//	* @param response
//	* @throws ActionException
//	* @throws IOException
//	 */
//	@ApiOperation(value="查询Android 版本")
//	@RequestMapping(value="/getAndroidChannelMax/{channel}" ,method=RequestMethod.GET)
//	public void getMaxChannelAndroidVersion(@PathVariable("channel") String channel,HttpServletResponse response) throws ActionException,IOException{
//		try {
//			AppVersion app = service.findNew(AppType.Android.value(), APP_NAME);
//			JSONObject json = new JSONObject();
//			if(null != app){
//				app.setAppDown(app.getAppDown()+"/amf_v"+app.getMainVersion()+"_"+channel+".apk");
//			}
//			json.put(APP_KEY, app); 
//			RespHandler.respOK(json, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			throw new ActionException(e);
//		}
//	}
//	/**
//	 * 
//	* 【查询安卓最新版本】(这里用一句话描述这个方法的作用)
//	* @param id
//	* @param response
//	* @throws IOException
//	 */
//	@ApiOperation(value="查询Android 版本")
//	@RequestMapping(value="/getAndroidMax" ,method=RequestMethod.GET)
//	public void getMaxAndroidVersion(HttpServletResponse response) throws ActionException,IOException{
//		
//		try {
//			AppVersion app = service.findNew(AppType.Android.value(), APP_NAME);
//			JSONObject json = new JSONObject();
//			if(null != app){
//				app.setAppDown(app.getAppDown()+"/amf_v"+app.getMainVersion()+"_1000.apk");
//			}
//			json.put(APP_KEY, app); 
//			RespHandler.respOK(json, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			throw new ActionException(e);
//		}
//		
//	}
////	/**
////	 * 
////	* 【查询IOS 最新版本】(这里用一句话描述这个方法的作用)
////	* @param id
////	* @param response
////	* @throws IOException
////	 */
////	@ApiOperation(value="查询ios 版本")
////	@RequestMapping(value="/getAppleMax" ,method=RequestMethod.GET)
////	public void getMaxAppleVersion(HttpServletResponse response) throws ActionException,IOException{
////		try {
////			AppVersion app = service.findNew(AppType.Apple.value(), APP_NAME);
////			JSONObject json = new JSONObject();
////			json.put(APP_KEY, app);
////			RespHandler.respOK(json, response);
////		} catch (ServiceException e) {
////			log.error(e);
////			throw new ActionException(e);
////		}
////	}
//	/**
//	 * 
//	 * 【查询IOS 最新版本】(这里用一句话描述这个方法的作用)
//	 * @param id
//	 * @param response
//	 * @throws IOException
//	 */
//	@ApiOperation(value="查询ios 版本")
//	@RequestMapping(value="/getAppleMax" ,method=RequestMethod.GET)
//	public void getMaxAppleVersion(HttpServletResponse response,@RequestParam(required=false,value="userType",defaultValue="2") Integer userType) throws ActionException,IOException{
//		
//		try {
//			AppVersion app = service.findNew(AppType.Apple.value(), APP_NAME,userType);
//			JSONObject json = new JSONObject();
//			json.put(APP_KEY, app);
//			RespHandler.respOK(json, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			throw new ActionException(e);
//		}
//	}
//	
//	
//	
//	
//}
