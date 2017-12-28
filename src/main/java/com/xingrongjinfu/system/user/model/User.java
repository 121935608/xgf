package com.xingrongjinfu.system.user.model;

import java.io.Serializable;
import java.util.List;
import com.xingrongjinfu.system.role.model.Role;

/**
 * 用户对象 sys_user
 * 
 * @author y
 */
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private String email;
    private String mobilePhone;
    private String accountName;
    private String password;
    private String salt;
    private String description;
    private String locked;
    private String createTime;
    private String status;


    private String roleName; // 角色临时字段

    private List<Role> roles; // 角色表

    public User()
    {
    }

    public User(String userId, String userName, String password)
    {
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLocked()
    {
        return locked;
    }

    public void setLocked(String locked)
    {
        this.locked = locked;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @Override
    public String toString()
    {
        return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", mobilePhone=" + mobilePhone
                + ", accountName=" + accountName + ", password=" + password + ", salt=" + salt + ", description="
                + description + ", locked=" + locked + ", createTime=" + createTime + ", roles=" + roles + "]";
    }

}
