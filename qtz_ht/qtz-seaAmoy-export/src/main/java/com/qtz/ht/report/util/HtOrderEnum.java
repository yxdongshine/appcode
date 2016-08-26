package com.qtz.ht.report.util;

public class HtOrderEnum {

	/**
	 * 
	 * 订单状态枚举
	 * 
	 * @author Administrator
	 *
	 */
	public enum OrderStatus{
		/**
		 * 用户取消订单
		 */
		cancel(6,"用户取消订单"),
		
		/**
		 * 未支付  刚下单 
		 */
		unpay(1,"下单 未支付"),
		/**
		 * 待发货 	
		 */
		waitSend(5,"待发货"),
		/**
		 * 已发货
		 */
		waitTake(4,"已发货"),
		/**
		 * 商家拒绝接单
		 */
		refused(3,"商家拒绝接单"),
		/**
		 * 申请退款/退货中	
		 */
		refund(7,"申请退款/退货中"),
		/**
		 * 完成
		 */
		success(0,"交易成功 "),
		
		/**
		 * 同意退款
		 */
		agree_refund(2,"商家同意退款"),
		/**
		 * 不同意退款
		 */
		not_refund(8,"不同意退款");
		
		private int  id;//id
		private String desc;//描述
		private OrderStatus(int id,String desc){
			this.id=id;
			this.desc=desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

	public enum htOrderPrefix{
		
		batchPrefix("5","批量前缀"),
		
		sonPrefix("3","子订单前缀");
		
		private String  id;//id
		private String desc;//描述
		private htOrderPrefix(String id,String desc){
			this.id=id;
			this.desc=desc;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public enum applyLaunch{
		
		user_launch(1,"用户发起申请"),
		
		shop_launch(2,"商家的回应"),
		
		customer_service(3,"客服介入");
		
		private int  id;//id
		
		private String desc;//描述
		
		private applyLaunch(int id,String desc){
			
			this.id=id;
			
			this.desc=desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
	}
	
	public enum applyState{
		user_apply_state(1,"用户申请"),
		
		shop_agree_state(2,"商家同意"),
		
		shop_refuse_state(3,"商家拒绝"),
		
		user_deliver_state(4,"用户发货"),
		
		admin_arbitration(5,"客服仲裁");
		
		private int  id;//id
		
		private String desc;//描述
		
		private applyState(int id,String desc){
			
			this.id=id;
			
			this.desc=desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public enum reciptTypeState{
		
		not_received(1,"待发货"),
		
		received_goods(2,"已发货"),
		
		return_receipt(3,"退货");
		
		private int  id;//id
		
		private String desc;//描述
		
		private reciptTypeState(int id,String desc){
			
			this.id=id;
			
			this.desc=desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public enum sendTypeState{
		
		not_shipped(1,"未发货"),
		
		already_shipped(2,"已发货");
		
		private int  id;//id
		
		private String desc;//描述
		
		private sendTypeState(int id,String desc){
			
			this.id=id;
			
			this.desc=desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public enum interceptState{
		/**
		 * 开始截取位置
		 */
		start(0,"开始截取位置"),
		/**
		 * 不同意退款
		 */
		end(3,"结束截取位置");
		
		private int  id;//id
		
		private String desc;//描述
		
		private interceptState(int id,String desc){
			
			this.id=id;
			
			this.desc=desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public enum interventionState{
		/**
		 * 客服介入
		 */
		intervention(1,"客服介入"),
		handled(2,"以仲裁");
		
		private int  id;//id
		
		private String desc;//描述
		
		private interventionState(int id,String desc){
			
			this.id=id;
			
			this.desc=desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public enum userDeliverState{
		/**
		 * 用户未发货
		 */
		not_deliver(0,"未发货"),
		/**
		 * 用户发货
		 */
		deliver(1,"用户发货"),
		
		not_received(2,"用户未收到货");
		
		private int  id;//id
		
		private String desc;//描述
		
		private userDeliverState(int id,String desc){
			
			this.id=id;
			
			this.desc=desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public enum printFlagState{
		print(0,"打印"),not_print(1,"不打印");
		private int id;
		private String desc;
		private printFlagState(int id,String desc){
			this.id = id;
			this.desc = desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
	}
	
	public enum orderTypeEnum{
		supermarket(1,"超市"),shop(2,"便利店");
		private int id;
		private String desc;
		private orderTypeEnum(int id,String desc){
			this.id = id;
			this.desc = desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
	}
	
}
