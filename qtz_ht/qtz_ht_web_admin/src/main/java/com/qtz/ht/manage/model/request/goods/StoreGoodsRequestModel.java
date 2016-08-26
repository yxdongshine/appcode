package com.qtz.ht.manage.model.request.goods;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
 * <p>Title:StoreGoodsRequestModel</p>
 * <p>Description:(商品请求模型)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年8月31日
 */
public class StoreGoodsRequestModel {
	
	private Long dmId;
	
	/**
	 * 商品名字
	 */
	private String goodsName;
	/**
	 * 日限量
	 */
	@ApiModelProperty(value="每日限量")
	@DecimalMin(value="0")
	private Integer limited;
	
	/**
	 * 产品分类
	 */
	private Long goodsCategoryId;
	
	/**
	 * 是否支持优惠卷  0 支持 1不支持
	 */
	@ApiModelProperty(value="0支持 1 不支持")
	@DecimalMax(value="1")
	@DecimalMin(value="0")
	private Integer  isCoupon;

	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 图片
	 */
	@NotEmpty
	private String imgs;
	
	@ApiModelProperty(value="0上架 1 下架")
	@DecimalMax(value="1")
	@DecimalMin(value="0")
	private Integer status;
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getLimited() {
		return limited;
	}

	public void setLimited(Integer limited) {
		this.limited = limited;
	}

	

	public Integer getIsCoupon() {
		return isCoupon;
	}

	public void setIsCoupon(Integer isCoupon) {
		this.isCoupon = isCoupon;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Long goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	
	
	

}
