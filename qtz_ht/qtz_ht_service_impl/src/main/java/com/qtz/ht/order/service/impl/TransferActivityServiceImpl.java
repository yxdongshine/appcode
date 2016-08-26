package com.qtz.ht.order.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.Arith;
import com.mall.core.common.Constants;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.dm.userwallet.service.UserWalletService;
import com.qtz.dm.userwallet.vo.UserWallet;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.order.model.OrderModel;
import com.qtz.ht.order.model.PaymentModel;
import com.qtz.ht.order.service.TransferActivityService;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.util.SeaAmoExceptionConstant;
import com.qtz.ht.wallet.service.HtPlatformWalletLogService;
import com.qtz.ht.wallet.service.HtStaffPaymentFlowService;
import com.qtz.ht.wallet.service.HtWalletService;
import com.qtz.ht.wallet.vo.HtPlatformWalletLog;
import com.qtz.ht.wallet.vo.HtStaffPaymentFlow;
import com.qtz.orig.common.Result;
import com.qtz.orig.order.service.OrigOrderService;
import com.qtz.orig.order.vo.HtOrder;


/**
 * <p>Title:TransferActivityServiceImpl</p>
 * <p>Description:(换车活动service实现)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月18日
 */
@Service("transferActivityServiceImpl")
public class TransferActivityServiceImpl implements TransferActivityService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(TransferActivityServiceImpl.class);
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	@Resource(name="htBusinessServiceImpl")
	private HtBusinessService htBusinessService;
	@Resource(name="htStaffPaymentFlowServiceImpl")
	private HtStaffPaymentFlowService htStaffPaymentFlowService;
	@Resource(name="htPlatformWalletLogServiceImpl")
	private HtPlatformWalletLogService htPlatformWalletLogService;
	@Resource(name="htWalletServiceImpl")
	private HtWalletService htWalletService;
	@Resource(name="userWalletServiceImpl")
	private UserWalletService UserWalletService;
	@Resource(name="origOrderServiceImpl")
	private OrigOrderService origOrderService;
	
	private Lock lock = new ReentrantLock();
	
	@Override
	public void addOrder(Long userId, String userName, Long goodId, String idCard, String payPassword, String mobilePhone) throws ServiceException {
		if (null == userId || null == goodId || null == payPassword) {
			throw new ServiceException(SeaAmoExceptionConstant.IS_NULL.getCode(),SeaAmoExceptionConstant.IS_NULL.getMsg());
		}
		lock.lock();
		try {
			//商品是否存在
			HtStaffGoods good = htStaffGoodsService.findVo(goodId, null);
			if (null == good) {
				throw new ServiceException(SeaAmoExceptionConstant.GOOD_IS_NULL.getCode(),SeaAmoExceptionConstant.GOOD_IS_NULL.getMsg());
			}
			log.debug(userId + " 正在购买  " + good.getFullName() + " 商品id = " +goodId);
			
			//判断库存是否足够
			if (null == good || good.getSales().intValue() <= 0) {
				throw new ServiceException(SeaAmoExceptionConstant.GOOD_STOCK_ENOUGH.getCode(),SeaAmoExceptionConstant.GOOD_STOCK_ENOUGH.getMsg());
			}
			
			//判断海淘补贴余额是否足够
			UserWallet wallet = UserWalletService.getWalletByUserId(userId, UserType.PERSON.value());
			if (null == wallet) {
				throw new ServiceException(SeaAmoExceptionConstant.WALLET_IS_NULL.getCode(),SeaAmoExceptionConstant.WALLET_IS_NULL.getMsg());
			}
			if (wallet.getHtsubsidyTotalAmount() == null || wallet.getHtsubsidyTotalAmount().doubleValue() < good.getPrice().doubleValue()) {
				throw new ServiceException(SeaAmoExceptionConstant.SUBSIDY_AMOUNT_NOT_ENOUGH.getCode(),SeaAmoExceptionConstant.SUBSIDY_AMOUNT_NOT_ENOUGH.getMsg());
			}
			
			HtBusiness business = htBusinessService.findVo(good.getBusiId(), null);	//查找商家
			//创建订单并支付
			OrderModel model = new OrderModel();
			model.setSellerId(business.getDmId());
			model.setSellerName(business.getFullName());
			model.setOrderPrice(good.getPrice());
			model.setPaymentPrice(good.getPrice());
			model.setCouponPrice(0d);
			List<HtStaffGoods> buyGoodlist = new ArrayList<>();	//设置商品
			good.setBuyNumber(1);
			buyGoodlist.add(good);
			model.setList(buyGoodlist);
			
			//调用订单服务返回结果处理
			Result<HtOrder> result = origOrderService.saveActivityOrder(JSONObject.toJSONString(model), userId, userName, UserType.PERSON.value(), mobilePhone, idCard, payPassword);
			if (!result.isSuccess()) {
				throw new ServiceException(result.getCode(),result.getFailMessage());
			}
			OrderModel o = (OrderModel) result.getCarrierObject();
			double paymentPrice = o.getPaymentPrice();
			
			//扣减库存
			htStaffGoodsService.modstock(goodId, 1);
			//添加商家对账记录
			double staffRevenue = 0d;	//商家货款
			double platformRevenue = 0d;	//海淘货款提成
			//平台折扣为1-9之间
			staffRevenue = Arith.div(Arith.mul(paymentPrice, business.getSettDiscount()), 10, 2);
			//对账时间周期
			Integer  recoCycle = business.getRecoCycle();
			//添加货款流水
			HtStaffPaymentFlow pf = new HtStaffPaymentFlow(business.getDmId(),o.getDmId(),o.getOrderPrice(),
					Arith.sub(paymentPrice, staffRevenue),staffRevenue,
					o.getPaymentMethod(),business.getSettDiscount(),System.currentTimeMillis(),
					System.currentTimeMillis() + 24 * 60 * 60 * 1000l * recoCycle,
					Constants.ONE,System.currentTimeMillis());
			this.htStaffPaymentFlowService.addVo(pf);
			//修改商家钱包对账金额
			htWalletService.modSuppReconciliation(business.getDmId(), pf.getStaffRevenue());
			
			//添加平台收入流水
			HtPlatformWalletLog pw = new HtPlatformWalletLog(business.getDmId(),o.getDmId(),pf.getPlatformRevenue(),
					Constants.ONE, o.getPaymentMethod(), business.getSettDiscount(), pf.getIncomeTime(),
					pf.getReleaseTime(), Constants.ONE, pf.getCrtime());
			
			this.htPlatformWalletLogService.addVo(pw);
			//修改平台钱包对账金额
			platformRevenue = Arith.add(platformRevenue, pw.getAmount()); //累加平台金额
			htWalletService.modPlatformReconciliation(platformRevenue);
			
			log.debug(userId + " 成功抢购购买了一件  " + good.getFullName() + " 商品id = " +goodId);
			
		} catch (ServiceException e) {
			throw e;
		}finally{
			lock.unlock();
		}
	}

	@Override
	public PaymentModel getSubsidy(Long userId, Long goodId) throws ServiceException {
		if (null == userId || null == goodId) {
			throw new ServiceException(SeaAmoExceptionConstant.IS_NULL.getCode(),SeaAmoExceptionConstant.IS_NULL.getMsg());
		}
		
		UserWallet wallet = UserWalletService.getWalletByUserId(userId, UserType.PERSON.value());
		if (null == wallet) {
			throw new ServiceException(SeaAmoExceptionConstant.WALLET_IS_NULL.getCode(),SeaAmoExceptionConstant.WALLET_IS_NULL.getMsg());
		}
		HtStaffGoods good = htStaffGoodsService.findVo(goodId, null);
		if (null == good) {
			throw new ServiceException(SeaAmoExceptionConstant.GOOD_IS_NULL.getCode(),SeaAmoExceptionConstant.GOOD_IS_NULL.getMsg());
		}
		if (wallet.getHtsubsidyTotalAmount() == null || wallet.getHtsubsidyTotalAmount().doubleValue() < good.getPrice().doubleValue()) {
			throw new ServiceException(SeaAmoExceptionConstant.SUBSIDY_AMOUNT_NOT_ENOUGH.getCode(),SeaAmoExceptionConstant.SUBSIDY_AMOUNT_NOT_ENOUGH.getMsg());
		}
		PaymentModel result = new PaymentModel();
		result.setSubsidyAmount(wallet.getHtsubsidyTotalAmount());
		result.setOrderAmount(good.getPrice());
		result.setGoodName(good.getGoodName());
		result.setMainPicture(good.getMainPicture());
		return result;
	}
}