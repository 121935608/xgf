package com.xingrongjinfu.system.role.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.role.model.RolePermission;
import com.xingrongjinfu.system.user.model.UserRole;

/**
 * 角色管理 数据层处理
 * 
 * @author y
 */
@Repository("roleDao")
public class RoleDao extends DynamicObjectBaseDao implements IRoleDao
{

    /**
     * 通过用户ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role findRoleByUserId(String userId)
    {
        return (Role) this.findForObject("SystemRoleMapper.findRoleByUserId", userId);
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role findRoleByRole(Role role)
    {
        return (Role) this.findForObject("SystemRoleMapper.findRoleByRole", role);
    }

    /**
     * 查询所有角色信息
     * 
     * @param roleId 角色ID
     * @return 角色集合对象信息
     */
    @SuppressWarnings("unchecked")
    public List<Role> findAllRole()
    {
        List<Role> roleList = null;
        try
        {
            roleList = (List<Role>) this.findForList("SystemRoleMapper.findRoleByRole", null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @SuppressWarnings("unchecked")
    public List<Permission> queryRolePermission(String roleId)
    {
        List<Permission> permsList = null;
        try
        {
            permsList = (List<Permission>) this.findForList("SystemRoleMapper.queryRolePermission", roleId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
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
        return this.update("SystemRoleMapper.updateUserRole", userRole);
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
        return (int) this.save("SystemRoleMapper.addUserRole", userRole);
    }

    /**
     * 删除角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int deleteRoleById(Role role)
    {
        return this.update("SystemRoleMapper.deleteRoleById", role);
    }

    /**
     * 根据条件分页查询角色对象
     * 
     * @param page 分页对象
     * @return 角色信息集合信息
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("SystemRoleMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }

    /**
     * 修改角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRoleInfo(Role role)
    {
        return this.update("SystemRoleMapper.updateRoleInfo", role);
    }

    /**
     * 新增角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int addRoleInfo(Role role)
    {
        return (int) this.save("SystemRoleMapper.addRoleInfo", role);
    }

    /**
     * 删除角色与菜单信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deletePremsRoleById(Integer roleId)
    {
        return (int) this.delete("SystemRoleMapper.deletePremsRoleById", roleId);
    }

    /**
     * 批量保存角色与菜单信息
     * 
     * @param roleId 角色ID
     * @param perms 菜单ID
     * @return 结果
     */
    public int batchSavePremsRole(List<RolePermission> rolePermissions)
    {
        return this.batchSave("SystemRoleMapper.batchSavePremsRole", rolePermissions);
    }

}
