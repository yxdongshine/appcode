package com.qtz.ht.user.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.user.vo.HtStaff;
/**
 * <p>Title:HtStaffPage</p>
 * <p>Description:海淘员工表分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
public class HtStaffPage extends Pager<HtStaff,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1609896795703297L;
	
	private Long dmId;
	
		return ToStringBuilder.reflectionToString(this);
	}
}