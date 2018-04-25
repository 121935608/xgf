package com.xingrongjinfu.crm.visit.controller;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.visit.common.VisitConstant;
import com.xingrongjinfu.crm.visit.service.IVisitService;

/**
 * 部门管理  处理
 * 
 * @author y
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL_DEPT)
public class VisitController extends BaseController
{
	
	@Autowired
    private IVisitService visitService;
	
    /**
     * 跳转访问记录列表界面
     */
    @RequestMapping(VisitConstant.VISIT_URL)
    public ModelAndView loadCrmDept()
    {
        return this.getModelAndView(VisitConstant.VISIT_PAGE);
    }
    
    
    /**
     * 查询访问记录列表
     */
    @RequestMapping(VisitConstant.VISIT_LIST_URL)
    public ModelAndView roleList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = visitService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
}
