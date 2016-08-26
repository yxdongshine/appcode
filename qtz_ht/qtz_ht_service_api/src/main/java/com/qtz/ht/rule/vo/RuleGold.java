package com.qtz.ht.rule.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:RuleGold</p>
 * <p>Description:金币规则VO类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015-09-23
 */
public class RuleGold extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1331121918396416L;
	/** 规则code */	private String code;	/** 规则名字 */	private String name;	/** 比例 */	private Double perce;	/** 排序 */	private Integer sort;
	
	@Override
	public Long getDmId() {
		return dmId;
	}
	@Override
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}	public String getCode() {	    return this.code;	}	public void setCode(String code) {	    this.code=code;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public Double getPerce() {	    return this.perce;	}	public void setPerce(Double perce) {	    this.perce=perce;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}