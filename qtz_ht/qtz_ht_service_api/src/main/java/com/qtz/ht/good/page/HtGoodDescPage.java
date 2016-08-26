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

	private Long dmId;	/** 商品祥情 */	private String goodDesc;
		public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getGoodDesc() {	    return this.goodDesc;	}	public void setGoodDesc(String goodDesc) {	    this.goodDesc=goodDesc;	}	public String toString() {		return ToStringBuilder.reflectionToString(this);	}
}