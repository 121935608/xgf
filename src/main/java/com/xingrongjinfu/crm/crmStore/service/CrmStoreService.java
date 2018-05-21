package com.xingrongjinfu.crm.crmStore.service;

import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

public interface CrmStoreService {

    /*查询门店和督导员的所有信息*/
    List<TableDataInfo> selectStoreSup(PageUtilEntity pageUtilEntity);

    /**
     * 获取银行账户信息
     */
    public BankAccount getCRMBankAccountInfo(String storeid);

    /**
     * 获取商户信息
     */
    public Store getCRMStoreInfo(String storeid);

    /**
     * 查询所有监督员对象
     *
     * @return 监督员信息集合
     */
    public List<Supervisor> getCRMSupervisorList();

    /*门店分配业务员*/
    int updateStoreSupervistor(Store store);

    /*门店分配业务员*/
    int addPublic(Store store);

    /**
     * 根据督导员编号查询该督导员名下的所有门店信息
     */
	List<Store> findBySupervisorNum(String supervisorIdOne);

	/**
	 * 根据督导员编号批量更新
	 */
	int batchUpdateStoreSupervistor(String supervisorIdOne, String supervisorId);
}
