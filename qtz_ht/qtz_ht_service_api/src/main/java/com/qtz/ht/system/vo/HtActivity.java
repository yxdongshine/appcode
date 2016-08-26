package com.qtz.ht.system.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:HtActivity</p>
 * <p>Description:活动管理表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-08
 */
public class HtActivity extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1783998137092096L;

		/** 活动名称 */	private String activityName;	/** 活动主图 */	private String mainPicture;	/** 跳转链接 */	private String linkUrl;	/** 状态 1上架  2下架 */	private Integer isValid;	/** 创建人ID */	private Long crUserId;	/** 创建时间 */	private Long crtime;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getActivityName() {	    return this.activityName;	}	public void setActivityName(String activityName) {	    this.activityName=activityName;	}	public String getMainPicture() {	    return this.mainPicture;	}	public void setMainPicture(String mainPicture) {	    this.mainPicture=mainPicture;	}	public String getLinkUrl() {	    return this.linkUrl;	}	public void setLinkUrl(String linkUrl) {	    this.linkUrl=linkUrl;	}	public Integer getIsValid() {	    return this.isValid;	}	public void setIsValid(Integer isValid) {	    this.isValid=isValid;	}	public Long getCrUserId() {	    return this.crUserId;	}	public void setCrUserId(Long crUserId) {	    this.crUserId=crUserId;	}	public Long getCrtime() {	    return this.crtime;	}	public void setCrtime(Long crtime) {	    this.crtime=crtime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "activityName:" + getActivityName() +"," + "mainPicture:" + getMainPicture() +"," + "linkUrl:" + getLinkUrl() +"," + "isValid:" + getIsValid() +"," + "crUserId:" + getCrUserId() +"," + "crtime:" + getCrtime() +"]";	}
}