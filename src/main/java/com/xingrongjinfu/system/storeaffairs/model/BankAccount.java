package com.xingrongjinfu.system.storeaffairs.model;
 
import java.util.Date; 
 


	/**
	 * 银行账户对象  t_bankcard
	 * 
	 * @author cj
	 */
public class BankAccount {
	    private static final long serialVersionUID = 1L;
	      
	    private String cardId;
	    private String userId;// '用户ID',
	    private String bankType;//'银行类型：国有商业银行，城市商业银行，农村商业银行，农村合作银行等',
	    private String bankName;//'银行名称',
	    private String accountType;//'账户类型：个人，企业',
	    private String userName;//'开户人姓名',
	    private String oprateType;//'经营类型：食品、生鲜等',
	    private String cardNumber;//'银行卡号',
	    private String cardPhone;// '银行卡预留手机号',
	    private String idType;//'证件类型',
	    private String idCard;// '证件号',
	    private String address;//'开户行地址',
	    private String remark;// '备注',
	    private String status;//'状态：0默认银行卡 1不是默认银行卡 -1已删除',
	    private String addTime;//'添加时间',
	    private String updateTime;//'最近修改时间',
   
	    
	    
	    
	    
		public String getCardId() {
			return cardId;
		}





		public void setCardId(String cardId) {
			this.cardId = cardId;
		}





		public String getUserId() {
			return userId;
		}





		public void setUserId(String userId) {
			this.userId = userId;
		}





		public String getBankType() {
			return bankType;
		}





		public void setBankType(String bankType) {
			this.bankType = bankType;
		}





		public String getBankName() {
			return bankName;
		}





		public void setBankName(String bankName) {
			this.bankName = bankName;
		}





		public String getAccountType() {
			return accountType;
		}





		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}





		public String getUserName() {
			return userName;
		}





		public void setUserName(String userName) {
			this.userName = userName;
		}





		public String getOprateType() {
			return oprateType;
		}





		public void setOprateType(String oprateType) {
			this.oprateType = oprateType;
		}





		public String getCardNumber() {
			return cardNumber;
		}





		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}





		public String getCardPhone() {
			return cardPhone;
		}





		public void setCardPhone(String cardPhone) {
			this.cardPhone = cardPhone;
		}





		public String getIdType() {
			return idType;
		}





		public void setIdType(String idType) {
			this.idType = idType;
		}





		public String getIdCard() {
			return idCard;
		}





		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}





		public String getAddress() {
			return address;
		}





		public void setAddress(String address) {
			this.address = address;
		}





		public String getRemark() {
			return remark;
		}





		public void setRemark(String remark) {
			this.remark = remark;
		}





		public String getStatus() {
			return status;
		}





		public void setStatus(String status) {
			this.status = status;
		}





		public String getAddTime() {
			return addTime;
		}





		public void setAddTime(String addTime) {
			this.addTime = addTime;
		}





		public String getUpdateTime() {
			return updateTime;
		}





		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}





		public String toString()
	    {
	        return  "BankAccount [cardId=" + cardId + 
	    			", userId=" + userId + 
	    			", bankType=" + bankType + 
	    			", bankName=" + bankName+ 
	    			", accountType=" + accountType + 
	    			", userName=" + userName + 
	    			", oprateType=" + oprateType + 
	    			", cardNumber=" + cardNumber + 
	    			", cardPhone=" + cardPhone + 
	    			", idType=" + idType + 
	    			", idCard=" + idCard + 
	    			", address=" + address + 
	    			", remark=" + remark + 
	    			", status=" + status +  
	    			", addTime=" + addTime + 
	    			", updateTime=" + updateTime + 
	    			"]";
	        		
	    }

	} 
