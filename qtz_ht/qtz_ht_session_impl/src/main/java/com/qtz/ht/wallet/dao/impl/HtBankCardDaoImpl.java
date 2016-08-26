package com.qtz.ht.wallet.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.ht.wallet.dao.HtBankCardDao;
/**
 * <p>Title:HtBankCardDaoImpl</p>
 * <p>Description:商户银行卡信息表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 唐礼军 - 1309025893@qq.com
 * @version v1.0 2016-06-21
 */
@Repository("htBankCardDaoImpl")
public class HtBankCardDaoImpl extends MyBaitsDaoImpl<com.qtz.ht.wallet.vo.HtBankCard,java.lang.Long> implements HtBankCardDao {
	/**MYBatis命名空间名*/
	private static String preName = HtBankCardDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}