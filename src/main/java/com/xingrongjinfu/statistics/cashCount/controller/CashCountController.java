package com.xingrongjinfu.statistics.cashCount.controller;

import java.util.List;

import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.cashCount.common.CashCountConstant;
import com.xingrongjinfu.statistics.paysWater.service.ICashService;

@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class CashCountController extends BaseController {
    @Autowired
    private ICashService cashService;
    /**
     * Description: 跳转收银统计页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(CashCountConstant.TO_CASHCOUNT_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(CashCountConstant.CASH_LIST_PAGE);
        return modelAndView;
    }
    /**
     * 查询收银统计列表
     */
    @RequestMapping(CashCountConstant.CASHCOUNT_LIST)
    public ModelAndView registerList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = cashService.cashCountpageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }



    
}
