/**
 * Copyright (C), 2018
 * FileName: ProductConstant
 * Author:   zxuser
 * Date:     2018/1/6 9:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.product.common;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/6
 * @since 1.0.0
 */
public class ProductConstant {


    /**
     * 请求地址:跳转到商品列表
     */
    public final static String PRODUCT_URL="productListView";

    /**
     * 逻辑视图名:商品列表界面
     */
    public final static String PRODUCT_PAGE="commodity/product-list";

    /**
     * 请求地址:查询商品列表
     */
    public final static String PRODUCT_LIST_URL="findProductList";

    /**
     * 请求地址:商品的启用与禁用
     */
    public final static String PRODUCT_CHANGE_STATUS_URL="changeProductStatus";

    /**
     * 请求地址:商品的修改
     */
    public final static String PRODUCT_EDIT_URL="toEditProduct";

    /**
     * 逻辑视图名:商品修改界面
     */
    public final static String PRODUCT_EDIT_PAGE="commodity/product-edit";

    /**
     * 请求地址:商品详情
     */
    public final static String PRODUCT_ADD_URL="productDtail";

    /**
     * 请求地址:新增商品列表
     */
    public final static String NEW_PRODUCT_URL="newProductListView";
}