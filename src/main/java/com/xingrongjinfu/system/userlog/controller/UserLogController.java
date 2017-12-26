package com.xingrongjinfu.system.userlog.controller;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.userlog.common.UserLogConstant;
import com.xingrongjinfu.system.userlog.service.IUserLogService;

/**
 * 登录日志 处理
 * 
 * @author y
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class UserLogController extends BaseController
{

    @Autowired
    private IUserLogService userLogService;

    /**
     * 跳转访问日志列表界面
     */
    @RequestMapping(UserLogConstant.USERLOG_URL)
    public ModelAndView loadSystemUser()
    {
        return this.getModelAndView(UserLogConstant.USERLOG_PAGE);
    }

    /**
     * 查询访问日志列表
     */
    @RequestMapping(UserLogConstant.USERLOG_LIST_URL)
    public ModelAndView operLogList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = userLogService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

}
