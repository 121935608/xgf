package com.xingrongjinfu.commodity.register.model;

import java.util.Date;

public class Register {
    public String commodityName;//商品名字
    public String commodityNo;//商品条码
    public String categoryCode;//分类编号
    public String unitCode;//单位编号
    public String supplierCode;//供应商编号
    public Double salePrice;//销售价格（单位为分）
    public Double inPrice;//进价（单位为分）
    public Double vipPrice;//会员价
    public Integer discount;//是否折扣，1是，-1否
    public Integer stockNum;//库存
    public Integer upperLimit;//库存上限
    public Integer lowerLimit;//库存下限
    
    public String commodityCode;//商品编码
    public String supplierName;//供应商名
    public String categoryName;//分类名
    public String unitName;//单位名
    public String imgMain;//主图片
    public String specification;//商品规格
    public double weight;//商品重量
    public String grade;//商品等级
    public String storage;//存储方式
    public String info;//商品介绍
    public Integer num;//序号
    
    public String storeId;//店铺Id
    public String commodityStatus;//商品状态 0:启用；-1:禁用
    public String commodityId;//商品Id
    public String categoryId;//商品类别
    public String unitId;//商品单位
    public String supplierId;//供应商
    public Date productionDate;//生产日期
    public Integer guaranteeDay;//保质期
    public Date addTime;//添加时间
    public Date updateTime;//修改时间
    public Integer status;//状态：1 正常 -1 已删除
    public String tag;//商品标签
    
    
    
    
    
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getUnitCode() {
        return unitCode;
    }
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
    
    public String getSupplierCode() {
        return supplierCode;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getUnitId() {
        return unitId;
    }
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public String getCommodityCode() {
        return commodityCode;
    }
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }
    public String getCommodityId() {
        return commodityId;
    }
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
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
    public String getImgMain() {
        return imgMain;
    }
    public void setImgMain(String imgMain) {
        this.imgMain = imgMain;
    }
    public String getSpecification() {
        return specification;
    }
    public void setSpecification(String specification) {
        this.specification = specification;
    }
    public Double getInPrice() {
        return inPrice;
    }
    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }
    public Double getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
    public Double getVipPrice() {
        return vipPrice;
    }
    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String getCommodityStatus() {
        return commodityStatus;
    }
    public void setCommodityStatus(String commodityStatus) {
        this.commodityStatus = commodityStatus;
    }
    public Integer getStockNum() {
        return stockNum;
    }
    public void setStockNum(Integer stockNum) {
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
    public Integer getDiscount() {
        return discount;
    }
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
    public Integer getGuaranteeDay() {
        return guaranteeDay;
    }
    public void setGuaranteeDay(Integer guaranteeDay) {
        this.guaranteeDay = guaranteeDay;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getStorage() {
        return storage;
    }
    public void setStorage(String storage) {
        this.storage = storage;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
}
