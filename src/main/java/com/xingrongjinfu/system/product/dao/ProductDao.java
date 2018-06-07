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
import com.xingrongjinfu.system.product.model.ProductDtl;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
@Repository("productDao")
public class ProductDao extends DynamicObjectBaseDao implements IProductDao {

    @Override
    public List<Product> getProductList() {
        List<Product> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<Product>)this.findForList("ProductMapper.getProductList");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return pageInfoQuery;
    }


    @Override
    public List<Category> findFirstCategory() {
        List<Category> findAllClass=null;
        try {
            findAllClass=(List<Category>)this.findForList("ProductMapper.findFirstCategory");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return findAllClass;
    }

    @Override
    public List<Product> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<Product> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<Product>)this.findForList("ProductMapper.pageInfoQuery",pageUtilEntity);
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

    @Override
    public int updateProduct(Product product) {
        return this.update("ProductMapper.updateProduct",product);
    }


    @Override
    public Category getCategoryById(String categoryId) {
        return (Category) this.findForObject("ProductMapper.findCategoryById",categoryId);
    }

    @Override
    public List<String> getCommodityNos() {
        try {
            return (List<String>)this.findForList("ProductMapper.getCommodityNos");
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getUnitList() {
        try {
            return (List<String>)this.findForList("ProductMapper.getUnitList");
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Product findProductByNo(String commodityNo) {
        return (Product) this.findForObject("ProductMapper.findProductByNo",commodityNo);
    }

    /**
     * 查询产品的详细信息
     */
    @Override
    public Product findProductInfoByNo(String commodityNo) {
        return (Product) this.findForObject("ProductMapper.findProductInfoByNo", commodityNo);
    }

    @Override
    public int updateProductStock(Product product) {
        return this.update("ProductMapper.updateProductStock", product);
    }

    @Override
    public List<Map> productInfoQuery(Map<String, String> param) {
        List<Map> productInfolsit=null;
        try {
            productInfolsit=(List<Map>)this.findForList("ProductMapper.productInfoQuery",param);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return productInfolsit;
    }
    //更新商品价格
    @Override
    public int updatePrice(List<ProductDtl> list) {
        return this.update("ProductMapper.updatePrice",list);
    }
    //判断商品编号是否有误
    @Override
    public Map isExist(Map map) {
        return (Map) this.findForObject("ProductMapper.isExist",map);
    }

    @Override
    public int updatePriceAndstatus(ProductDtl productDtl) {
        return this.update("ProductMapper.updatePriceAndstatus",productDtl);
    }
}