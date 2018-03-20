package com.xingrongjinfu.statistics.saleCount.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.paysWater.model.CashDetail;
import com.xingrongjinfu.statistics.paysWater.service.ICashService;
import com.xingrongjinfu.statistics.saleCount.common.SaleCountConstant;

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
}
