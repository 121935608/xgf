package com.xingrongjinfu.crm.visit.model;

import java.util.Date;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 访问记录管理对象
* @date 2018年4月25日
 */
public class Visit
{
	private String visitId;
    private String storeId;
    private String supervisorId;
	private Date visitTime;
    private Integer visitManner;
    private Integer visitGoal;
    private String visitWith;
    private Integer visitResultStatus;
    private String visitResult;
	private Date createTime;
	private Integer status;
	private Date updateTime;
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
    public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public Integer getVisitManner() {
		return visitManner;
	}
	public void setVisitManner(Integer visitManner) {
		this.visitManner = visitManner;
	}
	public Integer getVisitGoal() {
		return visitGoal;
	}
	public void setVisitGoal(Integer visitGoal) {
		this.visitGoal = visitGoal;
	}
	public String getVisitWith() {
		return visitWith;
	}
	public void setVisitWith(String visitWith) {
		this.visitWith = visitWith;
	}
	public Integer getVisitResultStatus() {
		return visitResultStatus;
	}
	public void setVisitResultStatus(Integer visitResultStatus) {
		this.visitResultStatus = visitResultStatus;
	}
	public String getVisitResult() {
		return visitResult;
	}
	public void setVisitResult(String visitResult) {
		this.visitResult = visitResult;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
