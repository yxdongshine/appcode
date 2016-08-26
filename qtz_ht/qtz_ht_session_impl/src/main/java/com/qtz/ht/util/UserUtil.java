package com.qtz.ht.util;

public class UserUtil {

	public static boolean isAdmin(String account, String password) 
	{
		String adminAccount = SystemUtil.getBaseValueByBaseKey("ADMIN_ACCOUNT");
		String adminPassword = SystemUtil.getBaseValueByBaseKey("ADMIN_PASSWORD");
		return adminAccount.equals(account) && adminPassword.equals(password);
	}
}
