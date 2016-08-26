package com.qtz_ht_timer.enums;

/**
 * 用户类型
 * 
 * <p>
 * Title:GoodsCategory
 * </p>
 * <p>
 * Description:(类描述)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016年3月15日
 */
public enum UserType {

	/**
	 * 代理商
	 */
	PROXY(2),
	/**
	 * 平台管理员
	 */
	PPUSER(4);


	private int value;

	private UserType(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
	
	/**
	 * <p>检查传入的值是否在枚举类型中</p>
	 * @param typeValue
	 * @return
	 */
	//start luoshun 20160316
	public static Boolean existTypeValue(int userType) {
		
		Boolean flag = false;
		
		for (UserType obj : UserType.values()) {
			if (userType == obj.value()) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	//end luoshun 20160316
}
