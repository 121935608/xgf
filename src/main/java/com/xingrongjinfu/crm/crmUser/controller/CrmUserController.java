package com.xingrongjinfu.crm.crmUser.controller;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.system.supervisor.service.ISupervisorService;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.crmUser.common.CrmUserConstant;

import java.util.List;

/**
 * 部门管理  处理
 * 
 * @author y
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL_USER)
public class CrmUserController extends BaseController
{
    @Autowired
    private ISupervisorService supervisorService;
    /**
     * 跳转用户列表界面
     */
    @RequestMapping(CrmUserConstant.USER_URL)
    public ModelAndView userCrmDept()
    {
        return this.getModelAndView(CrmUserConstant.USER_PAGE);
    }

    /*
    * 查询用户列表
    * */
    @RequestMapping(CrmUserConstant.USER_CRM_URL)
    public ModelAndView userList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo=supervisorService.SupervisorCRMPageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
}
