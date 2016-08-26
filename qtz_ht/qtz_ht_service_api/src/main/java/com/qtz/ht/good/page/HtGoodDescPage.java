package com.qtz.ht.good.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.good.vo.HtGoodDesc;
/**
 * <p>Title:HtGoodDescPage</p>
 * <p>Description:商户商品祥情分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-02-15
 */
public class HtGoodDescPage extends Pager<HtGoodDesc,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1536399218214913L;

	private Long dmId;
	
}