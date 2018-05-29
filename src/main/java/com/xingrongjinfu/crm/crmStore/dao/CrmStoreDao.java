package com.xingrongjinfu.crm.crmStore.dao;

import com.xingrongjinfu.crm.crmStore.model.Order;
import com.xingrongjinfu.crm.visit.model.Visit;
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.HashMap;
import java.util.List;

public interface CrmStoreDao {

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
     * 根据督导员编号查询所有门店信息
     */
	List<Store> findBySupervisorNum(String supervisorIdOne);

	/**
	 * 根据督导员编号批量更新门店信息
	 */
	int batchUpdateStoreSupervistor(String supervisorIdOne, String supervisorId);

    List<Object[]> queryReport(HashMap hashMap);


    //门店详情信息
    HashMap findStoreDetails(String storeId);

    //门店订单列表
    public List<TableDataInfo> findStoreDetailsOrder(PageUtilEntity pageUtilEntity);

    //门店最近订单时间
    Order recentOrder(HashMap param);

    //门店补券列表
    public List<TableDataInfo> findStoreDetailsCoupon(PageUtilEntity pageUtilEntity);

    //门店最近拜访记录
    Visit recentVisitRecord(String storeId);
    //门店所有拜访记录
    public List<TableDataInfo> storeVisitRecord(PageUtilEntity pageUtilEntity);
}
