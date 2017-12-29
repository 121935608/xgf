package com.xingrongjinfu.system.storeaffairs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.xingrongjinfu.system.permission.model.Permission;


	/**
	 * 角色管理对象  t_store
	 * 
	 * @author cj
	 */
public class Supervisor {
	    private static final long serialVersionUID = 1L;
	    
	    private String supervisorId;//'督导员id',
	    private String name;//'督导员姓名',
	    private String phone;//'督导员电话',
	    private String area;//,区域
	    private String status;//'督导员的状态:0是启用1是禁用',
	    private Date creatTime;//'添加时间',

		 
	    
		public String getSupervisorId() {
			return supervisorId;
		}



		public void setSupervisorId(String supervisorId) {
			this.supervisorId = supervisorId;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getPhone() {
			return phone;
		}



		public void setPhone(String phone) {
			this.phone = phone;
		}



		public String getArea() {
			return area;
		}



		public void setArea(String area) {
			this.area = area;
		}



		public String getStatus() {
			return status;
		}



		public void setStatus(String status) {
			this.status = status;
		}



		public Date getCreatTime() {
			return creatTime;
		}



		public void setCreatTime(Date creatTime) {
			this.creatTime = creatTime;
		}



		public String toString()
	    {
	        return "store [supervisorId=" + supervisorId +
	        		", name=" + name + 
	        		", phone=" + phone + 
	        		", area=" + area + 
	        		", status=" + status + 
	        		", creatTime=" + creatTime + 
	        		"]";
	    }
 
}
