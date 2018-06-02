/**
 * Copyright (C), 2018
 * FileName: OrderConstant
 * Author:   zxuser
 * Date:     2018/1/4 11:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.virtualOrder.common;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/4
 * @since 1.0.0
 */
public class VirtualOrderConstant {

    /**
     * 请求地址:跳转到订单管理
     */
    public final static String VIRTUALORDER_MANAGE_URL="virtualOrderManageView";

    /**
     * 逻辑视图名:订单管理界面
     */
    public final static String VIRTUALORDER_MANAGE_PAGE="order/virtualOrderManage-list";

    /**
     * 请求地址:查询订单管理信息
     */
    public final static String VIRTUALORDER_MANAGE_LIST_URL="findVirtualOrderManageList";

    /**
     * 请求地址:跳转到查看订单
     */
    public final static String VIRTUALORDER_LOOK_URL="toLookVirtualOrderInfo";

    /**
     * 逻辑视图名:查看订单信息界面
     */
    public final static String VIRTUALORDER_LOOK_PAGE="order/virtualOrder-look";

    /**
     * 请求地址:跳转发送订单
     */
    public final static String VIRTUALORDER_SEND_URL="toSendvirtualOrder";

    /**
     * 逻辑视图名:发送订单界面
     */
    public final static String VIRTUALORDER_SEND_PAGE="order/virtualOrder-send";

    /**
     * 请求地址:确认收货
     */
    public final static String VIRTUALORDER_CONFIRM_URL="toVirtualOrderConfirm";

    /**
     * 请求地址:跳转到打印配送单
     */
    public final static String VIRTUALORDER_PRINT_URL="toPrintVirtualOrder";

    /**
     * 逻辑视图名:打印配送单界面
     */
    public final static String VIRTUALORDER_PRINT_PAGE="order/virtualOrder-print";

    /**
     * 请求地址:订单快递查询
     */
    public final static String VIRTUALORDER_EXPRESS_URL="virtualOrderDeliveryView";

    /**
     * 逻辑视图名:订单快递查询界面
     */
    public final static String VIRTUALORDER_EXPRESS_PAGE="order/virtualOrder-express";

    /**
     * 请求地址:查询订单快递信息
     */
    public final static String VIRTUALORDER_EXPRESS_LIST_URL="findVirtualOrderExpressList";

    /**
     * 请求地址:跳转到财务结算单financial
     */
    public final static String VIRTUALORDER_FINANCIAL_URL="financialView";

    /**
     * 逻辑视图名:财务结算单界面
     */
    public final static String VIRTUALORDER_FINANCIAL_PAGE="order/virtualOrder-financial";

    /**
     * 请求地址:跳转到输入密码
     */
    public final static String VIRTUALORDER_CHECK_URL="toCheck";

    /**
     * 逻辑视图名:输入密码界面
     */
    public final static String VIRTUALORDER_CHECK_PAGE="order/virtualOrder-check";

    /**
     * 请求地址:确认结账
     */
    public final static String VIRTUALORDER_TOCHECK_URL="checkVirtualOrder";


    /**
     * 请求地址:确认收款
     */
    public final static String PAY_VIRTUAL_ORDER_URL="payVirtualOrder";

    /**
     * 请求地址:跳转到审核界面
     */
    public static final String VIRTUALORDER_AUDITING_URL = "toVirtualAuditingInfo";

    /**
     * 逻辑视图:审核订单界面
     */
    public static final String VIRTUALORDER_AUDITING_PAGE = "order/virtualOrder-auditing";

    /**
     * 请求地址:保存客服修改的订单
     */
    public static final String VIRTUALORDER_SAVE_MODIFY_OPS = "virtualOrderModifySave";

    /**
     * 请求地址:取消全部订单
     */
    public static final String VIRTUALORDER_CANCELL_ALL_OPS = "cancelAllVirtualOrder";

    /**
     * 请求地址:库存审核返回结果
     */
    public static final String VIRTUALORDER_STOREAUDITING_RESULT = "storeAuditingResult";

    /**
     * 请求地址:异步修改商品数量
     */
    public static final String VIRTUALORDER_CHANGE_COMMODITY_NUM = "changeCommodityNum";

    /**
     * 请求地址:请求库存的地址
     */
    public static final String STORAGE_URL = "http://wuhanxingrong.vicp.io:3080/stock2.0/app/order.action";
}