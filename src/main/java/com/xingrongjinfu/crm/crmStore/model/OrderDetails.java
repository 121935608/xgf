package com.xingrongjinfu.crm.crmStore.model;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hyq
 * @version V1.0
 * @Description: 订单详情
 */
@Table(name="t_order_detail")
@Data
@Accessors(chain = true)
public class OrderDetails {
    @Id
    @Column(name = "orderDetailId")
    private String orderDetailId;
    @Column(name = "orderNumber")
    private String orderNumber;//订单编号',
    @Column(name = "commodityId")
    private String commodityId;//商品id',
    @Column(name = "commodityNo")
    private String commodityNo;//商品条码',
    @Column(name = "commodityName")
    private String commodityName;//商品名称',
    @Column(name = "commodityNum")
    private Integer commodityNum;//商品数量',
    @Column(name = "addTime")
    private Date addTime;//添加时间',
    @Column(name = "inPrice")
    private Double inPrice;//进价（当时价格）',
    @Column(name = "salePrice")
    private Double salePrice;//售价（当时价格）',
    @Column(name = "taxRate")
    private Double taxRate;//费率',
    @Column(name = "unit")
    private String unit;//'单位',
    @Column(name = "imgMain")
    private String imgMain;//商品主图',
    @Column(name = "totalPrice")
    private Double totalPrice;//总价（同类商品）',
    @Column(name = "storageStatus")
    private Integer storageStatus;//是否入库  1入库  0未入库'
}
