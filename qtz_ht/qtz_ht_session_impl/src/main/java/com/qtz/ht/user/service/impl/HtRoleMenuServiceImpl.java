package com.qtz.ht.user.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.user.service.HtRoleMenuService;
import com.qtz.ht.user.dao.HtRoleMenuDao;
import com.qtz.ht.user.vo.HtRoleMenu;

/**
 * <p>Title:HtRoleMenuServiceImpl</p>
 * <p>Description:商户角色菜单服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
@Service("htRoleMenuServiceImpl")
public class HtRoleMenuServiceImpl extends BaseServiceImpl<HtRoleMenu,Long> implements HtRoleMenuService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtRoleMenuServiceImpl.class);
	/**注入商户角色菜单DAO接口类*/
	@Resource(name="htRoleMenuDaoImpl")
    private HtRoleMenuDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtRoleMenu,Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
}