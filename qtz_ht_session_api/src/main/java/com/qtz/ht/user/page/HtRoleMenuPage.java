package com.qtz.ht.user.page;


import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.user.vo.HtRoleMenu;
/**
 * <p>Title:HtRoleMenuPage</p>
 * <p>Description:商户角色菜单分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-01-25
 */
public class HtRoleMenuPage extends Pager<HtRoleMenu,Long> implements Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1506688409700353L;

	
		return ToStringBuilder.reflectionToString(this);
	}
}