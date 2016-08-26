//package com.qtz.ht.manage.controller.search;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mall.core.common.RegexUtil;
//import com.mall.core.common.RespKey;
//import com.mall.core.common.StringUtil;
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.vo.Pager;
//import com.qtz.ht.controller.BaseController;
//import com.qtz.ht.log.count.vo.CategorySearchCount;
//import com.qtz.ht.log.service.SearchLogService;
//import com.qtz.ht.system.service.CategoryService;
//import com.qtz.ht.system.vo.Category;
//import com.qtz.ht.user.page.UserPage;
//import com.qtz.ht.user.services.UsersService;
//import com.qtz.ht.user.vo.User;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
///**
// * 
// * <p>Title:SearchController</p>
// * <p>Description:(搜索接口)</p>
// * <p>Copyright: Copyright (c) 2015</p>
// * <p>Company: 深圳市擎天柱信息科技有限公司</p>
// * @author 涂鑫 - changbo.li
// * @version v1.0 2015年4月6日
// */
//@RestController
//@RequestMapping(value="/v1.0/search")
//public class SearchController extends BaseController{
//	@Autowired
//	private UsersService usersService;
//	@Autowired
//	private CategoryService categoryService;
//	@Autowired
//	private SearchLogService searchLogService;
//	/**
//	 * 
//	  * 【通过手机号码搜索】
//	  * @param phone	手机号码
//	 * @throws IOException 
//	 */
//	@ApiOperation(value="手机号码精准搜索")
//	@RequestMapping(value="/phone/{phone}",method=RequestMethod.GET)
//	public void searchPhone(@RequestHeader("token") String sid,@PathVariable("phone") String phone,HttpServletResponse response) throws IOException{
//		try {
//			log.debug("正在通过手机号码所搜好友用户");
//			User user = getUser(sid);
//			if(user==null){
//				RespHandler.respError(RespMsg.user_not_login, response);
//				return;
//			}
//			if(!RegexUtil.isMobile(phone)){
//				RespHandler.respError(RespMsg.phone_format_error, response);
//				return;
//			}
//			long dmId=user.getDmId().longValue();
//			//TODO 精准搜索
//			user = usersService.findPhone(phone);
//			if(user==null){
//				RespHandler.respError(RespMsg.account_inexistence, response);
//				return;
//			}
//			if (user.getDmId().longValue()==dmId) {
//				user =null;
//			}
//			if(user!= null){
//			user.setPwd(null);
//			user.setLoc(null);
//			}
//			JSONObject json=new JSONObject();
//			json.put(RespKey.user, user);
//			RespHandler.respOK(json, response);
//		} catch (ServiceException e) {
//			log.error("搜索处理失败",e);
//			RespHandler.respError(RespMsg.search_failed(e.getErrorType()), response);
//		}
//	}
//	
//	/**
//	 * 
//	  * 【热门搜索】
//	  * @param sid
//	  * @param response
//	 * @throws IOException 
//	 */
//	@ApiOperation(value="热门搜索")
//	@RequestMapping(value="/topSearchTag",method=RequestMethod.GET)
//	public void topSearchTag(HttpServletResponse response) throws IOException{
//		try {
//		List<CategorySearchCount> queryTopSearch = searchLogService.queryTopSearch();
//		RespHandler.respOK(queryTopSearch, response);
//		} catch (ServiceException e) {
//			log.error("",e.getErrorMessage());
//			RespHandler.respError(RespMsg.search_failed(e.getErrorType()), response);
//		}
//	}
//	
//	/**
//	 * 
//	  * 【添加好友界面模糊搜索】
//	  * @param phone
//	  * @param sid
//	 * @throws IOException 
//	 */
//	@ApiOperation(value="添加好友界面模糊搜索")
//	@RequestMapping(value="/likePhone/{search}/{pageIndex}",method=RequestMethod.GET)
//	public void LikePhone(@PathVariable("search") String search,@PathVariable("pageIndex") int pageIndex,@RequestHeader("token")String sid,HttpServletResponse response) throws IOException{
//		try {
//			log.info("搜索...");
//			User user = getUser(sid);
//			if(user==null){
//				RespHandler.respError(RespMsg.user_not_login, response);
//				return;
//			}
//			
//			List<User> users = usersService.findLikeUser(search,user,pageIndex);
//			RespHandler.respOK(users, response);
//		} catch (ServiceException e) {
//			log.error("",e);
//			RespHandler.respError(RespMsg.search_failed(e.getErrorType()), response);
//		}
//	}
//	
//	/**
//	 * 
//	  * 【标签搜索用户】
//	  * @param sid
//	  * @param tagName
//	  * @param pageIndex
//	  * @param response
//	 * @throws IOException 
//	 */
//	@ApiOperation(value="标签搜索用户")
//	@RequestMapping(value="/searchTag/{tagName}/{lon}/{lat}/{pageIndex}",method=RequestMethod.GET)
//	public  void searchTag(@RequestHeader("token") String sid,@PathVariable("lon") Double lon ,@PathVariable("lat") Double lat,@PathVariable("tagName") String tagName,@PathVariable("pageIndex") int pageIndex,HttpServletResponse response) throws IOException{
//		log.debug("搜索用户中...");
//		try {
//		User user = getUser(sid);
//		if(user==null){
//			RespHandler.respError(RespMsg.user_not_login, response);
//			return;
//			}
//		if(lon==null || lat==null){
//			RespHandler.respError(RespMsg.no_loc, response);
//			return;
//		}
//		UserPage userPage=new UserPage();
//		userPage.setId(user.getDmId());
//		userPage.setNowPage(pageIndex);
//		userPage.setTagName(tagName);
//		Pager<User, Long> pager = usersService.queryLikeTagSeller(userPage, lon, lat);
//		RespHandler.respOK(pager.getList(), response);
//		} catch (ServiceException e) {
//			log.error("",e);
//			RespHandler.respError(RespMsg.search_failed(e.getErrorType()), response);
//		} 
//	}
//	
//	
//	/**
//	 * 
//	  * 【搜索用户】
//	  * @param sid
//	  * @param nickName
//	  * @param tagName
//	  * @param response
//	  * @param isCouponSeller  是否有优惠券的商家
//	 * @throws IOException 
//	 */
//	@ApiOperation(value="搜索用户  传递搜素 接受 三个参数其中一个")
//	@RequestMapping(value="/searchUser",method=RequestMethod.GET)
//	public void searchUser(
//			@RequestHeader(value="token",required=false) String sid,
//			@RequestParam(value = "nickName", required = false) String nickName,
//			@RequestParam(value = "tagName", required = false) String tagName,
//			@RequestParam(value = "search", required = false) String search,
//			@RequestParam(value = "lon", required = false) Double lon,
//			@RequestParam(value = "lat", required = false) Double lat,
//			@RequestParam("pageIndex") int pageIndex,
//			@RequestParam(value="searchMark",required = false) @ApiParam(required=false,value="1 搜索 优惠卷商家  2搜索商家  不传 则搜索全部用户") Integer searchMark,
//			HttpServletResponse response) throws IOException {
//		try {
//			log.debug("正在搜索用户....");
//			if (lon==null || lat==null) {
//              RespHandler.respError(RespMsg.no_loc, response);
//              return;
//			}
//			if (StringUtils.isEmpty(nickName) && StringUtil.isEmpty(tagName)
//					&& StringUtil.isEmpty(search)) {
//				log.error("传递参数错误.");
//				return;
//			}
//			User user2=null;
//			List<Object> searchUsers;
//			if (!StringUtils.isEmpty(sid)) {
//				 user2 = getUser(sid);
//				if (user2 == null) {
//					RespHandler.respError(RespMsg.user_not_login, response);
//					return;
//				}
//				 searchUsers = usersService.searchUsers(user2==null?null:user2.getDmId(),
//						nickName, tagName, pageIndex, searchMark,search);
//			}else{
//				UserPage up =new UserPage();
//				up.setLongitude(lon);
//				up.setLatitude(lat);
//				up.setNickname(nickName);
//				up.setTagName(tagName);
//				up.setNowPage(pageIndex);
//				searchUsers=usersService.searchUsers(up , searchMark, search);
//			}
//			
//			RespHandler
//					.respOK(searchUsers == null || searchUsers.size() <= 0 ? new ArrayList<User>()
//							: searchUsers, response);
//		} catch (ServiceException e) {
//			log.error("", e);
//			RespHandler.respError(RespMsg.search_failed(e.getErrorType()), response);
//		} 
//	}
//	
//	/**
//	 * 
//	  * 【查询朋友或者粉丝】  
//	  * @time:2015年5月1日 上午11:48:42
//	  * @author 涂鑫
//	  * @version
//	 * @throws IOException 
//	 */
//	@ApiOperation(value="搜索粉丝关注",notes="type 1 表示搜索关注 2 表示搜索粉丝")
//	@RequestMapping(value = "/searchFriendOrFans/{search}/{pageIndex}/{type}",method=RequestMethod.GET)
//	public void searchFriendOrFans(@RequestHeader("token") String sid,
//			@PathVariable("search") String search,
//			@PathVariable("pageIndex") int pageIndex,
//			@PathVariable("type") Integer type, HttpServletResponse response) throws IOException {
//		try {
//			log.info("搜索关注或者粉丝");
//			User user = getUser(sid);
//			if (user == null) {
//				RespHandler.respError( RespMsg.user_not_login, response);
//				return;
//			}
//			if (StringUtils.isEmpty(search) || pageIndex <= 0 || type == null) {
//				log.error("传递参数错误");
//				return;
//			}
//			UserPage userPage = new UserPage();
//			userPage.setId(user.getDmId());
//			userPage.setMphonenum(search);
//			userPage.setNickname(search);
//			List<User> users = new ArrayList<User>();
//			if (type == 1) {
//				users = usersService.searchFollower(userPage).getList();
//			} else if(type==2) {
//				users = usersService.searchFans(userPage).getList();
//			}
//			RespHandler.respOK(users, response);
//		} catch (ServiceException e) {
//			log.error("", e);
//			RespHandler.respError(RespMsg.search_failed(e.getErrorType()), response);
//		} 
//	}
//
//	
//	
//	@ApiOperation(value="搜索用户ByIndex")
//	@RequestMapping(value="/searchSellerByIndex",method=RequestMethod.GET)
//	public void searchSellerByIndex(
//			@RequestHeader(value="token",required=false) String sid,
//			@RequestParam(value = "search", required = false) String search,
//			@RequestParam(value="tagId",required=true) Long tagId,
//			@RequestParam(value = "lon", required = false) Double lon,
//			@RequestParam(value = "lat", required = false) Double lat,
//			@RequestParam("pageIndex") int pageIndex,
//			HttpServletResponse response) throws IOException {
//		try {
//			log.debug("正在搜索用户....");
//			User user2=null;
//			List<Object> searchUsers;
//			Category category = categoryService.findById(tagId);
//			if (!StringUtils.isEmpty(sid)) {
//				 user2 = getUser(sid);
//				if (user2 == null) {
//					RespHandler.respError( RespMsg.user_not_login, response);
//					return;
//				}
//				 searchUsers = usersService.searchUsers(user2==null?null:user2.getDmId(),
//						null, category==null?null:category.getName(), pageIndex, 2,search);
//			}else{
//				UserPage up =new UserPage();
//				up.setLongitude(lon);
//				up.setLatitude(lat);
//				
//				if(category!=null){
//					up.setTagName(category.getName());
//				}
//				up.setNowPage(pageIndex);
//				searchUsers=usersService.searchUsers(up , 2, search);
//			}
//			
//			RespHandler
//					.respOK(searchUsers == null || searchUsers.size() <= 0 ? new ArrayList<User>()
//							: searchUsers, response);
//		} catch (ServiceException e) {
//			log.error("", e);
//			RespHandler.respError(RespMsg.search_failed(e.getErrorType()), response);
//		} 
//	}
//}
