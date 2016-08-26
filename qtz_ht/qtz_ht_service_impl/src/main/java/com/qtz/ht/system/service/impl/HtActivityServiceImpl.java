package com.qtz.ht.system.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.system.dao.HtActivityDao;
import com.qtz.ht.system.service.HtActivityService;
import com.qtz.ht.system.vo.HtActivity;

/**
 * <p>Title:HtActivityServiceImpl</p>
 * <p>Description:活动管理表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
 */
@Service("htActivityServiceImpl")
public class HtActivityServiceImpl extends BaseServiceImpl<HtActivity,Long> implements HtActivityService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtActivityServiceImpl.class);
	/**注入活动管理表DAO接口类*/
	@Resource(name="htActivityDaoImpl")
    private HtActivityDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtActivity,Long> getDao() {
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