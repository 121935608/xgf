/**
 * Copyright (C), 2018
 * FileName: Classes
 * Author:   zxuser
 * Date:     2018/1/6 11:50
 * Description: 分类信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.product.model;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈分类信息〉
 *
 * @author zxuser
 * @create 2018/1/6
 * @since 1.0.0
 */
public class Classes {

    private String classId;
    private String className;
    private Integer status;
    private Date addTime;
    private Integer type;
    private Integer sort;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}