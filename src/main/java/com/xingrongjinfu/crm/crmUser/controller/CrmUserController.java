package com.xingrongjinfu.crm.crmUser.controller;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.system.supervisor.common.SupervisorConstant;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import com.xingrongjinfu.system.supervisor.service.ISupervisorService;
import com.xingrongjinfu.utils.AccessCodeUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /*
    * 修改CRM督导员状态
    * */
    @RequestMapping(CrmUserConstant.USER_CRM_UPDATE_STATUS)
    public @ResponseBody Message changeCRMSupervisorStatus(Supervisor supervisor){
        int result=0;
        Integer supervisorId=supervisor.getSupervisorId();
        if (supervisorId!=null) {
            result = supervisorService.updateSupervisorById(supervisor);
        }
        return new Message(result);
    }
    /*修改督导员信息*/
    @RequestMapping(CrmUserConstant.USER_CRM_UPDATE_URL)
    public ModelAndView suoervistorAddUpdate(Integer supervisorId){
        ModelAndView modelAndView =this.getModelAndView(CrmUserConstant.USER_CRM_UPDATE_PAGE);
        Supervisor supervisor=supervisorService.getSupervisor(supervisorId);
        modelAndView.addObject("Supervisor",supervisor);
        return modelAndView;
    }

    /*跳转增加督导员信息界面*/
    @RequestMapping(CrmUserConstant.USER_CRM_ADD_URL)
    public ModelAndView suoervistorAddUpdate(){
        ModelAndView modelAndView =this.getModelAndView(CrmUserConstant.USER_CRM_ADD_PAGE);
        return modelAndView;
    }
    /*添加督导员接口*/
    @RequestMapping(CrmUserConstant.USER_CRM_ADD_URL_TWO)
    public @ResponseBody Message addCRMSupervistor(Supervisor supervisor){
        int result = 0;
        supervisor.setSupervisorNum(AccessCodeUtil.accessCode());
        System.out.println("登录名是"+supervisor.getCrmLogin());
        result = supervisorService.addCRMSupervisor(supervisor);
        return new Message(result);
    }
}
