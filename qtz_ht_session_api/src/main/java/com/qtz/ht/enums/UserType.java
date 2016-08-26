package com.qtz.ht.enums;

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
	 * 1 个人账户
	 */
	PERSON(1),
	/**
	 * 商家账户
	 */
	BUSINESS(2),
	/**
	 * 代理商
	 */
	PROXY(3),
	/**
	 * 平台管理员
	 */
	PPUSER(4),
	/**
	 * 后台管理账户
	 */
	SYSTEMUSER(5);

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
