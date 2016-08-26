package com.qtz.ht.good.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.good.service.HtGoodLogService;
import com.qtz.ht.good.dao.HtGoodLogDao;
import com.qtz.ht.good.vo.HtGoodLog;

/**
 * <p>Title:HtGoodLogServiceImpl</p>
 * <p>Description:商户商品操作记录服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
@Service("htGoodLogServiceImpl")
public class HtGoodLogServiceImpl extends BaseServiceImpl<HtGoodLog,Long> implements HtGoodLogService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtGoodLogServiceImpl.class);
	/**注入商户商品操作记录DAO接口类*/
	@Resource(name="htGoodLogDaoImpl")
    private HtGoodLogDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtGoodLog,Long> getDao() {
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