package com.xingrongjinfu.activity.conpusmanage.model;

import lombok.Data;


/**
 * @Author: fengqian
 * @Date: 2018/5/4 09:09
 * @Description:
 */
@Data
public class ACouponUser {
    private Integer id;
    private String couponId;
    private String storeId;
    private String userId;
    private Integer orderId;
    private Integer num;
    private String giveTime;
    private String reviveTime;
    private String useTime;
    private String expireTime;
    private Integer useStatus;
    private Integer expireStatus;
    //是否领取  1未领取 2领取未使用 3使用 4 过期
    private Integer status;

    private String storeName;
    private String couponName;

}
