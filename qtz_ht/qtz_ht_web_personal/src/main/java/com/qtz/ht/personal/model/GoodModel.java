package com.qtz.ht.personal.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class GoodModel implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 5110702672695741181L;
	@Min(value=0,message="不能小于0")
	@DecimalMax(value="1000000000000000000",message="主键过大")
	private Long dmId;
	/** 商品名称*/
	@NotBlank(message="商品名称不能为空")
	@Length(min=2,max=60,message="商品名称2-60之间")
	private String goodName;
	/** 商品简称 */
	@NotBlank(message="商品简称不能为空")
	@Length(min=2,max=20,message="商品简称2-20之间")
	private String simpleName;
	/** 商品编码 */
	@NotBlank(message="商品编码不能为空")
	@Length(min=2,max=20,message="商品编码2-20之间")
	private String goodCode;
	/** 供货价 */
	@NotNull(message="供货价不能为空")
	@DecimalMax(value="99999999.99",message="供货价最大99999999")
	@DecimalMin(value="0",message="供货价最小0")
	private Double supplyPrice;
	/** 商品原价 */
	@NotNull(message="商品原价不能为空")
	@DecimalMax(value="99999999.99",message="商品原价最大99999999")
	@DecimalMin(value="0",message="商品原价最小0")
	private Double costPrice;
	/** 商品售价 */
	@NotNull(message="商品售价不能为空")
	@DecimalMax(value="99999999.99",message="商品售价最大99999999")
	@DecimalMin(value="0",message="商品售价最小0")
	private Double price;
	/** 平台扣点 */
	@NotNull(message="平台扣点不能为空")
	@DecimalMax(value="100",message="平台扣点最大100")
	@DecimalMin(value="0",message="商品售价最小0")
	private Double settDiscount;
	/** 商品相册 */
	@NotEmpty(message="商品相册不能为空")
	private List<String> albums;
	/** 商品详情 */
	@Length(min=1,max=8000, message="商品详情1-8000之间")
	@NotBlank(message="商品详情不能为空")
	private String goodDesc;
	
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public String getGoodCode() {
		return goodCode;
	}
	public void setGoodCode(String goodCode) {
		this.goodCode = goodCode;
	}
	public Double getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(Double supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSettDiscount() {
		return settDiscount;
	}
	public void setSettDiscount(Double settDiscount) {
		this.settDiscount = settDiscount;
	}
	public List<String> getAlbums() {
		return albums;
	}
	public void setAlbums(List<String> albums) {
		this.albums = albums;
	}
	public String getGoodDesc() {
		return goodDesc;
	}
	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}
}
