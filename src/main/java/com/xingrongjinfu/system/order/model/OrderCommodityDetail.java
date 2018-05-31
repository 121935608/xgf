package com.xingrongjinfu.system.order.model;

/** @Author: niu @Data: 2018/5/23 @Description: 用于返回前端审核页面的订单明细和商品信息类 */
public class OrderCommodityDetail extends OrderDetail {
  private Double weight; // 重量
  private String subPrice; // 主观价
  private String subPriceUnit; // 主观价单位
  private Double salePrice; // 销售价

  @Override
  public Double getSalePrice() {
    return salePrice;
  }

  @Override
  public void setSalePrice(Double salePrice) {
    this.salePrice = salePrice;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
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
}
