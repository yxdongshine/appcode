package com.qtz.ht.order.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcException;
import com.mall.core.common.Arith;
import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.ht.enums.CommodityStatusEnum;
import com.qtz.ht.good.service.HtGoodCateAssoService;
import com.qtz.ht.good.service.HtGoodCateService;
import com.qtz.ht.good.service.HtGoodDescService;
import com.qtz.ht.good.service.HtGoodsAlbumService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.order.model.OrderModel;
import com.qtz.ht.order.service.HtOrderService;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.ht.wallet.service.HtPlatformWalletLogService;
import com.qtz.ht.wallet.service.HtStaffPaymentFlowService;
import com.qtz.ht.wallet.service.HtWalletService;
import com.qtz.ht.wallet.vo.HtPlatformWalletLog;
import com.qtz.ht.wallet.vo.HtStaffPaymentFlow;

import cache.BaseProperties;

/**
 * <p>Title:HtOrderServiceImpl</p>
 * <p>Description:商户订单表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-02-20
 */
@Service("htOrderServiceImpl")
public class HtOrderServiceImpl implements HtOrderService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtOrderServiceImpl.class);
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	@Resource(name="htGoodsAlbumServiceImpl")
	private HtGoodsAlbumService htGoodsAlbumService;
	@Resource(name="htGoodDescServiceImpl")
	private HtGoodDescService htGoodDescService;
	@Resource(name="htGoodCateServiceImpl")
	private HtGoodCateService htGoodCateService;
	@Resource(name="htGoodCateAssoServiceImpl")
	private HtGoodCateAssoService htGoodCateAssoService;
	@Resource(name="htBusinessServiceImpl")
	private HtBusinessService htBusinessService;
	@Resource(name="htStaffPaymentFlowServiceImpl")
	private HtStaffPaymentFlowService htStaffPaymentFlowService;
	@Resource(name="htPlatformWalletLogServiceImpl")
	private HtPlatformWalletLogService htPlatformWalletLogService;
	@Resource(name="htWalletServiceImpl")
	private HtWalletService htWalletService;
	
	
	@Override
	public List<OrderModel> calculateOrder(Map<Long, Integer> goodS) throws ServiceException,RpcException {
		log.debug(goodS.keySet());
		if (null == goodS || goodS.isEmpty()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误：calculateOrder(" + goodS + ")");
		}
		List<OrderModel> tlist = buildOrder(goodS);
		return tlist;
	}
    

	/**
	* 【订单计算以及拆单】
	* @param goodS
	* @return
	* @throws ServiceException  
	*/
	private List<OrderModel> buildOrder(Map<Long, Integer> goodS) throws ServiceException {
		List<OrderModel> list = null;
		if (null == goodS || goodS.isEmpty()) {//商品为空，直接返回为空
			return list;
		}
		//商家信息，对应商品列表
		Map<Long, List<HtStaffGoods>> map = new HashMap<>();
		//根据商品id集合获取到商品列表
		List<HtStaffGoods> listG = this.htStaffGoodsService.findListByGoodsId(goodS.keySet());
		//处理商品列表按商家分组用
		List<HtStaffGoods> list1 = null;
		for (HtStaffGoods g : listG) {
			if (goodS.get(g.getDmId()) == null || goodS.get(g.getDmId()).intValue() <= 0) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_7,"购买数量最低1件");
			}
			if (goodS.get(g.getDmId()).intValue() > g.getSales().intValue()) {
				log.error(g.getGoodName() + " " + g.getDmId() + " 商品库存不足");
				throw new ServiceException(ExceptionConstants.ERRORCODE_8, "商品库存不足");
			}
			if (CommodityStatusEnum.on.getValue() != g.getStatus().intValue()) {
				log.error(g.getGoodName() + " " + g.getDmId() + " 商品未上架");
				throw new ServiceException(ExceptionConstants.ERRORCODE_16, "商品未上架");
			}
			if (map.containsKey(g.getBusiId())) {
				list1 = map.get(g.getBusiId());
			}else{
				list1 = new ArrayList<>();
			}
			list1.add(g);
			map.put(g.getBusiId(), list1);
		}
		//根据商家信息id集合获取商家信息
		List<HtBusiness> listB = this.htBusinessService.findListByBusinessesId(map.keySet());
		//按照商家拆单
		list = new ArrayList<>();
		OrderModel model = null;
		for (HtBusiness b : listB) {//按商家组装订单
			double orderPrice = 0d;
			double orderSumfinalPrice = 0d;
			List<HtStaffGoods> list2 = map.get(b.getDmId());
			for (HtStaffGoods g : list2) {//计算支付价钱
				orderPrice = Arith.add(orderPrice, Arith.mul(g.getPrice(), goodS.get(g.getDmId())));
				//计算订单的总成本价格
				orderSumfinalPrice = Arith.add(orderSumfinalPrice, Arith.mul(g.getFinalPrice()==null?0d:g.getFinalPrice(), goodS.get(g.getDmId())));
				g.setBuyNumber(goodS.get(g.getDmId()));
			}
			model = new OrderModel();
			model.setList(list2);
			model.setSellerId(b.getDmId());
			model.setSellerName(b.getFullName());
			model.setOrderPrice(orderPrice);
			model.setPaymentPrice(orderPrice);
			model.setOrderSumfinalPrice(orderSumfinalPrice);
			list.add(model);
		}
		return list;
	}
	@Override
	public synchronized void modStock(List<OrderModel> list) throws ServiceException {
		if (list == null || list.isEmpty()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误 ( "+ list +")");
		}
		Map<Long, Integer> goodS = new HashMap<>();

		for (OrderModel o : list) {
			for (HtStaffGoods g : o.getList()) {//处理商品数据
				goodS.put(g.getDmId(), g.getBuyNumber());
			}
		}
		
		List<HtStaffGoods> listG = this.htStaffGoodsService.findListByGoodsId(goodS.keySet());
		if (null == listG || listG.isEmpty()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误 ( "+ goodS +")");
		}
		Integer buyNumber = null;
		for (HtStaffGoods good : listG) {
			buyNumber = goodS.get(good.getDmId());
			if (buyNumber == null || buyNumber.intValue() <= 0) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_7,"购买数量最低1件");
			}
			if (buyNumber.intValue() > good.getSales().intValue()) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_8, good.getGoodName() + " 商品库存不足");
			}
			if (CommodityStatusEnum.on.getValue() != good.getStatus().intValue()) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_16, good.getGoodName() + " 商品未上架");
			}
			this.htStaffGoodsService.modstock(good.getDmId(), buyNumber);//扣除库存
		}
	}


	@Override
	public synchronized void addStock(List<OrderModel> list) throws ServiceException {
		if (list == null || list.isEmpty()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误 ( "+ list +")");
		}
		Map<Long, Integer> goodS = new HashMap<>();
		HtBusiness b = null;		//商家
		
		double staffRevenue = 0d;	//商家货款
		double platformRevenue = 0d;	//海淘货款提成
		
		for (OrderModel o : list) {
			//找到商家
			b = this.htBusinessService.findVo(o.getSellerId(), null);
			if (null == b) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_7,"商家不存在");
			}
			//staffRevenue = Arith.div(Arith.mul(o.getCouponPrice(), b.getSettDiscount()), 100, 2);
			//删除商家货款分账流水
			HtStaffPaymentFlow oldPf = new HtStaffPaymentFlow();
			oldPf.setBusiId(b.getDmId());//商家编号
			oldPf.setOrderId(o.getDmId());//订单编号
			//oldPf.setPaymentType(o.getPaymentMethod());//付款方式
		
			List<HtStaffPaymentFlow> listHtStaffPaymentFlow= this.htStaffPaymentFlowService.findList(oldPf);
			
			if(listHtStaffPaymentFlow!=null && listHtStaffPaymentFlow.size()>0){
				HtStaffPaymentFlow oldPfDB = listHtStaffPaymentFlow.get(0);
				if(oldPfDB.getRecoStatus()==0){//完成则抛出异常
					throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"该订单已经解冻完成，不能继续操作");
				}
				staffRevenue = oldPfDB.getStaffRevenue();
				//修改供应商钱包
				htWalletService.modSuppReconciliation(b.getDmId(), -staffRevenue);
				//统计商家退单总额
				htWalletService.countWalletTotalRefund(b.getDmId(),staffRevenue);
				
				this.htStaffPaymentFlowService.delDataByCondition(oldPf);
				log.debug("*找到并且删除********shangjia编号*****"+b.getDmId()+"******订单编号*****"+o.getDmId());
			}else{
				log.debug("*没有找到********shangjia编号*****"+b.getDmId()+"******订单编号*****"+o.getDmId());
			}
			
			
			//删除平台退款后的分账收入流水
			HtPlatformWalletLog oldPw = new HtPlatformWalletLog();
			oldPw.setBusiId(b.getDmId());//商家编号
			oldPw.setOrderId(o.getDmId());//订单编号
			//oldPw.setPaymentType(o.getPaymentMethod());//付款方式
			List<HtPlatformWalletLog> listHtPlatformWalletLog =htPlatformWalletLogService.findList(oldPw);
			if(listHtPlatformWalletLog!=null &&listHtPlatformWalletLog.size()>0){
				HtPlatformWalletLog oldPwDB = listHtPlatformWalletLog.get(0);
				if(oldPwDB.getRecoStatus()==0){//完成则抛出异常
					throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"该订单已经解冻完成，不能继续操作");
				}
				platformRevenue = Arith.add(platformRevenue, oldPwDB.getAmount()); //累加平台金额
				try {
					htPlatformWalletLogService.delDataByCondition(oldPw);
				} catch (DaoException e) {
					throw new ServiceException();
				};
				log.debug("*找到并且删除****平台****编号*****"+b.getDmId()+"******订单编号*****"+o.getDmId());
			}else{
				log.debug("*没有找到****平台****编号*****"+b.getDmId()+"******订单编号*****"+o.getDmId());
			}
			//删除方法
			for (HtStaffGoods g : o.getList()) {//处理商品数据
				goodS.put(g.getDmId(), g.getBuyNumber());
			}
		}
		
		//修改平台钱包对账金额
		htWalletService.modPlatformReconciliation(-platformRevenue);
		//统计品台退单总额
		htWalletService.countWalletTotalRefund(HtStaff.ADMIN_WALLET_ID,platformRevenue);
		List<HtStaffGoods> listG = this.htStaffGoodsService.findListByGoodsId(goodS.keySet());
		if (null == listG || listG.isEmpty()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7,"入参错误 ( "+ goodS +")");
		}
		
		Integer buyNumber = null;
		for (HtStaffGoods good : listG) {
			buyNumber = goodS.get(good.getDmId());
			if (buyNumber == null || buyNumber.intValue() <= 0) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_7,"购买数量最低1件");
			}
			if (CommodityStatusEnum.on.getValue() != good.getStatus().intValue()) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_16, good.getGoodName() + " 商品未上架");
			}
			this.htStaffGoodsService.addstock(good.getDmId(), buyNumber);//增加库存
		}
			
	}


	@Override
	public void addReconciliation(List<OrderModel> list) throws ServiceException {
		HtBusiness b = null;		//商家
		double staffRevenue = 0d;	//商家货款
		double platformRevenue = 0d;	//海淘货款提成
		
		for (OrderModel o : list) {
			//找到商家
			b = this.htBusinessService.findVo(o.getSellerId(), null);
			if (null == b) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_7,"商家不存在");
			}
			//平台折扣为1-9之间
			staffRevenue = Arith.div(Arith.mul(o.getPaymentPrice(), b.getSettDiscount()), 10, 2);
			//对账时间周期
			Integer  recoCycle = b.getRecoCycle();
			if (null == recoCycle) {
				recoCycle = Integer.valueOf(BaseProperties.getBaseProperties("FREEZING_TIME"));
			}
			//添加货款流水
			HtStaffPaymentFlow pf = new HtStaffPaymentFlow(b.getDmId(),o.getDmId(),o.getPaymentPrice(),
					Arith.sub(o.getPaymentPrice().doubleValue(), staffRevenue),staffRevenue,
					o.getPaymentMethod(),b.getSettDiscount(),System.currentTimeMillis(),
					System.currentTimeMillis() + 24 * 60 * 60 * 1000l * recoCycle,
					Constants.ONE,System.currentTimeMillis());
			this.htStaffPaymentFlowService.addVo(pf);
			//修改商家钱包对账金额
			htWalletService.modSuppReconciliation(b.getDmId(), pf.getStaffRevenue());
			
			//添加平台收入流水
			HtPlatformWalletLog pw = new HtPlatformWalletLog(b.getDmId(),o.getDmId(),pf.getPlatformRevenue(),
					Constants.ONE, o.getPaymentMethod(), b.getSettDiscount(), pf.getIncomeTime(),
					pf.getReleaseTime(), Constants.ONE, pf.getCrtime());
			
			platformRevenue = Arith.add(platformRevenue, pw.getAmount()); //累加平台金额
			
			this.htPlatformWalletLogService.addVo(pw);
		}
		
		//修改平台钱包对账金额
		htWalletService.modPlatformReconciliation(platformRevenue);
	}
}