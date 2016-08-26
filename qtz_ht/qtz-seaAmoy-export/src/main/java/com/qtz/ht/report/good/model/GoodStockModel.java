package com.qtz.ht.report.good.model;

import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * <p>Title:GoodStockModel</p>
 * <p>Description:(商品库存导出模型)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月7日
 */
public class GoodStockModel implements SerializeFilter{

	/** 商品编码 */
	private String goodCode;
	/** 商品名称 */
	private String goodName;
	
	/**
	 * 商品成本价格
	 */
	private Double finalPrice;
	/**
	 * 商品销售价格
	 */
	private Double price;
	/** 商品名库存*/
	private Integer stock;
	public String getGoodCode() {
		return goodCode;
	}
	public void setGoodCode(String goodCode) {
		this.goodCode = goodCode;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
