package com.qtz_ht_timer.task.dao;
import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz_ht_timer.task.vo.HtWallet;
/**
 * 
 *  <p>Title:HtWalletDao</p>
 * <p>Description:钱包DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * 
 * 
 * ClassName: HtWalletDao <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月6日 上午9:26:42 <br/> 
 * 
 * @author yxd 
 * @version
 */
public interface HtWalletDao extends BizDao<HtWallet,Long> {

	/**
	* 【获取商家钱包】
	* @param busiId
	* @param userType
	* @return  
	*/
	HtWallet getWalletByUser(@Param("busiId")Long busiId, @Param("userType")int userType) throws DaoException;
	
}