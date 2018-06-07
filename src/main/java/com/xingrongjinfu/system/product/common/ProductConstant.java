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
    public final static String PRODUCT_ADD_URL="saveProduct";

    /**
     * 请求地址:新增商品列表
     */
    public final static String NEW_PRODUCT_URL="newProductListView";

    /**
     * 请求地址:富文本上传图片
     */
    public final static String PRODUCT_IMFFILE_URL="imgFile";



    /**
     * 请求地址:富文本上传图片
     */
    public final static String PRODUCT_IMFFILE_URLS="imgFiles";

    //跳转到导入excel的界面
    public final static String TO_EXCEL_IN="to_excel_in";

    public final static String TO_IMPORT_PRICE_PAGE="commodity/import-price" ;
    //导入商品价格的excel
    public final static String ADD_EXCEL="add_excel" ;
    //导入价格模版
    public final static String GET_PRICE_MODEL="get_price_model" ;
}