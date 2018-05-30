package com.xingrongjinfu.crm.department.controller;

import java.util.List;

import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.crmUser.common.CrmUserConstant;
import com.xingrongjinfu.crm.department.common.DeptConstant;
import com.xingrongjinfu.crm.department.model.Dept;
import com.xingrongjinfu.crm.department.service.IDeptService;
import com.xingrongjinfu.system.supervisor.model.Supervisor;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 部门管理   处理 
* @date 2018年4月25日
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL)
public class DeptController extends BaseController
{
	@Autowired
    private IDeptService deptService;

    /**
     * 跳转部门列表界面
     */
    @RequestMapping(DeptConstant.DEPT_URL)
    public ModelAndView loadCrmDept()
    {
        return this.getModelAndView(DeptConstant.DEPT_PAGE);
    }
    
    
    /**
     * 跳转部门新增界面
     */
    @RequestMapping(DeptConstant.TO_ADD_URL)
    public ModelAndView toDeptAdd(String deptId)
    {
        return this.getModelAndView(DeptConstant.ADD_PAGE);
    }
    
    
    /**
     * 跳转部门修改界面
     */
    @RequestMapping(DeptConstant.TO_UPDATE_URL)
    public ModelAndView toDeptModify(@RequestParam(required = true) String deptId)
    {
        ModelAndView modelAndView = this.getModelAndView(DeptConstant.UPDATE_PAGE);
        if (deptId != null)
        {
            modelAndView.addObject("dept", deptService.findByDeptId(deptId));
        }
        return modelAndView;
    }

    
    /**
     * 查询部门列表
     */
    @RequestMapping(DeptConstant.DEPT_LIST_URL)
    public ModelAndView deptList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = deptService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
    
    /**
     * 保存部门信息
     */
    @ActionControllerLog(title = "谢鲜CRM管理", action = "谢鲜CRM管理-保存部门", isSaveRequestData = true)
    @RequestMapping(DeptConstant.SAVE_DEPT_URL)
    public @ResponseBody Message saveDept(Dept dept)
    {
        int result = 0;

        if (dept.getDeptId() != null)
        {
            result = deptService.updateDeptInfo(dept);
        }
        else
        {
            result = deptService.addDeptInfo(dept);
        }
        return new Message(result);
    }
    
    
    /**
     * 根据ID删除部门
     */
    @ActionControllerLog(title = "谢鲜CRM管理", action = "谢鲜CRM管理-删除部门", isSaveRequestData = true)
    @RequestMapping(DeptConstant.DEL_URL)
    public @ResponseBody Message deleteDeptById(Dept dept)
    {
        int result = 0;
        if (dept.getDeptId() != null)
        {
            result = deptService.deleteDeptById(dept);
        }
        return new Message(result);
    }
    
    
    /**
     * 检查部门名称是否存在
     * */
    @RequestMapping(DeptConstant.CHECK_DEPT_UNIQUE)
    public @ResponseBody String checkDeptUnique(Dept dept){
		String checkDeptUnique = "0";
		if (dept != null && dept.getDeptName() != null) {
			List<Dept> list = deptService.checkDeptUnique(dept.getDeptName());
			if (list.size() > 0) {
				checkDeptUnique = "1";
			}
		}
		return checkDeptUnique;
    }
}
