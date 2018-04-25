package com.xingrongjinfu.system.storeaffairs.model;

/**
 * 角色管理对象  t_store
 *
 * @author cj
 */
public class PurchaseDto {
    private String storeName;
    private String name;
    private String orderPrice;
    private String orderNum;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
