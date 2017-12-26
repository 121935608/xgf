package com.xingrongjinfu.system.role.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.alibaba.fastjson.JSON;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.common.RoleConstant;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.role.service.IRoleService;

/**
 * 角色管理  处理
 * 
 * @author y
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class RoleController extends BaseController
{

    @Autowired
    private IRoleService roleService;

    /**
     * 跳转角色列表界面
     */
    @RequestMapping(RoleConstant.ROLE_URL)
    public ModelAndView loadSystemUser()
    {
        return this.getModelAndView(RoleConstant.ROLE_PAGE);
    }

    /**
     * 跳转角色新增界面
     */
    @RequestMapping(RoleConstant.TO_ADD_URL)
    public ModelAndView toRoleAdd(String roleId)
    {
        return this.getModelAndView(RoleConstant.ADD_PAGE);
    }

    /**
     * 跳转角色修改界面
     */
    @RequestMapping(RoleConstant.TO_MODIFY_URL)
    public ModelAndView toRoleModify(@RequestParam(required = true) Integer roleId)
    {
        ModelAndView modelAndView = this.getModelAndView(RoleConstant.MODIFY_PAGE);
        if (roleId != null)
        {
            modelAndView.addObject("role", roleService.findByRoleId(roleId));
        }
        return modelAndView;
    }

    /**
     * 跳转角色授权界面
     */
    @RequestMapping(RoleConstant.AUTH_URL)
    public ModelAndView toRoleAuthorize(@RequestParam(required = true) String roleId)
    {
        ModelAndView modelAndView = this.getModelAndView(RoleConstant.AUTH_PAGE);
        List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();
        if (roleId != null)
        {
            // 根据角色取出对应权限菜单
            List<Permission> permissionList = roleService.queryRolePermission(roleId);
            for (Permission permission : permissionList)
            {
                Map<String, Object> permissionMap = new HashMap<String, Object>();
                permissionMap.put("id", permission.getPermsId());
                permissionMap.put("pId", permission.getParentId());
                permissionMap.put("name", permission.getPermsName());
                permissionMap.put("open", false);
                permissionMap.put("checked", permission.getChecked());
                permissionMap.put("doCheck", "0".equals(permission.getAvailable()) ? true : false);
                resMapTrees.add(permissionMap);
            }
        }
        modelAndView.addObject("roleId", roleId);
        modelAndView.addObject("zTreeNodes", JSON.toJSONString(resMapTrees));
        return modelAndView;
    }

    /**
     * 查询用户列表
     */
    @RequestMapping(RoleConstant.ROLE_LIST_URL)
    public ModelAndView roleList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = roleService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 根据ID删除角色
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-删除角色", isSaveRequestData = true)
    @RequestMapping(RoleConstant.DEL_URL)
    public @ResponseBody Message deleteRoleById(Role role)
    {
        int result = 0;
        Integer id = role.getRoleId();
        if (id != null)
        {
            result = roleService.deleteRoleById(role);
        }
        return new Message(result);
    }

    /**
     * 保存角色信息
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-保存角色", isSaveRequestData = true)
    @RequestMapping(RoleConstant.SAVE_ROLE_URL)
    public @ResponseBody Message saveRole(Role role)
    {
        int result = 0;
        Integer roleId = role.getRoleId();

        if (roleId != null)
        {
            result = roleService.updateRoleInfo(role);
        }
        else
        {
            result = roleService.addRoleInfo(role);
        }
        return new Message(result);
    }

    /**
     * 保存授权信息
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-授权角色", isSaveRequestData = true)
    @RequestMapping(RoleConstant.SAVE_AUTH_URL)
    public @ResponseBody Message savePermissions(@RequestParam Integer roleId, @RequestParam String perms)
    {
        int result = 0;

        if (roleId != null)
        {
            result = roleService.batchSavePremsRole(roleId, perms);
        }
        return new Message(result);
    }

}
