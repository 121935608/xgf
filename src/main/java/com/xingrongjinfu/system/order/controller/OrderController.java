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

import java.text.SimpleDateFormat;
import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.financial.model.Financial;
import com.xingrongjinfu.system.financial.service.IFinancialService;
import com.xingrongjinfu.system.order.common.OrderConstant;
import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.system.order.model.OrderDetail;
import com.xingrongjinfu.system.order.service.IOrderService;
import com.xingrongjinfu.system.user.model.User;

/**
 * 订单管理（平台端）
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

    @Autowired
    private IFinancialService financialService;

    @RequestMapping(OrderConstant.ORDER_MANAGE_URL)
    public ModelAndView loadOrderManage(){return this.getModelAndView(OrderConstant.ORDER_MANAGE_PAGE);}

    /**
     *
     * @return
     */
    @RequestMapping(OrderConstant.ORDER_MANAGE_LIST_URL)
    public ModelAndView orderManageList()
    {
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=orderService.pageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    /**
     * 查看订单信息
     * @param orderNumber
     * @return
     */
    @RequestMapping(OrderConstant.ORDER_LOOK_URL)
    public ModelAndView lookOrderInfo(String orderNumber)
    {
        ModelAndView modelAndView=this.getModelAndView(OrderConstant.ORDER_LOOK_PAGE);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order orders=orderService.findOrderInfo(orderNumber);
        orders.setOrderPrice(orders.getOrderPrice()==null ? 0:orders.getOrderPrice());
        orders.setOrderTimes(orders.getOrderTime()==null ? "" :sdf.format(orders.getOrderTime()));
        orders.setTotalPrice(orders.getTotalPrice()==null?0:orders.getTotalPrice());
        orders.setFreight(orders.getFreight()==null ?0:orders.getFreight());
        orders.setPayment(orders.getPayment()==null?0:orders.getPayment());
        List<OrderDetail> orderDetails=orderService.findOrderDetailInfo(orderNumber);
        for (OrderDetail orderDetail:orderDetails){
            if (orderDetail.getInPrice()==null){orderDetail.setInPrice(0.00);}
            if (orderDetail.getCommodityNum()==null){orderDetail.setCommodityNum(0);}
            //总金额=数量*进价
            orderDetail.setInPrice(orderDetail.getInPrice());
            Double all=orderDetail.getInPrice()*orderDetail.getCommodityNum();
            orderDetail.setTotalMoney(all);
        }
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("orderDetails",orderDetails);
        return modelAndView;
    }

    /**
     * 发货界面
     * @param orderNumber
     * @return
     */
    @RequestMapping(OrderConstant.ORDER_SEND_URL)
    public ModelAndView loadSendPage(String orderNumber)
    {
        ModelAndView modelAndView=this.getModelAndView(OrderConstant.ORDER_SEND_PAGE);
        return modelAndView;
    }

    /**
     * 收货后更新订单信息
     * @param order
     * @return
     */
    @RequestMapping(OrderConstant.ORDER_CONFIRM_URL)
    public @ResponseBody
    Message orderConfirm(Order order)
    {
     int result=0;
     String orderNumber=order.getOrderNumber();
     if (orderNumber !=null && orderNumber !="") {
         result = orderService.updateOrderInfo(order);
     }
     return new Message(result);
    }

    /**
     * 跳转到打印配送单界面
     */
    @RequestMapping(OrderConstant.ORDER_PRINT_URL)
    public ModelAndView loadPrintPage(String orderNumber){
        ModelAndView modelAndView=this.getModelAndView(OrderConstant.ORDER_PRINT_PAGE);
        List<OrderDetail> orderDetails=orderService.findOrderDetailInfo(orderNumber);
        modelAndView.addObject("orderDetails",orderDetails);
        return modelAndView;
    }

    /**
     * 跳转到订单快递查询界面
     */
    @RequestMapping(OrderConstant.ORDER_EXPRESS_URL)
    public ModelAndView loadExpressPage(){return this.getModelAndView(OrderConstant.ORDER_EXPRESS_PAGE);}

    /**
     * 查询订单快递信息
     */
    @RequestMapping(OrderConstant.ORDER_EXPRESS_LIST_URL)
    public ModelAndView orderExpressList(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=orderService.expressPageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    /**
     * 财务结算单界面
     */
    @RequestMapping(OrderConstant.ORDER_FINANCIAL_URL)
    public ModelAndView loadFinancialPage(){return this.getModelAndView(OrderConstant.ORDER_FINANCIAL_PAGE);}

    /**
     * 跳转到输入密码界面
     */
    @RequestMapping(OrderConstant.ORDER_CHECK_URL)
    public ModelAndView loadCheckPage(Financial financial)
    {
        ModelAndView modelAndView=this.getModelAndView(OrderConstant.ORDER_CHECK_PAGE);
        modelAndView.addObject("financial",financial);
        return modelAndView;
    }

    /**
     * 确认结账
     */
    @RequestMapping(OrderConstant.ORDER_TOCHECK_URL)
    public @ResponseBody Message toCheck(Financial financial, User user)
    {
        int result=0;
        User nowUser=this.getCurrentUser();
        String amountId=financial.getAmountId();
        if (amountId !=null && amountId !=""){
            //校验密码
            if (nowUser.getPassword().equals(user.getPassword())) {
                result = 1;
            }
            //校验密码通过之后更新结算单
            if (result==1) {
                financial.setAmountStatus(0);

                result=financialService.updateAmountInfo(financial);
            }
        }
        return new Message(result);
    }
}