package com.qtz.ht.good.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.good.service.HtGoodsAlbumService;
import com.qtz.ht.good.dao.HtGoodsAlbumDao;
import com.qtz.ht.good.vo.HtGoodsAlbum;

/**
 * <p>Title:HtGoodsAlbumServiceImpl</p>
 * <p>Description:商户商品相册服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-02-15
 */
@Service("htGoodsAlbumServiceImpl")
public class HtGoodsAlbumServiceImpl extends BaseServiceImpl<HtGoodsAlbum,Long> implements HtGoodsAlbumService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtGoodsAlbumServiceImpl.class);
	/**注入商户商品相册DAO接口类*/
	@Resource(name="htGoodsAlbumDaoImpl")
    private HtGoodsAlbumDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtGoodsAlbum,Long> getDao() {
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