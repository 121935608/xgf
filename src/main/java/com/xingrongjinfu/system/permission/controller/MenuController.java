package com.xingrongjinfu.system.permission.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.permission.common.PermissionConstant;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.permission.service.IPermissionService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 权限管理 处理
 * 
 * @author y
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class MenuController extends BaseController
{

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IPermissionService permissionService;

    /**
     * 跳转菜单列表界面
     */
    @RequestMapping(PermissionConstant.MENU_URL)
    public ModelAndView loadSystemUser()
    {
        ModelAndView modelAndView = this.getModelAndView(PermissionConstant.MENU_PAGE);
        modelAndView.addObject("parents", getParentList());
        return modelAndView;
    }

    /**
     * 跳转图标选择界面
     */
    @RequestMapping(PermissionConstant.IOCN_URL)
    public ModelAndView icon()
    {
        return this.getModelAndView(PermissionConstant.ICON_PAGE);
    }

    /**
     * 查询菜单列表数据
     */
    @RequestMapping(PermissionConstant.MENU_LIST_URL)
    public @ResponseBody List<Map<String, Object>> menuList()
    {
        List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();

        // 根据用户取出菜单
        List<Permission> permissionList = permissionService.queryPermissions();

        for (Permission permission : permissionList)
        {
            Map<String, Object> permissionMap = new HashMap<String, Object>();
            permissionMap.put("id", permission.getPermsId());
            permissionMap.put("pId", permission.getParentId());
            permissionMap.put("isParent", permission.getPermsType().equals("0") ? true : false);
            permissionMap.put("name", permission.getPermsName());
            permissionMap.put("open", false);
            permissionMap.put("_href", permission.getPermsUrl());
            resMapTrees.add(permissionMap);
        }

        return resMapTrees;
    }

    /**
     * 获取菜单详细信息
     * 
     * @param permsId 菜单id
     * @return 菜单对象
     */
    @RequestMapping(PermissionConstant.VIEW_URL)
    public @ResponseBody Permission getMenu(Integer permsId)
    {
        logger.info("get getMenu:{}", permsId);
        Permission permission = permissionService.findPermissionById(permsId);
        return permission;
    }

    /**
     * 根据ID删除用户
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-删除菜单", isSaveRequestData = true)
    @RequestMapping(PermissionConstant.DEL_URL)
    public @ResponseBody Message delMenu(Permission permission)
    {
        int result = 0;
        Integer permsId = permission.getPermsId();
        Integer parentId = permission.getParentId();
        if (ObjectUtil.isNotNull(permsId) || ObjectUtil.isNotNull(parentId))
        {
            result = permissionService.deletePermission(permission);
        }
        if (ObjectUtil.isNull(parentId))
        {
            permission.setParentId(permsId);
            permission.setPermsId(null);
            permissionService.deletePermission(permission);
        }
        return new Message(result);
    }

    /**
     * 保存菜单信息
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-保存菜单", isSaveRequestData = true)
    @RequestMapping(PermissionConstant.SAVE_URL)
    public @ResponseBody Message saveMenu(Permission permission)
    {
        int result = 0;
        Integer permsId = permission.getPermsId();

        if (permsId != null)
        {
            result = permissionService.updatePermission(permission);
        }
        else
        {
            result = permissionService.insertPermission(permission);
        }
        return new Message(result);
    }

    /**
     * 获取上级菜单对象key
     */
    public List<SysCode> getParentList()
    {
        List<Permission> permissionList = permissionService.findPermissionByPid("");
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Permission permission : permissionList)
        {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(permission.getPermsId().toString());
            sysCode.setCodevalue(permission.getPermsName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }

}
