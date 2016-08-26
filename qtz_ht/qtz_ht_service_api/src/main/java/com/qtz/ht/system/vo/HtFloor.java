package com.qtz.ht.system.vo;
import java.util.List;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtFloor</p>
 * <p>Description:楼层管理表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-09
 */
public class HtFloor extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1785397440235520L;

		/** 楼层名称 */	private String floorName;	/** 楼层主图 */	private String mainPicture;	/** 跳转链接 */	private String linkUrl;	/** 是否专题活动 */	private Integer isSpecial;
	/**楼层商品关联*/
	private List<HtFloorGoods> floorGoods;	/** 商品数量 */	private Integer goodNumber;	/** 序号 */	private Integer sortNumber;	/** 分类id */	private Long categoryId;	/** 是否有效 */	private Integer isValid;	/** 创建人ID */	private Long crUserId;	/** 创建时间 */	private Long crtime;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getFloorName() {	    return this.floorName;	}	public void setFloorName(String floorName) {	    this.floorName=floorName;	}	public String getMainPicture() {	    return this.mainPicture;	}	public void setMainPicture(String mainPicture) {	    this.mainPicture=mainPicture;	}	public String getLinkUrl() {	    return this.linkUrl;	}	public void setLinkUrl(String linkUrl) {	    this.linkUrl=linkUrl;	}	public Integer getIsSpecial() {	    return this.isSpecial;	}	public void setIsSpecial(Integer isSpecial) {	    this.isSpecial=isSpecial;	}	public Integer getGoodNumber() {	    return this.goodNumber;	}	public void setGoodNumber(Integer goodNumber) {	    this.goodNumber=goodNumber;	}	public Integer getSortNumber() {	    return this.sortNumber;	}	public void setSortNumber(Integer sortNumber) {	    this.sortNumber=sortNumber;	}	public Long getCategoryId() {	    return this.categoryId;	}	public void setCategoryId(Long categoryId) {	    this.categoryId=categoryId;	}	public Integer getIsValid() {	    return this.isValid;	}	public void setIsValid(Integer isValid) {	    this.isValid=isValid;	}	public Long getCrUserId() {	    return this.crUserId;	}	public void setCrUserId(Long crUserId) {	    this.crUserId=crUserId;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public List<HtFloorGoods> getFloorGoods() {
		return floorGoods;
	}
	public void setFloorGoods(List<HtFloorGoods> floorGoods) {
		this.floorGoods = floorGoods;
	}
	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "floorName:" + getFloorName() +"," + "mainPicture:" + getMainPicture() +"," + "linkUrl:" + getLinkUrl() +"," + "isSpecial:" + getIsSpecial() +"," + "goodNumber:" + getGoodNumber() +"," + "sortNumber:" + getSortNumber() +"," + "categoryId:" + getCategoryId() +"," + "isValid:" + getIsValid() +"," + "crUserId:" + getCrUserId() +"," + "crtime:" + getCrtime() +","+"floorGoods:"+getFloorGoods()+"]";	}
}