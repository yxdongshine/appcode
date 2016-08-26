package com.qtz.ht.system.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.system.dao.HtCartDao;
import com.qtz.ht.system.service.HtCartService;
import com.qtz.ht.system.vo.HtCart;
/**
 * <p>Title:HtCartServiceImpl</p>
 * <p>Description:购物车服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 刘宝平 - liubaoping
 * @version v1.0 2016年8月12日
 */
@Service("htCartServiceImpl")
public class HtCartServiceImpl extends BaseServiceImpl<HtCart, Long> implements HtCartService {

	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtCartServiceImpl.class);
	
	@Resource(name="htCartDaoImpl")
	private HtCartDao dao;
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	@Override
	protected LogTool getLog() {
		return log;
	}

	@Override
	protected BizDao<HtCart, Long> getDao() {
		return dao;
	}
	
	
	@Override
	public void add(HtCart htCart)throws ServiceException{
		HtCart vo=new HtCart();
		htCart.setChangeTime(System.currentTimeMillis());
		vo.setUserId(htCart.getUserId());
		vo.setGoodId(htCart.getGoodId());
		List<HtCart> list=super.findList(vo); 
		if(list!=null&&list.size()>0){
			HtCart htCarInDB=list.get(0);
			vo.setDmId(htCarInDB.getDmId());
			vo.setGoodId(null);
			vo.setUserId(null);
			vo.setChangeTime(System.currentTimeMillis());
			Integer oldGoodNum= htCarInDB.getGoodNumber();
			if(oldGoodNum==null){
				oldGoodNum = 0;
			}
			vo.setGoodNumber(oldGoodNum+Constants.ONE);
			if(vo.getGoodNumber()>20){
				throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"单个商品购买数量不超过20件");
			}
			super.modVoNotNull(vo);
		}else{
			htCart.setGoodNumber(Constants.ONE);//第一次加入购物车，默认数量为1
			super.addVo(htCart);
		}
		
	}
	
	@Override
	public List<HtCart> list(Long userId)throws ServiceException{
		if(userId==null){
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误");
		}
		HtCart vo=new HtCart();
		vo.setUserId(userId);
		List<HtCart> cartList=super.findList(vo);
		HtStaffGoods htStaffGoods=null;
		if(cartList!=null&&cartList.size()>0){
			for(HtCart cart:cartList){
				htStaffGoods=htStaffGoodsService.findVo(cart.getGoodId(), new HtStaffGoods());
				if(htStaffGoods!=null){
					cart.setHtStaffGoods(htStaffGoods);
				}
			}
		}
		return cartList;
	}
	
	@Override
	public void modGoodsNum(Long dmId,int goodsNumber)throws ServiceException{
		if(dmId==null&&goodsNumber<1){
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误");
		}
		HtCart vo=new HtCart();
		vo.setDmId(dmId);
		vo.setGoodNumber(goodsNumber);
		if(goodsNumber>20){
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"单个商品购买数量不超过20件");
		}
		super.modVoNotNull(vo); 
	}
	
	@Override 
	public void delete(List<Long> goodsId,Long userId)throws ServiceException{
		if(goodsId==null&&userId==null){
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误");
		}
		try {
			HtCart vo=null;
			for (Long goodid : goodsId) {
				vo=new HtCart();
				vo.setGoodId(goodid);
				vo.setUserId(userId);
				dao.delAllbyUserIdAndGoodId(vo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
