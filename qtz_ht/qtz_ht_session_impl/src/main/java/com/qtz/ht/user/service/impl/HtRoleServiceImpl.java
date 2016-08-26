package com.qtz.ht.user.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.user.service.HtRoleService;
import com.qtz.ht.user.dao.HtRoleDao;
import com.qtz.ht.user.vo.HtRole;

/**
 * <p>Title:HtRoleServiceImpl</p>
 * <p>Description:商户角色服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
@Service("htRoleServiceImpl")
public class HtRoleServiceImpl extends BaseServiceImpl<HtRole,Long> implements HtRoleService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtRoleServiceImpl.class);
	/**注入商户角色DAO接口类*/
	@Resource(name="htRoleDaoImpl")
    private HtRoleDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtRole,Long> getDao() {
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