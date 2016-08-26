package com.qtz.ht.system.dao.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MongDmDaoImpl;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.ht.system.dao.CategoryDao;
import com.qtz.ht.system.page.HtCategoryPage;
import com.qtz.ht.system.vo.HtCategory;

@Repository
public class CategoryDaoImpl extends MongDmDaoImpl<HtCategory, Long> implements CategoryDao {

	@Override
	protected String getPreName() {
		return "category";
	}

	@Override
	public HtCategory findVoByMark(String mark) throws DaoException {
		Query query =new Query();
		try {
			query.addCriteria(Criteria.where("mark").is(mark));
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} 
		HtCategory findOne =  getMongoTemplate().findOne(query,HtCategory.class,getPreName());
		return findOne;
	}

	@Override
	public List<HtCategory> findAllByparentId(Long parentId,Integer status,Integer show) throws DaoException {
		Query query =new Query();
		try {
			query.addCriteria(Criteria.where("parentID").is(parentId));
			if(status!= null){ 
				query.addCriteria(Criteria.where("status").is(status));
			}
			if(show!= null){
				query.addCriteria(Criteria.where("show").is(show));
			}else{
				query.addCriteria(Criteria.where("order").ne(-1));
			}
			query.with(new Sort(Direction.ASC, "order"));
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} 
		List<HtCategory> List =  getMongoTemplate().find(query,HtCategory.class,getPreName());
		return List;
	}
	
	public List<HtCategory> queryOrder(Long parentId) throws DaoException{
		Query query =new Query();
		try {
			query.addCriteria(Criteria.where("parentID").is(parentId));
			query.with(new Sort(Direction.DESC, "order"));
			query.limit(1);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} 
		List<HtCategory> List =  getMongoTemplate().find(query,HtCategory.class,getPreName());
		return List;
	}
	@Override
	public Pager<HtCategory, Long> queryPage(HtCategoryPage page) throws DaoException {
		Query query = new Query();
		try {
			query.addCriteria(Criteria.where("parentID").is(page.getParentID()));
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
			List<HtCategory> list = (List<HtCategory>) getMongoTemplate().find(query, HtCategory.class, getPreName());
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
	public void delete(Long dmId)throws DaoException{
		Query query =new Query();
		try {
			query.addCriteria(Criteria.where("dmId").is(dmId));
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} 
		getMongoTemplate().remove(query, getPreName());
	}
	
}
