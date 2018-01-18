/**
 * Copyright (C), 2018
 * FileName: OrderExpress
 * Author:   zxuser
 * Date:     2018/1/5 12:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.order.model;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/5
 * @since 1.0.0
 */
public class OrderExpress {

    private String expressId;
    private String expressNo;
    private String orderNumber;
    private String expressFirm;
    private String address;
    private Integer status;
    private Date addTime;
    private Double freight;

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getExpressFirm() {
        return expressFirm;
    }

    public void setExpressFirm(String expressFirm) {
        this.expressFirm = expressFirm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }
}