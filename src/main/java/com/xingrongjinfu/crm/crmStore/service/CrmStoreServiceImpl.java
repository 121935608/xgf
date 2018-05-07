package com.xingrongjinfu.crm.crmStore.service;

import com.xingrongjinfu.crm.crmStore.dao.CrmStoreDao;
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmStoreServiceImpl implements CrmStoreService {
    @Autowired
    private CrmStoreDao crmStoreDao;
    @Override
    public List<TableDataInfo> selectStoreSup(PageUtilEntity pageUtilEntity) {
        return crmStoreDao.selectStoreSup(pageUtilEntity);
    }

    @Override
    public BankAccount getCRMBankAccountInfo(String storeid) {
        return crmStoreDao.getCRMBankAccountInfo(storeid);
    }

    @Override
    public Store getCRMStoreInfo(String storeid) {
        return crmStoreDao.getCRMStoreInfo(storeid);
    }

    @Override
    public List<Supervisor> getCRMSupervisorList() {
        return crmStoreDao.getCRMSupervisorList();
    }

    @Override
    public int updateStoreSupervistor(Store store) {
        return crmStoreDao.updateStoreSupervistor(store);
    }

    @Override
    public int addPublic(Store store) {
        return crmStoreDao.addPublic(store);
    }
}
