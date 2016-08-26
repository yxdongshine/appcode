package com.qtz.ht.report.good.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mall.core.exception.DaoException;
import com.qtz.ht.report.good.dao.GoodDao;
import com.qtz.ht.report.good.model.GoodStockModel;

/**
 * <p>Title:GoodDaoImpl</p>
 * <p>Description:(商品导入导出dao实现类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月7日
 */
@SuppressWarnings("deprecation")
@Repository
public class GoodDaoImpl implements GoodDao{
	 @Autowired
	 private JdbcTemplate htJdbcTemplate;

	@Override
	public List<GoodStockModel> findListGoods(Long busiId, Integer status, Integer auditStatus) throws DaoException {
		if (null == busiId) {
			throw new DaoException("入参错误！ findListGoods("+busiId+" , "+status+" , "+auditStatus+")");
		}
		StringBuffer sbSql = new StringBuffer("select good_code as goodCode,good_name as goodName,final_Price as finalPrice, price, sales as stock from ht_staff_goods where busi_id = ?");
		List<Object> params = new ArrayList<>();
		params.add(busiId);
		if (null != status) {
			sbSql.append(" and `status` = ?");
			params.add(status);
		} 
		if (null != status) {
			sbSql.append(" and audit_status = ?");
			params.add(auditStatus);
		}
		return htJdbcTemplate.query(sbSql.toString(), params.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(GoodStockModel.class));
	}
}
