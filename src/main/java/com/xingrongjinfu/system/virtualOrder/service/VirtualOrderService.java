/**
 * Copyright (C), 2018 FileName: VirtualOrderService Author: zxuser Date: 2018/1/4 11:08 Description:
 * History: <author> <time> <version> <desc> 作者姓名 修改时间 版本号 描述
 */
package com.xingrongjinfu.system.virtualOrder.service;

import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.system.order.model.OrderAuditing;
import com.xingrongjinfu.system.order.model.OrderDetail;
import com.xingrongjinfu.system.virtualOrder.dao.IVirtualOrderDao;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("virtualOrderService")
public class VirtualOrderService implements IVirtualOrderService {

  @Autowired private IVirtualOrderDao orderDao;

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
  public int updateOrderInfo(Order virtualOrder) {
    return orderDao.updateOrderInfo(virtualOrder);
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

  @Override
  public int updateModifyOrder(Order virtualOrder) {
    return orderDao.updateModifyOrder(virtualOrder);
  }

  @Override
  public List<OrderDetail> findOrderDetail(Map params) {
    return orderDao.findOrderDetail(params);
  }

  @Override
  public Integer insertOrderAuditing(OrderAuditing orderAuditing) {
    return orderDao.insertOrderAuditing(orderAuditing);
  }

  @Override
  public Integer insertOrderDetail(OrderDetail orderDetail) {
    return orderDao.insertOrderDetail(orderDetail);
  }

  @Override
  public Order findOrder(String orderId) {
    return orderDao.findOrder(orderId);
  }

  @Override
  public int updateStorageAndStatus(Order virtualOrder) {
    return orderDao.updateStorageAndStatus(virtualOrder);
  }

  @Override
  public int updateOrderStatus(Order virtualOrder) {
    return orderDao.updateOrderStatus(virtualOrder);
  }

  @Override
  public List<OrderDetail> findOrderDetailInfoByNo(String orderNumber){
    return orderDao.findOrderDetailInfoByNo(orderNumber);
  }
  @Override
  public Order findVirtualOrder(String orderNumber) {
    Order virtualOrder =orderDao.findVirtualOrder(orderNumber);
    return virtualOrder;
  }

  @Override
  public List<OrderDetail> findVirtualOrderDetails(String orderNumber) throws Exception {
    return  orderDao.findVirtualOrderDetails(orderNumber);
  }
  //
  @Override
  public int updateVirtualOrder(Order virtualOrder) {
    return orderDao.updateVirtualOrder(virtualOrder);
  }

  @Override
  public int updateOrderDetailComNum(OrderDetail orderDetail) {
    return orderDao.updateOrderDetailComNum(orderDetail);
  }

  @Override
  public int updateConfirmOrder(Order order) {
    return orderDao.updateConfirmOrder(order);
  }

  @Override
  public int updateOrderDetailStatus(OrderDetail orderDetail) {
    return orderDao.updateOrderDetailStatus(orderDetail);
  }
}
