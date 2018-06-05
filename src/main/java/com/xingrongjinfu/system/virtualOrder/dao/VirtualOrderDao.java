/**
 * Copyright (C), 2018
 * FileName: VirtualOrderDao
 * Author:   zxuser
 * Date:     2018/1/4 11:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.virtualOrder.dao;

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
public class VirtualOrderDao extends DynamicObjectBaseDao implements IVirtualOrderDao {

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> pageInfoQuery = null;
        try {
            pageInfoQuery = (List<TableDataInfo>) this.findForList("VirtualOrderMapper.virtualpageInfoQuery", pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfoQuery;
    }

    @Override
    public Order findOrderInfo(String orderNumber) {
        return (Order) this.findForObject("VirtualOrderMapper.findVirtualOrderInfo", orderNumber);
    }

    @Override
    public List<OrderDetail> findOrderDetailInfo(String orderNumber) {
        List<OrderDetail> findOrderDetailInfo = null;
        try {
            findOrderDetailInfo = (List<OrderDetail>) this.findForList("VirtualOrderMapper.findVirtualOrderDetailInfo", orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findOrderDetailInfo;
    }

    @Override
    public List<OrderDetail> findOrderDetailInfoByNo(String orderNumber) {
        List<OrderDetail> findOrderDetailInfo = null;
        try {
            findOrderDetailInfo = (List<OrderDetail>) this.findForList("VirtualOrderMapper.findVirtualOrderDetailInfoByNo", orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findOrderDetailInfo;
    }

    @Override
    public int updateOrderInfo(Order order) {
        return this.update("VirtualOrderMapper.updateVirtualOrderInfo", order);
    }


    @Override
    public int updatePayOrder(String orderNumber) {
        return this.update("VirtualOrderMapper.updatePayVirtualOrder", orderNumber);
    }

    @Override
    public List<TableDataInfo> expressPageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> expressPageInfoQuery = null;
        try {
            expressPageInfoQuery = (List<TableDataInfo>) this.findForList("VirtualOrderMapper.virtualExpresspageInfoQuery", pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expressPageInfoQuery;
    }

    @Override
    public int findAllOrders() {
        return (int) this.findForObject("VirtualOrderMapper.findAllVirtualOrders", null);
    }

    @Override
    public List<Order> orderAllList() {
        List<Order> virtualOrderList = null;
        try {
            virtualOrderList = (List<Order>) this.findForList("VirtualOrderMapper.virtualOrderAllList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return virtualOrderList;
    }

    @Override
    public int updateModifyOrder(Order virtualOrder) {
        return this.update("VirtualOrderMapper.updateModifyVirtualOrder", virtualOrder);
    }

    @Override
    public List<OrderDetail> findOrderDetail(Map params) {
        List<OrderDetail> orderDetails = null;
        try {
            orderDetails = (List<OrderDetail>) this.findForList("VirtualOrderMapper.findVirtualOrderDetail", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public Integer insertOrderAuditing(OrderAuditing orderAuditing) {
        return this.save("VirtualOrderMapper.insertVirtualOrderAuditing", orderAuditing);
    }

    @Override
    public Integer insertOrderDetail(OrderDetail orderDetail) {
        return this.save("VirtualOrderMapper.insertVirtualOrderDetail", orderDetail);
    }

    @Override
    public Order findOrder(String orderId) {
        return (Order) this.findForObject("VirtualOrderMapper.findVirtualOrder", orderId);
    }

    @Override
    public int updateStorageAndStatus(Order virtualOrder) {
        return this.update("VirtualOrderMapper.updateVirtualOrderStorageNoAndStatus", virtualOrder);
    }

    @Override
    public int updateOrderStatus(Order virtualOrder) {
        return this.update("VirtualOrderMapper.updateVirtualOrderStatus", virtualOrder);
    }

    //查询虚拟订单信息
    @Override
    public Order findVirtualOrder(String orderNumber) {
        return (Order) this.findForObject("VirtualOrderMapper.findVirtualOrder", orderNumber);
    }

    @Override
    public List<OrderDetail> findVirtualOrderDetails(String orderNumber) throws Exception {
        return (List<OrderDetail>) this.findForList("VirtualOrderMapper.findVirtualOrderDetails", orderNumber);
    }

    @Override
    public int updateVirtualOrder(Order order) {
        return (int) this.update("VirtualOrderMapper.updateVirtualOrder", order);
    }

    @Override
    public int updateOrderDetailComNum(OrderDetail orderDetail) {
        return this.update("VirtualOrderMapper.updateOrderDetailComNum", orderDetail);
    }

    @Override
    public int updateConfirmOrder(Order order) {
        return this.update("VirtualOrderMapper.updateConfirmOrder", order);
    }

    @Override
    public int updateOrderDetailStatus(OrderDetail orderDetail) {
        return this.update("VirtualOrderMapper.updateOrderDetailStatus", orderDetail);
    }
}