/**
 * Copyright (C), 2017
 * FileName: AccountInfo
 * Author:   zxuser
 * Date:     2017/12/27 14:00
 * Description: 商户的店铺银行信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.merchant.model;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商户的店铺银行信息〉
 *
 * @author zxuser
 * @create 2017/12/27
 * @since 1.0.0
 */
public class AccountInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String storeName;
    private String userName;
    private String idCard;
    private String phone;
    private String area;
    private String address;
    private String frontPic;
    private String backPic;
    private String licensePic;
    private String frontStorePic;
    private String innerStorePic;
    private String bankType;
    private String accountType;
    private String bankUserName;
    private String oprateType;
    private String cardNumber;
    private String idType;
    private String cIdCard;
    private String remark;
    private String licenseId;



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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getcIdCard() {
        return cIdCard;
    }

    public void setcIdCard(String cIdCard) {
        this.cIdCard = cIdCard;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }
}