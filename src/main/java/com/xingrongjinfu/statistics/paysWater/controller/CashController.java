package com.xingrongjinfu.statistics.paysWater.controller;

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
import com.xingrongjinfu.statistics.paysWater.common.CashConstant;
import com.xingrongjinfu.statistics.paysWater.model.CashFlow;
import com.xingrongjinfu.statistics.paysWater.service.ICashService;

@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class CashController extends BaseController{
    @Autowired
    private ICashService cashService;
    /**
     * Description: 跳转收银流水页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(CashConstant.TO_CASH_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(CashConstant.CASH_LIST_PAGE);
        return modelAndView;
    }
    /**
     * Description: 跳转收银明细页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(CashConstant.TO_CASH_DETAIL_URL)
    public ModelAndView toRegisterAdd(String cashId) throws Exception{
        ModelAndView modelAndView = new ModelAndView(CashConstant.CASH_DETAIL_PAGE);
        CashFlow cashFlow = cashService.getById(cashId);
        modelAndView.addObject("cashFlow", cashFlow);
        return modelAndView;
    }
    /**
     * 查询收银流水列表
     */
    @RequestMapping(CashConstant.CASH_LIST_URL)
    public ModelAndView registerList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = cashService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
}
