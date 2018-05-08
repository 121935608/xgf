package com.xingrongjinfu.crm.performanceRank.controller;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.department.common.DeptConstant;
import com.xingrongjinfu.crm.performanceRank.common.PerformanceRankConstant;
import com.xingrongjinfu.crm.performanceRank.service.IPerformanceRankService;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * @author hyq
 * @version V1.0
 * @Description: 业绩排名   处理
 * @date 2018年4月25日
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL)
public class PerformanceRankController extends BaseController {
    @Autowired
    private IPerformanceRankService performanceRankService;

    /**
     * 跳转访问记录列表界面
     */
    @RequestMapping(PerformanceRankConstant.PERFORMANCE_RANK_URL)
    public ModelAndView loadCrmVisit()
    {
        return this.getModelAndView(PerformanceRankConstant.PERFORMANCE_RANK_PAGE);
    }

    /**
     * 查询部门列表
     */
    @RequestMapping(PerformanceRankConstant.PERFORMANCE_LIST_URL)
    public ModelAndView performanceRankList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = performanceRankService.pageInfoQueryBydept(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

}
