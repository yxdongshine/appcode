//package com.qtz.ht.controller.circle;
//
//import java.io.IOException;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.vo.Pager;
//import com.qtz.ht.circle.page.CirclePage;
//import com.qtz.ht.circle.page.CircleUserPage;
//import com.qtz.ht.circle.service.CircleService;
//import com.qtz.ht.circle.vo.Circle;
//import com.qtz.ht.circle.vo.CircleApply;
//import com.qtz.ht.circle.vo.CircleUser;
//import com.qtz.ht.controller.BaseController;
//import com.wordnik.swagger.annotations.Api;
///**
// * 圈子接口
// * <p>Title:CircleApplyControll</p>
// * <p>Description:(用一句话描述该文件做什么)</p>
// * <p>Copyright: Copyright (c) 2015</p>
// * <p>Company: 深圳市擎天柱信息科技有限公司</p>
// * @author 李昌波 - changbo.li
// * @version v1.0 2015年3月14日
// */
//@Api(description="圈子接口", value = "circle-api")
//@RestController
//@RequestMapping(value="/v1.0/circle" , produces = {"application/json;charset=UTF-8"})
//public class CircleControll extends BaseController {
//
//	@Resource(name="circleService")
//	private CircleService circleService;
//
//	
//	/**
//	 * 
//	* 【查找圈子】(这里用一句话描述这个方法的作用)
//	* @param id  圈子ID
// 	* @param response
//	 * @throws IOException 
//	 */
//	@RequestMapping(value="/find/{id}",method=RequestMethod.GET)
//	public void find(@PathVariable(value = "id") Long id,HttpServletResponse response) throws IOException{
//		try {
//			//1.查找圈子
//			Circle c = circleService.findVo(id, new Circle());
//			RespHandler.respOK(c, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respOperationFailed(response);
//			
//		}catch (Exception e) {
//			log.error(e);
//			RespHandler.respError(500, RespMsg.server_throws_exception, response);
//		}
//		
//	}
//	/**
//	 * 
//	* 【分页查询圈子用户】(这里用一句话描述这个方法的作用)
//	* @param id  圈子id
//	* @param pageNo  查询页数
//	* @param response
//	* @throws IOException
//	 */
//	@RequestMapping(value="/find/{id}/{pageNo}",method=RequestMethod.GET)
//	public void findUser(@PathVariable(value = "id") Long id,@PathVariable(value = "pageNo") int pageNo,HttpServletResponse response)throws IOException{
//		
//		CircleUserPage cup = new CircleUserPage();
//		cup.setNowPage(pageNo);
//		cup.setCircleId(id);
//		try {
//			Pager<CircleUser, Long> page = circleService.queryByJoinUser(cup);
//			RespHandler.respOK(page, page.getList(), response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respOperationFailed(response);
//		}catch (Exception e) {
//			log.error(e);
//			RespHandler.respServerError(response);
//		}
//		
//	}
//	/**
//	 * 
//	* 【加入圈子】(这里用一句话描述这个方法的作用)
//	* @param id  圈子ID
//	* @param sid
//	* @param response
//	* @throws IOException
//	 */
//	@RequestMapping(value="/add/{id}",method=RequestMethod.GET)
//	public void add(@PathVariable(value = "id") Long id,@RequestHeader("token") String sid,HttpServletResponse response) throws IOException{
//		
//		try {
//			CircleUser user = new CircleUser(id, getUser(sid).getDmId());
//			circleService.saveCircleUser(user);
//			RespHandler.respOK(response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respOperationFailed(response);
//		}catch (Exception e) {
//			RespHandler.respServerError(response);
//		}
//		
//	}
//	
//	/**
//	 * 
//	* 【用户退出圈子】(这里用一句话描述这个方法的作用)
//	* @param id 圈子ID
//	* @param sid
//	* @param response
//	* @throws IOException
//	 */
//	@RequestMapping(value="/signOut/{id}",method=RequestMethod.GET)
//	public void signOut(@PathVariable(value = "id") Long id,@RequestHeader("token") String sid,HttpServletResponse response) throws IOException{
//		
//		try {
//			circleService.delCircleUser(id, getUser(sid).getDmId());
//			RespHandler.respOK(response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respOperationFailed(response);
//		}catch (Exception e) {
//			log.error(e);
//			RespHandler.respServerError(response);
//		}
//	}
//	
//	
//	/**
//	 * 
//	* 【查找圈子列表】(圈子默认列表)
//	* @param pageNo  查询页数
//	* @param response
//	 * @throws IOException 
//	 */
//	@RequestMapping(value="/showList/{pageNo}",method=RequestMethod.GET)
//	public void showList(@PathVariable(value = "pageNo") int pageNo,HttpServletResponse response) throws IOException{
//		
//		CirclePage cp = new CirclePage();
//		cp.setOrderField("crtime");
//		cp.setNowPage(pageNo);
//		try {
//			Pager<Circle, Long> page = circleService.query(cp, Circle.class);
//			RespHandler.respOK(page, page.getList(), response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respOperationFailed(response);
//		}catch (Exception e) {
//			log.error(e);
//			RespHandler.respServerError(response);
//		}
//		
//	}
//	/**
//	 * 
//	* 【查找圈子】(根据名字查找圈子)
//	* @param name  圈子名字
//	* @param pageNo 查询的页数
//	* @param response
//	 * @throws IOException 
//	 */
//	@RequestMapping(value="/findName/{name}/{pageNo}",method=RequestMethod.GET)
//	public void findByName(@PathVariable(value = "name")String name,@PathVariable(value = "pageNo")int pageNo,HttpServletResponse response) throws IOException{
//
//		if(StringUtils.isEmpty(name))
//		{
//			RespHandler.respError(400, RespMsg.request_parameter_error, response);
//			return;
//		}
//		try {
//			CirclePage pp = new CirclePage();
//			pp.setOrderField("crtime");
//			pp.setOrderDirection(false);
//			pp.setNowPage(pageNo);
//			pp.setName(name);
//			Pager<Circle, Long> p = circleService.queryByLike(pp);
//			RespHandler.respOK(p, p.getList(), response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respOperationFailed(response);
//		}catch (Exception e) {
//			log.error(e);
//			RespHandler.respServerError(response);
//		}
//	}
//	
//	/**
//	 * 
//	* 【申请创建圈子】(这里用一句话描述这个方法的作用)
//	* @param name  圈子名字
//	* @param sid tooken
//	* @param explainTxt 圈子说明
//	* @param response
//	 * @throws IOException 
//	 * @throws ServiceException 
//	 */
//	@RequestMapping(value="/apply" , method=RequestMethod.POST)
//	public void apply(String name, @RequestHeader("token") String sid,String explainTxt,HttpServletResponse response) throws IOException{
//		
//		if(StringUtils.isEmpty(name))
//		{
//			RespHandler.respError(400, RespMsg.request_parameter_error, response);
//			return;
//		}
//		try {
//			CircleApply app = new CircleApply(name, explainTxt, getUser(sid).getDmId());
//			circleService.saveCircleApply(app);
//			RespHandler.respOK(response);
//		} catch (ServiceException e) {
//			//e.printStackTrace();
//			log.error(e);
//			RespHandler.respOperationFailed(response);
//		}catch (Exception e) {
//			log.error(e);
//			RespHandler.respServerError(response);
//		}
//	}
//	
//}
