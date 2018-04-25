package com.xingrongjinfu.crm.crmUser.controller;

import com.xingrongjinfu.crm.CrmConstant;
import org.framework.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.crmUser.common.DeptConstant;

/**
 * 部门管理  处理
 * 
 * @author y
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL)
public class DeptController extends BaseController
{
    /**
     * 跳转角色列表界面
     */
    @RequestMapping(DeptConstant.DEPT_URL)
    public ModelAndView loadCrmDept()
    {
        return this.getModelAndView(DeptConstant.DEPT_PAGE);
    }

    /**
     * 跳转用户列表界面
     */
    @RequestMapping(DeptConstant.USER_URL)
    public ModelAndView userCrmDept()
    {
        return this.getModelAndView(DeptConstant.USER_PAGE);
    }

}
