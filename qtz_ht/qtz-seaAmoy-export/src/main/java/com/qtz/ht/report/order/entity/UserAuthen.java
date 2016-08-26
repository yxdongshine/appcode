package com.qtz.ht.report.order.entity;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.vo.VO;

public class UserAuthen extends VO<Long> implements java.io.Serializable {
	private static final long serialVersionUID = 2570864732165428882L;

	public Long userId; /** 申请用户ID */
	public String pno; // 身份证号
	public String name; // 姓名
	public String sex; // 性别
	public String address; // 住址
	public String nation; // 民族
	public String birth; // 出生日期
	public String office; // 签发机关
	public String validdate; // 有效期限
	public String pnoImg; // 照片 正面,反面
	/** 申请时间 */
	private Long applyTime;
	/** 0 未审核 1 认证通过 2 不通过 */
	private Integer applyStatus;
	/** 审核时间 */
	private Long applyReviewTime;
	/** 审核人 */
	private Long applyReviewUid;
	/** 审核备注 */
	private String applyReviewContont;
	/**有效期*/
	private Long newApplyTime;
	/** 0 自动审核，1是手动审核 */
	private Integer authStatus; 
	/**状态  0  正常   1  失效*/
	private Integer status;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		if (!StringUtils.isEmpty(pno)) {
			pno = pno.trim();
		}
		this.pno = pno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getValiddate() {
		return validdate;
	}

	public void setValiddate(String validdate) {
		this.validdate = validdate;
	}

	public String getPnoImg() {
		return pnoImg;
	}

	public void setPnoImg(String pnoImg) {
		this.pnoImg = pnoImg;
	}

	public Long getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Long applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Long getApplyReviewTime() {
		return applyReviewTime;
	}

	public void setApplyReviewTime(Long applyReviewTime) {
		this.applyReviewTime = applyReviewTime;
	}

	public Long getApplyReviewUid() {
		return applyReviewUid;
	}

	public void setApplyReviewUid(Long applyReviewUid) {
		this.applyReviewUid = applyReviewUid;
	}

	public String getApplyReviewContont() {
		return applyReviewContont;
	}

	public void setApplyReviewContont(String applyReviewContont) {
		this.applyReviewContont = applyReviewContont;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public Long getNewApplyTime() {
		return newApplyTime;
	}

	public void setNewApplyTime(Long newApplyTime) {
		this.newApplyTime = newApplyTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
