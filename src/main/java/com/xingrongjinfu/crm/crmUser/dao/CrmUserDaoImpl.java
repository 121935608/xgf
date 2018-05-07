package com.xingrongjinfu.crm.crmUser.dao;

import com.xingrongjinfu.crm.department.model.Dept;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrmUserDaoImpl extends DynamicObjectBaseDao implements CrmUserDao {
    @Override
    public List<Dept> selectAllDept() {
        List<Dept> listDept = null;
        try {
            listDept = (List<Dept>)this.findForList("DeptMapper.selectAllCRMDept",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDept;
    }

    /*查询所有角色*/
    @Override
    public List<Role> selectRole() {
        List<Role> list = null;
        try {
            list = (List<Role>)this.findForList("SystemRoleMapper.findRoleByRole",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Supervisor> checkCrmLogin(String crmLogin) {
        List<Supervisor> list = null;
        try {
            list = (List<Supervisor>) this.findForList("UsersMapper.checkCrmLogin",crmLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
