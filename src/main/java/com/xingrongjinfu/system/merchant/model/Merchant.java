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

import java.io.Serializable;

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