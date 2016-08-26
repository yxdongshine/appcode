package com.qtz.ht.enums;


/**
 * <p>Title:CommodityStatusEnum</p>
 * <p>Description:(商品状态枚举)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月25日
 */
public enum CommodityStatusEnum {

	on(1, "上架"),under(2, "下架"), force(3, "强制下架");

	private CommodityStatusEnum(int value, String text) {
		this.value = value;
		this.text = text;
	}

	private int value;

	private String text;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}