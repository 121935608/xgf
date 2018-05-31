/**
 * Copyright (C), 2018
 * FileName: CommodityConstant
 * Author:   zxuser
 * Date:     2018/1/3 14:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.commodity.common;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public class CommodityConstant {

    /**
     * 请求地址:跳转到商品销售统计
     */
    public final static String COMMODITY_URL="commodityView";

    /**
     * 逻辑视图名:商品统计界面
     */
    public final static String COMMODITY_PAGE="dataCount/commodity-list";

    /**
     * 请求地址:查询商品销售统计信息
     */
    public final static String COMMODITY_LIST_URL="findCommodityInfo";

    public final static String DOWNLOAD_COMMODITY_DATA="downloadCommodityData";

    /** 请求地址: 根据商品条形码查询商品 */
    public static final String COMMODITY_ONE_NO = "findCommodityByNo";

    /** 请求地址: 根据商品名称查询商品 */
    public static final String COMMODITY_ALL_NAME = "findCommodityByName";

    /** 请求地址: 查询所有商品 */
    public static final String COMMODITY_ALL = "findAllCommodity";
}