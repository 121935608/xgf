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
package com.xingrongjinfu.system.order.common;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/4
 * @since 1.0.0
 */
public class OrderConstant {

    /**
     * 请求地址:跳转到订单管理
     */
    public final static String ORDER_MANAGE_URL = "orderManageView";

    /**
     * 逻辑视图名:订单管理界面
     */
    public final static String ORDER_MANAGE_PAGE = "order/orderManage-list";

    /**
     * 请求地址:查询订单管理信息
     */
    public final static String ORDER_MANAGE_LIST_URL = "findOrderManageList";

    /**
     * 请求地址:跳转到查看订单
     */
    public final static String ORDER_LOOK_URL = "toLookOrderInfo";

    /**
     * 逻辑视图名:查看订单信息界面
     */
    public final static String ORDER_LOOK_PAGE = "order/order-look";

    /**
     * 请求地址:跳转发送订单
     */
    public final static String ORDER_SEND_URL = "toSendOrder";

    /**
     * 逻辑视图名:发送订单界面
     */
    public final static String ORDER_SEND_PAGE = "order/order-send";

    /**
     * 请求地址:确认收货
     */
    public final static String ORDER_CONFIRM_URL = "toOrderConfirm";

    /**
     * 请求地址:跳转到打印配送单
     */
    public final static String ORDER_PRINT_URL = "toPrintOrder";

    /**
     * 逻辑视图名:打印配送单界面
     */
    public final static String ORDER_PRINT_PAGE = "order/order-print";

    /**
     * 请求地址:订单快递查询
     */
    public final static String ORDER_EXPRESS_URL = "orderDeliveryView";

    /**
     * 逻辑视图名:订单快递查询界面
     */
    public final static String ORDER_EXPRESS_PAGE = "order/order-express";

    /**
     * 请求地址:查询订单快递信息
     */
    public final static String ORDER_EXPRESS_LIST_URL = "findOrderExpressList";

    /**
     * 请求地址:跳转到财务结算单financial
     */
    public final static String ORDER_FINANCIAL_URL = "financialView";

    /**
     * 逻辑视图名:财务结算单界面
     */
    public final static String ORDER_FINANCIAL_PAGE = "order/order-financial";

    /**
     * 请求地址:跳转到输入密码
     */
    public final static String ORDER_CHECK_URL = "toCheck";

    /**
     * 逻辑视图名:输入密码界面
     */
    public final static String ORDER_CHECK_PAGE = "order/order-check";

    /**
     * 请求地址:确认结账
     */
    public final static String ORDER_TOCHECK_URL = "checkOrder";


    /**
     * 请求地址:确认收款
     */
    public final static String PAY_ORDER_URL = "payOrder";

    /**
     * 请求地址:跳转到审核界面
     */
    public static final String ORDER_AUDITING_URL = "toAuditingInfo";

    /**
     * 逻辑视图:审核订单界面
     */
    public static final String ORDER_AUDITING_PAGE = "order/order-auditing";

    /**
     * 请求地址:保存客服修改的订单
     */
    public static final String ORDER_SAVE_MODIFY_OPS = "orderModifySave";

    /**
     * 请求地址:取消全部订单
     */
    public static final String ORDER_CANCELL_ALL_OPS = "cancelAllOrder";

    /**
     * 请求地址:库存审核返回结果
     */
    public static final String ORDER_STOREAUDITING_RESULT = "storeAuditingResult";

    /**
     * 请求地址:异步修改商品数量
     */
    public static final String ORDER_CHANGE_COMMODITY_NUM = "changeCommodityNum";

    /**
     * 请求地址:请求库存的地址
     */
    public static final String STORAGE_URL              = "http://wuhanxingrong.vicp.io:3080/stock2.0/app/order.action";
    public static final String STORAGE_COMFIRMORDER_URL = "stockUrl=http://wuhanxingrong.vicp.io:3080/stock2.0/app/confirmMoney.action";

    public static final String SERVICE_URL = "http://localhost:8886/xgf/third/confirmOrder";

    /**
     * 请求地址:请求确认发货
     */
    public static final String ORDER_CONFIRMORDER = "confirmOrder";
}