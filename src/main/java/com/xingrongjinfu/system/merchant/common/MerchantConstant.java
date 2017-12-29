/**
 * Copyright (C), 2017
 * FileName: MerchantConstant
 * Author:   zxuser
 * Date:     2017/12/26 16:18
 * Description: 商户管理列表常量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.merchant.common;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商户管理列表常量〉
 *
 * @author zxuser
 * @create 2017/12/26
 * @since 1.0.0
 */
public class MerchantConstant {

    /**
     * 请求地址：跳转至商品管理列表
     */
    public final static String MERCHANT_URL = "merchantView";

    /**
     * 逻辑视图名：查询商户管理列表界面
     */
    public final static String MERCHANT_PAGE = "system/merchant-list";

    /**
     * 请求地址：商品管理查询列表
     */
    public final static String MERCHANT_LIST = "merchantList";

    /**
     * 请求地址：商品管理商户信息查询
     */
    public final static String MERCHANT_QUERY_URL = "toMerchantQuery";

    /**
     * 逻辑视图名：查询商户信息界面
     */
    public final static String MERCHANT_QUERY_PAGE = "system/merchant-query";

    /**
     * 请求地址：商品管理商户信息查询
     */
    public final static String CHANGE_MERCHANT_STATUS = "changeMerchantStatus";
    /**
     * 请求地址：商品管理商户余额信息查询
     */
    public final static String MERCHANT_BALANCE_URL = "accountBalance";
    /**
     * 逻辑视图名：商品管理商户余额信息界面
     */
    public final static String MERCHANT_BALANCE_PAGE = "system/merchant-balance";

    /**
     * 请求地址:商品管理商户余额信息查询
     */
    public final static String MERCHANT_BALANCE_LIST= "accountInfoList";
}