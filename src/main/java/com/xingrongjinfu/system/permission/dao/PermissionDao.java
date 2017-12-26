package com.xingrongjinfu.system.permission.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.system.permission.model.Permission;

/**
 * 权限管理 数据层处理
 * 
 * @author y
 */
@Repository("permissionDao")
public class PermissionDao extends DynamicObjectBaseDao implements IPermissionDao
{
    /**
     * 查询所有权限信息
     * 
     * @return 权限集合
     */
    @SuppressWarnings("unchecked")
    public List<Permission> queryPermissions()
    {
        List<Permission> permsList = null;
        try
        {
            permsList = (List<Permission>) this.findForList("SystemPermissionMapper.queryPermissions", null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

    /**
     * 根据用户ID查询菜单集合
     * 
     * @param userid 用户ID
     * @return 菜单集合数据
     */
    @SuppressWarnings("unchecked")
    public List<Permission> queryMenuListByUserId(Integer userId)
    {
        List<Permission> permsList = null;
        try
        {
            permsList = (List<Permission>) this.findForList("SystemPermissionMapper.queryMenuListByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

    /**
     * 新增菜单
     * 
     * @param menu 菜单对象
     * @return 结果
     */
    public int insertPermission(Permission permission)
    {
        return (int) this.save("SystemPermissionMapper.insertPermission", permission);
    }

    /**
     * 修改菜单
     * 
     * @param menu 菜单对象
     * @return 结果
     */
    public int updatePermission(Permission permission)
    {
        return (int) this.update("SystemPermissionMapper.updatePermission", permission);
    }

    /**
     * 删除菜单
     * 
     * @param menuId 菜单Id
     * @return
     */
    public int deletePermission(Permission permission)
    {
        return (int) this.delete("SystemPermissionMapper.deletePermission", permission);
    }

    /**
     * 根据菜单ID查询
     * 
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    public Permission findPermissionById(@Param(value = "permsId") Integer permsId)
    {
        return (Permission) this.findForObject("SystemPermissionMapper.findPermissionById", permsId);
    }

    /**
     * 根据父菜单ID查询
     * 
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    @SuppressWarnings("unchecked")
    public List<Permission> findPermissionByPid(@Param(value = "parentId") String parentId)
    {
        List<Permission> parentList = null;
        try
        {
            parentList = (List<Permission>) this.findForList("SystemPermissionMapper.findPermissionByPid", parentId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return parentList;
    }
}
