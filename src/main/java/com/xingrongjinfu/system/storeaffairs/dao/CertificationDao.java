package com.xingrongjinfu.system.storeaffairs.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository; 
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor; 

/**
 * 申请审核 数据层处理
 * 
 * @author cj
 */
@Repository("certificationDao")
public class CertificationDao extends DynamicObjectBaseDao implements ICertificationDao
{

     

    /**
     * 根据条件分页查询角色对象
     * 
     * @param page 分页对象
     * @return 角色信息集合信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("StoreAffairMapper.CertificationpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }

    /**
     * 查询所有监督员对象
     *  
     * @return 监督员信息集合 
     */
	@SuppressWarnings("unchecked")
    @Override
	public List<Supervisor> getSupervisorList() {
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

 
 
	/**
     * 获取商户信息
     */
	@Override
    public  Store getStoreInfo(String storeId)
    {  
    	Store storeInfo = null;
		try
        {
			storeInfo = (Store) this.findForObject("StoreAffairMapper.getStoreInfo",storeId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return storeInfo;
    }

    /**
     * 根据用户Id获取商户信息
     */
    @Override
    public  Store getStoreInfoByUserId(String userId)
    {
        Store storeInfo = null;
        try
        {
            storeInfo = (Store) this.findForObject("StoreAffairMapper.getStoreInfoByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return storeInfo;
    }

    
    /**
     * 获取银行账户信息
     */
    @Override
    public  BankAccount getBankAccountInfo(String storeid)
    { 
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
    
    /**
     * 更新审核信息到Store表
     */
    @Override
    public  int saveCertificationCheck(Store store)
    {
    	int result = 0;
		try
        {
			result = (int) this.save("StoreAffairMapper.updateCheckToStore", store);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return result; 
    }

    @Override
    public int findAllCount() {
        return (int) this.findForObject("StoreAffairMapper.findAllCount",null);
    }

    /** 查询店铺详细信息*/
    @Override
    public Store getStoreByUserId(String userId) {
        Store storeInfo = null;
        try {
            storeInfo = (Store) this.findForObject("StoreAffairMapper.getStoreByUserId", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storeInfo;
    }
}
