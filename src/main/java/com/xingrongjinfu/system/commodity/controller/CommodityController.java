/**
 * Copyright (C), 2018
 * FileName: CommodityController
 * Author:   zxuser
 * Date:     2018/1/3 14:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.commodity.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.commodity.common.CommodityConstant;
import com.xingrongjinfu.system.commodity.model.Commodity;
import com.xingrongjinfu.system.commodity.service.ICommodityService;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.DATACOUNT_URL)
public class CommodityController extends BaseController{

    @Autowired
    private ICommodityService commodityService;

    /**
     * 跳转到商品销售统计
     * @return
     */
    @RequestMapping(CommodityConstant.COMMODITY_URL)
    public ModelAndView loadView(){return this.getModelAndView(CommodityConstant.COMMODITY_PAGE);}


    /**
     * 查询商品销售信息
     */
    @RequestMapping(CommodityConstant.COMMODITY_LIST_URL)
    public ModelAndView findCommodityInfo()
    {
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<Commodity> tableDataInfo=commodityService.pageInfoQuery(pageUtilEntity);
//        for (Commodity commodity :tableDataInfo){
//            //纳税 数量*进价*17%
//            if (commodity.getSaleNum()==null) {commodity.setSaleNum(0);}
//            if (commodity.getSalePrice()==null) {commodity.setSalePrice(0.00);}
//            if (commodity.getInPrice()==null) {commodity.setInPrice(0.00);}
//            Double taxes=(commodity.getSaleNum()*commodity.getSalePrice()*17)/100;
//            commodity.setPayTaxes(taxes);
//            //利润 (售价-进价)*数量-纳税
//            Double profits=null;
//            if (commodity.getSaleNum()!=0) {
//                 profits = (commodity.getSalePrice() - commodity.getInPrice()) * commodity.getSaleNum() - taxes;
//            }else {
//                profits=0.00;
//            }
//            commodity.setProfit(profits);
//        }
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }
}