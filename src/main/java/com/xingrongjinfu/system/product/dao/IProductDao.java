/**
 * Copyright (C), 2018
 * FileName: IProductDao
 * Author:   zxuser
 * Date:     2018/1/6 9:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.product.dao;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.product.model.Classes;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.system.product.model.ProductDtl;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/6
 * @since 1.0.0
 */
public interface IProductDao {

    /**
     * 查询商品信息
     * @param pageUtilEntity
     * @return
     */
    List<Product>pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 查询所有分类
     */
    List<Category>findAllClass();

    List<Product> getProductList();
    /**
     * 查询主分类
     */
    List<Category> findFirstCategory();

    Category getCategoryById(String categoryId);

    /**
     * 查询所有的标签
     */
    List<Category>findAllCategory();

    /**
     * 查询所有的供货地
     */
    List<Product>findAllSupply();

    /**
     * 查询商品的所有产地
     * @return
     */
    List<Product>findAllOrigin();

    /**
     * 修改商品的状态
     */
    int changeStatus(Product product);

    /**
     * 查询商品信息
     * @param product
     * @return
     */
    Product findProductById(Product product);

    /**
     * 更新商品信息
     */
    int updateProduct(Product product);

    List<String> getCommodityNos();

    /**
     * 获取单位列表
     * @return
     */
    List<String> getUnitList();

    Product findProductByNo(String commodityNo);

    /** 查询商品详情 */
    Product findProductInfoByNo(String commodityNo);

    int updateProductStock(Product product);

    List<Map> productInfoQuery(Map<String, String> param);
    //批量修改价格
    int updatePrice(List<ProductDtl> list);
    //判断是否存在
    Map isExist(Map map);

    int updatePriceAndstatus(ProductDtl productDtl);
}