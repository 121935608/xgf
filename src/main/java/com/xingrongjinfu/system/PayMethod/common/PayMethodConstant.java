package com.xingrongjinfu.system.PayMethod.common;

/**
 * @创建人 Lelouch
 * @创建时间 $date$
 * @描述
 */
public class PayMethodConstant {


    /**
     * 请求地址:跳转到支付方式管理
     */
    public final static String PAY_URL="payView";


    /**
     * 逻辑视图名:跳转到督导员管理界面
     */
    public final static String PAY_PAGE="system/pay";


    /**
     * 请求地址:查询支付方式列表
     */
    public final static String PAY_LIST_URL="payList";


    /**
     * 请求地址:添加支付方式
     */
    public final static String PAYMETHOD_ADD="savePayMethod";

    /**
     * 请求地址:添加支付方式地址
     */
    public final static String PAYMETHOD_ADD_URL="toPayMethodAdd";


    /**
     * 逻辑视图名:添加支付方式界面
     */
    public final static String PAYMETHOD_ADD_PAGE="system/pay-add";

    /**
     * 请求地址:支付方式的启用,停用
     */
    public final static String PAYMETHOD_STATUS_CHANGE="changePayMethodStatus";
}
