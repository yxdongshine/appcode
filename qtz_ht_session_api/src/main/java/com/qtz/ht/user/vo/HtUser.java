package com.qtz.ht.user.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtUser</p>
 * <p>Description:商户订单表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-01
 */
public class HtUser extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1601393422649344L;

	
	private String password;
		return ToStringBuilder.reflectionToString(this);
	}
}