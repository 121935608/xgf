package com.xingrongjinfu.statistics.pays.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 */
public class PaysDto
{

    
	private String cashNo;
	private Date addTime;
	private double money;
	private String payType;
	private String tradeCode;
	private String cashName;

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getCashNo() {
		return cashNo;
	}

	public void setCashNo(String cashNo) {
		this.cashNo = cashNo;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		if(payType==1){
			this.payType = "支付宝支付";
		}else if(payType==2){
			this.payType = "微信支付";
		}else{
			this.payType = "现金";
		}
	}

	public String getCashName() {
		return cashName;
	}

	public void setCashName(String cashName) {
		this.cashName = cashName;
	}
}
