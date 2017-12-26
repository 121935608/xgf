package com.xingrongjinfu.system.role.model;

/**
 * 角色管理对象 sys_role_permission
 * 
 * @author y
 */
public class RolePermission
{
    private Integer roleId;
    private Integer permsId;

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    public Integer getPermsId()
    {
        return permsId;
    }

    public void setPermsId(Integer permsId)
    {
        this.permsId = permsId;
    }

    public String toString()
    {
        return "RolePermission [roleId=" + roleId + ", permsId=" + permsId + "]";
    }
}
