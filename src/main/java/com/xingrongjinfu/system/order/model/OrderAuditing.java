package com.xingrongjinfu.system.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author: niu @Data: 2018/5/22 @Description: 订单审核实体
 */
public class OrderAuditing {

    private String  auditingId; // 审核订单id
    private String  orderId; // 订单id
    private String  orderDetailId; // 订单明细id
    private String  orderNumber; // 订单编号
    private String  commodityId; // 商品id
    private String  commodityNo; // 商品条码
    private String  commodityName; // 商品名称
    private Integer commodityNum; // 商品原数量
    private String  serviceId; // 操作人员id
    private String  serviceRemark; // 操作客服备注
    private Integer serviceModify; // 客服修改后数量
    private Integer modifyStatus; // 客服操作类型: 0 新增 1 修改 2 取消单个订单 3 取消整个订单

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH-mm-ss")
    private Date modifyTime; // 客服操作时间

    public String getAuditingId() {
        return auditingId;
    }

    public void setAuditingId(String auditingId) {
        this.auditingId = auditingId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceRemark() {
        return serviceRemark;
    }

    public void setServiceRemark(String serviceRemark) {
        this.serviceRemark = serviceRemark;
    }

    public Integer getServiceModify() {
        return serviceModify;
    }

    public void setServiceModify(Integer serviceModify) {
        this.serviceModify = serviceModify;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyStatus() {

        return modifyStatus;
    }

    public void setModifyStatus(Integer modifyStatus) {
        this.modifyStatus = modifyStatus;
    }
}
