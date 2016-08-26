package com.qtz.ht.report.good.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.report.good.dao.GoodDao;
import com.qtz.ht.report.good.model.GoodStockModel;
import com.qtz.ht.report.good.service.GoodService;

/**
 * <p>Title:GoodServiceImpl</p>
 * <p>Description:(商品导入导出service实现类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月7日
 */
@Service
public class GoodServiceImpl implements GoodService{
	
	@Autowired
    private GoodDao dao;
	
	@Override
	public List<GoodStockModel> findListGoods(Long busiId, Integer status, Integer auditStatus) throws ServiceException {
		try {
			return dao.findListGoods(busiId, status, auditStatus);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}
}
