package com.xingrongjinfu.crm.statistics.controller;

import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.statistics.common.StatisticsConstant;
import com.xingrongjinfu.crm.statistics.service.IStatisticsService;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 统计数据查询管理   处理 
* @date 2018年4月27日
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL)
public class StatisticsController extends BaseController
{
	@Autowired
    private IStatisticsService statisticsService;

	
    /**
     * 跳转统计数据界面
     */
    @RequestMapping(StatisticsConstant.STATISTICS_URL)
    public ModelAndView loadCrmStatistics()
    {
    	ModelAndView modelAndView = this.getModelAndView(StatisticsConstant.STATISTICS_PAGE);
    	modelAndView.addObject("statistics", statisticsService.statisticsQuery());
        return modelAndView;
    }
}
	