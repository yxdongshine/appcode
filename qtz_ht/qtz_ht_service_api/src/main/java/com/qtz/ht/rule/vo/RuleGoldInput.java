package com.qtz.ht.rule.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RuleGoldInput {
	
	private Long dmId;
	/** 规则code */
	private String code;
	/** 规则名字 */
	private String name;
	/** 比例 */
	private String perce;
	/** 排序 */
	private Integer sort;
	
	/** 入参类型类型 */
	private String _state;
	
	private int _uid;
	
	private String prece;
	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerce() {
		return perce;
	}

	public void setPerce(String perce) {
		this.perce = perce;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public int get_uid() {
		return _uid;
	}

	public void set_uid(int _uid) {
		this._uid = _uid;
	}

	public String getPrece() {
		return prece;
	}

	public void setPrece(String prece) {
		this.prece = prece;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
