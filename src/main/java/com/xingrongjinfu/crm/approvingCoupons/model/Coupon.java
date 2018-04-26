package com.xingrongjinfu.crm.approvingCoupons.model;

import java.util.Date;
import java.util.List;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券管理对象
* @date 2018年4月25日
 */
public class Coupon
{
	private String couponId;
    private String storeName;
    private String contacts;
	private String phoneNum;
    private String address;
    private String orderNum;
    private String logisticsSingleNum;
    private String damagedFruit;
    private Integer inspectionScene;
	private String damageDescription;
	private String amount;
	private String remarks;
	private Integer auditStatus;
	private Date auditTime;
	private String auditRemarks;
	private Date createTime;
	
	private List<CouponImg> couponImgList;
	
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getDamagedFruit() {
		return damagedFruit;
	}

	public void setDamagedFruit(String damagedFruit) {
		this.damagedFruit = damagedFruit;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getLogisticsSingleNum() {
		return logisticsSingleNum;
	}

	public void setLogisticsSingleNum(String logisticsSingleNum) {
		this.logisticsSingleNum = logisticsSingleNum;
	}

	public Integer getInspectionScene() {
		return inspectionScene;
	}

	public void setInspectionScene(Integer inspectionScene) {
		this.inspectionScene = inspectionScene;
	}

	public String getDamageDescription() {
		return damageDescription;
	}

	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditRemarks() {
		return auditRemarks;
	}

	public void setAuditRemarks(String auditRemarks) {
		this.auditRemarks = auditRemarks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<CouponImg> getCouponImgList() {
		return couponImgList;
	}

	public void setCouponImgList(List<CouponImg> couponImgList) {
		this.couponImgList = couponImgList;
	}
	
}
