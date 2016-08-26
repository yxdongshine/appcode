package com.qtz.ht.enums;

/**
 * <p>Title:OperationActionEnum</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月7日
 */
public enum OperationActionEnum {

	add(1, "添加"),edit(2, "编辑"), delete(3, "删除");

	private OperationActionEnum(int value, String text) {
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