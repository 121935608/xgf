/**
 * Copyright (C), 2017
 * FileName: Balance
 * Author:   zxuser
 * Date:     2017/12/27 20:06
 * Description: 账户余额
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.merchant.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈账户余额〉
 *
 * @author zxuser
 * @create 2017/12/27
 * @since 1.0.0
 */
public class Balance implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Date addTime;
    private Double money;
    private String item;
    private String userId;
    private Double remain;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getRemain() {
        return remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }
}