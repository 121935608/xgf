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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH-mm-ss")
    private Date payTime;
    private Long totalPrice;
    private Long remain;

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getRemain() {
        return remain;
    }

    public void setRemain(Long remain) {
        this.remain = remain;
    }
}