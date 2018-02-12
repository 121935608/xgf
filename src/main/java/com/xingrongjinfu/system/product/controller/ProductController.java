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

import java.util.ArrayList;
import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSException;
import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.merchant.common.MerchantConstant;
import com.xingrongjinfu.system.product.common.ProductConstant;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.system.product.service.IProductService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;
import com.xingrongjinfu.utils.StringUtil;

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
        Product product=new Product();
        product.setType("c");
        modelAndView.addObject("product",product);
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
        List<Product> tableDataInfo=productService.pageInfoQuery(pageUtilEntity);
        Integer i=1;
        for(Product product:tableDataInfo){
            product.setCommodityNum(pageUtilEntity.getPage()+(i++));
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
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
            if(!StringUtil.nullOrBlank(products.getImgMain())){
                products.setImgMain(MerchantConstant.ALIYUN_URL+products.getImgMain());
            }else{
                products.setImgMain("");
            }
            modelAndView.addObject("product",products);
            //分类
            modelAndView.addObject("classes",getClassList());
            //标签
            modelAndView.addObject("category",getCategoryList());
            //规格
            modelAndView.addObject("specification",getSpecificationList());
        }
        return modelAndView;
    }


    /**
     * 跳转到新增商品列表
     *
     */

    @RequestMapping(ProductConstant.NEW_PRODUCT_URL)
    public ModelAndView loadNewProduct(){
        ModelAndView modelAndView=this.getModelAndView(ProductConstant.PRODUCT_PAGE);
        Product product=new Product();
        product.setType("s");
        modelAndView.addObject("product",product);
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
     * 提交商品
     */
    @RequestMapping(ProductConstant.PRODUCT_ADD_URL)
    public @ResponseBody Message saveProduct(Product product,String imgs,String imgZ){
        int result=0;
        if(!imgZ.equals("undefined")){
            String img = imgZ.replace("http://"+AliyunOSSClientUtil.BACKET_NAME+"."+AliyunOSSClientUtil.ENDPOINT+"/", "");
            product.setImgMain(img);
        }else{
            return new Message(false,"请选择主图！");
        }
        String[] imgList = imgs.split(",");
        String imgOther = "";
        for(int i=0;i<imgList.length;i++){
            String p = imgList[i].replace("http://"+AliyunOSSClientUtil.BACKET_NAME+"."+AliyunOSSClientUtil.ENDPOINT+"/", "");
            if(!p.equals("undefined")){
                imgOther += p+";";
            }
        }
        product.setImgOther(imgOther);
        String commodityId=product.getCommodityId();
        if (commodityId !=null && commodityId!=""){
               //商品为c就是商品表中的信息
               if (product.getType().equalsIgnoreCase("c")) {
                    result = productService.updateProduct(product);
               }else {
                   //商品为s这里的提交还需将type改为c
                   product.setType("c");
                   result =productService.updateProduct(product);
               }
            }

        return new Message(result);
    }

    /**
     * 富文本上传图片
     */
    @RequestMapping(ProductConstant.PRODUCT_IMFFILE_URL)
    public @ResponseBody Message imgFile(MultipartFile file){
        AliyunOSSClientUtil aliyunOSSClientUtil=new AliyunOSSClientUtil();
        JSONObject jsonObject = new JSONObject();
        try {
            String key = aliyunOSSClientUtil.uploadImgs(file);

            String filePath = aliyunOSSClientUtil.FOLDER2 + AliyunOSSClientUtil.filePath;
            // 返回地址
            return new Message(true,filePath);
        }
        catch (OSSException e)
        {
            e.printStackTrace();
            return new Message(false,"对不起图片上传失败!");

        }

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
            if (category!=null) {
                sysCode.setCodeid(category.getCategoryId()==null?"":category.getCategoryId());
                sysCode.setCodevalue(category.getCategoryName()==null?"":category.getCategoryName());
                sysCodeList.add(sysCode);
            }
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
            if (category!=null) {
                sysCode.setCodeid(category.getCategoryName()==null?"":category.getCategoryName());
                sysCode.setCodevalue(category.getCategoryName()==null?"":category.getCategoryName());
                sysCodeList.add(sysCode);
            }
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
            if (product!=null) {
                sysCode.setCodeid(null == product.getSupply() ? "" : product.getSupply());
                sysCode.setCodevalue(product.getSupply() == null ? "" : product.getSupply());
                sysCodeList.add(sysCode);
            }
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
            if (product !=null) {
                sysCode.setCodeid(product.getOrigin()==null?"":product.getOrigin());
                sysCode.setCodevalue(product.getOrigin()==null?"":product.getOrigin());
                sysCodeList.add(sysCode);
            }
        }
        return sysCodeList;
    }

    /**
     * 获取商品中所有规格
     * @return
     */
    public List<SysCode> getSpecificationList(){
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        SysCode sysCode = new SysCode();
        sysCode.setCodeid("70");
        sysCode.setCodevalue("70");
        sysCodeList.add(sysCode);
        SysCode sysCode1 = new SysCode();
        sysCode1.setCodeid("80");
        sysCode1.setCodevalue("80");
        sysCodeList.add(sysCode1);
        return sysCodeList;
    }
}