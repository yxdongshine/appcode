package com.qtz.ht.wallet.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtBankCard</p>
 * <p>Description:商户银行卡信息表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 唐礼军 - 1309025893@qq.com
 * @version v1.0 2016-06-21
 */
public class HtBankCard extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1715845820172288L;

	public static String BANK_PROVINCE_TEXT = "bankProvinceText";/** 银行卡地址省 */
	public static String BANK_CITY_TEXT = "bankCityText";/** 银行卡地址市 */
	/**  */	private Long busiId;	/** 银行卡号 */	private String cardNum;	/** 开户行名称 */	private String bankName;	/** 支行名称 */	private String bankBranch;	/** 开户行所在省 */	private Long accountProvince;	/** 开户行所在市 */	private Long accountCity;	/** 银行账户类型 0 对公 1对私 */	private Integer accountType;	/** 持卡人 */	private String cardholder;	/** 添加人id */	private Long crUser;	/** 添加时间 */	private Long crTime;
		public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getBusiId() {	    return this.busiId;	}	public void setBusiId(Long busiId) {	    this.busiId=busiId;	}	public String getCardNum() {	    return this.cardNum;	}	public void setCardNum(String cardNum) {	    this.cardNum=cardNum;	}	public String getBankName() {	    return this.bankName;	}	public void setBankName(String bankName) {	    this.bankName=bankName;	}	public String getBankBranch() {	    return this.bankBranch;	}	public void setBankBranch(String bankBranch) {	    this.bankBranch=bankBranch;	}	public Long getAccountProvince() {	    return this.accountProvince;	}	public void setAccountProvince(Long accountProvince) {	    this.accountProvince=accountProvince;	}	public Long getAccountCity() {	    return this.accountCity;	}	public void setAccountCity(Long accountCity) {	    this.accountCity=accountCity;	}	public Integer getAccountType() {	    return this.accountType;	}	public void setAccountType(Integer accountType) {	    this.accountType=accountType;	}	public String getCardholder() {	    return this.cardholder;	}	public void setCardholder(String cardholder) {	    this.cardholder=cardholder;	}	public Long getCrUser() {	    return this.crUser;	}	public void setCrUser(Long crUser) {	    this.crUser=crUser;	}	public Long getCrTime() {	    return this.crTime;	}	public void setCrTime(Long crTime) {	    this.crTime=crTime;	}	public String toString() {	    return ToStringBuilder.reflectionToString(this);	}
}