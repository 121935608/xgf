/**
 * Copyright (C), 2018
 * FileName: SaleController
 * Author:   zxuser
 * Date:     2018/1/3 19:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.sale.controller;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.system.commodity.common.CommodityConstant;
import com.xingrongjinfu.system.commodity.model.Commodity;
import com.xingrongjinfu.system.commodity.model.CommodityDto;
import com.xingrongjinfu.system.commodity.model.CommodityRegDto;
import com.xingrongjinfu.system.sale.model.Sale;
import com.xingrongjinfu.utils.ExportExcel;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.sale.common.SaleConstant;
import com.xingrongjinfu.system.sale.service.ISaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品销售登记（平台端）
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.DATACOUNT_URL)
public class SaleController extends BaseController {

    @Autowired
    private ISaleService saleService;

    @RequestMapping(SaleConstant.SALE_URL)
    public ModelAndView loadSalePage(){return this.getModelAndView(SaleConstant.SALE_PAGE);}

    @RequestMapping(SaleConstant.SALE_LIST_URL)
    public ModelAndView saleList(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=saleService.pageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    @RequestMapping(SaleConstant.DOWNLOAD_SALE_DATA)
    public Message downloadCommodityData(HttpServletRequest request, HttpServletResponse response) {


        Map<String, String> param = new HashMap();
        param.put("beginTime", request.getParameter("beginTime"));
        param.put("endTime", request.getParameter("endTime"));
        param.put("commodityName", request.getParameter("commodityName"));

        List<Sale> data = saleService.infoQuery(param);
        //data.stream().filter(s->s.getSalePrice())
        try {
            ExportExcel<Sale> ee = new ExportExcel<>();
            String[] headers = new String[]{"商品名称", "商品编号", "订单号", "创建时间", "单位", "销售数量", "销售金额（元）"};
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ee.exportExcelUsePartField("商品销售登记表", headers, data, output,"yyyy-MM-dd");
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }
}