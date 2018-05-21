/**
 * Copyright (C), 2017
 * FileName: Supervisor
 * Author:   zxuser
 * Date:     2017/12/29 10:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.supervisor.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
public class Supervisor implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer supervisorId;
    private String name;
    private String phone;
    private String area;
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH-mm-ss")
    private Date creatTime;
    private String supervisorNum;
    private String deptId;
    private String crmLogin;
    private String crmPwd;
    private String province;
    private String headPortrait;
    private String cityCode;
    private String provinceCode;
    private String address;

    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    public String getHeadPortrait() {
        return headPortrait;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    private String roleId;/*CRM督导员的角色ID*/

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    private String city;
    private String county;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getCrmLogin() {
        return crmLogin;
    }

    public void setCrmLogin(String crmLogin) {
        this.crmLogin = crmLogin;
    }

    public String getCrmPwd() {
        return crmPwd;
    }

    public void setCrmPwd(String crmPwd) {
        this.crmPwd = crmPwd;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getSupervisorNum() {
        return supervisorNum;
    }

    public void setSupervisorNum(String supervisorNum) {
        this.supervisorNum = supervisorNum;
    }
}