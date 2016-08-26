package com.qtz.ht.rule.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:RuleDeduct</p>
 * <p>Description:行业扣点规则VO类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015-09-23
 */
public class RuleDeduct extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1331154682906624L;

	/** 行业ID */	private Long tradeid;	/** 扣点比例 */	private Double perce;	/** 排序 */	private Integer sort;	@Override
	public Long getDmId() {
		return dmId;
	}
	@Override
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}	public Long getTradeid() {	    return this.tradeid;	}	public void setTradeid(Long tradeid) {	    this.tradeid=tradeid;	}	public Double getPerce() {	    return this.perce;	}	public void setPerce(Double perce) {	    this.perce=perce;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}