package com.qtz.ht.user.page;


import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.user.vo.HtMenu;
/**
 * <p>Title:HtMenuPage</p>
 * <p>Description:商户员工菜单分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
public class HtMenuPage extends Pager<HtMenu,Long> implements Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1506688413304833L;

	
		return ToStringBuilder.reflectionToString(this);
	}
}