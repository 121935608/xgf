package com.xingrongjinfu.statistics.graph.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.shiro.common.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.graph.common.GraphConstant;
import com.xingrongjinfu.statistics.paysWater.model.CashDetail;
import com.xingrongjinfu.statistics.paysWater.model.CashFlow;
import com.xingrongjinfu.statistics.paysWater.service.ICashService;

@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class GraphController {
    @Autowired
    private ICashService cashService;
    /**
     * Description: 跳转销售分析页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(GraphConstant.TO_ANALYSIS_URL)
    public ModelAndView salesAnalysisView() throws Exception{
        ModelAndView modelAndView = new ModelAndView(GraphConstant.ANALYSIS_PAGE);
        return modelAndView;
    }
    /**
     * Description: 跳转销售饼状图页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(GraphConstant.TO_PIE_URL)
    public ModelAndView pieChartView() throws Exception{
        ModelAndView modelAndView = new ModelAndView(GraphConstant.PIE_PAGE);
        return modelAndView;
    }
    /**
     * Description: 跳转今日客流分析页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(GraphConstant.TO_PASSENGER_ANALYSIS_URL)
    public ModelAndView passengerAnalysisView() throws Exception{
        ModelAndView modelAndView = new ModelAndView(GraphConstant.PASSENGER_ANALYSIS_PAGE);
        return modelAndView;
    }
    /**
     * 销售分析数据
     */
    @RequestMapping(GraphConstant.ANALYSIS_LIST)
    public @ResponseBody List<CashFlow> salesAnalysisList(String commodityId) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        List<CashFlow> list = cashService.saleGraphCombo(storeId);
        return list;
    }
    /**
     * 今日客流分析数据
     */
    @RequestMapping(GraphConstant.PASSENGER_ANALYSIS_LIST)
    public @ResponseBody List<CashFlow> passengerAnalysisGraph(String commodityId) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        List<CashFlow> list = cashService.passengerAnalysisGraph(storeId);
        return list;
    }
    /**
     * 销售饼图数据
     */
    @RequestMapping(GraphConstant.PIE_LIST)
    public @ResponseBody List<CashDetail> pieList(String commodityId) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        List<CashDetail> list = cashService.saleGraphPie(storeId);
        double count = 0;
        if(null != list){
            for (CashDetail cashDetail : list) {
                if(null != cashDetail.getTotalVipPrice())
                    count += cashDetail.getTotalVipPrice().doubleValue();
            }
            for (CashDetail cashDetail : list) {
                if(null != cashDetail.getTotalVipPrice()){
                    if(count != 0)
                        cashDetail.setTotalVipPrice(new BigDecimal(Double.toString(cashDetail.getTotalVipPrice().doubleValue()/count*100)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }else{
                    cashDetail.setTotalVipPrice(new BigDecimal("0"));
                }
            }
        }
        return list;
    }
}
