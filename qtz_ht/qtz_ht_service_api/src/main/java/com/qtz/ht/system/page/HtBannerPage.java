package com.qtz.ht.system.page;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.Pager;
import com.qtz.ht.system.vo.HtBanner;
/**
 * <p>Title:HtBannerPage</p>
 * <p>Description:横幅管理表分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-15
 */
public class HtBannerPage extends Pager<HtBanner,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1621160682211329L;
	
	private Long dmId;
	
		return ToStringBuilder.reflectionToString(this);
	}
}