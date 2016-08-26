package com.qtz.ht.personal.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.core.common.Constants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.qtz.dm.system.service.RegionService;
import com.qtz.dm.system.vo.Region;


/**
 * <p>Title:MainControll</p>
 * <p>Description:(管理页面主界面)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市爱免费信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016-1-28
 */
@Controller
@RequestMapping(value="/")
public class MainControll extends BaseController {
	
	@Resource(name="regionServiceImpl")
	private RegionService regionService;
	
	@RequestMapping(value="getNextRegion")
	public void getNextRegion(Long parentId,HttpServletResponse response,HttpServletRequest request) throws IOException {
		if (null == parentId) {//如果为空默认获取所有省
			parentId = 1l;
		}
		try {
			Region q = new Region();
			q.setParentid(parentId);
			q.setStatus(Constants.ZERO);
			RespJsonPHandler.respOK(regionService.findList(q), response, request);
		} catch (ServiceException e) {
			log.error(e.getErrorTitle(), e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response,request);
		}
	}
	
	//获取主页用户权限页面  导航页
	@RequestMapping(value="getMenuByEmp")
	public String getMenuByEmp(HttpServletRequest req,HttpServletResponse resp) throws ActionException{
//		Session session = getSesion(req);
//		try {
//			if(session != null){
//				Employee emp = (Employee) session.getSessionObject(Constants.SESSION_USER);
//				if(emp.getName().equals("管理员") && emp.getAccount().equals("admin")){
//					Menu m = new Menu();
//					m.setLevel(1);
//					m.setStatus(0);
//					List<Menu> mList = this.menuService.findList(m);
//					PrintUtil.outprint(GsonUtil.toJson(mList, List.class), resp);
//				}else{
//					EmpRole er = new EmpRole();
//					er.setEmpId(emp.getDmId());
//					List<EmpRole> list = empRoleService.findList(er);
//					Map<Long,Menu> map = new HashMap<Long, Menu>();
//					MenuPage page = null;
//					for(EmpRole e : list){
//						Role role = roleService.findVo(e.getRoleId(), new Role());
//						//过滤掉禁用角色
//						if(role.getStatus() == 0){
//							page = new MenuPage();
//							page.setRoleId(e.getRoleId());
//							page.setLevel(1);
//							page.setStatus(0);
//							List<Menu> mList = this.menuService.findPrivileges(page);
//							//过滤重复菜单
//							for(Menu m : mList){
//								if(map.get(m.getDmId()) == null){
//									map.put(m.getDmId(), m);
//								}
//							}
//						}
//					}
//					List<Menu> mList = new ArrayList<Menu>();
//
//					for(Long mId : map.keySet()){
//						mList.add(map.get(mId));
//					}
//					
//					if(mList != null){
//						if(mList.size() > 0){
//							Menu[] array = mList.toArray(new Menu[mList.size()]);
//							Menu temp = null;
//					        for(int i=0;i<array.length;i++){
//					            for(int j=array.length-1;j>i;j--){
//					                if(array[j-1].getSort() >= array[j].getSort()){
//					                    temp = array[j];
//					                    array[j] = array[j-1];
//					                    array[j-1] = temp;
//					                }
//					            }
//					        }
//					        mList = new ArrayList<Menu>();
//					        for(int i = 0;i<array.length;i++){
//					        	mList.add(array[i]);
//					        }
//						}
//					}
//					PrintUtil.outprint(GsonUtil.toJson(mList, List.class), resp);
////				}
//			}else{
//				return "welcome";
//			}
//		} catch (ServiceException e) {
//			log.error(e);
//		}
		return "main";
	}
}
