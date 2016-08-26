package com.qtz.ht.user.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.user.service.HtUserRoleService;
import com.qtz.ht.user.dao.HtUserRoleDao;
import com.qtz.ht.user.vo.HtUserRole;

/**
 * <p>Title:HtUserRoleServiceImpl</p>
 * <p>Description:商户用户与角色关联服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
@Service("htUserRoleServiceImpl")
public class HtUserRoleServiceImpl extends BaseServiceImpl<HtUserRole,Long> implements HtUserRoleService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtUserRoleServiceImpl.class);
	/**注入商户用户与角色关联DAO接口类*/
	@Resource(name="htUserRoleDaoImpl")
    private HtUserRoleDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtUserRole,Long> getDao() {
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