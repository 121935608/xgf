package com.xingrongjinfu.activity.conpusmanage.model;

import lombok.Data;


/**
 * @Author: fengqian
 * @Date: 2018/5/4 09:09
 * @Description:
 */
@Data
public class ACoupon {
    private Integer id;
    private String name;
    private Integer money;
    private Integer timeType;
    private String startTime;


    private String endTime;
    private Integer useDays;
    private Integer num;
    private Integer receiveType;

    private String createTime;
    private Integer useForAll;
    private String useCondition;
    private String orderSKU;
    private String useDescription;
    private Integer status;
    private String remark;


    public void setStartTime(String startTime) {
        if (startTime != null) {
            this.startTime = startTime.split(" ")[0];
        } else {
            this.startTime = null;
        }

    }


    public void setEndTime(String endTime) {
        if (endTime != null) {
            this.endTime = endTime.split(" ")[0];
        } else {
            this.endTime = null;
        }
    }

}
