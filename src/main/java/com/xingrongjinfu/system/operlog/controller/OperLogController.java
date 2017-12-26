package com.xingrongjinfu.system.operlog.controller;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.operlog.common.OperLogConstant;
import com.xingrongjinfu.system.operlog.service.IOperLogService;

/**
 * 操作日志 处理
 * 
 * @author y
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class OperLogController extends BaseController
{

    @Autowired
    private IOperLogService operLogService;

    /**
     * 跳转日志列表界面
     */
    @RequestMapping(OperLogConstant.OPERLOG_URL)
    public ModelAndView loadSystemUser()
    {
        return this.getModelAndView(OperLogConstant.OPERLOG_PAGE);
    }

    /**
     * 查询操作日志列表
     */
    @RequestMapping(OperLogConstant.OPERLOG_LIST_URL)
    public ModelAndView operLogList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo = operLogService.pageInfoQuery(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

}
