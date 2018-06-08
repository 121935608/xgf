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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.xingrongjinfu.commodity.register.common.RegisterConstant;
import com.xingrongjinfu.commodity.register.controller.RegisterController;
import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.commodity.register.service.IRegisterService;
import com.xingrongjinfu.common.constants.Constants;
import com.xingrongjinfu.system.commodity.common.CommodityConstant;
import com.xingrongjinfu.system.product.model.ProductDtl;
import com.xingrongjinfu.utils.*;
import org.apache.shiro.common.utils.SessionUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品列表+新增商品列表（平台端）
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/6
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.COMMODITY_URL)
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @RequestMapping(ProductConstant.PRODUCT_URL)
    public ModelAndView loadProductPage() {
        ModelAndView modelAndView = this.getModelAndView(ProductConstant.PRODUCT_PAGE);
        Product product = new Product();
        product.setType("c");
        modelAndView.addObject("product", product);
        //分类
        modelAndView.addObject("classes", getClassList());
        //标签
        modelAndView.addObject("category", getCategoryList());
        //供货地
        modelAndView.addObject("supply", getSupplyList());
        //产地
        modelAndView.addObject("origin", getOriginList());
        return modelAndView;
    }

    /**
     * 查询所有的商品信息
     *
     * @return
     */
    @RequestMapping(ProductConstant.PRODUCT_LIST_URL)
    public ModelAndView findProductList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<Product> tableDataInfo = productService.pageInfoQuery(pageUtilEntity);
        Integer i = 1;
        for (Product product : tableDataInfo) {
            product.setCommodityNum(pageUtilEntity.getPage() + (i++));
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 商品的启用与禁用
     */
    @RequestMapping(ProductConstant.PRODUCT_CHANGE_STATUS_URL)
    public @ResponseBody
    Message changeStatus(Product product) {
        int result = 0;
        String commodityId = product.getCommodityId();
        if (commodityId != null && commodityId != "") {
            //根据商品id修改状态
            result = productService.changeStatus(product);
        }
        return new Message(result);
    }

    /**
     * 商品的修改界面
     */

    @RequestMapping(ProductConstant.PRODUCT_EDIT_URL)
    public ModelAndView loadEditPage(Product product) {
        ModelAndView modelAndView = this.getModelAndView(ProductConstant.PRODUCT_EDIT_PAGE);
        String commodityId = product.getCommodityId();
        if (commodityId != null && commodityId != "") {
            Product products = productService.findProductById(product);
            if (!StringUtil.nullOrBlank(products.getImgMain())) {
                products.setImgMain(MerchantConstant.ALIYUN_URL + products.getImgMain());
            } else {
                products.setImgMain("");
            }
            modelAndView.addObject("product", products);
            //分类
            modelAndView.addObject("classes", getClassList());
            //标签
            modelAndView.addObject("category", getCategoryList());
            //规格
            modelAndView.addObject("specification", getSpecificationList());
            //单位
            modelAndView.addObject("unit", getUnitList());
        }
        return modelAndView;
    }


    /**
     * 跳转到新增商品列表
     */

    @RequestMapping(ProductConstant.NEW_PRODUCT_URL)
    public ModelAndView loadNewProduct() {
        ModelAndView modelAndView = this.getModelAndView(ProductConstant.PRODUCT_PAGE);
        Product product = new Product();
        product.setType("s");
        modelAndView.addObject("product", product);
        //分类
        modelAndView.addObject("classes", getClassList());
        //标签
        modelAndView.addObject("category", getCategoryList());
        //供货地
        modelAndView.addObject("supply", getSupplyList());
        //产地
        modelAndView.addObject("origin", getOriginList());
        return modelAndView;
    }

    /**
     * 提交商品
     */
    @RequestMapping(ProductConstant.PRODUCT_ADD_URL)
    public @ResponseBody
    Message saveProduct(Product product, String imgs, String imgZ) {
        int result = 0;
        if (!imgZ.equals("undefined")) {
            String img = imgZ.replace("http://" + AliyunOSSClientUtil.BACKET_NAME + "." + AliyunOSSClientUtil.ENDPOINT + "/", "");
            product.setImgMain(img);
        } else {
            return new Message(false, "请选择主图！");
        }
        String[] imgList = imgs.split(",");
        String imgOther = "";
        for (int i = 0; i < imgList.length; i++) {
            String p = imgList[i].replace("http://" + AliyunOSSClientUtil.BACKET_NAME + "." + AliyunOSSClientUtil.ENDPOINT + "/", "");
            if (!p.equals("undefined")) {
                imgOther += p + ";";
            }
        }
        product.setImgOther(imgOther);
        String commodityId = product.getCommodityId();
        if (commodityId != null && commodityId != "") {
            //商品为c就是商品表中的信息
            if (product.getType().equalsIgnoreCase("c")) {
                product.setUpdateTime(new Date());
                result = productService.updateProduct(product);
            } else {
                //商品为s这里的提交还需将type改为c
                product.setType("c");
                product.setUpdateTime(new Date());
                result = productService.updateProduct(product);
            }

            //将商品上下架信息推给库存

            JSONObject json = new JSONObject();
            json.put("barCode", product.getCommodityNo());
            json.put("currentState", product.getCommodityStatus());
            Map map = new HashMap();
            map.put("params", json);

            try {
                String s = HttpClientUtil.httpPostRequest(productService.getStockUrl() + "/app/currentState.action", map);//
                System.out.println("s:" + s);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return new Message(result);
    }

    /**
     * 富文本上传图片
     */
    @RequestMapping(ProductConstant.PRODUCT_IMFFILE_URL)
    public @ResponseBody
    Message imgFile(MultipartFile file) {
        AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
        JSONObject jsonObject = new JSONObject();
        try {
            String key = aliyunOSSClientUtil.uploadImgs(file);

            String filePath = aliyunOSSClientUtil.FOLDER2 + AliyunOSSClientUtil.filePath;
            // 返回地址
            return new Message(true, filePath);
        } catch (OSSException e) {
            e.printStackTrace();
            return new Message(false, "对不起图片上传失败!");

        }

    }


    /**
     * 富文本上传图片
     */
    @RequestMapping(ProductConstant.PRODUCT_IMFFILE_URLS)
    public @ResponseBody
    Object imgFiles(MultipartFile file) {
        AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
        JSONObject jsonObject = new JSONObject();
        try {
            String key = aliyunOSSClientUtil.uploadImgs(file);

            String filePath = Constants.PATH + aliyunOSSClientUtil.BACKET_NAME + "." + aliyunOSSClientUtil.ENDPOINT + "/" + aliyunOSSClientUtil.FOLDER2 + AliyunOSSClientUtil.filePath;
            // 返回地址
            jsonObject.put("error", 0);
            jsonObject.put("url", filePath);
            return jsonObject;
//            return new Message(true,filePath);
        } catch (OSSException e) {
            e.printStackTrace();
//            return new Message(false,"对不起图片上传失败!");
            jsonObject.put("msg", "对不起图片上传失败!");
            jsonObject.put("error", 1);
            return jsonObject;
        }

    }


    /**
     * 获取所有的分类
     *
     * @return
     */
    public List<SysCode> getClassList() {
        List<Category> categories = productService.findAllClass();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Category category : categories) {
            SysCode sysCode = new SysCode();
            if (category != null) {
                sysCode.setCodeid(category.getCategoryId() == null ? "" : category.getCategoryId());
                sysCode.setCodevalue(category.getCategoryName() == null ? "" : category.getCategoryName());
                sysCodeList.add(sysCode);
            }
        }
        return sysCodeList;
    }

    /**
     * 获取所有的标签
     *
     * @return
     */
    public List<SysCode> getCategoryList() {
        List<Category> categories = productService.findAllCategory();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Category category : categories) {
            SysCode sysCode = new SysCode();
            if (category != null) {
                sysCode.setCodeid(category.getCategoryName() == null ? "" : category.getCategoryName());
                sysCode.setCodevalue(category.getCategoryName() == null ? "" : category.getCategoryName());
                sysCodeList.add(sysCode);
            }
        }
        return sysCodeList;
    }

    /**
     * 获取商品中所有的供货地
     *
     * @return
     */
    public List<SysCode> getSupplyList() {
        List<Product> products = productService.findAllSupply();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Product product : products) {
            SysCode sysCode = new SysCode();
            if (product != null) {
                sysCode.setCodeid(null == product.getSupply() ? "" : product.getSupply());
                sysCode.setCodevalue(product.getSupply() == null ? "" : product.getSupply());
                sysCodeList.add(sysCode);
            }
        }
        return sysCodeList;
    }

    /**
     * 获取商品中所有产地
     *
     * @return
     */
    public List<SysCode> getOriginList() {
        List<Product> products = productService.findAllOrigin();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Product product : products) {
            SysCode sysCode = new SysCode();
            if (product != null) {
                sysCode.setCodeid(product.getOrigin() == null ? "" : product.getOrigin());
                sysCode.setCodevalue(product.getOrigin() == null ? "" : product.getOrigin());
                sysCodeList.add(sysCode);
            }
        }
        return sysCodeList;
    }

    /**
     * 获取商品中所有规格
     *
     * @return
     */
    public List<SysCode> getSpecificationList() {
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

    public List<SysCode> getUnitList() {
        List<String> unitList = productService.getUnitList();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (String str : unitList) {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(str);
            sysCode.setCodevalue(str);
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }


    /**
     * 导出商品列表数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("exportExcel")
    @ResponseBody
    public Message downloadProducts(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> param = new HashMap();
        param.put("tag", request.getParameter("tag"));
        param.put("categoryId", request.getParameter("categoryId"));
        param.put("commodityStatus", request.getParameter("commodityStatus"));
        param.put("commodityName", request.getParameter("commodityName"));
        param.put("origin", request.getParameter("origin"));
        List<Map> data = productService.productInfoQuery(param);
        try {
            String[][] headers = new String[][]{{"编号", "商品名称", "商品条码", "分类", "产地", "单位", "国内/国外", "主观价", "客观价", "进价(元)", "售价(元)", "库存", "犹豫库存", "客服库存", "可下单库存", "上下架"},
                    {"barCode", "commodityName", "commodityNo", "categoryName", "origin", "unit", "country", "subprice", "salePrice", "inPrice", "salePrice", "stockNum", "yyStock", "kfStock", "kxdStock", "commodityStatus"}};
            response.setContentType("application/force-" +
                    "");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("商品信息", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }

    /**
     * 跳转到导入excel页面
     */
    @RequestMapping(ProductConstant.TO_EXCEL_IN)
    public ModelAndView toImpPage(String commodityId) throws Exception{
        ModelAndView modelAndView = new ModelAndView(ProductConstant.TO_IMPORT_PRICE_PAGE);
        return modelAndView;
    }

    /**
     * 价格模版
     * @param response
     * @return
     */
    @RequestMapping(ProductConstant.GET_PRICE_MODEL)
    public @ResponseBody Message getImpExcelModel(HttpServletResponse response){
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition","attachment;fileName=exportPrice.xls");// 设置文件名
        try {
            InputStream is= RegisterController.class.getClassLoader().getResource("../jsp/excelModel/price.xls").openStream();
            OutputStream output = response.getOutputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                output.write(ch);
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }

    /**
     * 批量导入修改商品价格,单位,上下架状态
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(ProductConstant.ADD_EXCEL)
    public @ResponseBody Message impRegisterList(MultipartFile file) throws IllegalStateException, IOException {
        String[] fields=new String[]{"commodityNo","subPrice","subPriceUnit","priceSpecification","priceSpecificationUnit",
                "commodityStatus"};
        List<ProductDtl> list = new ArrayList<>();
        String a=null;
        try {
            ImportExcel<ProductDtl> ie=new ImportExcel<ProductDtl>();
            list=ie.readExcel(file.getInputStream(), fields, new ProductDtl().getClass().getName());
            if(list.size()==0){
                return new Message(false,"没有数据！");
            }
            int n = list.size();
                for(ProductDtl productDtl:list){
                    Map map = new HashMap();
                    if(null == productDtl.getCommodityNo()){
                        return new Message(false,"请输入商品名称和条码！");
                    }
                    map.put("commodityNo", productDtl.getCommodityNo());
                    a=productDtl.getCommodityNo();
                    //判断商品编号是否存在
                    Map m = productService.isExist(map);
                    String b=null;
                    if(null==m.get("commodityNo")){
                        return  new Message(false,"单号"+productDtl.getCommodityNo()+"不存在");
                    }
                    a=productDtl.getCommodityNo();
                    if(null == m){
                        return new Message(false,"请输入正确的编号！");
                    }
                    if(null != productDtl.getSubPrice()){
                        productDtl.setSubPrice(String.valueOf(Integer.parseInt(productDtl.getSubPrice())*100));
                    }
                    if(null != productDtl.getPriceSpecification()) {
                        productDtl.setPriceSpecification(String.valueOf(Integer.parseInt(productDtl.getPriceSpecification())*100));
                    }
                }
                for (ProductDtl productDtl:list){
                    productService.updatePriceAndstatus(productDtl);
                }
               // n=productService.updatePrice(list);
                return new Message(true,"导入成功，修改数据"+n+"条！");
            }
         catch (Exception e) {
            e.printStackTrace();
            return new Message(false,"修改失败!"+"单号"+a+"不存在,请检查");
        }
    }
}