package com.xingrongjinfu.crm.crmStore.dao;

import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Repository
public class CrmStoreDaoImpl extends DynamicObjectBaseDao implements CrmStoreDao {
    @Override
    public List<TableDataInfo> selectStoreSup(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> supervisorPageInfo = null;
        try {
            supervisorPageInfo = (List<TableDataInfo>)this.findForList("CrmStoreMapper.pageInfoQuery",pageUtilEntity);
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

    @Override
    public int addPublic(Store store) {
        return this.update("CrmStoreMapper.addPublic",store);
    }

	@Override
	public List<Store> findBySupervisorNum(String supervisorIdOne) {
		List<Store> storeList = null;
		try {
			storeList = (List<Store>) this.findForList("CrmStoreMapper.findBySupervisorNum",supervisorIdOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  storeList;
	}

	@Override
	public int batchUpdateStoreSupervistor(String supervisorIdOne, String supervisorId) {
		HashMap hashMap = new HashMap();
		hashMap.put("supervisorIdOne", supervisorIdOne);
		hashMap.put("supervisorId", supervisorId);
		return this.update("CrmStoreMapper.batchUpdateStoreSupervistor",hashMap);
	}
}
