package com.qtz.ht.personal.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import com.mall.core.common.RegexUtil;

public class UserSaveModel implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 2762671402339707211L;
	@Min(value=0,message="不能小于0")
	@Max(value=1000000000000000000l,message="主键过大")
	private Long dmId;
	/** 全称 */
	@NotBlank(message="公司名称不能为空")
	@Length(min=5,max=50,message="公司名称5-50之间")
	private String fullName;
	/** 手机 */
	@NotBlank(message="手机不能为空")
	@Pattern(regexp = RegexUtil.MOBILE,message = "手机号格式错误")
	private String mobilePhone;
	/**  */
	@NotBlank(message="密码不能为空")
	private String password;
	/** 经营特色 */
	@NotBlank(message="经营特色不能为空")
	private String manaChar;
	/** 结算折扣 */
	@NotNull(message="结算折扣不能为空")
	@Max(value=10,message="结算折扣最大10")
	@Min(value=0,message="结算折扣最小0")
	private Double settDiscount;
	/** 商户地址省 */
	@NotNull(message="商户地址省不能为空")
	private Long mercProvince;
	/** 商户地址市 */
	@NotNull(message="商户地址市不能为空")
	private Long mercCity;
	/** 商户信息县/区 */
	@NotNull(message="商户信息县/区不能为空")
	private Long mercCounty;
	/** 商户信息镇 */
	private Long mercTown;
	/** 商户地址 */
	@NotBlank(message="商户详细地址不能为空")
	@Length(min=1,max=50, message="公司详细地址1-50之间")
	private String mercAddr;
	/** 成立时间 */
	@NotBlank(message="成立时间不能为空")
	private String setUpTime;
	/** 姓名 */
	@NotBlank(message="供应商姓名不能为空")
	private String mercName;
	/** 银行卡号 */
	@NotBlank(message="银行卡号不能为空")
	private java.lang.String cardnum;
	/** 开户行名称 */
	@NotBlank(message="开户行名称不能为空")
	private java.lang.String bankname;
	/** 法人 */
	@NotBlank(message="法人不能为空")
	private String legalPerson;
	/** 法人身份证号 */
	@NotBlank(message="法人身份证号不能为空")
	@Pattern(regexp=RegexUtil.IDCARD,message="身份证号码格式错误")
	private String legalIdCard;
	/** 法人地址省 */
	@NotNull(message="法人地址省不能为空")
	private Long legalProvince;
	/** 法人地址市 */
	@NotNull(message="法人地址市不能为空")
	private Long legalCity;
	/** 法人地址县 */
	@NotNull(message="法人地址县不能为空")
	private Long legalCounty;
	/** 法人地址镇 */
	private Long legalTown;
	/** 法人地址祥细 */
	@NotNull(message="法人祥细地址不能为空")
	@Length(min=1,max=50, message="详细地址1-50之间")
	private String legalAddr;
	/** 法人身份证正面 */
	@NotBlank(message="法人身份证正面不能为空")
	@Length(min=1,max=100, message="身份证正面地址1-100之间")
	@URL(message="法人身份证正面地址错误")
	private String idcardBefore;
	/** 法人身份证反面 */
	@NotBlank(message="法人身份证反面不能为空")
	@Length(min=1,max=100, message="身份证反面地址1-100之间")
	@URL(message="法人身份证反面地址错误")
	private String idcardAfter;
	/** 营业执照 */
	@NotBlank(message="营业执照不能为空")
	@Length(min=1,max=100, message="营业执照地址1-100之间")
	@URL(message="营业执照地址错误")
	private String busiLicense;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getManaChar() {
		return manaChar;
	}
	public void setManaChar(String manaChar) {
		this.manaChar = manaChar;
	}
	public Double getSettDiscount() {
		return settDiscount;
	}
	public void setSettDiscount(Double settDiscount) {
		this.settDiscount = settDiscount;
	}
	public Long getMercProvince() {
		return mercProvince;
	}
	public void setMercProvince(Long mercProvince) {
		this.mercProvince = mercProvince;
	}
	public Long getMercCity() {
		return mercCity;
	}
	public void setMercCity(Long mercCity) {
		this.mercCity = mercCity;
	}
	public Long getMercCounty() {
		return mercCounty;
	}
	public void setMercCounty(Long mercCounty) {
		this.mercCounty = mercCounty;
	}
	public Long getMercTown() {
		return mercTown;
	}
	public void setMercTown(Long mercTown) {
		this.mercTown = mercTown;
	}
	public String getMercAddr() {
		return mercAddr;
	}
	public void setMercAddr(String mercAddr) {
		this.mercAddr = mercAddr;
	}
	public String getSetUpTime() {
		return setUpTime;
	}
	public void setSetUpTime(String setUpTime) {
		this.setUpTime = setUpTime;
	}
	public String getMercName() {
		return mercName;
	}
	public void setMercName(String mercName) {
		this.mercName = mercName;
	}
	public java.lang.String getCardnum() {
		return cardnum;
	}
	public void setCardnum(java.lang.String cardnum) {
		this.cardnum = cardnum;
	}
	public java.lang.String getBankname() {
		return bankname;
	}
	public void setBankname(java.lang.String bankname) {
		this.bankname = bankname;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalIdCard() {
		return legalIdCard;
	}
	public void setLegalIdCard(String legalIdCard) {
		this.legalIdCard = legalIdCard;
	}
	public Long getLegalProvince() {
		return legalProvince;
	}
	public void setLegalProvince(Long legalProvince) {
		this.legalProvince = legalProvince;
	}
	public Long getLegalCity() {
		return legalCity;
	}
	public void setLegalCity(Long legalCity) {
		this.legalCity = legalCity;
	}
	public Long getLegalCounty() {
		return legalCounty;
	}
	public void setLegalCounty(Long legalCounty) {
		this.legalCounty = legalCounty;
	}
	public Long getLegalTown() {
		return legalTown;
	}
	public void setLegalTown(Long legalTown) {
		this.legalTown = legalTown;
	}
	public String getLegalAddr() {
		return legalAddr;
	}
	public void setLegalAddr(String legalAddr) {
		this.legalAddr = legalAddr;
	}
	public String getIdcardBefore() {
		return idcardBefore;
	}
	public void setIdcardBefore(String idcardBefore) {
		this.idcardBefore = idcardBefore;
	}
	public String getIdcardAfter() {
		return idcardAfter;
	}
	public void setIdcardAfter(String idcardAfter) {
		this.idcardAfter = idcardAfter;
	}
	public String getBusiLicense() {
		return busiLicense;
	}
	public void setBusiLicense(String busiLicense) {
		this.busiLicense = busiLicense;
	}
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
}
