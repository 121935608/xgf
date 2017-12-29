package com.xingrongjinfu.system.storeaffairs.model;
 
import java.util.Date; 
 


	/**
	 * 审核申请对象  t_Order
	 * 
	 * @author cj
	 */
public class Order {
	    private static final long serialVersionUID = 1L;
	    
	    private String orderId;
	    private String orderNumber;  //'订单编号',
	    private String addressId;    //'送货地址',
	    private Date orderTime;      //'下单时间',
	    private Date payTime;        //'支付时间',
	    private Date arriveTime;     //'到货时间',
	    private Integer orderStatus; //'订单状态（1:待支付；2:代发货；3:待收货；4:已完成）',
	    private String  userId;      //'用户id',
	    private Integer totalPrice;  //'总费用',
	    private Integer number;      //'总件数',
	    private Integer status;      //'状态：1 正常 -1 已删除',
	    private Integer freight;     //'运费',
	    private Integer payment;     //'账期金额',
	    private String paymentNumber;//还款单号
	    
	    
	    private String storeName;    //'商品名称',
	       
	    public String getOrderId() {
			return orderId;
		}


		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}


		public String getOrderNumber() {
			return orderNumber;
		}


		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}


		public String getAddressId() {
			return addressId;
		}


		public void setAddressId(String addressId) {
			this.addressId = addressId;
		}


		public Date getOrderTime() {
			return orderTime;
		}


		public void setOrderTime(Date orderTime) {
			this.orderTime = orderTime;
		}


		public Date getPayTime() {
			return payTime;
		}


		public void setPayTime(Date payTime) {
			this.payTime = payTime;
		}


		public Date getArriveTime() {
			return arriveTime;
		}


		public void setArriveTime(Date arriveTime) {
			this.arriveTime = arriveTime;
		}


		public Integer getOrderStatus() {
			return orderStatus;
		}


		public void setOrderStatus(Integer orderStatus) {
			this.orderStatus = orderStatus;
		}

 
		public String getUserId() {
			return userId;
		}


		public void setUserId(String userId) {
			this.userId = userId;
		}


		public Integer getTotalPrice() {
			return totalPrice;
		}


		public void setTotalPrice(Integer totalPrice) {
			this.totalPrice = totalPrice;
		}


		public Integer getNumber() {
			return number;
		}


		public void setNumber(Integer number) {
			this.number = number;
		}


		public Integer getStatus() {
			return status;
		}


		public void setStatus(Integer status) {
			this.status = status;
		}


		public Integer getFreight() {
			return freight;
		}


		public void setFreight(Integer freight) {
			this.freight = freight;
		}


		public Integer getPayment() {
			return payment;
		}


		public void setPayment(Integer payment) {
			this.payment = payment;
		}

		
 


		public String getStoreName() {
			return storeName;
		}


		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}


		public String getPaymentNumber() {
			return paymentNumber;
		}


		public void setPaymentNumber(String paymentNumber) {
			this.paymentNumber = paymentNumber;
		}


		public String toString()
	    {
	        return  "Order [orderId=" + orderId + 
	    			", orderNumber=" + orderNumber + 
	    			", addressId=" + addressId + 
	    			", orderTime=" + orderTime+ 
	    			", payTime=" + payTime + 
	    			", arriveTime=" + arriveTime + 
	    			", orderStatus=" + orderStatus +  
	    			", userId=" + userId + 
	    			", totalPrice=" + totalPrice + 
	    			", number=" + number + 
	    			", status=" + status + 
	    			", freight=" + freight + 
	    			", payment=" + payment +  
	    			", storename=" + storeName + 
	    			", paymentNumber=" + paymentNumber +
	    			"]";
	        		
	    }

	} 
