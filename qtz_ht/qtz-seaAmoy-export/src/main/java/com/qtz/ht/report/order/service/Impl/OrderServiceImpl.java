package com.qtz.ht.report.order.service.Impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.Arith;
import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.FifteenLongId;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
import com.qtz.ht.report.order.dao.HtBusinessDao;
import com.qtz.ht.report.order.dao.OrderDao;
import com.qtz.ht.report.order.dao.UserAuthenDao;
import com.qtz.ht.report.order.entity.HtBusiness;
import com.qtz.ht.report.order.entity.HtPlatformWalletLog;
import com.qtz.ht.report.order.entity.HtStaffPaymentFlow;
import com.qtz.ht.report.order.entity.OrderLog;
import com.qtz.ht.report.order.entity.UserAuthen;
import com.qtz.ht.report.order.model.BulkShippingModel;
import com.qtz.ht.report.order.model.HtOrderResponseModel;
import com.qtz.ht.report.order.model.ModOrderModel;
import com.qtz.ht.report.order.model.OrderModel;
import com.qtz.ht.report.order.service.OrderService;
import com.qtz.ht.report.util.HtOrderEnum;

/**
 * <p>Title:OrderServiceImpl</p>
 * <p>Description:(订单导入导出service实现类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年7月7日
 */
@Service
public class OrderServiceImpl implements OrderService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(OrderServiceImpl.class);
	/**注入商品DAO接口类*/
	@Autowired
    private OrderDao dao;
	@Autowired
	private FifteenLongId fifteenLongId;
	@Autowired
	private UserAuthenDao userAuthenDao;
	@Autowired
	private HtBusinessDao htBusinessDao;
	@Override
	public int modBulkShipping(Long busiId, List<BulkShippingModel> list)throws ServiceException {
		// TODO Auto-generated method stub
		if (null == list || list.isEmpty()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"入参错误");
		}
		int length = list.size();
		long time = System.currentTimeMillis();
		List<OrderLog> logList = new ArrayList<>();	//日志列表
		List<HtStaffPaymentFlow> suppList = new ArrayList<>();	//商家货款流水列表
		List<HtPlatformWalletLog> platList = new ArrayList<>();	//平台货款流水列表
		List<BulkShippingModel> pendingList = new ArrayList<>();
		List<BulkShippingModel> bufferList = new ArrayList<>();
		List<OrderModel> reconciliationList = new ArrayList<>();	//批量更新钱包对账记录
		List<OrderModel> subList;
		HtBusiness business = null;		//商家
		double staffRevenue = 0d;	//商家货款
		double platformRevenue = 0d;	//海淘货款提成
		try {
		while (length > 500) {  //过滤处理所有未发货订单
			bufferList = list.subList(0, 500);
			subList = dao.getOrderList(bufferList);
			if (null != subList && subList.size() > 0) {
				for (BulkShippingModel b : bufferList) {
					for (OrderModel s : subList) {
						if (b.getOrderId().longValue() == s.getDmId().longValue()) {
							pendingList.add(b);
							//设置日志
							OrderLog log = new OrderLog(fifteenLongId.nextId(), s.getDmId(), time, HtOrderEnum.OrderStatus.waitTake.getId(), "商家发货");
							logList.add(log);
							
							double sellMoney =Arith.div(Arith.mul(s.getOrderPrice(), 9), 10, 2) ;
							business = htBusinessDao.getEntityById(s.getSellerId());
							if (null != business) {
								//平台折扣为1-9之间
								staffRevenue = Arith.div(Arith.mul(sellMoney, business.getSettDiscount()), 10, 2);
								//对账时间周期
								Integer  recoCycle = business.getRecoCycle();
								//设置商家货款流水
								HtStaffPaymentFlow supp = new HtStaffPaymentFlow(business.getDmId(),s.getDmId(),sellMoney,
										Arith.sub(sellMoney, staffRevenue),staffRevenue,
										s.getPaymentMethod(),business.getSettDiscount(),time,
										time + 24 * 60 * 60 * 1000l * recoCycle,
										Constants.ONE,System.currentTimeMillis(),fifteenLongId.nextId());
								suppList.add(supp);
								
								//修改商家钱包对账金额
								OrderModel busi = new OrderModel();
								busi.setSellerId(business.getDmId());
								busi.setOrderPrice(supp.getStaffRevenue());
								reconciliationList.add(busi);
								
								
								//设置平台货款流水
								HtPlatformWalletLog plat = new HtPlatformWalletLog(business.getDmId(),s.getDmId(),supp.getPlatformRevenue(),
										Constants.ONE, s.getPaymentMethod(), business.getSettDiscount(), supp.getIncomeTime(),
										supp.getReleaseTime(), Constants.ONE, supp.getCrtime(),fifteenLongId.nextId());
								platList.add(plat);
								platformRevenue = Arith.add(platformRevenue, plat.getAmount()); //累加平台金额
							}
						}
					}
				}
				
			}
			bufferList.clear();
			subList.clear();
			list = list.subList(500, list.size());
			length = list.size();
		}
		if (length > 0) {
			subList = dao.getOrderList(list);
			if (null != subList && subList.size() > 0) {
				for (BulkShippingModel b : list) {
					for (OrderModel s : subList) {
						if (b.getOrderId().longValue() == s.getDmId().longValue()) {
							pendingList.add(b);
							//设置日志
							OrderLog log = new OrderLog(fifteenLongId.nextId(), s.getDmId(), time, HtOrderEnum.OrderStatus.waitTake.getId(), "商家发货");
							logList.add(log);
							
							double sellMoney =Arith.div(Arith.mul(s.getOrderPrice(), 9), 10, 2) ;
							business = htBusinessDao.getEntityById(s.getSellerId());
							if (null != business) {
								//平台折扣为1-9之间
								staffRevenue = Arith.div(Arith.mul(sellMoney, business.getSettDiscount()), 10, 2);
								//对账时间周期
								Integer  recoCycle = business.getRecoCycle();
								//设置商家货款流水
								HtStaffPaymentFlow supp = new HtStaffPaymentFlow(business.getDmId(),s.getDmId(),sellMoney,
										Arith.sub(sellMoney, staffRevenue),staffRevenue,
										s.getPaymentMethod(),business.getSettDiscount(),time,
										time + 24 * 60 * 60 * 1000l * recoCycle,
										Constants.ONE,System.currentTimeMillis(),fifteenLongId.nextId());
								suppList.add(supp);
								
								//修改商家钱包对账金额
								OrderModel busi = new OrderModel();
								busi.setSellerId(business.getDmId());
								busi.setOrderPrice(supp.getStaffRevenue());
								reconciliationList.add(busi);
								
								
								//设置平台货款流水
								HtPlatformWalletLog plat = new HtPlatformWalletLog(business.getDmId(),s.getDmId(),supp.getPlatformRevenue(),
										Constants.ONE, s.getPaymentMethod(), business.getSettDiscount(), supp.getIncomeTime(),
										supp.getReleaseTime(), Constants.ONE, supp.getCrtime(),fifteenLongId.nextId());
								platList.add(plat);
								platformRevenue = Arith.add(platformRevenue, plat.getAmount()); //累加平台金额
							}
						}
					}
				}
			}
		}
		if (pendingList.isEmpty()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1,"入参错误");
		}
		//修改订单发货状态
		int[] result = dao.batchUpdateAudit(pendingList);
		//添加 商家对账记录
		htBusinessDao.addSuppBatchRecord(suppList);
		//添加平台对账记录
		htBusinessDao.addPlatBatchRecord(platList);
		//添加 订单日志
		dao.addBatchOrderLogRecord(logList);
		//添加 平台钱包修改金额
		OrderModel busi = new OrderModel();
		busi.setSellerId(HtBusiness.ADMIN_WALLET_ID);
		busi.setOrderPrice(platformRevenue);
		reconciliationList.add(busi);
		//批量修改钱包
		htBusinessDao.modBatchWallet(reconciliationList);
		return result.length;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<HtOrderResponseModel> findHtOrderResponseModels(Long busiId, Integer status, String detailAddr,
			Long dmId, String consignee, String mphoneNo, Long startCrtime, Long endCrtime) throws ServiceException {
		
		try {
			return dao.findHtOrderResponseModels(busiId, status, detailAddr, dmId, consignee, mphoneNo, startCrtime, endCrtime);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}
	
	/**
	 * 返回转换后的数据列表
	 * transHtOrderResponseModels:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param htOrderResponseModelList
	 * @return
	 */
	public List<HtOrderResponseModel> transHtOrderResponseModels(List<HtOrderResponseModel> htOrderResponseModelList){
		if(htOrderResponseModelList!=null){
			for (int i = 0; i < htOrderResponseModelList.size(); i++) {
				HtOrderResponseModel htOrderResponseModel=htOrderResponseModelList.get(i);
				String paymentWay=htOrderResponseModel.getPaymentWay();
				if(paymentWay!=null&&paymentWay.trim().length()>0){
					paymentWay=PaymentMethodEnum.getTextByValue(Integer.valueOf(paymentWay));
					//设置成新的支付值
					htOrderResponseModel.setPaymentWay(paymentWay);
					htOrderResponseModelList.set(i, htOrderResponseModel);
				}
			}
		}
		return htOrderResponseModelList;
	}
	
	@Override
	public String getBusinessName(Long busiId) throws ServiceException {
		try {
			return dao.getBusinessName(busiId);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void modOrderIdCard() throws ServiceException {
		try {
			List<ModOrderModel> list = dao.getOrderList();
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					UserAuthen user = userAuthenDao.findCountByUserId(list.get(i).getUserId(), new UserAuthen());
					if (null != user) {
						list.get(i).setIdCard(user.getPno());
						list.get(i).setVipName(user.getName());
					}else{
						list.remove(i);
						i--;
					}
				}
				int result = dao.batchUpdateIdCard(list);
				log.debug("处理订单数量为 = " + result);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}