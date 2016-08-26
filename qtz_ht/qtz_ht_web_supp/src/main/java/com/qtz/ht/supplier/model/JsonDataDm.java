package com.qtz.ht.supplier.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class JsonDataDm implements java.io.Serializable{
	
	private static final long serialVersionUID = 9070835484874949589L;
	private PagerDm page;
	private List<JSONObject> list;
	private JSONObject jsonObject;
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public PagerDm getPage() {
		return page;
	}
	public void setPage(PagerDm page) {
		this.page = page;
	}
	public List<JSONObject> getList() {
		return list;
	}
	public void setList(List<JSONObject> list) {
		this.list = list;
	}
	
}
