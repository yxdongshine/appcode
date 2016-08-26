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

	
	@Override
	public Long getDmId() {
		return dmId;
	}
	@Override
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
		return ToStringBuilder.reflectionToString(this);
	}
}