package com.xingrongjinfu.crm.crmStore.dao;

import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CrmStoreDaoImpl extends DynamicObjectBaseDao implements CrmStoreDao {
    @Override
    public List<TableDataInfo> selectStoreSup(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> supervisorPageInfo = null;
        try {
            supervisorPageInfo = (List<TableDataInfo>)this.findForList("StoreAffairMapper.selectStoreSup",pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supervisorPageInfo;
    }

    @Override
    public BankAccount getCRMBankAccountInfo(String storeid) {
        BankAccount bankAccountInfo = null;
        try
        {
            bankAccountInfo = (BankAccount) this.findForObject("StoreAffairMapper.getBankAccountInfo", storeid);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bankAccountInfo;
    }

    @Override
    public Store getCRMStoreInfo(String storeid) {
        Store storeInfo = null;
        try
        {
            storeInfo = (Store) this.findForObject("StoreAffairMapper.getStoreInfo",storeid);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return storeInfo;
    }

    @Override
    public List<Supervisor> getCRMSupervisorList() {
        List<Supervisor> supervisorList = null;
        try
        {
            supervisorList = (List<Supervisor>) this.findForList("StoreAffairMapper.getSupervisorList", null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return supervisorList;
    }

    /*门店分配业务员*/
    @Override
    public int updateStoreSupervistor(Store store) {
        return this.update("StoreAffairMapper.updateCheckToStore",store);
    }
}
