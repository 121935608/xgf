package com.xingrongjinfu.system.storeaffairs.service;
 
import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.xingrongjinfu.system.storeaffairs.dao.ICertificationDao;
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor; 

/**
 * 角色管理 业务层处理
 * 
 * @author y
 */
@Service("certificationService")
public class CertificationService implements ICertificationService
{

    @Autowired
    private ICertificationDao certificationDao;

     
    /**
     * 根据条件分页查询角色对象
     * 
     * @param page 分页对象
     * @return 角色信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return certificationDao.pageInfoQuery(pageUtilEntity);
    }

 
	public List<Supervisor> getSupervisorList() { 
		return certificationDao.getSupervisorList(); 
	}

  
	/**
     * 获取商户信息
     */
    public  Store getStoreInfo(String storeid) 
    {
    	return certificationDao.getStoreInfo(storeid);   
    }


    /**
     *  根据用户Id获取商户信息
     */
    public  Store getStoreInfoByUserId(String userId)
    {
        return certificationDao.getStoreInfoByUserId(userId);
    }

    
    /**
     * 获取银行账户信息
     */
    public  BankAccount getBankAccountInfo(String storeid)
    {
    	return certificationDao.getBankAccountInfo(storeid);   
    }

    /**
     * 更新审核信息到Store表
     */
    public  int  saveCertificationCheck(Store store)
    {
    	return certificationDao.saveCertificationCheck(store);   
    }

    @Override
    public int findAllCount() {
        return certificationDao.findAllCount();
    }
}
