package com.qtz.ht.report.order.dao.Impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mall.core.exception.DaoException;
import com.qtz.ht.report.order.dao.OrderDao;
import com.qtz.ht.report.order.entity.OrderLog;
import com.qtz.ht.report.order.model.BulkShippingModel;
import com.qtz.ht.report.order.model.HtOrderResponseModel;
import com.qtz.ht.report.order.model.ModOrderModel;
import com.qtz.ht.report.order.model.OrderModel;
import com.qtz.ht.report.util.HtOrderEnum;

@SuppressWarnings("deprecation")
@Repository
public class OrderDaoImpl implements OrderDao{
	 @Autowired
	 private JdbcTemplate orderJdbcTemplate;
	 @Autowired
	 private JdbcTemplate htJdbcTemplate;
	
	 @Override
	 public int[] batchUpdateAudit(List<BulkShippingModel> dataSet)throws DaoException {
        String updateSql = "update ht_order set express = ?,express_code=?,delivery_time = ?,`status`=?,recipt_type=? where `status` = ? and dm_id = ?";
        List<Object[]> list = new ArrayList<Object[]>();
        
        long deliveryTime = System.currentTimeMillis();
        for (Iterator<BulkShippingModel> iterator = dataSet.iterator(); iterator.hasNext();) {
        	BulkShippingModel wcspResColRef = iterator.next();
            
            Object[] obj = {wcspResColRef.getLogisticsCompany(), wcspResColRef.getLogisticsNumber(),deliveryTime,
            		 HtOrderEnum.OrderStatus.waitTake.getId(),HtOrderEnum.reciptTypeState.received_goods.getId(),
            		 HtOrderEnum.OrderStatus.waitSend.getId(), wcspResColRef.getOrderId()};
            list.add(obj);
        }
        int[] reslut = {};
        try {
        	reslut = this.orderJdbcTemplate.batchUpdate(updateSql, list); 
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
        return reslut;  
    }
	 
	 @Override
	 public List<HtOrderResponseModel> findHtOrderResponseModels(Long busiId, Integer status, String detailAddr,
			Long dmId, String consignee, String mphoneNo, Long startCrtime, Long endCrtime) throws DaoException {
		 if (null == busiId) {
			throw new DaoException("请传入商家编号！");
		}
		StringBuffer sbSql = new StringBuffer("SELECT o.dm_id dmId,og.good_spec AS goodsStandard,(CASE o.`status` WHEN 5 THEN '待发货' WHEN 4 THEN '已发货' WHEN 0 THEN '交易成功' WHEN 1 THEN '未付款' WHEN 2 THEN '商家同意退款' WHEN 3 THEN '商家拒绝接单' WHEN 6 THEN '用户取消订单' WHEN 7 THEN '申请退款/退货中' WHEN 8 THEN '商家拒绝退款' WHEN 9 THEN '商家同意退货'");
		sbSql.append(" WHEN 0 THEN '已完成' END) AS status,o.vip_name,og.goods_name,og.final_Price,og.sale_money,og.goods_num,o.count_money as payMoney,(og.final_Price*og.goods_num) as orderFinalPrice,o.consignee,o.detail_addr,");
		sbSql.append("o.house_number,o.mphone_no,o.id_card,o.consignee as idName,FROM_UNIXTIME(o.crtime/1000) AS crtime,FROM_UNIXTIME(o.pay_time/1000) AS pay_time,");
		sbSql.append("o.deduction_price,o.payment_way,o.staff_name,o.note_vip FROM ht_order AS o INNER JOIN ht_order_goods AS og on o.dm_id = og.order_id WHERE o.staff_code= ? ");
		List<Object> params = new ArrayList<>();
		params.add(busiId);
		if (null != status) {
			if(status.intValue()==10){
				sbSql.append(" and (o.`status` = 7 || o.`status` = 2 ) ");
			}else{
				sbSql.append(" and o.`status` = ?");
				params.add(status);
			}

		}
		if(detailAddr!=null && detailAddr.trim().length()>0){
			sbSql.append(" and o.detail_addr like '%?%'");
			params.add(detailAddr.trim());
		}
		if (null != dmId) {
			sbSql.append(" and o.dm_id = ?");
			params.add(dmId);
		}
		if(consignee!=null && consignee.trim().length()>0){
			sbSql.append(" and o.consignee like '%?%'");
			params.add(consignee.trim());
		}
		if(mphoneNo!=null && mphoneNo.trim().length()>0){
			sbSql.append(" and o.mphoneNo ='?'");
			params.add(mphoneNo.trim());
		}
		if(startCrtime!=null){
			sbSql.append(" and o.pay_time >= ?");
			params.add(startCrtime);
		}
		if(endCrtime!=null){
			sbSql.append(" and o.pay_time <= ?");
			params.add(endCrtime);
		}
		sbSql.append(" ORDER BY o.pay_time ASC");
		return orderJdbcTemplate.query(sbSql.toString(), params.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(HtOrderResponseModel.class));
	}
		

	@Override
	public String getBusinessName(Long busiId) throws DaoException {
		if (null == busiId) {
			throw new DaoException("入参错误！ getBusinessName(" + busiId + ")");
		}
		String name= "";
		List<Object> params = new ArrayList<>();
		params.add(busiId);
		StringBuffer sbSql = new StringBuffer("select full_name as fullName from ht_business where dm_id = ? ");
		List<String> nameList = htJdbcTemplate.queryForList(sbSql.toString(), params.toArray(), String.class);
		if(nameList!=null&&nameList.size()>0){
			name = nameList.get(0);
		}
		return name;
//			return "胖胖生活测试";
	}


	@Override
	public List<ModOrderModel> getOrderList() throws DaoException {
		String sql = " select dm_id as orderId,vip_code as userId from ht_order t where id_card is null ";
		return htJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ModOrderModel.class));
	}


	@Override
	public int batchUpdateIdCard(List<ModOrderModel> list) throws DaoException{
		 String updateSql = "update ht_order set id_card= ? where dm_id = ?";
	        List<Object[]> params = new ArrayList<Object[]>();
	        
	        for (Iterator<ModOrderModel> iterator = list.iterator(); iterator.hasNext();) {
	        	ModOrderModel wcspResColRef = iterator.next();
	            if (null != wcspResColRef.getIdCard()) {
	            	Object[] obj = {wcspResColRef.getIdCard(), wcspResColRef.getOrderId()};
	            	params.add(obj);
				}
	        }
	        int[] reslut = {};
	        try {
	        	reslut = this.orderJdbcTemplate.batchUpdate(updateSql, params); 
			} catch (DataAccessException e) {
				throw new DaoException(e);
			}
	        return reslut.length;
	}

	@Override
	public List<OrderModel> getOrderList(List<BulkShippingModel> subList)throws DaoException {
		if (null == subList || subList.isEmpty()) {
			return null;
		}
		List<Object> params = new ArrayList<>();
		StringBuffer sbSql = new StringBuffer("select t.dm_id dmId,t.staff_code sellerId,t.count_money orderPrice,");
		sbSql.append("t.payment_way paymentMethod from ht_order t where `status` = ? and dm_id in (");
		params.add(HtOrderEnum.OrderStatus.waitSend.getId());
		for (int i = 0; i < subList.size(); i++) {
			if (i == 0 ) {
				sbSql.append("?");
			}else{
				sbSql.append(",?");
			}
			params.add(subList.get(i).getOrderId());
		}
		sbSql.append(")");
		return orderJdbcTemplate.query(sbSql.toString(), params.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(OrderModel.class));
	}

	@Override
	public void addBatchOrderLogRecord(final List<OrderLog> logList) {
		
		String sql = "INSERT INTO ht_order_log (dmId, orderId, `time`, `status`, `notes`) VALUES (?, ?, ?, ?, ?)";
		orderJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				int row = 1;
				ps.setLong(row, logList.get(i).getDmId());
				row++;
				ps.setLong(row, logList.get(i).getOrderId());
				row++;
				ps.setLong(row, logList.get(i).getTime());
				row++;
				ps.setInt(row, logList.get(i).getStatus());
				row++;
				ps.setString(row, logList.get(i).getNotes());
			}
			
			@Override
			public int getBatchSize() {
				return logList.size();
			}
		});
	}
}
