package com.qtz.ht.good.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.good.vo.HtGoodLog;
/**
 * <p>Title:HtGoodLogPage</p>
 * <p>Description:商户商品操作记录分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
public class HtGoodLogPage extends Pager<HtGoodLog,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1609857969014785L;
	
	private Long dmId;
	
}