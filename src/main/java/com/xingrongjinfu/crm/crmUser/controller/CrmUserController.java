package com.xingrongjinfu.crm.crmUser.controller;

import com.xingrongjinfu.crm.CrmConstant;
import org.framework.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.crmUser.common.CrmUserConstant;

/**
 * 部门管理  处理
 * 
 * @author y
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL_USER)
public class CrmUserController extends BaseController
{
    /**
     * 跳转角色列表界面
     */
    @RequestMapping(CrmUserConstant.DEPT_URL)
    public ModelAndView loadCrmDept()
    {
        return this.getModelAndView(CrmUserConstant.DEPT_PAGE);
    }

    /**
     * 跳转用户列表界面
     */
    @RequestMapping(CrmUserConstant.USER_URL)
    public ModelAndView userCrmDept()
    {
        return this.getModelAndView(CrmUserConstant.USER_PAGE);
    }

}
