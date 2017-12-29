package com.xingrongjinfu.system.storeaffairs.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.role.model.RolePermission;
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.storeaffairs.model.Supervisor;
import com.xingrongjinfu.system.user.model.UserRole;

/**
 * 申请审核 数据层处理
 * 
 * @author cj
 */
@Repository("repaymentDao")
public class RepaymentDao extends DynamicObjectBaseDao implements IRepaymentDao
{

     

	/**
     * 根据条件分页查询还款计划表
     * 
     * @param page 分页对象
     * @return  
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> repaymentListQuery(PageUtilEntity pageUtilEntity)
    {
        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("StoreAffairMapper.repaymentpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }
 
	 
}
