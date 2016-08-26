package com.qtz.ht.system.page;


import com.mall.core.vo.Pager;
import com.qtz.ht.system.vo.HtFloor;
/**
 * <p>Title:HtFloorPage</p>
 * <p>Description:楼层管理表分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-09
 */
public class HtFloorPage extends Pager<HtFloor,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1785397440235521L;
	
	private Long dmId;
	
}