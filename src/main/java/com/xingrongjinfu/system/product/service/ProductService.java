/**
 * Copyright (C), 2018
 * FileName: ProductDao
 * Author:   zxuser
 * Date:     2018/1/6 9:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.product.service;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.product.dao.IProductDao;
import com.xingrongjinfu.system.product.model.Classes;
import com.xingrongjinfu.system.product.model.Product;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/6
 * @since 1.0.0
 */
@Service("productService")
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return productDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public List<Category> findAllClass() {
        return productDao.findAllClass();
    }

    @Override
    public List<Category> findAllCategory() {
        return productDao.findAllCategory();
    }

    @Override
    public List<Product> findAllSupply() {
        return productDao.findAllSupply();
    }

    @Override
    public List<Product> findAllOrigin() {
        return productDao.findAllOrigin();
    }

    @Override
    public int changeStatus(Product product) {
        return productDao.changeStatus(product);
    }

    @Override
    public Product findProductById(Product product) {
        return productDao.findProductById(product);
    }

    @Override
    public List<String> getUnitList() {
        return productDao.getUnitList();
    }

    @Override
    public Product findProductByNo(String commodityNo) {
        return productDao.findProductByNo(commodityNo);
    }



    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public List<Product> getProductList() {
        return productDao.getProductList();
    }

    @Override
    public List<Category> findFirstCategory() {
        return productDao.findFirstCategory();
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return productDao.getCategoryById(categoryId);
    }

    @Override
    public List<String> getCommodityNos() {
        return productDao.getCommodityNos();
    }
}