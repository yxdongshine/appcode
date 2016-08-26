package com.qtz.ht.system.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.system.dao.HtFloorGoodsDao;
import com.qtz.ht.system.vo.HtFloorGoods;
/**
 * <p>Title:HtFloorGoodsDaoImpl</p>
 * <p>Description:楼层关联商品DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
 */
@Repository("htFloorGoodsDaoImpl")
public class HtFloorGoodsDaoImpl extends MyBaitsDaoImpl<HtFloorGoods,Long> implements HtFloorGoodsDao {
	/**MYBatis命名空间名*/
	private static String preName = HtFloorGoodsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}