package com.qtz.ht.system.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.system.service.HtFloorGoodsService;
import com.qtz.ht.system.dao.HtFloorGoodsDao;
import com.qtz.ht.system.vo.HtFloorGoods;

/**
 * <p>Title:HtFloorGoodsServiceImpl</p>
 * <p>Description:楼层关联商品服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
 */
@Service("htFloorGoodsServiceImpl")
public class HtFloorGoodsServiceImpl extends BaseServiceImpl<HtFloorGoods,Long> implements HtFloorGoodsService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtFloorGoodsServiceImpl.class);
	/**注入楼层关联商品DAO接口类*/
	@Resource(name="htFloorGoodsDaoImpl")
    private HtFloorGoodsDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtFloorGoods,Long> getDao() {
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