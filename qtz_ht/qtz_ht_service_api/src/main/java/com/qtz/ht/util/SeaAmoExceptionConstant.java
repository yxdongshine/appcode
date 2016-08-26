package com.qtz.ht.util;

/**
 * <p>Title:SeaAmoExceptionConstant</p>
 * <p>Description:(异常常量类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月18日
 */
public class SeaAmoExceptionConstant {
	
	public static final ExceptionModel IS_NULL = new ExceptionModel(10000,"入参错误，参数为空");
	
	public static final ExceptionModel WALLET_IS_NULL = new ExceptionModel(10001,"用户钱包为空");
	
	public static final ExceptionModel GOOD_IS_NULL = new ExceptionModel(10002,"商品不存在");
	
	public static final ExceptionModel GOOD_STOCK_ENOUGH = new ExceptionModel(10003,"商品已经售罄");
	
	public static final ExceptionModel SUBSIDY_AMOUNT_NOT_ENOUGH  = new ExceptionModel(10004,"海淘补贴金额不足");
}
