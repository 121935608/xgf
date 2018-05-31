/**
 * Copyright (C), 2018
 * FileName: IOrderService
 * Author:   zxuser
 * Date:     2018/1/4 11:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.order.service;

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
public interface IOrderService {

    /**
     * 查询订单管理信息
     * @param pageUtilEntity
     * @return
     */
    List<TableDataInfo>pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 根据订单号查询订单的信息
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
     * 查询所有订单的数量
     */
    int findAllOrders();

    /** 更新审核后的订单信息 */
    int updateModifyOrder(Order order);

    /** 根据orderNumber,commodityNo查询订单明细 */
    List<OrderDetail> findOrderDetail(Map params);

    /** 插入订单审核表 */
    Integer insertOrderAuditing(OrderAuditing orderAuditing);

    Integer insertOrderDetail(OrderDetail orderDetail);

    /** 根据订单id查询订单*/
    Order findOrder(String orderId);

    /** 填充订单的库存*/
    int pushToStorage(Order order);

    /** 更新订单状态*/
    int updateOrderStatus(Order order);
}