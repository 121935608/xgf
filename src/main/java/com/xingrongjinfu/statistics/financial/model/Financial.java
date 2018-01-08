package com.xingrongjinfu.statistics.financial.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 */
public class Financial implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String amountId;
	private String userId;
	private Date addTime;
	private int totalMoney;
	private int xzfRate;
	private int amountMoney;
	private int status;
	private String amountNum;
	public String getAmountId() {
		return amountId;
	}
	public void setAmountId(String amountId) {
		this.amountId = amountId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	public int getXzfRate() {
		return xzfRate;
	}
	public void setXzfRate(int xzfRate) {
		this.xzfRate = xzfRate;
	}
	public int getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(int amountMoney) {
		this.amountMoney = amountMoney;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAmountNum() {
		return amountNum;
	}
	public void setAmountNum(String amountNum) {
		this.amountNum = amountNum;
	}
	@Override
	public String toString() {
		return "Financial [amountId=" + amountId + ", userId=" + userId + ", addTime=" + addTime + ", totalMoney="
				+ totalMoney + ", xzfRate=" + xzfRate + ", amountMoney=" + amountMoney + ", status=" + status
				+ ", amountNum=" + amountNum + "]";
	}
	
	
}
