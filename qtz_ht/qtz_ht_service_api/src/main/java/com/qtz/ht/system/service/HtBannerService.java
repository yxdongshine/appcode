package com.qtz.ht.system.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.ht.system.vo.HtBanner;

/**
 * <p>Title:HtBannerService</p>
 * <p>Description:横幅管理表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-15
 */
public interface HtBannerService extends BaseService<HtBanner,Long> {

	/**
	* 【首页显示查询列表方法】
	* @param versionType
	* 				版本类型
	* @param clientType
	* 				客户端类型
	* @param status
	* 				是否启用       
	* @return  
	*/
	List<HtBanner> findSpecifyFieldList(Integer versionType, Integer clientType, int status)throws ServiceException;
	
}