package com.xingrongjinfu.system.financial.model;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialDetail {
    private String amountDetailId;
    private String amountId;
    private BigDecimal money;
    private Date addTime;
    private String userId;
    private String remark;
    private String userName;
    
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getAmountDetailId() {
        return amountDetailId;
    }
    public void setAmountDetailId(String amountDetailId) {
        this.amountDetailId = amountDetailId;
    }
    public String getAmountId() {
        return amountId;
    }
    public void setAmountId(String amountId) {
        this.amountId = amountId;
    }
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
}
