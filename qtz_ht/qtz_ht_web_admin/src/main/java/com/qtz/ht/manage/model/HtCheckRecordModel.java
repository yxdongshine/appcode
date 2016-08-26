
package com.qtz.ht.manage.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.qtz.ht.wallet.vo.HtCheckRecord;

/** 
 * 
 * 批量操作钱包审核Mode
 * ClassName:HtCheckRecordModel <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年5月31日 下午5:37:13 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class HtCheckRecordModel implements Serializable{

	/****/  
	private static final long serialVersionUID = -3858464668647402044L;
	
	/** 操作驳回原因*/
	@NotBlank(message="操作原因不能为空")
	@Length(min=2,max=1000,message="操作原因2-1000之间")
	private String operateContent;
	
	@NotEmpty(message="记录不能为空")
	@Size(min=1,message="最少1")
	private List<HtCheckRecord> list;

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public List<HtCheckRecord> getList() {
		return list;
	}

	public void setList(List<HtCheckRecord> list) {
		this.list = list;
	}
	
	
    
}
  