package com.qtz_ht_timer.enums;


/**
 * <p>Title:ExeResultEnum</p>
 * <p>Description:(任务执行结果枚举)</p>
* <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2015-7-23
 */
public enum ExeResultEnum {
	
	SUCCESS(1,"成功"),
	FAIL(2,"失败"),
	DELAY(3,"延时"),
	handleFinished(4,"已经处理");
	private int result = -1;
	private String caption = null;
	
	private ExeResultEnum(int result,String caption) {
		this.result = result;
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}
	
	public int getResult() {
		return result;
	}
	
	public static ExeResultEnum getByResult(int result){
        for(ExeResultEnum enums : ExeResultEnum.values()) {
        	if(enums.result == result)
        		return enums;
        }
        return null;
	}
}