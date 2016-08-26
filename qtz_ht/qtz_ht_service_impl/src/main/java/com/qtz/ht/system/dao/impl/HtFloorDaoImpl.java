package com.qtz.ht.system.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.system.dao.HtFloorDao;
import com.qtz.ht.system.vo.HtFloor;
/**
 * <p>Title:HtFloorDaoImpl</p>
 * <p>Description:楼层管理表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
 */
@Repository("htFloorDaoImpl")
public class HtFloorDaoImpl extends MyBaitsDaoImpl<HtFloor,Long> implements HtFloorDao {
	/**MYBatis命名空间名*/
	private static String preName = HtFloorDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}