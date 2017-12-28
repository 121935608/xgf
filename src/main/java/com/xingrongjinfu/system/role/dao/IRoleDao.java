package com.xingrongjinfu.system.role.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.role.model.RolePermission;
import com.xingrongjinfu.system.user.model.UserRole;

/**
 * 角色管理 数据层
 * 
 * @author y
 */
public interface IRoleDao
{
    /**
     * 通过用户ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role findRoleByUserId(String userId);

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role findRoleByRole(Role role);

    /**
     * 查询所有角色信息
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public List<Role> findAllRole();

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public List<Permission> queryRolePermission(String roleId);

    /**
     * 根据用户ID修改角色
     * 
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 结果
     */
    public int updateUserRole(UserRole userRole);

    /**
     * 新增角色用户关联信息
     * 
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 结果
     */
    public int addUserRole(UserRole userRole);

    /**
     * 删除角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int deleteRoleById(Role role);

    /**
     * 根据条件分页查询角色对象
     * 
     * @param page 分页对象
     * @return 角色信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 修改角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRoleInfo(Role role);

    /**
     * 新增角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int addRoleInfo(Role role);

    /**
     * 删除角色与菜单信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deletePremsRoleById(Integer roleId);

    /**
     * 批量保存角色与菜单信息
     * 
     * @param roleId 角色ID
     * @param perms 菜单ID
     * @return 结果
     */
    public int batchSavePremsRole(List<RolePermission> rolePermissions);

}
