package com.xingrongjinfu.system.storeaffairs.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.sale.common.SaleConstant;
import com.xingrongjinfu.system.storeaffairs.commom.StoreaffairConstant;
import com.xingrongjinfu.system.storeaffairs.service.IStoreSaleCountService;
import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping(SystemConstant.DATACOUNT_URL)
public class StoreSaleCount extends BaseController {

    @Autowired
    private IStoreSaleCountService storeSaleCountService;

    @RequestMapping(StoreaffairConstant.STORE_SALE_VIEW)
    public ModelAndView loadSalePage(){
        return this.getModelAndView(StoreaffairConstant.STORE_SALE_PAGE);
    }

    @RequestMapping(StoreaffairConstant.STORE_SALE_LIST)
    public ModelAndView storeSaleCountQuery(){
     //   String storeName = (String) SessionUtils.getSession().getAttribute("storeName");
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeName = pageUtilEntity.getRelationMap().get("storeName");
        if(storeName!=null&&!storeName.equals("")){
            try {
                pageUtilEntity.getRelationMap().put("storeName", URLDecoder.decode(storeName, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        List<TableDataInfo> tableDataInfo = storeSaleCountService.storeSaleCountQuery(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }



}
