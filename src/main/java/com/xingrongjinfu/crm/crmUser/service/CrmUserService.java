package com.xingrongjinfu.crm.crmUser.service;

import com.xingrongjinfu.crm.department.model.Dept;

import java.util.List;

public interface CrmUserService {

    /*查询所有部门*/
    List<Dept> selectAllDept();
}
