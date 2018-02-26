package com.xingrongjinfu.memberManage.level.model;

import java.math.BigDecimal;
import java.util.Date;

public class Level {
    private String levelId;
    private String levelNo;
    private String storeId;
    private BigDecimal discount;
    private Integer score;
    private Date addTime;
    private String remark;
    private Integer status;
    private String addWay;
    
    public String getAddWay() {
        return addWay;
    }
    public void setAddWay(String addWay) {
        this.addWay = addWay;
    }
    public String getLevelId() {
        return levelId;
    }
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
    public String getLevelNo() {
        return levelNo;
    }
    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
