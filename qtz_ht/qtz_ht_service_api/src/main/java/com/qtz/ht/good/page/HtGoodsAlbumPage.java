package com.qtz.ht.good.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.good.vo.HtGoodsAlbum;
/**
 * <p>Title:HtGoodsAlbumPage</p>
 * <p>Description:商户商品相册分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-02-15
 */
public class HtGoodsAlbumPage extends Pager<HtGoodsAlbum,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1536399215691777L;

	private Long dmId;	/** 商品ID */	private Long goodId;	/**  */	private String photoAddr;	/** 序号 */	private Integer sort;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getGoodId() {	    return this.goodId;	}	public void setGoodId(Long goodId) {	    this.goodId=goodId;	}	public String getPhotoAddr() {	    return this.photoAddr;	}	public void setPhotoAddr(String photoAddr) {	    this.photoAddr=photoAddr;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}}