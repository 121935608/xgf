package com.xingrongjinfu.crm.crmUser.dao;

import com.xingrongjinfu.crm.department.model.Dept;

import java.util.List;

public interface CrmUserDao {

    /*查询所有部门*/
    List<Dept> selectAllDept();
}
