package com.xingrongjinfu.statistics.saleCount.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.sellWater.common.SellWaterConstant;
import com.xingrongjinfu.utils.ExportExcel;
import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.paysWater.model.CashDetail;
import com.xingrongjinfu.statistics.paysWater.service.ICashService;
import com.xingrongjinfu.statistics.saleCount.common.SaleCountConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 销售统计表（收银端）
 * date: 2018年3月12日 下午6:06:46 <br/>
 *
 * @author huYL
 * @version 
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class SaleCountController extends BaseController {
    @Autowired
    private ICashService cashService;
    /**
     * Description: 跳转销售统计表页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(SaleCountConstant.TO_SALECOUNT_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(SaleCountConstant.SALECOUNT_LIST_PAGE);
        return modelAndView;
    }
    /**
     * 查询销售统计表列表
     */
    @RequestMapping(SaleCountConstant.SALECOUNT_LIST)
    public ModelAndView registerList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<CashDetail> tableDataInfo = cashService.saleCountpageInfoQuery(pageUtilEntity);
        double count = cashService.getTotal(pageUtilEntity);
        if(null != tableDataInfo){
            for (CashDetail data : tableDataInfo) {
                if(null != data.getTotalVipPrice()){
                    if(count != 0)
                        data.setTotalPrice(new BigDecimal(Double.toString(data.getTotalVipPrice().doubleValue()/count*100)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }else{
                    data.setTotalPrice(new BigDecimal("0"));
                }
            }
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 导出商品销售流水数据
     *
     * @author fengqian
     * @date 2018/4/26 11:18
     * @param request
     * @param response
     * @return org.framework.core.model.Message
     */
    @RequestMapping(SaleCountConstant.DOWNLOAD_SALECOUNT_DATA)
    public Message downloadCashData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> param = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        param.put("storeId", storeId);
        param.put("beginTime", request.getParameter("beginTime"));
        param.put("endTime", request.getParameter("endTime"));
        param.put("commodityNo", request.getParameter("commodityNo"));

        List<Map> data = cashService.saleCountInfoQuery(param);
        double count = cashService.getTotal(param);
        for(Map m :data){
            Double total = Double.parseDouble((m.get("totalVipPrice").toString()));
            String totalPrince = (total/count*100)+"%";
            m.put("totalPrice",totalPrince);
        }
        //data.stream().filter(s->s.getSalePrice())
        try {

            String[][] headers = new String[][]{
                    {"商品编码", "商品名称", "商品条码", "销量","单位", "销售金额（元）", "成本（元）","利润", "销量占比"},
                    {"commodityCode", "commodityName", "commodityNo", "number","unitName", "totalVipPrice", "inPrice", "lirun", "totalPrice"}
            };

            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("收银统计表", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }
}
