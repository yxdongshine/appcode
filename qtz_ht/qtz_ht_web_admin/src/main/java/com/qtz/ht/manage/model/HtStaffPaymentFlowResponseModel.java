
package com.qtz.ht.manage.model;

import com.qtz.ht.wallet.vo.HtStaffPaymentFlow;

/** 
 * ClassName:HtStaffPaymentFlowResponseModel <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年6月27日 上午11:06:08 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class HtStaffPaymentFlowResponseModel extends HtStaffPaymentFlow{

	/****/  
	private static final long serialVersionUID = 8078180328787581365L;

	/**
	 * 支付方式名称
	 */
	private String paymentTypeName;

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	
	
}
  