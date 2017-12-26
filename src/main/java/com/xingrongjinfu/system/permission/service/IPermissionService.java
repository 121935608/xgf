package com.xingrongjinfu.system.permission.service;

import java.util.List;
import com.xingrongjinfu.system.permission.model.Permission;

/**
 * 权限管理 业务层
 * 
 * @author y
 */
public interface IPermissionService
{
    /**
     * 查询所有权限信息
     * 
     * @return 权限集合
     */
    public List<Permission> queryPermissions();

    /**
     * 根据用户ID查询菜单集合
     * 
     * @param userid 用户ID
     * @return 菜单集合数据
     */
    public List<Permission> queryMenuListByUserId(Integer userId);

    /**
     * 新增菜单
     * 
     * @param menu 菜单对象
     * @return 结果
     */
    public int insertPermission(Permission permission);

    /**
     * 修改菜单
     * 
     * @param menu 菜单对象
     * @return 结果
     */
    public int updatePermission(Permission permission);

    /**
     * 删除菜单
     * 
     * @param menuId 菜单Id
     * @return
     */
    public int deletePermission(Permission permission);

    /**
     * 根据菜单ID查询
     * 
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    public Permission findPermissionById(Integer permsId);

    /**
     * 根据父菜单ID查询
     * 
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    public List<Permission> findPermissionByPid(String parentId);
}
