package com.xingrongjinfu.system.permission.service;

import java.util.List;

import com.xingrongjinfu.utils.ObjectUtil;
import org.apache.shiro.common.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.system.permission.dao.IPermissionDao;
import com.xingrongjinfu.system.permission.model.Permission;

/**
 * 权限管理 业务处理
 * 
 * @author y
 */
@Service("permissionService")
public class PermissionService implements IPermissionService
{

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有权限信息
     * 
     * @return 权限集合
     */
    public List<Permission> queryPermissions()
    {
        return permissionDao.queryPermissions();
    }

    /**
     * 根据用户ID查询菜单集合
     * 
     * @param userid 用户ID
     * @return 菜单集合数据
     */
    public List<Permission> queryMenuListByUserId(Integer userId)
    {
        return permissionDao.queryMenuListByUserId(userId);
    }

    /**
     * 新增菜单
     * 
     * @param menu 菜单对象
     * @return 结果
     */
    public int insertPermission(Permission permission)
    {
        return permissionDao.insertPermission(permission);
    }

    /**
     * 修改菜单
     * 
     * @param menu 菜单对象
     * @return 结果
     */
    public int updatePermission(Permission permission)
    {
        return permissionDao.updatePermission(permission);
    }

    /**
     * 删除菜单
     * 
     * @param menuId 菜单Id
     * @return
     */
    public int deletePermission(Permission permission)
    {
        return permissionDao.deletePermission(permission);
    }

    /**
     * 根据菜单ID查询
     * 
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    public Permission findPermissionById(Integer permsId)
    {
        return permissionDao.findPermissionById(permsId);
    }

    /**
     * 根据父菜单ID查询
     * 
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    public List<Permission> findPermissionByPid(String parentId)
    {
        return permissionDao.findPermissionByPid(parentId);
    }


    @Override
    public int checkMenuName(Permission permission) {
        int notUnique=0;
        int unique=1;
        //一级菜单不校验重复
        if (permission.getParentId()==0){
            return unique;
            //二级菜单校验
        }else {
            Permission newPermission=permissionDao.checkMenuName(permission);
            //查询有结果就不唯一
            if (ObjectUtil.isNotNull(newPermission)){
                return notUnique;
            }
            //没有结果则是唯一的
            return unique;
        }

    }
}
