package com.xingrongjinfu.commercial.storeApply.model;

import java.util.Date;

public class StoreApply {
    private Integer num;
    private String applyId;
    private String applyName;
    private String phone;
    private String storeName;
    private String storeAdrr;
    private String detailAdrr;
    private Date dealTime;
    private String dealResult;
    private String dealUser;
    private Date addTime;
    private Integer status;
    private Integer dealStatus;
    
    
    public Integer getDealStatus() {
        return dealStatus;
    }
    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public String getApplyId() {
        return applyId;
    }
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
    public String getApplyName() {
        return applyName;
    }
    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getStoreAdrr() {
        return storeAdrr;
    }
    public void setStoreAdrr(String storeAdrr) {
        this.storeAdrr = storeAdrr;
    }
    public String getDetailAdrr() {
        return detailAdrr;
    }
    public void setDetailAdrr(String detailAdrr) {
        this.detailAdrr = detailAdrr;
    }
    public Date getDealTime() {
        return dealTime;
    }
    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }
    public String getDealResult() {
        return dealResult;
    }
    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }
    public String getDealUser() {
        return dealUser;
    }
    public void setDealUser(String dealUser) {
        this.dealUser = dealUser;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
