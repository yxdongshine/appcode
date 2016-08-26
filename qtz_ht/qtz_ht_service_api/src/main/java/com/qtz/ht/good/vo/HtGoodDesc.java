package com.qtz.ht.good.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtGoodDesc</p>
 * <p>Description:商户商品祥情VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-02-16
 */
public class HtGoodDesc extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1537820895168512L;

		/** 商品祥情 */	private String goodDesc;
		public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getGoodDesc() {	    return this.goodDesc;	}	public void setGoodDesc(String goodDesc) {	    this.goodDesc=goodDesc;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}