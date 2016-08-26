
package com.qtz.ht.manage.model;

import java.util.List;

import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtStaffGoods;

/** 
 * ClassName:HtStaffGoodsResponseModel <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年6月27日 上午9:53:23 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class HtStaffGoodsResponseModel extends HtStaffGoods {

	/****/  
	private static final long serialVersionUID = -291402786484866657L;
	/**关联分类*/
	private List<HtGoodCateAsso> cateS;
	
	public List<HtGoodCateAsso> getCateS() {
		return cateS;
	}
	public void setCateS(List<HtGoodCateAsso> cateS) {
		this.cateS = cateS;
	}
}
  