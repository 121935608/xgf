package com.xingrongjinfu.system.role.model;

import java.io.Serializable;
import java.util.List;
import com.xingrongjinfu.system.permission.model.Permission;

/**
 * 角色管理对象 sys_role
 * 
 * @author y
 */
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer roleId;
    private String status;
    private String roleName;
    private String roleKey;
    private String description;
    private String createTime;

    private List<Permission> permissions; // 菜表单

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleKey()
    {
        return roleKey;
    }

    public void setRoleKey(String roleKey)
    {
        this.roleKey = roleKey;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public List<Permission> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions)
    {
        this.permissions = permissions;
    }

    public String toString()
    {
        return "Role [roleId=" + roleId + ", status=" + status + ", roleName=" + roleName + ", roleKey=" + roleKey
                + ", description=" + description + ", createTime=" + createTime + ", permissions=" + permissions + "]";
    }

}
