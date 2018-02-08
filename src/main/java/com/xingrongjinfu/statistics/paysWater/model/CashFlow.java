package com.xingrongjinfu.statistics.paysWater.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xingrongjinfu.commodity.register.model.Register;

public class CashFlow {
    private String cashId;//收银支付流水的id
    private String tradeCode;//交易流水号
    private String userId;//B端商户id
    private String storeId;//商铺Id
    private String machineNo;
    private Integer type;
    private String cashierId;
    private String menberNo;
    private Integer payType;
    private BigDecimal totalMoney;
    private BigDecimal money;
    private Integer number;
    private Integer status;
    private String remark;
    private Date addTime;
    private String addIP;
    private String cashName;
    private String memberName;
    private List<CashDetail> cashDetail;
    private List<Register> register; 
    private BigDecimal xianjin;
    private BigDecimal zhifubao;
    private BigDecimal weixin;
    private BigDecimal yinlian;
    private BigDecimal baitiao;
    private BigDecimal lirun;
    private BigDecimal maolilv;
    
    
    
    public String getMenberNo() {
        return menberNo;
    }
    public void setMenberNo(String menberNo) {
        this.menberNo = menberNo;
    }
    public BigDecimal getLirun() {
        return lirun;
    }
    public void setLirun(BigDecimal lirun) {
        this.lirun = lirun;
    }
    public BigDecimal getMaolilv() {
        return maolilv;
    }
    public void setMaolilv(BigDecimal maolilv) {
        this.maolilv = maolilv;
    }
    public BigDecimal getXianjin() {
        return xianjin;
    }
    public void setXianjin(BigDecimal xianjin) {
        this.xianjin = xianjin;
    }
    public BigDecimal getZhifubao() {
        return zhifubao;
    }
    public void setZhifubao(BigDecimal zhifubao) {
        this.zhifubao = zhifubao;
    }
    public BigDecimal getWeixin() {
        return weixin;
    }
    public void setWeixin(BigDecimal weixin) {
        this.weixin = weixin;
    }
    public BigDecimal getYinlian() {
        return yinlian;
    }
    public void setYinlian(BigDecimal yinlian) {
        this.yinlian = yinlian;
    }
    public BigDecimal getBaitiao() {
        return baitiao;
    }
    public void setBaitiao(BigDecimal baitiao) {
        this.baitiao = baitiao;
    }
    public List<CashDetail> getCashDetail() {
        return cashDetail;
    }
    public void setCashDetail(List<CashDetail> cashDetail) {
        this.cashDetail = cashDetail;
    }
    public void setRegister(List<Register> register) {
        this.register = register;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getCashName() {
        return cashName;
    }
    public void setCashName(String cashName) {
        this.cashName = cashName;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public List<Register> getRegister() {
        return register;
    }
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    public String getCashId() {
        return cashId;
    }
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
    public String getTradeCode() {
        return tradeCode;
    }
    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getMachineNo() {
        return machineNo;
    }
    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }
    public String getCashierId() {
        return cashierId;
    }
    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }
    public Integer getPayType() {
        return payType;
    }
    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
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
    public String getAddIP() {
        return addIP;
    }
    public void setAddIP(String addIP) {
        this.addIP = addIP;
    }
    @Override
    public String toString() {
        return "CashFlow [cashId=" + cashId + ", tradeCode=" + tradeCode + ", userId=" + userId + ", storeId=" + storeId
                + ", machineNo=" + machineNo + ", type=" + type + ", cashierId=" + cashierId + ", menberNo=" + menberNo
                + ", payType=" + payType + ", totalMoney=" + totalMoney + ", money=" + money + ", number=" + number
                + ", status=" + status + ", remark=" + remark + ", addTime=" + addTime + ", addIP=" + addIP
                + ", cashName=" + cashName + ", memberName=" + memberName + ", cashDetail=" + cashDetail + ", register="
                + register + ", xianjin=" + xianjin + ", zhifubao=" + zhifubao + ", weixin=" + weixin + ", yinlian="
                + yinlian + ", baitiao=" + baitiao + ", lirun=" + lirun + ", maolilv=" + maolilv + "]";
    }
    
    
}
