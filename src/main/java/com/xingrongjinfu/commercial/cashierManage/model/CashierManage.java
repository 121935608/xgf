package com.xingrongjinfu.commercial.cashierManage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * t_cashier
 * 
 * @author
 */
public class CashierManage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cashierId;
	private String cashierName;
	private String password;
	private String userId;
	private int status;
	private Date addTime;
	private Date updateTime;
	public String getCashierId() {
		return cashierId;
	}
	public void setCashierId(String cashierId) {
		this.cashierId = cashierId;
	}
	public String getCashierName() {
		return cashierName;
	}
	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "CashierManage [cashierId=" + cashierId + ", cashierName=" + cashierName + ", password=" + password
				+ ", userId=" + userId + ", status=" + status + ", addTime=" + addTime + ", updateTime=" + updateTime
				+ "]";
	}
}
