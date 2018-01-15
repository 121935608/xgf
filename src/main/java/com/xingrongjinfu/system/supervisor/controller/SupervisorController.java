/**
 * Copyright (C), 2017
 * FileName: SupervisorController
 * Author:   zxuser
 * Date:     2017/12/29 10:15
 * Description: 督导员的控制层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.supervisor.controller;

import com.xingrongjinfu.system.SystemConstant;
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

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈督导员的控制层〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class SupervisorController extends BaseController {

    @Autowired
    private ISupervisorService supervisorService;
    /**
     * 跳转到督导员页面
     * @return
     */
    @RequestMapping(SupervisorConstant.SUPERVISOR_URL)
    public ModelAndView loadSupervisor(){return this.getModelAndView(SupervisorConstant.SUPERVISOR_PAGE);}

    /**
     * 查询督导员信息列表
     */
    @RequestMapping(SupervisorConstant.SUPERVISOR_LIST_URL)
    public ModelAndView supervisorList(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=supervisorService.SupervisorPageInfoQuery(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }


    /**
     * 跳转到修改督导员界面
     */
    @RequestMapping(SupervisorConstant.SUPERVISOR_MODIFY_URL)
    public ModelAndView loadModifyPage(Integer supervisorId)
    {
       ModelAndView modelAndView =this.getModelAndView(SupervisorConstant.SUPERVISOR_MODIFY_PAGE);
       Supervisor supervisor=supervisorService.getSupervisor(supervisorId);
       modelAndView.addObject("Supervisor",supervisor);
       return modelAndView;
    }

    /**
     * 编辑督导员
     * @param supervisor
     * @return
     */
    @RequestMapping(SupervisorConstant.SUPERVISOR_MODIFY)
    public @ResponseBody
    Message toModifySupervisor(Supervisor supervisor)
    {
       int result=0;
       Integer supervisorId=supervisor.getSupervisorId();
       if (supervisorId !=null){
           result = supervisorService.updateSupervisorById(supervisor);
       }
       return new Message(result);
    }

    /**
     * 跳转到督导员添加界面
     * @return
     */
    @RequestMapping(SupervisorConstant.SUPERVISOR_ADD_URL)
    public ModelAndView loadAddPage(){return this.getModelAndView(SupervisorConstant.SUPERVISOR_ADD_PAGE);}

    /**
     * 添加督导员
     * @param supervisor
     * @return
     */
    @RequestMapping(SupervisorConstant.SUPERVISOR_ADD)
    public @ResponseBody Message addSupervisor(Supervisor supervisor)
        {
            int result=0;
            supervisor.setSupervisorNum(AccessCodeUtil.accessCode());
            result = supervisorService.addSupervisor(supervisor);
            return new Message(result);
    }

    /**
     * 督导员的启用停用
     */
    @RequestMapping(SupervisorConstant.SUPERVISOR_STATUS_CHANGE)
    public @ResponseBody Message changeStatus(Supervisor supervisor)
    {
        int result=0;
        Integer supervisorId=supervisor.getSupervisorId();
        if (supervisorId!=null) {
            result = supervisorService.updateSupervisorById(supervisor);
        }
        return new Message(result);
    }

    /**
     * 校验手机号
     */
    @RequestMapping(SupervisorConstant.CHECK_PHONE_URL)
    public @ResponseBody String checkPhone(Supervisor supervisor){
        String uniqueFlag="0";
        if (supervisor !=null)
        {
            uniqueFlag=supervisorService.checkPhone(supervisor);
        }
        return uniqueFlag;
    }
}