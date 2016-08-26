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
		/** 楼层id */	private Long linkUrl;	/** 商品id */	private Long goodId;	/** 序号 */	private Integer sortNumber;	/** 首页显示 */	private Integer isIbdexShow;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getLinkUrl() {	    return this.linkUrl;	}	public void setLinkUrl(Long linkUrl) {	    this.linkUrl=linkUrl;	}	public Long getGoodId() {	    return this.goodId;	}	public void setGoodId(Long goodId) {	    this.goodId=goodId;	}	public Integer getSortNumber() {	    return this.sortNumber;	}	public void setSortNumber(Integer sortNumber) {	    this.sortNumber=sortNumber;	}	public Integer getIsIbdexShow() {	    return this.isIbdexShow;	}	public void setIsIbdexShow(Integer isIbdexShow) {	    this.isIbdexShow=isIbdexShow;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "linkUrl:" + getLinkUrl() +"," + "goodId:" + getGoodId() +"," + "sortNumber:" + getSortNumber() +"," + "isIbdexShow:" + getIsIbdexShow() +"]";	}
}