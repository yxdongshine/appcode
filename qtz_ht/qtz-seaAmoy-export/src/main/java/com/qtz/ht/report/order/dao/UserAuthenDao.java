package com.qtz.ht.report.order.dao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.ht.report.order.entity.UserAuthen;
import com.qtz.ht.report.order.model.UserAuthenPage;
/**
 * <p>Title:UserPersonDao</p>
 * <p>Description:个人认证DAO接口类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author lzg - charli
 * @version v1.0 2015-03-14
 */
public interface UserAuthenDao extends BizDao<UserAuthen,Long> {
		
	public Long findCountByStatus(UserAuthen vo) throws DaoException ;
	
	UserAuthen findCountByUserId(Long userId, UserAuthen clazz) throws DaoException ;
	
	public Pager<UserAuthen, Long> queryNeStatus(UserAuthenPage page) throws DaoException ;
}	