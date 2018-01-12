package com.xingrongjinfu.system.storeaffairs.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo; 
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor; 

/**
 * 角色管理 数据层
 * 
 * @author cj
 */
public interface ICertificationDao
{
	 /**
     * 根据条件分页查询角色对象
     * 
     * @param page 分页对象
     * @return 角色信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 查询所有监督员对象
     *  
     * @return 监督员信息集合 
     */
    public List<Supervisor> getSupervisorList(); 
    
     
    /**
     * 获取商户信息
     */
    public  Store getStoreInfo(String storeid);
    
    /**
     * 获取银行账户信息
     */
    public  BankAccount getBankAccountInfo(String storeid);
    
    /**
     * 更新审核信息到Store表
     */
    public  int saveCertificationCheck(Store store);

    /**
     * 查询所有的认证申请数量
     */
    int findAllCount();
}
