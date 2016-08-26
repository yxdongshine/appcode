package com.qtz.ht.report.order.dao.Impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mall.core.exception.DaoException;
import com.qtz.ht.report.order.dao.HtBusinessDao;
import com.qtz.ht.report.order.entity.HtBusiness;
import com.qtz.ht.report.order.entity.HtPlatformWalletLog;
import com.qtz.ht.report.order.entity.HtStaffPaymentFlow;
import com.qtz.ht.report.order.model.OrderModel;

@SuppressWarnings("deprecation")
@Repository
public class HtBusinessDaoImpl implements HtBusinessDao{
	 @Autowired
	 private JdbcTemplate htJdbcTemplate;
	@Override
	public HtBusiness getEntityById(Long busiId) throws DaoException {
		if (null == busiId) {
			return null;
		}
		String sql = "select dm_id,sett_discount,reco_cycle from ht_business where dm_id = ?";
		return htJdbcTemplate.queryForObject(sql, new Object[]{busiId}, ParameterizedBeanPropertyRowMapper.newInstance(HtBusiness.class));
	}
	@Override
	public int addSuppBatchRecord(final List<HtStaffPaymentFlow> suppList) throws DaoException {
		StringBuffer sql = new StringBuffer("INSERT INTO ht_staff_payment_flow (`dm_id`, `busi_id`, `order_id`, `amount`,");
		sql.append(" `platform_revenue`, `staff_revenue`, `payment_type`, `platform_points`, `income_time`, `release_time`,");
		sql.append(" `reco_status`, `crtime`) VALUES (?, ?, ?,?,?, ?, ?, ?, ?, ?, ?, ?)");
		int[] result = htJdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				int row = 1;
				ps.setLong(row, suppList.get(i).getDmId());
				row++;
				ps.setLong(row, suppList.get(i).getBusiId());
				row++;
				ps.setLong(row, suppList.get(i).getOrderId());
				row++;
				ps.setDouble(row, suppList.get(i).getAmount());
				row++;
				ps.setDouble(row, suppList.get(i).getPlatformRevenue());
				row++;
				ps.setDouble(row, suppList.get(i).getStaffRevenue());
				row++;
				ps.setInt(row, suppList.get(i).getPaymentType());
				row++;
				ps.setDouble(row, suppList.get(i).getPlatformPoints());
				row++;
				ps.setLong(row, suppList.get(i).getIncomeTime());
				row++;
				ps.setLong(row, suppList.get(i).getReleaseTime());
				row++;
				ps.setInt(row, suppList.get(i).getRecoStatus());
				row++;
				ps.setLong(row, suppList.get(i).getCrtime());
			}
			
			@Override
			public int getBatchSize() {
				return suppList.size();
			}
		});
		return result.length;
	}
	@Override
	public int addPlatBatchRecord(final List<HtPlatformWalletLog> platList) throws DaoException {
		StringBuffer sql = new StringBuffer("INSERT INTO ht_platform_wallet_log (`dm_id`, `busi_id`, `order_id`, `amount`, `income_type`, `payment_type`,");
		sql.append(" `platform_points`, `income_time`, `release_time`, `reco_status`, `crtime`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		int[] result = htJdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				int row = 1;
				ps.setLong(row, platList.get(i).getDmId());
				row++;
				ps.setLong(row, platList.get(i).getBusiId());
				row++;
				ps.setLong(row, platList.get(i).getOrderId());
				row++;
				ps.setDouble(row, platList.get(i).getAmount());
				row++;
				ps.setInt(row, platList.get(i).getIncomeType());
				row++;
				ps.setInt(row, platList.get(i).getPaymentType());
				row++;
				ps.setDouble(row, platList.get(i).getPlatformPoints());
				row++;
				ps.setLong(row, platList.get(i).getIncomeTime());
				row++;
				ps.setLong(row, platList.get(i).getReleaseTime());
				row++;
				ps.setInt(row, platList.get(i).getRecoStatus());
				row++;
				ps.setLong(row, platList.get(i).getCrtime());
			}
			
			@Override
			public int getBatchSize() {
				return platList.size();
			}
		});
		return result.length;
	}
	@Override
	public int modBatchWallet(final List<OrderModel> reconciliationList) throws DaoException {
		String sql = "update ht_wallet set total_reconciliation = coalesce(total_reconciliation,0) + ? where busi_id = ?";
		int[] result = htJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setDouble(1, reconciliationList.get(i).getOrderPrice());
				ps.setLong(2, reconciliationList.get(i).getSellerId());
			}
			
			@Override
			public int getBatchSize() {
				return reconciliationList.size();
			}
		});
		return result.length;
	}
	
//	@Override
//	public void modWallet(Long busiId, Double amount) throws DaoException {
//		if (null == busiId || null == amount) {
//			throw new DaoException("入参错误");
//		}
//		String sql = "update ht_wallet set total_reconciliation = coalesce(total_reconciliation,0) + ? where busi_id = ?";
//		htJdbcTemplate.update(sql, new Object[]{amount, busiId});
//	}
	
	/**
	* 【批量修改订单进行发货处理】
	* @param dataSet
	* @return  
	*/
//	int[] BatchUpdateAudit(List<BulkShippingModel> dataSet)throws DaoException;
}
