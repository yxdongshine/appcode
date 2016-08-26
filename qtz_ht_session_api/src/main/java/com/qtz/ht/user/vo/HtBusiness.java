package com.qtz.ht.user.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtBusiness</p>
 * <p>Description:商户信息表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-08
 */
public class HtBusiness extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1611442937595904L;

	public static String DMID = "dmId";
	public static String SIMPLENAME = "simpleName";
	public static String FULLNAME = "fullName";
	public static String MOBILEPHONE = "mobilePhone";
	public static String LEGALPERSON = "legalPerson";
	public static String LEGAL_PROVINCE_TEXT = "legalProvinceText";/** 法人地址省 */
	public static String LEGAL_CITY_TEXT = "legalCityText";/** 法人地址市 */
	public static String LEGAL_COUNTY_TEXT = "legalCountyText";/** 法人地址县 */
	public static String LEGAL_TOWN_TEXT = "legalTownText";/** 法人地址镇 */
	public static String MERC_PROVINCE_TEXT = "mercProvinceText";/** 商户地址省 */
	public static String MERC_CITY_TEXT = "mercCityText";/** 商户地址市 */
	public static String MERC_COUNTY_TEXT = "mercCountyText";/** 商户信息县/区 */
	public static String MERC_TOWN_TEXT = "mercTownText";/** 商户信息镇 */	/** 供应商简称 */	private String simpleName;	/** 全称 */	private String fullName;	/** 手机 */	private String mobilePhone;	/** 供应商头像 */	private String trademark;	/** 结算折扣 */	private Double settDiscount;	/** 对账周期（单位 天） */	private Integer recoCycle;	/** 经营特色 */	private String manaChar;	/** 商户地址省 */	private Long mercProvince;	/** 商户地址市 */	private Long mercCity;	/** 商户信息县/区 */	private Long mercCounty;	/** 商户信息镇 */	private Long mercTown;	/** 商户地址 */	private String mercAddr;	/** 成立时间 */	private String setUpTime;	/** 姓名 */	private String mercName;	/** 法人 */	private String legalPerson;	/** 法人身份证号 */	private String legalIdCard;	/** 法人地址省 */	private Long legalProvince;	/** 法人地址市 */	private Long legalCity;	/** 法人地址县 */	private Long legalCounty;	/** 法人地址镇 */	private Long legalTown;	/** 法人地址祥细 */	private String legalAddr;	/** 法人身份证正面 */	private String idcardBefore;	/** 法人身份证反面 */	private String idcardAfter;	/** 营业执照 */	private String busiLicense;	/** 是否屏蔽(1：屏蔽；2：不屏蔽) */	private Integer isShield;	/** 是否冻结(1：冻结；2：不冻结) */	private Integer isFrozen;
	/** 注册时间 */
	private Long crtime;
	/** 非数据库字段开始时间 */
	private Long startTime;
	/** 非数据库字段结束时间 */
	private Long endTime;
	/** 非数据库字段最后登录时间 */
	private Long lastLogin;
	
	public Long getDmId() {
	    return this.dmId;
	}
	public void setDmId(Long dmId) {
	    this.dmId=dmId;
	}
	public String getSimpleName() {
	    return this.simpleName;
	}
	public void setSimpleName(String simpleName) {
	    this.simpleName=simpleName;
	}
	public String getFullName() {
	    return this.fullName;
	}
	public void setFullName(String fullName) {
	    this.fullName=fullName;
	}
	public String getMobilePhone() {
	    return this.mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
	    this.mobilePhone=mobilePhone;
	}
	public String getTrademark() {
	    return this.trademark;
	}
	public void setTrademark(String trademark) {
	    this.trademark=trademark;
	}
	public Double getSettDiscount() {
	    return this.settDiscount;
	}
	public void setSettDiscount(Double settDiscount) {
	    this.settDiscount=settDiscount;
	}
	public Integer getRecoCycle() {
	    return this.recoCycle;
	}
	public void setRecoCycle(Integer recoCycle) {
	    this.recoCycle=recoCycle;
	}
	public String getManaChar() {
	    return this.manaChar;
	}
	public void setManaChar(String manaChar) {
	    this.manaChar=manaChar;
	}
	public Long getMercProvince() {
	    return this.mercProvince;
	}
	public void setMercProvince(Long mercProvince) {
	    this.mercProvince=mercProvince;
	}
	public Long getMercCity() {
	    return this.mercCity;
	}
	public void setMercCity(Long mercCity) {
	    this.mercCity=mercCity;
	}
	public Long getMercCounty() {
	    return this.mercCounty;
	}
	public void setMercCounty(Long mercCounty) {
	    this.mercCounty=mercCounty;
	}
	public Long getMercTown() {
	    return this.mercTown;
	}
	public void setMercTown(Long mercTown) {
	    this.mercTown=mercTown;
	}
	public String getMercAddr() {
	    return this.mercAddr;
	}
	public void setMercAddr(String mercAddr) {
	    this.mercAddr=mercAddr;
	}
	public String getSetUpTime() {
	    return this.setUpTime;
	}
	public void setSetUpTime(String setUpTime) {
	    this.setUpTime=setUpTime;
	}
	public String getMercName() {
	    return this.mercName;
	}
	public void setMercName(String mercName) {
	    this.mercName=mercName;
	}
	public String getLegalPerson() {
	    return this.legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
	    this.legalPerson=legalPerson;
	}
	public String getLegalIdCard() {
	    return this.legalIdCard;
	}
	public void setLegalIdCard(String legalIdCard) {
	    this.legalIdCard=legalIdCard;
	}
	public Long getLegalProvince() {
	    return this.legalProvince;
	}
	public void setLegalProvince(Long legalProvince) {
	    this.legalProvince=legalProvince;
	}
	public Long getLegalCity() {
	    return this.legalCity;
	}
	public void setLegalCity(Long legalCity) {
	    this.legalCity=legalCity;
	}
	public Long getLegalCounty() {
	    return this.legalCounty;
	}
	public void setLegalCounty(Long legalCounty) {
	    this.legalCounty=legalCounty;
	}
	public Long getLegalTown() {
	    return this.legalTown;
	}
	public void setLegalTown(Long legalTown) {
	    this.legalTown=legalTown;
	}
	public String getLegalAddr() {
	    return this.legalAddr;
	}
	public void setLegalAddr(String legalAddr) {
	    this.legalAddr=legalAddr;
	}
	public String getIdcardBefore() {
	    return this.idcardBefore;
	}
	public void setIdcardBefore(String idcardBefore) {
	    this.idcardBefore=idcardBefore;
	}
	public String getIdcardAfter() {
	    return this.idcardAfter;
	}
	public void setIdcardAfter(String idcardAfter) {
	    this.idcardAfter=idcardAfter;
	}
	public String getBusiLicense() {
	    return this.busiLicense;
	}
	public void setBusiLicense(String busiLicense) {
	    this.busiLicense=busiLicense;
	}
	public Integer getIsShield() {
	    return this.isShield;
	}
	public void setIsShield(Integer isShield) {
	    this.isShield=isShield;
	}
	public Integer getIsFrozen() {
	    return this.isFrozen;
	}
	public void setIsFrozen(Integer isFrozen) {
	    this.isFrozen=isFrozen;
	}
	public Long getCrtime() {
	    return this.crtime;
	}
	public void setCrtime(Long crtime) {
	    this.crtime=crtime;
	}

	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Long lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}