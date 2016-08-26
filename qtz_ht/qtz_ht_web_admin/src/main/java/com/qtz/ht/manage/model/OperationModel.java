package com.qtz.ht.manage.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import com.qtz.ht.user.vo.HtBusiness;

/**
 * <p>Title:OperationModel</p>
 * <p>Description:(操作模型)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月11日
 */
public class OperationModel implements Serializable {

	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = 5496636881474308232L;
	/** 操作原因*/
	@NotBlank(message="操作原因不能为空")
	@Length(min=2,max=1000,message="操作原因2-1000之间")
	private String operateContent;
	/** 操作凭证 */
  //	@NotBlank(message="操作凭证不能为空")
	@Length(min=0,max=100, message="操作凭证地址0-100之间")
	@URL(message="操作凭证地址错误")
	private String voucherAddr;
	@NotEmpty(message="商家不能为空")
	@Size(min=1,message="最少1")
	private List<HtBusiness> list;
	
	public List<HtBusiness> getList() {
		return list;
	}
	public void setList(List<HtBusiness> list) {
		this.list = list;
	}
	public String getOperateContent() {
		return operateContent;
	}
	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	public String getVoucherAddr() {
		return voucherAddr;
	}
	public void setVoucherAddr(String voucherAddr) {
		this.voucherAddr = voucherAddr;
	}
}
