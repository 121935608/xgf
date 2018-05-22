package com.xingrongjinfu.crm.crmStore.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author hyq
 * @version V1.0
 * @Description: 订单
 */
@Table(name="t_order")
@Data
@Accessors(chain = true)
public class Order {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "orderId")
    private String orderId;
    @Column(name = "orderNumber")
    private String orderNumber;  //'订单编号',
    @Column(name = "addressId")
    private String addressId;    //'送货地址',
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "orderTime")
    private Date orderTime;      //'下单时间',
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "payTime")
    private Date payTime;        //'支付时间',
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "arriveTime")
    private Date arriveTime;     //'到货时间',
    @Column(name = "orderStatus")
    private Integer orderStatus; //'订单状态（1:待支付；2:代发货；3:待收货；4:已完成）',
    @Column(name = "userId")
    private String  userId;      //'用户id',
    @Column(name = "orderPrice")
    private Double orderPrice;  //'总费用',
    @Column(name = "number")
    private Integer number;      //'总件数',
    @Column(name = "status")
    private Integer status;      //'状态：1 正常 -1 已删除',
    @Column(name = "freight")
    private Integer freight;     //'运费',
    @Column(name = "payment")
    private Integer payment;     //'账期金额',
    @Column(name = "paymentNumber")
    private String paymentNumber;//还款单号
    @Column(name = "storeName")
    private String storeName;    //'店铺名称'
    @Transient
    private List<OrderDetails> orderDetailsLists;//订单商品详情
}
