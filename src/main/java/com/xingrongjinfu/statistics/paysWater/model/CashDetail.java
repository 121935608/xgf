package com.xingrongjinfu.statistics.paysWater.model;

import java.math.BigDecimal;
import java.util.Date;

public class CashDetail {
    private String detailId;
    private String cashId;
    private String commodityId;
    private String commodityNo;
    private String commodityName;
    private BigDecimal number;
    private String unitName;
    private BigDecimal inPrice;
    private BigDecimal salePrice;
    private BigDecimal vipPrice;
    private BigDecimal totalPrice;
    private BigDecimal totalVipPrice;
    private Date addTime;
    private Integer status;
    private String commodityCode;
    private BigDecimal lirun;
    private String categoryName;
    
    
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public BigDecimal getLirun() {
        return lirun;
    }
    public void setLirun(BigDecimal lirun) {
        this.lirun = lirun;
    }
    public String getCommodityCode() {
        return commodityCode;
    }
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }
    public String getDetailId() {
        return detailId;
    }
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
    public String getCashId() {
        return cashId;
    }
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
    public String getCommodityId() {
        return commodityId;
    }
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityNo() {
        return commodityNo;
    }
    public void setCommodityNo(String commodityNo) {
        this.commodityNo = commodityNo;
    }
    public String getCommodityName() {
        return commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public BigDecimal getNumber() {
        return number;
    }
    public void setNumber(BigDecimal number) {
        this.number = number;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public BigDecimal getInPrice() {
        return inPrice;
    }
    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }
    public BigDecimal getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    public BigDecimal getVipPrice() {
        return vipPrice;
    }
    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public BigDecimal getTotalVipPrice() {
        return totalVipPrice;
    }
    public void setTotalVipPrice(BigDecimal totalVipPrice) {
        this.totalVipPrice = totalVipPrice;
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
