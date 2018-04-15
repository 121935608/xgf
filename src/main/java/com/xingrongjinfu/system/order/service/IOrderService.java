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
import com.xingrongjinfu.system.order.model.OrderDetail;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

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
}