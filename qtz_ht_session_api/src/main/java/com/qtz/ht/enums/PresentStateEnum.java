package com.qtz.ht.enums;


/**
 * <p>Title:PresentStateEnum</p>
 * <p>Description:(提现状态 (0：表示对账中，1：表示已提现，2：表示驳回，3：表示取消))</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年5月3日
 */
public enum PresentStateEnum {

	APPLY(0, "对账中"), APPLIED(1, "已提现"), APPLYBACK(2, "驳回"), APPLYCANCEL(3, "取消"),RECOVER(4,"追回");
	

	private PresentStateEnum(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	private Integer value;

	private String text;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
