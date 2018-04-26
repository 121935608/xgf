/**
 * Copyright (C), 2017
 * FileName: Merchant
 * Author:   zxuser
 * Date:     2017/12/26 18:21
 * Description: 商品信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.merchant.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品信息〉
 *
 * @author zhaoyunfei
 * @create 2017/12/26
 * @since 1.0.0
 */
public class Merchant implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String userId;
    private String mobilePhone;
    private String storeName;
    private String userName;
    private String phone;
    private String locked;
    private String storeId;
    private String licenseId;
    private String supervisorName;
    private String addTime;
    private String storeArea;//门店面积
    private String chain;//是否连锁
    private String equipment;//门店设备
    private String storeType;//门店类型
    private String partners;//合作方
    private String startHours;//开始营业时间
    private String endHours;//结束营业时间
    private String startCollect;//开始收货时间
    private String endCollect;//结束收货时间
    private String building;//周边建筑


    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getPartners() {
        return partners;
    }

    public void setPartners(String partners) {
        this.partners = partners;
    }

    public String getStartHours() {
        return startHours;
    }

    public void setStartHours(String startHours) {
        this.startHours = startHours;
    }

    public String getEndHours() {
        return endHours;
    }

    public void setEndHours(String endHours) {
        this.endHours = endHours;
    }

    public String getStartCollect() {
        return startCollect;
    }

    public void setStartCollect(String startCollect) {
        this.startCollect = startCollect;
    }

    public String getEndCollect() {
        return endCollect;
    }

    public void setEndCollect(String endCollect) {
        this.endCollect = endCollect;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }
}