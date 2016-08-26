package com.qtz.ht.system.page;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.system.vo.HtCategory;

@SuppressWarnings("serial")
public class HtCategoryPage extends Pager<HtCategory,Long>{
	
	public static String HT_MARK= "TWO_SEA_AMOY";
	private Long dmId;
	/**栏目图片 */
	private String sysHeadImg;
	/**标签分类 */
	private String mark;
	
	/**楼层数量*/
	private Integer floorNumber;
	/**父ID*/
	private Long parentID;
	/**排序*/
	private int order;
	/**状态0为上架 1为下架*/
	private int status;
	/**楼层是否显示 1：显示；2：不显示*/
	private Integer show;
	
//	@Override
//	public String getDBName() {
//		return "category";
//	}
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Integer getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}
	public String getSysHeadImg() {
		return sysHeadImg;
	}
	public void setSysHeadImg(String sysHeadImg) {
		this.sysHeadImg = sysHeadImg;
	}
	
	
	public Integer getShow() {
		return show;
	}
	public void setShow(Integer show) {
		this.show = show;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getParentID() {
		return parentID;
	}
	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
