package com.xingrongjinfu.commercial.supplierApply.model;

import java.util.Date;

public class SupplierApply {
    private String applyId;
    private String applyName;
    private String applyPhone;
    private String compName;
    private String compAddr;
    private String detailAddr;
    private String email;
    private Integer status;
    private String remark;
    private Date addTime;
    private Integer num;
    private Date updateTime;
    
    
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getApplyId() {
        return applyId;
    }
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
    public String getApplyName() {
        return applyName;
    }
    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }
    public String getApplyPhone() {
        return applyPhone;
    }
    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }
    public String getCompName() {
        return compName;
    }
    public void setCompName(String compName) {
        this.compName = compName;
    }
    public String getCompAddr() {
        return compAddr;
    }
    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }
    public String getDetailAddr() {
        return detailAddr;
    }
    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
}
