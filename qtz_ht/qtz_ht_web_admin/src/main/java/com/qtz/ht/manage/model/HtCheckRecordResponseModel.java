
package com.qtz.ht.manage.model;

import com.qtz.ht.wallet.vo.HtCheckRecord;

/** 
 * ClassName:HtCheckRecordResponseModel <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年6月23日 下午5:22:19 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class HtCheckRecordResponseModel extends HtCheckRecord{

	/****/  
	private static final long serialVersionUID = 1L;
	/** 全称 */
	private String fullName;
	/** 手机 */
	private String mobilePhone;
	/**
	 * 操作人名称
	 */
	private String corPersonName;
	
	public String getCorPersonName() {
		return corPersonName;
	}
	public void setCorPersonName(String corPersonName) {
		this.corPersonName = corPersonName;
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
	
	
}
  