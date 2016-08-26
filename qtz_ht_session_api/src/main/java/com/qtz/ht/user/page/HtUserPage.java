package com.qtz.ht.user.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.user.vo.HtUser;
/**
 * <p>Title:HtUserPage</p>
 * <p>Description:商户订单表分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-01
 */
public class HtUserPage extends Pager<HtUser,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1601393422649345L;

	private Long dmId;
		return ToStringBuilder.reflectionToString(this);
	}
}