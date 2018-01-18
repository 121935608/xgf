/**
 * Copyright (C), 2018
 * FileName: Setting
 * Author:   zxuser
 * Date:     2018/1/2 18:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.setting.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
public class Setting implements Serializable
{

    private Integer systemId;
    private String xzfMethod;
    private Double xzfRate;
    private Double orderRate;
    private Date settingTime;
    private Integer status;
    private String creator;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getXzfMethod() {
        return xzfMethod;
    }

    public void setXzfMethod(String xzfMethod) {
        this.xzfMethod = xzfMethod;
    }

    public Double getXzfRate() {
        return xzfRate;
    }

    public void setXzfRate(Double xzfRate) {
        this.xzfRate = xzfRate;
    }

    public Double getOrderRate() {
        return orderRate;
    }

    public void setOrderRate(Double orderRate) {
        this.orderRate = orderRate;
    }

    public Date getSettingTime() {
        return settingTime;
    }

    public void setSettingTime(Date settingTime) {
        this.settingTime = settingTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}