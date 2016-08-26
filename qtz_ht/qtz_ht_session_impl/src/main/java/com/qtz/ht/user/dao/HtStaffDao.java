package com.qtz.ht.user.dao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.ht.user.vo.HtStaff;
/**
 * <p>Title:HtStaffDao</p>
 * <p>Description:海淘员工表DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-06
 */
public interface HtStaffDao extends BizDao<HtStaff,Long> {

	/**
	* 【根据账号，密码查询用户】
	* @param account
	* 				账号
	* @param password
	* 				密码
	* @param jobOn
	* 				是否在职 0  在职   1  离职
	* @param status
	* 				是否禁用  0 正常 1 屏蔽
	* @return
	* @throws DaoException  
	*/
	HtStaff getLoginVo(String account, String password, int jobOn, int status)throws DaoException;
	
}