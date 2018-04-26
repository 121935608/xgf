package com.xingrongjinfu.crm.crmUser.service;

import com.xingrongjinfu.crm.crmUser.dao.CrmUserDao;
import com.xingrongjinfu.crm.department.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("crmUserServiceImpl")
public class CrmUserServiceImpl implements CrmUserService{

    @Autowired
    private CrmUserDao crmUserDao;

    @Override
    public List<Dept> selectAllDept() {
        return crmUserDao.selectAllDept();
    }
}
