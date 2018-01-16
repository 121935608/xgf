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
    private Integer salePrice;
    private Integer inPrice;
    private Integer payTaxes;
    private Integer profit;
    private String commodityId;

    
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

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getInPrice() {
        return inPrice;
    }

    public void setInPrice(Integer inPrice) {
        this.inPrice = inPrice;
    }

    public Integer getPayTaxes() {
        return payTaxes;
    }

    public void setPayTaxes(Integer payTaxes) {
        this.payTaxes = payTaxes;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Commodity [commodityName=" + commodityName + ", commodityNo=" + commodityNo + ", saleNum=" + saleNum
                + ", salePrice=" + salePrice + ", inPrice=" + inPrice + ", payTaxes=" + payTaxes + ", profit=" + profit
                + ", commodityId=" + commodityId + "]";
    }
    
}