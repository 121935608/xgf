package com.xingrongjinfu.system.user.model;

/**
 * 用户和角色关联对象 sys_user_role
 * 
 * @author y
 */
public class UserRole
{
    private Integer userId;
    private Integer roleId;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

}
