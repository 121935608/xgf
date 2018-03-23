package com.xingrongjinfu.commodity.toStock.common;

public class ToStockConstant {
    /**
     * 模块请求地址：谢夫生鲜采购入库
     */
    public final static String TOSTOCK_URL = "/toStockView";
    /**
     * 请求地址：列表查询
     */
    public final static String TOSTOCK_LIST_URL = "toStockList";
    /**
     * 请求地址：跳转入库
     */
    public final static String TOSTOCK_MODIFY_URL = "toStockModify";
    /**
     * 请求地址：通过商品编号加载商品信息
     */
    public final static String LOAD_COMMODITY_URL = "loadCommodity";
    /**
     * 请求地址：入库
     */
    public final static String ADD_COMMODITY_URL = "addCommodityToStock";
    /**
     * 逻辑视图名：入库页面
     */
    public final static String TOSTOCK_PAGE = "commodity/toStock-list";
    /**
     * 逻辑视图名：入库页面
     */
    public final static String TOSTOCK_MODIFY_PAGE = "commodity/toStock-modify";
}
