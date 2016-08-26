package com.qtz.ht.util;

import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * <p>Title:ExceptionModel</p>
 * <p>Description:(异常模型类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月18日
 */
public class ExceptionModel implements SerializeFilter {

	private int code;
	private String msg;
	
	
	public ExceptionModel() {
		super();
	}
	
	public ExceptionModel(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
