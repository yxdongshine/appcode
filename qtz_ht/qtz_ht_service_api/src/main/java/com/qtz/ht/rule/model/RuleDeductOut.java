package com.qtz.ht.rule.model;
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
public class RuleDeductOut extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1331154682906624L;
	
	private Long dmId;
	/** 行业名称 */
	private String name;
	
	/** 行业Id  */
	private Long scDmId;
	
	/** 规则起点 */
	private Integer startpoint;
	
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	public Long getTradeid() {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getScDmId() {
		return scDmId;
	}
	public void setScDmId(Long scDmId) {
		this.scDmId = scDmId;
	}
	public Integer getStartpoint() {
		return startpoint;
	}
	public void setStartpoint(Integer startpoint) {
		this.startpoint = startpoint;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}