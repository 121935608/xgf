package com.xingrongjinfu.statistics.sellWater.controller;

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
import com.xingrongjinfu.statistics.paysWater.service.ICashService;
import com.xingrongjinfu.statistics.sellWater.common.SellWaterConstant;

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
    
}
