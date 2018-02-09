package com.xingrongjinfu.statistics.pays.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 */
public class Pays implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String cashId;
	private String userId;
	private String tradeCode;
	private int payType;
	private double money;
	private int status;
	private String remark;
	private Date addTime;
	private String addIP;
	private String storeId;
	private String cashNo;
	private String cashName;

	public String getCashName() {
		return cashName;
	}

	public void setCashName(String cashName) {
		this.cashName = cashName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getCashNo() {
		return cashNo;
	}

	public void setCashNo(String cashNo) {
		this.cashNo = cashNo;
	}

	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
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
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getAddIP() {
		return addIP;
	}
	public void setAddIP(String addIP) {
		this.addIP = addIP;
	}
	@Override
	public String toString() {
		return "Pays [cashId=" + cashId + ", userId=" + userId + ", tradeCode=" + tradeCode + ", payType=" + payType
				+ ", money=" + money + ", status=" + status + ", remark=" + remark + ", addTime=" + addTime + ", addIP="
				+ addIP + "]";
	}	
}
