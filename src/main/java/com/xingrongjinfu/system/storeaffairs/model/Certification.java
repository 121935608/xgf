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
public class Certification {
	    private static final long serialVersionUID = 1L;
	    private String storeId;
	    private String storeNo;
	    private Date addTime; 
	    private String accountName; 
	    private String supervisorId; 
	    private String process; 
	    private String remark;  
	      
	   


		public String getStoreId() {
			return storeId;
		}




		public void setStoreId(String storeId) {
			this.storeId = storeId;
		}




		public String getStoreNo() {
			return storeNo;
		}




		public void setStoreNo(String storeNo) {
			this.storeNo = storeNo;
		}




		public Date getAddTime() {
			return addTime;
		}




		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}




		public String getAccountName() {
			return accountName;
		}




		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}




		public String getSupervisorId() {
			return supervisorId;
		}




		public void setSupervisorId(String supervisorId) {
			this.supervisorId = supervisorId;
		}




		public String getProcess() {
			return process;
		}




		public void setProcess(String process) {
			this.process = process;
		}




		public String getRemark() {
			return remark;
		}




		public void setRemark(String remark) {
			this.remark = remark;
		}




		public String toString()
	    {
	        return "Role [storeNo=" + storeNo + 
	        		", addTime=" + addTime + 
	        		", accountName=" + accountName + 
	        		", supervisorId=" + supervisorId + 
	        		", process=" + process + 
	        		", remark=" + remark +  
	        		"]";
	    }
 
}
