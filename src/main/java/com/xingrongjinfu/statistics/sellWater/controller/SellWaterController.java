package com.xingrongjinfu.statistics.sellWater.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.paysWater.common.CashConstant;
import com.xingrongjinfu.statistics.paysWater.model.CashFlowDto;
import com.xingrongjinfu.utils.ExportExcel;
import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.paysWater.service.ICashService;
import com.xingrongjinfu.statistics.sellWater.common.SellWaterConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:商品销售流水（收银端）
 * date: 2018年3月12日 下午6:07:03 <br/>
 *
 * @author huYL
 * @version 
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class SellWaterController extends BaseController{
    @Autowired
    private ICashService cashService;
    /**
     * Description: 跳转商品销售流水页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(SellWaterConstant.TO_SELLWATER_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(SellWaterConstant.SELLWATER_LIST_PAGE);
        return modelAndView;
    }
    /**
     * 查询商品销售流水列表
     */
    @RequestMapping(SellWaterConstant.SELLWATER_LIST)
    public ModelAndView registerList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = cashService.sellWaterpageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
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
    @RequestMapping(SellWaterConstant.DOWNLOAD_SELLINGWATER_DATA)
    public Message downloadCashData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> param = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        param.put("storeId", storeId);
        param.put("beginTime", request.getParameter("beginTime"));
        param.put("endTime", request.getParameter("endTime"));
        param.put("commodityNo", request.getParameter("commodityNo"));

        List<Map> data = cashService.sellWaterInfoQuery(param);
        //data.stream().filter(s->s.getSalePrice())
        try {

            String[][] headers = new String[][]{
                    {"商品编码", "商品名称", "商品条码", "销售时间","单位", "数量", "销售金额（元）", "成本（元）"},
                    {"commodityCode", "commodityName", "commodityNo", "addTime","unitName", "number", "totalVipPrice", "inPrice"}
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
