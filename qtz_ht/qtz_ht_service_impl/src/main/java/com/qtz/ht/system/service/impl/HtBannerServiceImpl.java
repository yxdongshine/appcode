package com.qtz.ht.system.service.impl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.system.service.HtBannerService;
import com.qtz.ht.system.dao.HtBannerDao;
import com.qtz.ht.system.vo.HtBanner;

/**
 * <p>Title:HtBannerServiceImpl</p>
 * <p>Description:横幅管理表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-15
 */
@Service("htBannerServiceImpl")
public class HtBannerServiceImpl extends BaseServiceImpl<HtBanner,Long> implements HtBannerService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtBannerServiceImpl.class);
	/**注入横幅管理表DAO接口类*/
	@Resource(name="htBannerDaoImpl")
    private HtBannerDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtBanner,Long> getDao() {
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
	@Override
	public List<HtBanner> findSpecifyFieldList(Integer versionType, Integer clientType, int status)throws ServiceException{
		try {
			HtBanner obj = new HtBanner();
			obj.setClientType(clientType);
			obj.setVersionType(versionType);
			obj.setStatus(status);
			return dao.findSpecifyFieldList(obj);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}