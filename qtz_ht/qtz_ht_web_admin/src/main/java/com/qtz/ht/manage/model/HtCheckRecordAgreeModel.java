
package com.qtz.ht.manage.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.qtz.ht.wallet.vo.HtCheckRecord;

/** 
 * ClassName:HtCheckRecordAgreeModel <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年5月31日 下午5:42:06 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class HtCheckRecordAgreeModel implements Serializable{
	
	/****/  
	private static final long serialVersionUID = -1033231341191462105L;

	@NotEmpty(message="记录不能为空")
	@Size(min=1,message="最少1")
	private List<HtCheckRecord> list;

	public List<HtCheckRecord> getList() {
		return list;
	}

	public void setList(List<HtCheckRecord> list) {
		this.list = list;
	}

	
	
}
  