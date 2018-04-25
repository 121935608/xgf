package com.xingrongjinfu.system.commodity.model;

import java.util.Date;

/**
 * @Author: fengqian
 * @Date: 2018/4/24 18:50
 * @Description:
 */
public class CommodityRegDto {



    private String commodityName;

    private String commodityNo;
    /**   */
    private String orderNumber;
    /**创建时间 */
    private Date addTime;
    /**
     * 单位
     */
    private String unit;
    /**
     * 销售数量
     */
    private Integer commodityNum;
    /**
     * 销售金额（元）
     */
    private Double totalPrice;

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
