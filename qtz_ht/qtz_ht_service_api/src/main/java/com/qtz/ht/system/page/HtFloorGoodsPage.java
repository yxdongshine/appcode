package com.qtz.ht.system.page;


import com.mall.core.vo.Pager;
import com.qtz.ht.system.vo.HtFloorGoods;
/**
 * <p>Title:HtFloorGoodsPage</p>
 * <p>Description:楼层关联商品分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
 */
public class HtFloorGoodsPage extends Pager<HtFloorGoods,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1783998156261377L;
	
	private Long dmId;
	
}