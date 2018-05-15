package com.xingrongjinfu.crm.information.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Information {

    private String infoId;//消息ID
    private String supervisorNum;//督导员编号
    private String infoHeadline;//消息标题
    private String infoContent;//消息内容
    private String infoStatus;//消息状态0：未读取；1：已读取

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getSupervisorNum() {
        return supervisorNum;
    }

    public void setSupervisorNum(String supervisorNum) {
        this.supervisorNum = supervisorNum;
    }

    public String getInfoHeadline() {
        return infoHeadline;
    }

    public void setInfoHeadline(String infoHeadline) {
        this.infoHeadline = infoHeadline;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH-mm-ss")
    private Date infoTime;//督导员编号
}
