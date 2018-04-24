package com.xingrongjinfu.system.pays.model;

import java.util.Date;

/**
 * @Author: fengqian
 * @Date: 2018/4/24 18:04
 * @Description:
 */
public class PaysDto {
    private String tradeCode;
    private Date addTime;
    private String storeName;
    private Double money;
    private String payType;

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
