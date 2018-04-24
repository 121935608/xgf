package com.xingrongjinfu.crm.controller;

import org.framework.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.common.DeptConstant;
import com.xingrongjinfu.system.SystemConstant;

/**
 * 部门管理  处理
 * 
 * @author y
 */
@Controller
@RequestMapping(SystemConstant.CRM_URL)
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

}
