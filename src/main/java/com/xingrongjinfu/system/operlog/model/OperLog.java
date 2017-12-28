package com.xingrongjinfu.system.operlog.model;

import java.util.Date;

/**
 * 操作日志对象 sys_operlog
 * 
 * @author y
 */
public class OperLog
{
    private Integer logId;
    private String action;
    private String title;
    private String channel;
    private String requestParam;
    private String userIp;
    private Date createTIme;
    private String url;
    private String status;
    private String userId;
    private String userName;
    private String errorMessage;

    public Integer getLogId()
    {
        return logId;
    }

    public void setLogId(Integer logId)
    {
        this.logId = logId;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getRequestParam()
    {
        return requestParam;
    }

    public void setRequestParam(String requestParam)
    {
        this.requestParam = requestParam;
    }

    public String getUserIp()
    {
        return userIp;
    }

    public void setUserIp(String userIp)
    {
        this.userIp = userIp;
    }

    public Date getCreateTIme()
    {
        return createTIme;
    }

    public void setCreateTIme(Date createTIme)
    {
        this.createTIme = createTIme;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

}