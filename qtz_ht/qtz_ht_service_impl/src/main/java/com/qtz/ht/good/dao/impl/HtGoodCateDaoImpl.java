package com.qtz.ht.good.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.good.dao.HtGoodCateDao;
import com.qtz.ht.good.vo.HtGoodCate;
/**
 * <p>Title:HtGoodCateDaoImpl</p>
 * <p>Description:商户商品分类DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-11
 */
@Repository("htGoodCateDaoImpl")
public class HtGoodCateDaoImpl extends MyBaitsDaoImpl<HtGoodCate,Long> implements HtGoodCateDao {
	/**MYBatis命名空间名*/
	private static String preName = HtGoodCateDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}