package com.qtz.ht.rule.page;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
/**
 * <p>Title:RuleGoldPage</p>
 * <p>Description:金币规则分页类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015-09-23
 */
public class RuleGoldPage extends Pager<com.qtz.ht.rule.vo.RuleGold,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1331121918396417L;
	/** 规则code */	private String code;	/** 规则名字 */	private String name;	/** 比例 */	private Double perce;	/** 排序 */	private Integer sort;	public String getCode() {	    return this.code;	}	public void setCode(String code) {	    this.code=code;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public Double getPerce() {	    return this.perce;	}	public void setPerce(Double perce) {	    this.perce=perce;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}