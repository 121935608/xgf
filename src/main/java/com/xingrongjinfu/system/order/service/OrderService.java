/**
 * Copyright (C), 2018
 * FileName: OrderService
 * Author:   zxuser
 * Date:     2018/1/4 11:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.order.service;

import com.xingrongjinfu.system.order.dao.IOrderDao;
import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.system.order.model.OrderDetail;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/4
 * @since 1.0.0
 */
@Service("orderService")
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return orderDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public Order findOrderInfo(String orderNumber) {
        return orderDao.findOrderInfo(orderNumber);
    }

    @Override
    public List<OrderDetail> findOrderDetailInfo(String orderNumber) {
        return orderDao.findOrderDetailInfo(orderNumber);
    }

    @Override
    public int updateOrderInfo(Order order) {
        return orderDao.updateOrderInfo(order);
    }


    @Override
    public int updatePayOrder(String orderNumber) {
        return orderDao.updatePayOrder(orderNumber);
    }

    @Override
    public List<TableDataInfo> expressPageInfoQuery(PageUtilEntity pageUtilEntity) {
        return orderDao.expressPageInfoQuery(pageUtilEntity);
    }

    @Override
    public int findAllOrders() {
        return orderDao.findAllOrders();
    }
}