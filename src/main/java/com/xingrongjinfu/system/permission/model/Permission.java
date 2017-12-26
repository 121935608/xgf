package com.xingrongjinfu.system.permission.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理对象  sys_permission
 * 
 * @author y
 */
public class Permission implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer permsId;
    private String permsName;
    private Integer parentId;
    private String permsKey;
    private String permsType;
    private String permsUrl;
    private String permsLevel;
    private String permsIcon;
    private Integer available;
    private String description;

    private String checked; // 是否选中
    private List<Permission> children = new ArrayList<Permission>();

    public Integer getPermsId()
    {
        return permsId;
    }

    public void setPermsId(Integer permsId)
    {
        this.permsId = permsId;
    }

    public String getPermsName()
    {
        return permsName;
    }

    public void setPermsName(String permsName)
    {
        this.permsName = permsName;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public String getPermsKey()
    {
        return permsKey;
    }

    public void setPermsKey(String permsKey)
    {
        this.permsKey = permsKey;
    }

    public String getPermsType()
    {
        return permsType;
    }

    public void setPermsType(String permsType)
    {
        if (this.parentId == 0)
        {
            permsType = "0";
        }
        this.permsType = permsType;
    }

    public String getPermsUrl()
    {
        return permsUrl;
    }

    public void setPermsUrl(String permsUrl)
    {
        this.permsUrl = permsUrl;
    }

    public String getPermsLevel()
    {
        return permsLevel;
    }

    public void setPermsLevel(String permsLevel)
    {
        this.permsLevel = permsLevel;
    }

    public String getPermsIcon()
    {
        return permsIcon;
    }

    public void setPermsIcon(String permsIcon)
    {
        this.permsIcon = permsIcon;
    }

    public Integer getAvailable()
    {
        return available;
    }

    public void setAvailable(Integer available)
    {
        this.available = available;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getChecked()
    {
        return checked;
    }

    public void setChecked(String checked)
    {
        this.checked = checked;
    }

    public List<Permission> getChildren()
    {
        return children;
    }

    public void setChildren(List<Permission> children)
    {
        this.children = children;
    }

    public String toString()
    {
        return "Permission [permsId=" + permsId + ", permsName=" + permsName + ", parentId=" + parentId + ", permsKey="
                + permsKey + ", permsType=" + permsType + ", permsUrl=" + permsUrl + ", permsLevel=" + permsLevel
                + ", permsIcon=" + permsIcon + ", available=" + available + ", description=" + description
                + ", checked=" + checked + ", children=" + children + "]";
    }

}
