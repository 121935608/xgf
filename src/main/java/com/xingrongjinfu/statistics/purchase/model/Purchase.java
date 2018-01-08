package com.xingrongjinfu.statistics.purchase.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 */
public class Purchase implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String orderDetailId;
	private String orderNumber;
	private String commodityId;
	private int commodityNum;
	private String commodityName;
	private Date addTime;
	private int inPrice;
	private int salePrice;
	private double taxRate;
	private Date unit;
	private String imgMain;
	private String totalPrice;
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public int getCommodityNum() {
		return commodityNum;
	}
	public void setCommodityNum(int commodityNum) {
		this.commodityNum = commodityNum;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getInPrice() {
		return inPrice;
	}
	public void setInPrice(int inPrice) {
		this.inPrice = inPrice;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public Date getUnit() {
		return unit;
	}
	public void setUnit(Date unit) {
		this.unit = unit;
	}
	public String getImgMain() {
		return imgMain;
	}
	public void setImgMain(String imgMain) {
		this.imgMain = imgMain;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "Procurement [orderDetailId=" + orderDetailId + ", orderNumber=" + orderNumber + ", commodityId="
				+ commodityId + ", commodityNum=" + commodityNum + ", commodityName=" + commodityName + ", addTime="
				+ addTime + ", inPrice=" + inPrice + ", salePrice=" + salePrice + ", taxRate=" + taxRate + ", unit="
				+ unit + ", imgMain=" + imgMain + ", totalPrice=" + totalPrice + "]";
	}	
}
