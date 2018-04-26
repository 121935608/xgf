package com.xingrongjinfu.crm.crmUser.dao;

import com.xingrongjinfu.crm.department.model.Dept;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrmUserDaoImpl extends DynamicObjectBaseDao implements CrmUserDao {
    @Override
    public List<Dept> selectAllDept() {
        List<Dept> listDept = null;
        try {
            listDept = (List<Dept>)this.findForList("SupervisorMapper.selectAllCRMDept",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDept;
    }
}
