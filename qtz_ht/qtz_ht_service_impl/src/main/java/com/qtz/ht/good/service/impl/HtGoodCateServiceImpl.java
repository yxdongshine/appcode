package com.qtz.ht.good.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.good.service.HtGoodCateService;
import com.qtz.ht.good.dao.HtGoodCateDao;
import com.qtz.ht.good.vo.HtGoodCate;

/**
 * <p>Title:HtGoodCateServiceImpl</p>
 * <p>Description:商户商品分类服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-11
 */
@Service("htGoodCateServiceImpl")
public class HtGoodCateServiceImpl extends BaseServiceImpl<HtGoodCate,Long> implements HtGoodCateService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtGoodCateServiceImpl.class);
	/**注入商户商品分类DAO接口类*/
	@Resource(name="htGoodCateDaoImpl")
    private HtGoodCateDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtGoodCate,Long> getDao() {
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