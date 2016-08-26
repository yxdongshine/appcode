package com.qtz.ht.user.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.user.service.HtBusinessLogService;
import com.qtz.ht.user.dao.HtBusinessLogDao;
import com.qtz.ht.user.vo.HtBusinessLog;

/**
 * <p>Title:HtBusinessLogServiceImpl</p>
 * <p>Description:商户信息日志表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-11
 */
@Service("htBusinessLogServiceImpl")
public class HtBusinessLogServiceImpl extends BaseServiceImpl<HtBusinessLog,Long> implements HtBusinessLogService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtBusinessLogServiceImpl.class);
	/**注入商户信息日志表DAO接口类*/
	@Resource(name="htBusinessLogDaoImpl")
    private HtBusinessLogDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtBusinessLog,Long> getDao() {
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