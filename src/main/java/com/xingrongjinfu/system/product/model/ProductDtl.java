package com.xingrongjinfu.system.product.model;

/**
 * 批量修改商品价格,状态
 */
public class ProductDtl {
    public String commodityNo;//商品条码
    public String subPrice;//主观价
    public String subPriceUnit;//主观价单位
    public String priceSpecification;//客观价
    public String priceSpecificationUnit;//客观价单位
    public String  commodityStatus;//状态：1 正常 -1 已删除

    public String getCommodityNo() {
        return commodityNo;
    }

    public void setCommodityNo(String commodityNo) {
        this.commodityNo = commodityNo;
    }

    public String getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(String subPrice) {
        this.subPrice = subPrice;
    }

    public String getSubPriceUnit() {
        return subPriceUnit;
    }

    public void setSubPriceUnit(String subPriceUnit) {
        this.subPriceUnit = subPriceUnit;
    }

    public String getPriceSpecification() {
        return priceSpecification;
    }

    public void setPriceSpecification(String priceSpecification) {
        this.priceSpecification = priceSpecification;
    }

    public String getPriceSpecificationUnit() {
        return priceSpecificationUnit;
    }

    public void setPriceSpecificationUnit(String priceSpecificationUnit) {
        this.priceSpecificationUnit = priceSpecificationUnit;
    }

    public String getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(String commodityStatus) {
        this.commodityStatus = commodityStatus;
    }
}
