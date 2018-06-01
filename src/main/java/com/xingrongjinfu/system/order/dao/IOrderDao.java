/**
 * Copyright (C), 2018
 * FileName: IOrderDao
 * Author:   zxuser
 * Date:     2018/1/4 11:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.order.dao;

import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.system.order.model.OrderAuditing;
import com.xingrongjinfu.system.order.model.OrderDetail;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/4
 * @since 1.0.0
 */
public interface IOrderDao {

    /**
     * 查询订单管理信息
     */
    List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 根据订单号查询订单信息
     */
    Order findOrderInfo(String orderNumber);

    /**
     * 根据订单号查询订单详情
     */
    List<OrderDetail> findOrderDetailInfo(String orderNumber);

    /**
     * 收货后更新订单信息
     */
    int updateOrderInfo(Order order);

    /**
     * 收货后更新订单信息
     */
    int updatePayOrder(String orderNumber);

    /**
     * 查询订单快递信息
     */
    List<TableDataInfo>expressPageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 查询订单总数
     *
     */
    int findAllOrders();

    /**
     * 查询全部待发货订单列表
     *
     */
	List<Order> orderAllList();

    /**
     * 更新审核后订单的信息
     *
     * @param order
     * @return
     */
    int updateModifyOrder(Order order);

    /**
     * 根据订单号和商品条码查询对应的订单
     */
    List<OrderDetail> findOrderDetail(Map params);

    /**
     * 插入订单审核表
     */
    Integer insertOrderAuditing(OrderAuditing orderAuditing);

    /**
     * 插入订单明细
     *
     * @param orderDetail
     * @return
     */
    Integer insertOrderDetail(OrderDetail orderDetail);

    /**
     * 查询order
     *
     * @param orderId
     * @return
     */
    Order findOrder(String orderId);

    /** 填充订单的库存号*/
    int updateStorageAndStatus(Order order);

    /** 更新订单状态 */
    int updateOrderStatus(Order order);

    List<OrderDetail> findOrderDetailInfoByNo(String orderNumber);
}