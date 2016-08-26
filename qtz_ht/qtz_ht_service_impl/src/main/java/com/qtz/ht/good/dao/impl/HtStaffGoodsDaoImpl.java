package com.qtz.ht.good.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.dao.HtStaffGoodsDao;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.vo.HtStaffGoods;
/**
 * <p>Title:HtStaffGoodsDaoImpl</p>
 * <p>Description:商户商品DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
@Repository("htStaffGoodsDaoImpl")
public class HtStaffGoodsDaoImpl extends MyBaitsDaoImpl<HtStaffGoods,Long> implements HtStaffGoodsDao {
	/**MYBatis命名空间名*/
	private static String preName = HtStaffGoodsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<HtStaffGoods> findSpecifyFieldList(HtStaffGoods obj) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().selectList(getPreName()+".findSpecifyFieldList", obj);
	}
	
	@Override
	public List<HtStaffGoods> getPanicBuyingGoodsList(HtStaffGoods obj) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().selectList(getPreName()+".getPanicBuyingGoodsList", obj);
	}
	
	@Override
	public List<HtStaffGoods> findListByGoodsId(Set<Long> idS) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().selectList(getPreName()+".findListByGoodsId", new ArrayList<>(idS));
	}
	
	@Override
	public void modstock(Long dmId, Integer buyNumber) throws DaoException {
		HtStaffGoods vo = new HtStaffGoods();
		vo.setDmId(dmId);
		vo.setSales(buyNumber);
		getMyBaitsTemplate().getSqlSession().update(getPreName()+".modstock", vo);
	}
	
	@Override
	public void addstock(Long dmId, Integer buyNumber) throws DaoException {
		HtStaffGoods vo = new HtStaffGoods();
		vo.setDmId(dmId);
		vo.setSales(buyNumber);
		getMyBaitsTemplate().getSqlSession().update(getPreName()+".addstock", vo);		
	}
	
	@Override
	public List<HtStaffGoods> findShelvesTop5List() throws DaoException {
		// TODO Auto-generated method stub
		return getMyBaitsTemplate().getSqlSession().selectList(getPreName()+".findShelvesTop5List");
	}

	@Override
	public List<HtStaffGoods> findListByGoodsIdAndCrtime(Set<Long> idS) throws DaoException {
		// TODO Auto-generated method stub
		return getMyBaitsTemplate().getSqlSession().selectList(getPreName()+".findListByGoodsIdAndCrtime", new ArrayList<>(idS));

	}
	
	public Pager<HtStaffGoods, Long> unclassifiedGoods(HtStaffGoodsPage page) throws DaoException {
		Pager<HtStaffGoods, Long> pager=new Pager<HtStaffGoods, Long>();
		List<HtStaffGoods> list=getMyBaitsTemplate().getSqlSession().selectList(getPreName()+".queryUnclassifiedGoods",page); 
		int count=getMyBaitsTemplate().getSqlSession().selectOne(getPreName()+".queryUnclassifiedCount");
		pager.setList(list);
		pager.setRowCount(count);
		return pager;
	}
}