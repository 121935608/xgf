package com.xingrongjinfu.crm.department.model;

import java.util.Date;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 部门管理对象  
* @date 2018年4月25日
 */
public class Dept
{
	private String deptId;
    private String deptName;
    private String describe;
    private Date createTime;
	private Date updateTime;
    
    public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
