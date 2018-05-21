package com.xingrongjinfu.crm.crmStore.dao;

import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    @Override
    public List<Object[]> queryReport() {
        List<Object[]> dataList = new ArrayList<Object[]>();
        List<HashMap> supervisorPageInfo = null;
        try {
            supervisorPageInfo = (List<HashMap>)this.findForList("CrmStoreMapper.excelTable",null);
            Object[] objs = null;
            for (int i = 0; i < supervisorPageInfo.size(); i++) {// 循环每一条数据
                HashMap detail = supervisorPageInfo.get(i);
                objs = new Object[13];
                objs[0] = (i + 1);// 序号
                objs[1] = detail.get("storeName") == null ? "" : detail.get("storeName");// 门店名称
                objs[2] = detail.get("userName") == null ? "" : detail.get("userName");//联系人
                objs[3] = detail.get("phone") == null ? "" : detail.get("phone");// 手机号
                objs[4] = detail.get("area") == null ? "" : detail.get("area");// 地址
                objs[5] = detail.get("addTime") == null ? "" : detail.get("addTime");// 申请时间
                objs[6] = detail.get("name") == null ? "" : detail.get("name");//督导员
                objs[7] = detail.get("deptName") == null ? "" : detail.get("deptName");//组类
                objs[8] = detail.get("totalPrice") == null ? "" : detail.get("totalPrice");//下单总金额
                objs[9] = detail.get("monthPrice") == null ? "" : detail.get("monthPrice");//近30天下单总金额
                objs[10] = detail.get("monthNum") == null ? "" : detail.get("monthNum");//近30天下单次数(次)
                objs[11] = detail.get("totalVisitNum") == null ? "" : detail.get("totalVisitNum");//本周拜访次数(次)
                objs[12] = detail.get("storeName") == null ? "" : detail.get("storeName");//状态
                // 数据添加到excel表格
                dataList.add(objs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
