/**
 * Copyright (C), 2018
 * FileName: ProductController
 * Author:   zxuser
 * Date:     2018/1/6 9:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.product.controller;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.product.common.ProductConstant;
import com.xingrongjinfu.system.product.model.Classes;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.system.product.service.IProductService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/6
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.COMMODITY_URL)
public class ProductController extends BaseController{

    @Autowired
    private IProductService productService;

    @RequestMapping(ProductConstant.PRODUCT_URL)
    public ModelAndView loadProductPage(){
        ModelAndView modelAndView=this.getModelAndView(ProductConstant.PRODUCT_PAGE);
        //分类
        modelAndView.addObject("classes",getClassList());
        //标签
        modelAndView.addObject("category",getCategoryList());
        //供货地
        modelAndView.addObject("supply",getSupplyList());
        //产地
        modelAndView.addObject("origin",getOriginList());
        return modelAndView;
    }

    /**
     * 查询所有的商品信息
     * @return
     */
    @RequestMapping(ProductConstant.PRODUCT_LIST_URL)
    public ModelAndView findProductList()
    {
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=productService.pageInfoQuery(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    /**
     * 商品的启用与禁用
     */
   @RequestMapping(ProductConstant.PRODUCT_CHANGE_STATUS_URL)
    public @ResponseBody
    Message changeStatus(Product product){
        int result=0;
        String commodityId=product.getCommodityId();
        if (commodityId!=null && commodityId !=""){
            //根据商品id修改状态
            result=productService.changeStatus(product);
        }
        return new Message(result);
    }

    /**
     * 商品的修改界面
     */

    @RequestMapping(ProductConstant.PRODUCT_EDIT_URL)
    public ModelAndView loadEditPage(Product product){
        ModelAndView modelAndView=this.getModelAndView(ProductConstant.PRODUCT_EDIT_PAGE);
        String commodityId=product.getCommodityId();
        if (commodityId !=null && commodityId !=""){
            Product products=productService.findProductById(product);
            modelAndView.addObject("product",products);
        }
        return modelAndView;
    }


    /**
     * 获取所有的分类
     * @return
     */
    public List<SysCode> getClassList(){
        List<Category> categories=productService.findAllClass();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Category category:categories){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(category.getCategoryId());
            sysCode.setCodevalue(category.getCategoryName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }

    /**
     * 获取所有的标签
     * @return
     */
    public List<SysCode> getCategoryList(){
        List<Category> categories=productService.findAllCategory();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Category category:categories){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(category.getCategoryId());
            sysCode.setCodevalue(category.getCategoryName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }

    /**
     * 获取商品中所有的供货地
     * @return
     */
    public List<SysCode> getSupplyList(){
        List<Product> products=productService.findAllSupply();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Product product:products){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(product.getSupply());
            sysCode.setCodevalue(product.getSupply());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }

    /**
     * 获取商品中所有产地
     * @return
     */
    public List<SysCode> getOriginList(){
        List<Product> products=productService.findAllOrigin();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Product product:products){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(product.getOrigin());
            sysCode.setCodevalue(product.getOrigin());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }
}