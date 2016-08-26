package com.qtz.ht.good.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.good.service.HtGoodDescService;
import com.qtz.ht.good.dao.HtGoodDescDao;
import com.qtz.ht.good.vo.HtGoodDesc;

/**
 * <p>Title:HtGoodDescServiceImpl</p>
 * <p>Description:商户商品祥情服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-02-15
 */
@Service("htGoodDescServiceImpl")
public class HtGoodDescServiceImpl extends BaseServiceImpl<HtGoodDesc,Long> implements HtGoodDescService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtGoodDescServiceImpl.class);
	/**注入商户商品祥情DAO接口类*/
	@Resource(name="htGoodDescDaoImpl")
    private HtGoodDescDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtGoodDesc,Long> getDao() {
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