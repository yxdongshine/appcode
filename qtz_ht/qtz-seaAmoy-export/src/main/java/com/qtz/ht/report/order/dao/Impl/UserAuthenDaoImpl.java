package com.qtz.ht.report.order.dao.Impl;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MongDmDaoImpl;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.ht.report.order.dao.UserAuthenDao;
import com.qtz.ht.report.order.entity.UserAuthen;
import com.qtz.ht.report.order.model.UserAuthenPage;
/**
 * <p>Title:UserJobDaoImpl</p>
 * <p>Description:职业认证DAO实现类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author lzg - charli
 * @version v1.0 2015-03-14
 * BizDaoImpl
 */
@Repository
public class UserAuthenDaoImpl extends MongDmDaoImpl<UserAuthen,Long> implements UserAuthenDao {
	
	@Override
	protected String getPreName() {
		return "user_authen";
	}

	public Long findCountByStatus(UserAuthen vo) throws DaoException {
		Query query = new Query();
		if (StringUtils.isNotEmpty(vo.getPno())) {
			query.addCriteria(Criteria.where("pno").is(vo.getPno()));
		}
		if (null != vo.getApplyStatus()) {
		}
		if (null != vo.getStatus() ) {
			query.addCriteria(Criteria.where("status").ne(vo.getStatus()));
		}
		return getMongoTemplate().count(query, getPreName());
	}
	
	public Pager<UserAuthen, Long> queryNeStatus(UserAuthenPage page) throws DaoException {
		Query query = new Query();
		try {
			query.addCriteria(Criteria.where("userId").is(page.getUserId()));
			if (null != page.getStatus()) {
				query.addCriteria(Criteria.where("status").ne(page.getStatus()));
			}
			long count = getMongoTemplate().count(query, getPreName());
			query.skip(page.getPageOffset());
			query.limit(page.getPageSize());
			if (null != page.getOrderField() && !"".equals(page.getOrderField())) {
				if (page.getOrderCondition()) {
					query.with(new Sort(Direction.ASC, page.getOrderField()));
				} else {
					query.with(new Sort(Direction.DESC, page.getOrderField()));
				}
			}
			List<UserAuthen> list = (List<UserAuthen>) getMongoTemplate().find(query, UserAuthen.class, getPreName());
			page.setList(list);
			String valueOf = String.valueOf(count);
			page.setRowCount(Integer.valueOf(valueOf));
			return page;
		} catch (IllegalArgumentException e) {
			throw new DaoException();
		} catch (Exception e) {
			throw new DaoException();
		}
	}

	@Override
	public UserAuthen findCountByUserId(Long userId,UserAuthen clazz) throws DaoException {
		Query query =new Query();
		try {
			query.addCriteria(Criteria.where("userId").is(userId));
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} 
		UserAuthen findOne =  getMongoTemplate().findOne(query,clazz.getClass(),getPreName());
		return findOne==null?null:findOne;
	}

}