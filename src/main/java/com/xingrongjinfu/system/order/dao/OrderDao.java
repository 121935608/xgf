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

import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.system.order.model.OrderDetail;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        List<TableDataInfo> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<TableDataInfo>)this.findForList("OrderMapper.pageInfoQuery",pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfoQuery;
    }

    @Override
    public Order findOrderInfo(String orderNumber) {
        return (Order)this.findForObject("OrderMapper.findOrderInfo",orderNumber);
    }

    @Override
    public List<OrderDetail> findOrderDetailInfo(String orderNumber) {
        List<OrderDetail> findOrderDetailInfo=null;
        try {
            findOrderDetailInfo=(List<OrderDetail>)this.findForList("OrderMapper.findOrderDetailInfo",orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findOrderDetailInfo;
    }

    @Override
    public int updateOrderInfo(Order order) {
        return this.update("OrderMapper.updateOrderInfo",order);
    }

    @Override
    public List<TableDataInfo> expressPageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> expressPageInfoQuery=null;
        try {
            expressPageInfoQuery=(List<TableDataInfo>)this.findForList("OrderMapper.ExpresspageInfoQuery",pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expressPageInfoQuery;
    }

    @Override
    public int findAllOrders() {
        return (int) this.findForObject("OrderMapper.findAllOrders",null);
    }
}