package com.qtz.ht.good.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.good.dao.HtGoodsAlbumDao;
import com.qtz.ht.good.vo.HtGoodsAlbum;
/**
 * <p>Title:HtGoodsAlbumDaoImpl</p>
 * <p>Description:商户商品相册DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-02-15
 */
@Repository("htGoodsAlbumDaoImpl")
public class HtGoodsAlbumDaoImpl extends MyBaitsDaoImpl<HtGoodsAlbum,Long> implements HtGoodsAlbumDao {
	/**MYBatis命名空间名*/
	private static String preName = HtGoodsAlbumDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}