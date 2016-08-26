package com.qtz.ht.good.page;


import com.mall.core.vo.Pager;
import com.qtz.ht.good.vo.HtGoodCateAsso;
/**
 * <p>Title:HtGoodCateAssoPage</p>
 * <p>Description:商家商品与分类关联分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-10
 */
public class HtGoodCateAssoPage extends Pager<HtGoodCateAsso,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1786832283420673L;
	
	private Long dmId;
		/** 商户信息id */	private Long busiId;	/** 商品ID */	private Long goodId;	/** 一级分类ID */	private Long oneCate;	/** 二级分类ID */	private Long twoCate;	/** 三级分类ID */	private Long threeCate;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getBusiId() {	    return this.busiId;	}	public void setBusiId(Long busiId) {	    this.busiId=busiId;	}	public Long getGoodId() {	    return this.goodId;	}	public void setGoodId(Long goodId) {	    this.goodId=goodId;	}	public Long getOneCate() {	    return this.oneCate;	}	public void setOneCate(Long oneCate) {	    this.oneCate=oneCate;	}	public Long getTwoCate() {	    return this.twoCate;	}	public void setTwoCate(Long twoCate) {	    this.twoCate=twoCate;	}	public Long getThreeCate() {	    return this.threeCate;	}	public void setThreeCate(Long threeCate) {	    this.threeCate=threeCate;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "busiId:" + getBusiId() +"," + "goodId:" + getGoodId() +"," + "oneCate:" + getOneCate() +"," + "twoCate:" + getTwoCate() +"," + "threeCate:" + getThreeCate() +"]";	}
}