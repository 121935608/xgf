package com.xingrongjinfu.statistics.paysWater.model;

import java.util.Date;

public class CashFlowDto {


     /**  流水号  */
     private String tradeCode;
     /**  收银机  */
     private String machineNo;
     /**  日期  */
     private Date addTime;
     /**  类型  */
     private String type;
     /**  收银员  */
     private String cashName;
     /**  会员  */
     private String memberName;
     /**  商品数量  */
     private Integer number;
     /**  商品价格  */
     private String totalMoney;
     /**  折后价格  */
     private Double money;

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getType() {
        return type;
    }

    public void setType(int type) {
        if(type == 1 ){
            this.type = "销售";
        }else if(type == 2){
            this.type =  "退货";
        }else{
            this.type = "";
        }

    }

    public String getCashName() {
        return cashName;
    }

    public void setCashName(String cashName) {
        this.cashName = cashName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
