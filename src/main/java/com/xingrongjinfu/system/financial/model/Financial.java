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
    private Integer totalMoney;
    private Integer xzfRate;
    private Integer amountMoney;
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

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getXzfRate() {
        return xzfRate;
    }

    public void setXzfRate(Integer xzfRate) {
        this.xzfRate = xzfRate;
    }

    public Integer getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Integer amountMoney) {
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
}