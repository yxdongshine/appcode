package com.qtz.ht.user.dao;
import java.util.List;
import java.util.Set;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.ht.user.vo.HtBusiness;
/**
 * <p>Title:HtBusinessDao</p>
 * <p>Description:商户信息DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
public interface HtBusinessDao extends BizDao<HtBusiness,Long> {

	/**
	* 【根据商家信息ID集合查询商家信息列表】
	* @param idS
	* @return
	*/
	List<HtBusiness> findListByBusinessesId(Set<Long> idS)throws DaoException;
	
}