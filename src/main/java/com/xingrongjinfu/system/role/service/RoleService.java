package com.xingrongjinfu.system.role.service;

import java.util.ArrayList;
import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.dao.IRoleDao;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.role.model.RolePermission;
import com.xingrongjinfu.system.user.model.UserRole;

/**
 * 角色管理 业务层处理
 * 
 * @author y
 */
@Service("roleService")
public class RoleService implements IRoleService
{

    @Autowired
    private IRoleDao roleDao;

    /**
     * 通过用户ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role findByUserId(String userId)
    {
        return roleDao.findRoleByUserId(userId);
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role findByRoleId(Integer roleId)
    {
        Role role = new Role();
        role.setRoleId(roleId);
        return roleDao.findRoleByRole(role);
    }

    /**
     * 查询所有角色信息
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public List<Role> findAllRole()
    {
        return roleDao.findAllRole();
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public List<Permission> queryRolePermission(String roleId)
    {
        return roleDao.queryRolePermission(roleId);
    }

    /**
     * 根据用户ID修改角色
     * 
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 结果
     */
    public int updateUserRole(UserRole userRole)
    {
        return roleDao.updateUserRole(userRole);
    }

    /**
     * 新增角色用户关联信息
     * 
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 结果
     */
    public int addUserRole(UserRole userRole)
    {
        return roleDao.addUserRole(userRole);
    }

    /**
     * 删除角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int deleteRoleById(Role role)
    {
        roleDao.deletePremsRoleById(role.getRoleId());
        return roleDao.deleteRoleById(role);
    }

    /**
     * 根据条件分页查询角色对象
     * 
     * @param page 分页对象
     * @return 角色信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return roleDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 修改角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRoleInfo(Role role)
    {
        return roleDao.updateRoleInfo(role);
    }

    /**
     * 新增角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int addRoleInfo(Role role)
    {
        return roleDao.addRoleInfo(role);
    }

    /**
     * 批量保存角色与菜单信息
     * 
     * @param roleId 角色ID
     * @param perms 菜单ID
     * @return 结果
     */
    public int batchSavePremsRole(Integer roleId, String perms)
    {
        String[] permIds = perms.split("\\,");
        List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
        for (String permsId : permIds)
        {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermsId(Integer.valueOf(permsId));
            rolePermission.setRoleId(roleId);
            rolePermissions.add(rolePermission);
        }
        roleDao.deletePremsRoleById(roleId);
        return roleDao.batchSavePremsRole(rolePermissions);
    }

}
