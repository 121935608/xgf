package com.xingrongjinfu.crm.crmUser.service;

import com.xingrongjinfu.crm.department.model.Dept;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.supervisor.model.Supervisor;

import java.util.List;

public interface CrmUserService {

    /*查询所有部门*/
    List<Dept> selectAllDept();

    /*查询所有角色*/
    List<Role> selectRole();

    /*根据用户名查询数据*/
    List<Supervisor> checkCrmLogin(String crmLogin);
}
