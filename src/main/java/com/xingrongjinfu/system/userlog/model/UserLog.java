package com.xingrongjinfu.system.userlog.model;

import java.util.Date;

/**
 * 登录日志对象 sys_operlog
 * 
 * @author y
 */
public class UserLog
{
    private Integer signId;
    private String userName;
    private String status;
    private String loginIP;
    private String browser;
    private String os;
    private String msg;
    private Date loginTime;

    public Integer getSignId()
    {
        return signId;
    }

    public void setSignId(Integer signId)
    {
        this.signId = signId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getLoginIP()
    {
        return loginIP;
    }

    public void setLoginIP(String loginIP)
    {
        this.loginIP = loginIP;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

}