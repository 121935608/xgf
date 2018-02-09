/**
 * Copyright (C), 2018
 * FileName: Commodity
 * Author:   zxuser
 * Date:     2018/1/3 14:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.commodity.model;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public class Commodity {

    private String commodityName;
    private String commodityNo;
    private Integer saleNum;
    private Double salePrice;
    private Double inPrice;
    private Double payTaxes;
    private Double profit;
    private String commodityId;
    private Double totalInPrice;
    private Double totalPrice;
    private String unit;

    
    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
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

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Double getPayTaxes() {
        return payTaxes;
    }

    public void setPayTaxes(Double payTaxes) {
        this.payTaxes = payTaxes;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getTotalInPrice() {
        return totalInPrice;
    }

    public void setTotalInPrice(Double totalInPrice) {
        this.totalInPrice = totalInPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Commodity [commodityName=" + commodityName + ", commodityNo=" + commodityNo + ", saleNum=" + saleNum
                + ", salePrice=" + salePrice + ", inPrice=" + inPrice + ", payTaxes=" + payTaxes + ", profit=" + profit
                + ", commodityId=" + commodityId + "]";
    }
    
}