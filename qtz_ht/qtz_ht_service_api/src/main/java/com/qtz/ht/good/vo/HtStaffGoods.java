package com.qtz.ht.good.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtStaffGoods</p>
 * <p>Description:商户商品操作记录VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
public class HtStaffGoods extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1609975852992512L;

		/** 商品名称 */	private String goodName;	/** 商品简称  设计修改，去掉此字段*///	private String simpleName;	/** 商户信息id */	private Long busiId;	/** 商品编码 */	private String goodCode;	/** 品牌ID */	private Long brandId;	/** 供货价   因之前产品设计问题，此字段暂时不要 *///	private Double supplyPrice;	/** 商品原价 *///	private Double costPrice;	/** 商品售价 */	private Double price;	/** 活动价 */	private Double activityPrice;
	/** 商品成本价格 */
	private Double finalPrice;	/** 平台扣点 */	private Double settDiscount;	/** 净重(克为单位) */	private Long netWeight;	/** 毛重(克为单位) */	private Long grossWeight;	/** 包装方式 */	private String packMethod;	/** 货号 */	private String goodsNumber;	/** 条形码 */	private String barCode;	/** 可销售数量 */	private Integer sales;	/** 限量购买 */	private Integer limited;	/** 简短描述 */	private String shortDesc;	/** 审核状态(1：未审核；2：审核中；3：审核通过) */	private Integer auditStatus;	/** 商品状态(1：上架；2：下架；3：强制下架) */	private Integer status;	/** 产品主图 */	private String mainPicture;	/** 是否支持优惠券（1：支持；2：不支持） */	private Integer isCoupon;	/** 上架时间 */	private Long shelvesTime;	/** 创建用户ID */	private Long cruser;	/** 创建时间 */	private Long crtime;
	/** 非数据库字段 ，购买数量*/
	private Integer buyNumber;
	/** 全称 */
	private String fullName;
	/** 手机 */
	private String mobilePhone;
	/** 法人 */
	private String legalPerson;
	
		public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getGoodName() {	    return this.goodName;	}	public void setGoodName(String goodName) {	    this.goodName=goodName;	}//	public String getSimpleName() {//	    return this.simpleName;//	}//	public void setSimpleName(String simpleName) {//	    this.simpleName=simpleName;//	}	public Long getBusiId() {	    return this.busiId;	}	public void setBusiId(Long busiId) {	    this.busiId=busiId;	}	public String getGoodCode() {	    return this.goodCode;	}	public void setGoodCode(String goodCode) {	    this.goodCode=goodCode;	}	public Long getBrandId() {	    return this.brandId;	}	public void setBrandId(Long brandId) {	    this.brandId=brandId;	}//	public Double getSupplyPrice() {//	    return this.supplyPrice;//	}//	public void setSupplyPrice(Double supplyPrice) {//	    this.supplyPrice=supplyPrice;//	}//	public Double getCostPrice() {//	    return this.costPrice;//	}//	public void setCostPrice(Double costPrice) {//	    this.costPrice=costPrice;//	}	public Double getPrice() {	    return this.price;	}	public void setPrice(Double price) {	    this.price=price;	}	public Double getSettDiscount() {	    return this.settDiscount;	}	public void setSettDiscount(Double settDiscount) {	    this.settDiscount=settDiscount;	}	public Long getNetWeight() {	    return this.netWeight;	}	public void setNetWeight(Long netWeight) {	    this.netWeight=netWeight;	}	public Long getGrossWeight() {	    return this.grossWeight;	}	public void setGrossWeight(Long grossWeight) {	    this.grossWeight=grossWeight;	}	public String getPackMethod() {	    return this.packMethod;	}	public void setPackMethod(String packMethod) {	    this.packMethod=packMethod;	}	public String getGoodsNumber() {	    return this.goodsNumber;	}	public void setGoodsNumber(String goodsNumber) {	    this.goodsNumber=goodsNumber;	}	public String getBarCode() {	    return this.barCode;	}	public void setBarCode(String barCode) {	    this.barCode=barCode;	}	public Integer getSales() {	    return this.sales;	}	public void setSales(Integer sales) {	    this.sales=sales;	}	public Integer getLimited() {	    return this.limited;	}	public void setLimited(Integer limited) {	    this.limited=limited;	}	public String getShortDesc() {	    return this.shortDesc;	}	public void setShortDesc(String shortDesc) {	    this.shortDesc=shortDesc;	}	public Integer getAuditStatus() {	    return this.auditStatus;	}	public void setAuditStatus(Integer auditStatus) {	    this.auditStatus=auditStatus;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public String getMainPicture() {	    return this.mainPicture;	}	public void setMainPicture(String mainPicture) {	    this.mainPicture=mainPicture;	}	public Integer getIsCoupon() {	    return this.isCoupon;	}	public void setIsCoupon(Integer isCoupon) {	    this.isCoupon=isCoupon;	}	public Long getShelvesTime() {	    return this.shelvesTime;	}	public void setShelvesTime(Long shelvesTime) {	    this.shelvesTime=shelvesTime;	}	public Long getCruser() {	    return this.cruser;	}	public void setCruser(Long cruser) {	    this.cruser=cruser;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	
	public Integer getBuyNumber() {
		return buyNumber;
	}
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Double getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(Double activityPrice) {
		this.activityPrice = activityPrice;
	}
}