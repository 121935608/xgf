package com.xingrongjinfu.statistics.cashCount.model;

import java.util.Date;

public class Amount {
    private String amountId;
    private String userId;
    private String storeId;
    private Date addTime;
    private double totalMoney;
    private double xzfRate;
    private double amountMoney;
    private int status;
    private String amountNum;
    private int amountStatus;

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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getXzfRate() {
        return xzfRate;
    }

    public void setXzfRate(double xzfRate) {
        this.xzfRate = xzfRate;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
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

    public int getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(int amountStatus) {
        this.amountStatus = amountStatus;
    }
}
