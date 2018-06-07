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

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.*;

import com.xingrongjinfu.system.commodity.model.CommodityDto;
import com.xingrongjinfu.system.pays.common.PaysConstant;
import com.xingrongjinfu.system.pays.model.Pays;
import com.xingrongjinfu.system.pays.model.PaysDto;
import com.xingrongjinfu.utils.ExportExcel;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.commodity.common.CommodityConstant;
import com.xingrongjinfu.system.commodity.model.Commodity;
import com.xingrongjinfu.system.commodity.service.ICommodityService;
import com.xingrongjinfu.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class CommodityController extends BaseController {

    @Autowired
    private ICommodityService commodityService;

    /**
     * 跳转到商品销售统计
     *
     * @return
     */
    @RequestMapping(CommodityConstant.COMMODITY_URL)
    public ModelAndView loadView() {
        return this.getModelAndView(CommodityConstant.COMMODITY_PAGE);
    }


    /**
     * 查询商品销售信息
     */
    @RequestMapping(CommodityConstant.COMMODITY_LIST_URL)
    public ModelAndView findCommodityInfo() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<Commodity> tableDataInfo = commodityService.pageInfoQuery(pageUtilEntity);
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
//        int count=0;
        Iterator<Commodity> it = tableDataInfo.iterator();
        while (it.hasNext()) {
            Commodity commodity = it.next();
            if (StringUtil.nullOrBlank(commodity.getCommodityId())) {
                it.remove();
            }
        }
//        int index = 0;
//        for (Commodity commodity : tableDataInfo) {
//            if(StringUtil.nullOrBlank(commodity.getCommodityId())){
//                index = tableDataInfo.indexOf(commodity);
//                tableDataInfo.remove(index);
//            }
//        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     *    导出商品销售统计数据
     *
     * @author fengqian
     * @date 2018/4/27 16:34
     * @param request
     * @param response
     * @return org.framework.core.model.Message
     */
    @RequestMapping(CommodityConstant.DOWNLOAD_COMMODITY_DATA)
    @ResponseBody
    public Message downloadCommodityData(HttpServletRequest request, HttpServletResponse response) {


        Map<String, String> param = new HashMap();
        param.put("beginTime", request.getParameter("beginTime"));
        param.put("endTime", request.getParameter("endTime"));
        param.put("commodityName", request.getParameter("commodityName"));

        List<Map> data = commodityService.infoQuery(param);

        try {

            String[][] headers = new String[][]{{"商品名称", "商品编号", "单位", "销售数量", "销售金额（元）", "进价（元）", "利润（元）"},
                                        {"commodityName","commodityNo","unit","saleNum","totalPrice","totalInPrice","profit"}};
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("商品销售表", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }
}