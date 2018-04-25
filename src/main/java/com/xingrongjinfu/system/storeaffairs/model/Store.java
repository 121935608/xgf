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
public class Store { 
	
	    private static final long serialVersionUID = 1L;
	    private String storeId;
	    private String userId;// '用户Id',
	    private String supervisorId;//'督导员ID',
	    private String storeNo;//'商铺编号',
	    private String storeName;//'商铺名字',
	    private String userName;//'商铺店主姓名',
	    private String phone;//'联系方式',
	    private String idCard;//'商铺店主身份证号码',
	    private String area;//'商铺所在区域',
	    private String address;//'商铺具体地址',
	    private String frontPic;//'商铺店主身份证正面照片',
	    private String backPic;//'商铺店主身份证反面照片',
	    private String licensePic;//'商铺营业执照',
	    private String frontStorePic;//'店铺门脸照',
	    private String innerStorePic;//'店铺店内照',
	    private String contractPic;//'租赁合同',
	    private String transactionPic;//'经营流水',
	    private String utilitiesPic;//'水电费',
	    private String contract;//'合同信息',
	    private String process;//'未认证:WRZ 审核中:APRING 审核不通过:APRNO 审核通过:APRYES',
	    private Integer status;//'状态：1 正常 -1 已删除',
	    private String remark;//'审核说明',
	    private Date addTime;//'添加时间',
	    private Date updateTime;//'最近修改时间',
		// '用户账号',
	    private String accountName;
		//督导员的名字
		private String name;
		//默认银行卡号
		private String cardNumber;

		private Integer orderPrice;

		private Integer orderNum;

		private Date orderTime;

		public String getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}

		public String getStoreId() {
			return storeId;
		}

		public void setStoreId(String storeId) {
			this.storeId = storeId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getSupervisorId() {
			return supervisorId;
		}

		public void setSupervisorId(String supervisorId) {
			this.supervisorId = supervisorId;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getIdCard() {
			return idCard;
		}

		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getFrontPic() {
			return frontPic;
		}

		public void setFrontPic(String frontPic) {
			this.frontPic = frontPic;
		}

		public String getBackPic() {
			return backPic;
		}

		public void setBackPic(String backPic) {
			this.backPic = backPic;
		}

		public String getLicensePic() {
			return licensePic;
		}

		public void setLicensePic(String licensePic) {
			this.licensePic = licensePic;
		}

		public String getFrontStorePic() {
			return frontStorePic;
		}

		public void setFrontStorePic(String frontStorePic) {
			this.frontStorePic = frontStorePic;
		}

		public String getInnerStorePic() {
			return innerStorePic;
		}

		public void setInnerStorePic(String innerStorePic) {
			this.innerStorePic = innerStorePic;
		}

		public String getContractPic() {
			return contractPic;
		}

		public void setContractPic(String contractPic) {
			this.contractPic = contractPic;
		}

		public String getTransactionPic() {
			return transactionPic;
		}

		public void setTransactionPic(String transactionPic) {
			this.transactionPic = transactionPic;
		}

		 
		public String getUtilitiesPic() {
			return utilitiesPic;
		}

		public void setUtilitiesPic(String utilitiesPic) {
			this.utilitiesPic = utilitiesPic;
		}

		public String getContract() {
			return contract;
		}

		public void setContract(String contract) {
			this.contract = contract;
		}

		public String getProcess() {
			return process;
		}

		public void setProcess(String process) {
			this.process = process;
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

		public Date getAddTime() {
			return addTime;
		}

		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		 
		public String getStoreNo() {
			return storeNo;
		}

		public void setStoreNo(String storeNo) {
			this.storeNo = storeNo;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getOrderPrice() {
			return orderPrice;
		}

		public void setOrderPrice(Integer orderPrice) {
			this.orderPrice = orderPrice;
		}

		public Integer getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(Integer orderNum) {
			this.orderNum = orderNum;
		}

		public Date getOrderTime() {
			return orderTime;
		}

		public void setOrderTime(Date orderTime) {
			this.orderTime = orderTime;
		}

		public String toString()
	    {
	        return "store [storeId=" + storeId +
	        		", userId=" + userId + 
	        		", supervisorId=" + supervisorId + 
	        		", storeName=" + storeName + 
	        		", userName=" + userName + 
	        		", phone=" + phone +
	        		", idCard=" + idCard +
	        		", area=" + area +
	        		", address=" + address +
	        		", frontPic=" + frontPic +
	        		", backPic=" + backPic +
	        		", licensePic=" + licensePic +
	        		", frontStorePic=" + frontStorePic +
	        		", innerStorePic=" + innerStorePic +
	        		", contractPic=" + contractPic +
	        		", transactionPic=" + transactionPic +
	        		", utilitiesPic=" + utilitiesPic +
	        		", contract=" + contract +
	        		", process=" + process +
	        		", status=" + status +
	        		", remark=" + remark +
	        		", addTime=" + addTime +
	        		", updateTime=" + updateTime +
	        		"]";
	    }
 
}
