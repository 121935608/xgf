package com.xingrongjinfu.system.storeaffairs.model;

import java.math.BigDecimal;
import java.util.Date;

public class RepayDetail {
    private String repayDetailId;
    private String repayId;
    private BigDecimal repayMoney;
    private Date repayTime;
    private Integer repayType;
    private String operator;
    private String remark;
    private String userId;
    public String getRepayDetailId() {
        return repayDetailId;
    }
    public void setRepayDetailId(String repayDetailId) {
        this.repayDetailId = repayDetailId;
    }
    public String getRepayId() {
        return repayId;
    }
    public void setRepayId(String repayId) {
        this.repayId = repayId;
    }
    public BigDecimal getRepayMoney() {
        return repayMoney;
    }
    public void setRepayMoney(BigDecimal repayMoney) {
        this.repayMoney = repayMoney;
    }
    public Date getRepayTime() {
        return repayTime;
    }
    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }
    public Integer getRepayType() {
        return repayType;
    }
    public void setRepayType(Integer repayType) {
        this.repayType = repayType;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "RepayDetail [repayDetailId=" + repayDetailId + ", repayId=" + repayId + ", repayMoney=" + repayMoney
                + ", repayTime=" + repayTime + ", repayType=" + repayType + ", operator=" + operator + ", remark="
                + remark + ", userId=" + userId + "]";
    }
    
    
}
