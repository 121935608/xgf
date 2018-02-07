 package com.xingrongjinfu.commodity.register.model;

import java.math.BigDecimal;

public class RegisterExp {
  //序号","编码","商品名称","条码","供货商","分类","单位","进价（元）","售价（元）","会员价（元）","折扣","库存","库存上限","库存下限","状态"
    public Integer num;//序号
    public String commodityCode;//商品编码
    public String commodityName;//商品名字
    public String commodityNo;//商品条码
    public String supplierName;//供应商名
    public String categoryName;//分类名
    public String unitName;//单位名
    public double inPrice;//进价（单位为分）
    public double salePrice;//销售价格（单位为分）
    public double vipPrice;//会员价
    public String discount;//商品折扣
    public BigDecimal stockNum;//库存
    public Integer upperLimit;//库存上限
    public Integer lowerLimit;//库存下限
    public String commodityStatus;//商品状态 0:启用；-1:禁用
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public String getCommodityCode() {
        return commodityCode;
    }
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }
    public String getCommodityName() {
        return commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getCommodityNo() {
        return commodityNo;
    }
    public void setCommodityNo(String commodityNo) {
        this.commodityNo = commodityNo;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public double getInPrice() {
        return inPrice;
    }
    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }
    public double getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
    public double getVipPrice() {
        return vipPrice;
    }
    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public BigDecimal getStockNum() {
        return stockNum;
    }
    public void setStockNum(BigDecimal stockNum) {
        this.stockNum = stockNum;
    }
    public Integer getUpperLimit() {
        return upperLimit;
    }
    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }
    public Integer getLowerLimit() {
        return lowerLimit;
    }
    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
    public String getCommodityStatus() {
        return commodityStatus;
    }
    public void setCommodityStatus(String commodityStatus) {
        this.commodityStatus = commodityStatus;
    }
    
    
}
