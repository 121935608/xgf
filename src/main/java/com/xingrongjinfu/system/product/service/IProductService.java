/**
 * Copyright (C), 2018
 * FileName: IProductDao
 * Author:   zxuser
 * Date:     2018/1/6 9:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.product.service;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.product.model.Classes;
import com.xingrongjinfu.system.product.model.Product;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/6
 * @since 1.0.0
 */
public interface IProductService {

    /**
     * 查询商品信息
     * @return
     */
    List<Product>pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 查询商品信息
     * @return
     */
    List<Product>getProductList();

    /**
     * 查询所有分类
     */
    List<Category> findAllClass();

    /**
     * 查询所有的标签
     */
    List<Category> findAllCategory();

    /**
     * 查询主分类
     */
    List<Category> findFirstCategory();

    /**
     * 查询所有的供货地
     */
    List<Product>findAllSupply();

    /**
     * 查询商品中的所有产地
     * @return
     */
    List<Product>findAllOrigin();

    /**
     * 修改商品的状态
     */
    int changeStatus(Product product);

    /**
     * 查询商品信息
     */
    Product findProductById(Product product);

    List<String> getUnitList();

    /**
     * 更新商品信息
     */
    int updateProduct(Product product);


    Category getCategoryById(String categoryId);

    List<String> getCommodityNos();

    public Product findProductByNo(String commodityNo);
}
