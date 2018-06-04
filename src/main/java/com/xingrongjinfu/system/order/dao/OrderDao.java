/**
 * Copyright (C), 2018
 * FileName: OrderDao
 * Author:   zxuser
 * Date:     2018/1/4 11:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.order.dao;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.system.order.model.OrderAuditing;
import com.xingrongjinfu.system.order.model.OrderDetail;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

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
@Repository
public class OrderDao extends DynamicObjectBaseDao implements IOrderDao {

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> pageInfoQuery = null;
        try {
            pageInfoQuery = (List<TableDataInfo>) this.findForList("OrderMapper.pageInfoQuery", pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfoQuery;
    }

    @Override
    public Order findOrderInfo(String orderNumber) {
        return (Order) this.findForObject("OrderMapper.findOrderInfo", orderNumber);
    }

    @Override
    public List<OrderDetail> findOrderDetailInfo(String orderNumber) {
        List<OrderDetail> findOrderDetailInfo = null;
        try {
            findOrderDetailInfo = (List<OrderDetail>) this.findForList("OrderMapper.findOrderDetailInfo", orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findOrderDetailInfo;
    }

    @Override
    public List<OrderDetail> findOrderDetailInfoByNo(String orderNumber) {
        List<OrderDetail> findOrderDetailInfo = null;
        try {
            findOrderDetailInfo = (List<OrderDetail>) this.findForList("OrderMapper.findOrderDetailInfoByNo", orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findOrderDetailInfo;
    }

    @Override
    public int updateOrderInfo(Order order) {
        return this.update("OrderMapper.updateOrderInfo", order);
    }


    @Override
    public int updatePayOrder(String orderNumber) {
        return this.update("OrderMapper.updatePayOrder", orderNumber);
    }

    @Override
    public List<TableDataInfo> expressPageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> expressPageInfoQuery = null;
        try {
            expressPageInfoQuery = (List<TableDataInfo>) this.findForList("OrderMapper.ExpresspageInfoQuery", pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expressPageInfoQuery;
    }

    @Override
    public int findAllOrders() {
        return (int) this.findForObject("OrderMapper.findAllOrders", null);
    }

    @Override
    public List<Order> orderAllList() {
        List<Order> orderList = null;
        try {
            orderList = (List<Order>) this.findForList("OrderMapper.orderAllList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public int updateModifyOrder(Order order) {
        return this.update("OrderMapper.updateModifyOrder", order);
    }

    @Override
    public List<OrderDetail> findOrderDetail(Map params) {
        List<OrderDetail> orderDetails = null;
        try {
            orderDetails = (List<OrderDetail>) this.findForList("OrderMapper.findOrderDetail", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public Integer insertOrderAuditing(OrderAuditing orderAuditing) {
        return this.save("OrderMapper.insertOrderAuditing", orderAuditing);
    }

    @Override
    public Integer insertOrderDetail(OrderDetail orderDetail) {
        return this.save("OrderMapper.insertOrderDetail", orderDetail);
    }

    @Override
    public Order findOrder(String orderId) {
        return (Order) this.findForObject("OrderMapper.findOrder", orderId);
    }

    @Override
    public int updateStorageAndStatus(Order order) {
        return this.update("OrderMapper.updateOrderStorageNoAndStatus", order);
    }

    @Override
    public int updateOrderStatus(Order order) {
        return this.update("OrderMapper.updateOrderStatus", order);
    }

    //查询虚拟订单信息
    @Override
    public Order findVirtualOrder(String orderNumber) {
        return (Order) this.findForObject("OrderMapper.findVirtualOrder", orderNumber);
    }

    @Override
    public List<OrderDetail> findVirtualOrderDetails(String orderNumber) throws Exception {
        return (List<OrderDetail>) this.findForList("OrderMapper.findVirtualOrderDetails", orderNumber);
    }

    @Override
    public int updateVirtualOrder(Order order) {
        return (int) this.update("OrderMapper.updateVirtualOrder", order);
    }

    @Override
    public int updateOrderDetailComNum(OrderDetail orderDetail) {
        return this.update("OrderMapper.updateOrderDetailComNum", orderDetail);
    }
}