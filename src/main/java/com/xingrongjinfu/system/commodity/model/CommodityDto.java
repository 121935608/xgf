package com.xingrongjinfu.system.commodity.model;

/**
 * @Author: fengqian
 * @Date: 2018/4/24 18:50
 * @Description:
 */
public class CommodityDto {

    /**
     * <result column="commodityName" property="commodityName" />
     <result column="commodityNo" property="commodityNo" />
     <result column="saleNum" property="saleNum" />
     <result column="salePrice" property="salePrice" />
     <result column="inPrice" property="inPrice" />
     <result column="unit" property="unit" />
     <result column="totalPrice" property="totalPrice" />
     <result column="totalInPrice" property="totalInPrice" />
     */

    private String commodityName;
    private String commodityNo;
    private String unit;
    private Integer saleNum;
    private Double totalPrice;
    private Double totalInPrice;
    private Double profit;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalInPrice() {
        return totalInPrice;
    }

    public void setTotalInPrice(Double totalInPrice) {
        this.totalInPrice = totalInPrice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
