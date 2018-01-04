package com.xingrongjinfu.system.storeaffairs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.xingrongjinfu.system.permission.model.Permission;


	/**
	 * 角色管理对象  t_store
	 * 
	 * @author cj
	 */
public class Repay {
	    private static final long serialVersionUID = 1L;
	    private String repayId;  //'主键Id',
	    private String repayNo;  //'还款单号',
	    private String orderId;  //'订单id',
	    private String userId;  //'用户Id',
	    private String planTotal;  //'应还总额（分）',
	    private Date planRepayDate;  //'应还日期',
	    private String repayMoney;  //'实还（分）',
	    private Date repayDate;  //'实际还款日期',
	    private String withholdMoney;  //'代扣金额',
	    private Date withholdDate;  //'代扣时间',
	    private String dueFee;  //'逾期费用（分）',
	    private Date addTime;  //'添加时间',
	    private String status;  //'状态：0 待还款 1 已还款 -1 未出账',
	    private String remark;  //'备注'
	    
	    private String orderNumber;  //'状态：0 待还款 1 已还款 -1 未出账',
	    private String storeName;  //'备注'
	    
	    
		public String getOrderNumber() {
			return orderNumber;
		}



		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}



		public String getStoreName() {
			return storeName;
		}



		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}



		public String getRepayId() {
			return repayId;
		}



		public void setRepayId(String repayId) {
			this.repayId = repayId;
		}



		public String getRepayNo() {
			return repayNo;
		}



		public void setRepayNo(String repayNo) {
			this.repayNo = repayNo;
		}



		public String getOrderId() {
			return orderId;
		}



		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}



		public String getUserId() {
			return userId;
		}



		public void setUserId(String userId) {
			this.userId = userId;
		}



		public String getPlanTotal() {
			return planTotal;
		}



		public void setPlanTotal(String planTotal) {
			this.planTotal = planTotal;
		}



		public Date getPlanRepayDate() {
			return planRepayDate;
		}



		public void setPlanRepayDate(Date planRepayDate) {
			this.planRepayDate = planRepayDate;
		}



		public String getRepayMoney() {
			return repayMoney;
		}



		public void setRepayMoney(String repayMoney) {
			this.repayMoney = repayMoney;
		}



		public Date getRepayDate() {
			return repayDate;
		}



		public void setRepayDate(Date repayDate) {
			this.repayDate = repayDate;
		}



		public String getWithholdMoney() {
			return withholdMoney;
		}



		public void setWithholdMoney(String withholdMoney) {
			this.withholdMoney = withholdMoney;
		}



		public Date getWithholdDate() {
			return withholdDate;
		}



		public void setWithholdDate(Date withholdDate) {
			this.withholdDate = withholdDate;
		}



		public String getDueFee() {
			return dueFee;
		}



		public void setDueFee(String dueFee) {
			this.dueFee = dueFee;
		}



		public Date getAddTime() {
			return addTime;
		}



		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}



		public String getStatus() {
			return status;
		}



		public void setStatus(String status) {
			this.status = status;
		}



		public String getRemark() {
			return remark;
		}



		public void setRemark(String remark) {
			this.remark = remark;
		}



		public String toString()
	    {
	        return "Repay [repayId=" + repayId +
	        		", repayNo=" + repayNo + 
	        		", orderId=" + orderId + 
	        		", userId=" + userId + 
	        		", planTotal=" + planTotal + 
	        		", planRepayDate=" + planRepayDate +
	        		", repayMoney=" + repayMoney +
	        		", repayDate=" + repayDate +
	        		", withholdMoney=" + withholdMoney +
	        		", withholdDate=" + withholdDate +
	        		", dueFee=" + dueFee +
	        		", addTime=" + addTime +
	        		", status=" + status +
	        		", remark=" + remark + 
	        		", orderNumber=" + orderNumber + 
	        		", storeName=" + storeName + 
	        		"]";
	    }
 
}
