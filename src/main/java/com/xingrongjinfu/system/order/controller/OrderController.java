/**
 * Copyright (C), 2018
 * FileName: OrderController
 * Author:   zxuser
 * Date:     2018/1/4 11:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.order.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.order.common.OrderConstant;
import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.system.order.model.OrderDetail;
import com.xingrongjinfu.system.order.service.IOrderService;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/4
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.ORDER_URL)
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping(OrderConstant.ORDER_MANAGE_URL)
    public ModelAndView loadOrderManage(){return this.getModelAndView(OrderConstant.ORDER_MANAGE_PAGE);}

    @RequestMapping(OrderConstant.ORDER_MANAGE_LIST_URL)
    public ModelAndView orderManageList()
    {
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=orderService.pageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    @RequestMapping(OrderConstant.ORDER_LOOK_URL)
    public ModelAndView lookOrderInfo(String orderNumber)
    {
        ModelAndView modelAndView=this.getModelAndView(OrderConstant.ORDER_LOOK_PAGE);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Order orders=orderService.findOrderInfo(orderNumber);
        orders.setOrderPrice(orders.getOrderPrice()==null ? 0:orders.getOrderPrice()/100);
        orders.setOrderTimes(orders.getOrderTime()==null ? "" :sdf.format(orders.getOrderTime()));
        orders.setTotalPrice(orders.getTotalPrice()==null?0:orders.getTotalPrice()/100);
        orders.setFreight(orders.getFreight()==null ?0:orders.getFreight()/100);
        orders.setPayment(orders.getPayment()==null?0:orders.getPayment()/100);
        List<OrderDetail> orderDetails=orderService.findOrderDetailInfo(orderNumber);
        for (OrderDetail orderDetail:orderDetails){
            if (orderDetail.getInPrice()==null){orderDetail.setInPrice(0);}
            if (orderDetail.getCommodityNum()==null){orderDetail.setCommodityNum(0);}
            //总金额=数量*进价
            orderDetail.setInPrice(orderDetail.getInPrice()/100);
            Integer all=orderDetail.getInPrice()*orderDetail.getCommodityNum();
            orderDetail.setTotalMoney(all);
        }
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("orderDetails",orderDetails);
        return modelAndView;
    }

    @RequestMapping(OrderConstant.ORDER_SEND_URL)
    public ModelAndView loadSendPage(String orderNumber)
    {
        ModelAndView modelAndView=this.getModelAndView(OrderConstant.ORDER_SEND_PAGE);
        return modelAndView;
    }
}