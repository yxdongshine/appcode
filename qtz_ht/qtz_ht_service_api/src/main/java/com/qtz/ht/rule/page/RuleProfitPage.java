package com.qtz.ht.rule.page;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
/**
 * <p>Title:RuleProfitPage</p>
 * <p>Description:利润分配规则分页类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015-09-23
 */
public class RuleProfitPage extends Pager<com.qtz.ht.rule.vo.RuleProfit,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1331121970137089L;
	/** 规则code */	private String code;	/** 规则名字 */	private String name;	/** 比例 */	private Double prece;	/** 排序 */	private Integer sort;	public String getCode() {	    return this.code;	}	public void setCode(String code) {	    this.code=code;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public Double getPrece() {	    return this.prece;	}	public void setPrece(Double prece) {	    this.prece=prece;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}