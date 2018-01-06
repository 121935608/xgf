/**
 * Copyright (C), 2018
 * FileName: ProductDao
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
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/6
 * @since 1.0.0
 */
@Repository("productDao")
public class ProductDao extends DynamicObjectBaseDao implements IProductDao {


    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<TableDataInfo>)this.findForList("ProductMapper.pageInfoQuery",pageUtilEntity);
        }catch (Exception e)
        {
         e.printStackTrace();
        }

        return pageInfoQuery;
    }


    @Override
    public List<Category> findAllClass() {
        List<Category> findAllClass=null;
        try {
            findAllClass=(List<Category>)this.findForList("ProductMapper.findAllClass",null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return findAllClass;
    }

    @Override
    public List<Category> findAllCategory() {
        List<Category> findAllCategory=null;
        try {
            findAllCategory=(List<Category>)this.findForList("ProductMapper.findAllCategory",null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return findAllCategory;
    }

    @Override
    public List<Product> findAllSupply() {
        List<Product> findAllSupply=null;
        try {
            findAllSupply=(List<Product>)this.findForList("ProductMapper.findAllSupply",null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return findAllSupply;
    }

    @Override
    public List<Product> findAllOrigin() {
        List<Product> findAllOrigin=null;
        try {
            findAllOrigin=(List<Product>)this.findForList("ProductMapper.findAllOrigin",null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return findAllOrigin;
    }

    @Override
    public int changeStatus(Product product) {
        return this.update("ProductMapper.changeStatus",product);
    }

    @Override
    public Product findProductById(Product product) {
        return (Product) this.findForObject("ProductMapper.findProductById",product);
    }
}