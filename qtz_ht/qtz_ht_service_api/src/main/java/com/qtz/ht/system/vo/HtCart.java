package com.qtz.ht.system.vo;

import java.io.Serializable;

import com.mall.core.vo.VO;
import com.qtz.ht.good.vo.HtStaffGoods;

/**
 * <p>Title:HtCart</p>
 * <p>Description:(购物车实体类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 刘宝平 - liubaoping
 * @version v1.0 2016年8月12日
 */
public class HtCart extends VO<Long> implements Serializable {

	
	private static final long serialVersionUID = 8432746664705826973L;
	
	/**用户Id*/
	private Long userId;
	/**商品Id*/
	private Long goodId;
	
	private HtStaffGoods htStaffGoods;
	/**商品数量*/
	private Integer goodNumber;
	/**修改时间*/
	private Long changeTime;
	public Long getDmId() {
	    return this.dmId;
	}
	public void setDmId(Long dmId) {
	    this.dmId=dmId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getGoodId() {
		return goodId;
	}
	public void setGoodId(Long goodId) {
		this.goodId = goodId;
	}
	
	public Integer getGoodNumber() {
		return goodNumber;
	}
	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}
	public Long getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Long changeTime) {
		this.changeTime = changeTime;
	}
	public HtStaffGoods getHtStaffGoods() {
		return htStaffGoods;
	}
	public void setHtStaffGoods(HtStaffGoods htStaffGoods) {
		this.htStaffGoods = htStaffGoods;
	}
	
	
}
