/**
 * Copyright (C), 2018
 * FileName: Financial
 * Author:   zxuser
 * Date:     2018/1/3 14:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.financial.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public class Financial implements Serializable
{

    private String amountId;
    private String userId;
    private Date addTime;
    private Date updateTime;
    private Double totalMoney;
    private Double xzfRate;
    private Double amountMoney;
    private Integer amountStatus;
    private Integer status;
    private String storeName;
    private String amountNum;


    public String getAmountId() {
        return amountId;
    }

    public void setAmountId(String amountId) {
        this.amountId = amountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getXzfRate() {
        return xzfRate;
    }

    public void setXzfRate(Double xzfRate) {
        this.xzfRate = xzfRate;
    }

    public Double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAmountNum() {
        return amountNum;
    }

    public void setAmountNum(String amountNum) {
        this.amountNum = amountNum;
    }

    public Integer getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(Integer amountStatus) {
        this.amountStatus = amountStatus;
    }
}