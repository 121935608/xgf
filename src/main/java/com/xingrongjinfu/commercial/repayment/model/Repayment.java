package com.xingrongjinfu.commercial.repayment.model;

import java.io.Serializable;
import java.util.Date;

/**
 * t_order
 * 
 * @author
 */
public class Repayment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String repayId;
	private String repayNo;
	private String orderId;
	private String userId;
	private double planTotal;
	private Date planRepayDate;
	private double repayMoney;
	private Date repayDate;
	private double withholdMoney;
	private Date withholdDate;
	private double dueFee;
	private Date addTime;
	private int status;
	private String remark;
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
	public double getPlanTotal() {
		return planTotal;
	}
	public void setPlanTotal(double planTotal) {
		this.planTotal = planTotal;
	}
	public Date getPlanRepayDate() {
		return planRepayDate;
	}
	public void setPlanRepayDate(Date planRepayDate) {
		this.planRepayDate = planRepayDate;
	}
	public double getRepayMoney() {
		return repayMoney;
	}
	public void setRepayMoney(double repayMoney) {
		this.repayMoney = repayMoney;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	public double getWithholdMoney() {
		return withholdMoney;
	}
	public void setWithholdMoney(double withholdMoney) {
		this.withholdMoney = withholdMoney;
	}
	public Date getWithholdDate() {
		return withholdDate;
	}
	public void setWithholdDate(Date withholdDate) {
		this.withholdDate = withholdDate;
	}
	public double getDueFee() {
		return dueFee;
	}
	public void setDueFee(double dueFee) {
		this.dueFee = dueFee;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Repayment [repayId=" + repayId + ", repayNo=" + repayNo + ", orderId=" + orderId + ", userId=" + userId
				+ ", planTotal=" + planTotal + ", planRepayDate=" + planRepayDate + ", repayMoney=" + repayMoney
				+ ", repayDate=" + repayDate + ", withholdMoney=" + withholdMoney + ", withholdDate=" + withholdDate
				+ ", dueFee=" + dueFee + ", addTime=" + addTime + ", status=" + status + ", remark=" + remark + "]";
	}
	
}
