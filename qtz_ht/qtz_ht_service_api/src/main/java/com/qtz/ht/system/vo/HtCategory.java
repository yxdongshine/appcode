package com.qtz.ht.system.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.qtz.dm.system.vo.Category;

@SuppressWarnings("serial")
public class HtCategory extends Category{
	
	/** 状态  0 正常 1 屏蔽 */
	
	/**栏目图片 */
	private String sysHeadImg;
	/**标签分类 */
	private String mark;
	
	/**楼层数量*/
	private Integer floorNumber;
	/**楼层是否显示 1：显示；2：不显示*/
	private Integer show;
	/**商品数量*/
	private Integer goodNumber;
	
//	@Override
//	public String getDBName() {
//		return "category";
//	}
	@Override
	public Long getDmId() {
		return dmId;
	}
	@Override
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getShow() {
		return show;
	}
	public void setShow(Integer show) {
		this.show = show;
	}
	public Integer getGoodNumber() {
		return goodNumber;
	}
	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}
}
