package com.xingrongjinfu.system.storeaffairs.model;

import java.math.BigDecimal;
import java.util.Date;


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
	    private BigDecimal planTotal;  //'应还总额（分）',
	    private Date planRepayDate;  //'应还日期',
	    private BigDecimal repayMoney;  //'实还（分）',
	    private Date repayDate;  //'实际还款日期',
	    private BigDecimal withholdMoney;  //'代扣金额',
	    private Date withholdDate;  //'代扣时间',
	    private BigDecimal dueFee;  //'逾期费用（分）',
	    private Date addTime;  //'添加时间',
	    private Date updateTime;  
	    private Integer status;  //'状态：0 待还款 1 已还款 -1 未出账',
	    private Integer repayType;  
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






		public Date getPlanRepayDate() {
			return planRepayDate;
		}



		public void setPlanRepayDate(Date planRepayDate) {
			this.planRepayDate = planRepayDate;
		}






		public Date getRepayDate() {
			return repayDate;
		}



		public void setRepayDate(Date repayDate) {
			this.repayDate = repayDate;
		}





		public Date getWithholdDate() {
			return withholdDate;
		}



		public void setWithholdDate(Date withholdDate) {
			this.withholdDate = withholdDate;
		}

		public Date getAddTime() {
			return addTime;
		}



		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}



		public BigDecimal getPlanTotal() {
            return planTotal;
        }



        public void setPlanTotal(BigDecimal planTotal) {
            this.planTotal = planTotal;
        }



        public BigDecimal getRepayMoney() {
            return repayMoney;
        }



        public void setRepayMoney(BigDecimal repayMoney) {
            this.repayMoney = repayMoney;
        }



        public BigDecimal getWithholdMoney() {
            return withholdMoney;
        }



        public void setWithholdMoney(BigDecimal withholdMoney) {
            this.withholdMoney = withholdMoney;
        }



        public BigDecimal getDueFee() {
            return dueFee;
        }



        public void setDueFee(BigDecimal dueFee) {
            this.dueFee = dueFee;
        }



        public Date getUpdateTime() {
            return updateTime;
        }



        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }



        public Integer getStatus() {
            return status;
        }



        public void setStatus(Integer status) {
            this.status = status;
        }



        public Integer getRepayType() {
            return repayType;
        }



        public void setRepayType(Integer repayType) {
            this.repayType = repayType;
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
