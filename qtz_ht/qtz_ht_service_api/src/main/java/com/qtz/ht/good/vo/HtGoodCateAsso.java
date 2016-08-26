package com.qtz.ht.good.vo;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mall.core.vo.VO;
/**
 * <p>Title:HtGoodCateAsso</p>
 * <p>Description:商家商品与分类关联VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-10
 */
public class HtGoodCateAsso extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1786832283420672L;

	
	
	/*******************非数据库字段**********************/
	/** 一级分类ID */
	private String oneCateName;
	/** 二级分类ID */
	private String twoCateName;
	/** 三级分类ID */
	private String threeCateName;
		return oneCateName;
	}
	public void setOneCateName(String oneCateName) {
		this.oneCateName = oneCateName;
	}
	public String getTwoCateName() {
		return twoCateName;
	}
	public void setTwoCateName(String twoCateName) {
		this.twoCateName = twoCateName;
	}
	public String getThreeCateName() {
		return threeCateName;
	}
	public void setThreeCateName(String threeCateName) {
		this.threeCateName = threeCateName;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}