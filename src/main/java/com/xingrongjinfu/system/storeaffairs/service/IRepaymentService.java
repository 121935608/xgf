package com.xingrongjinfu.system.storeaffairs.service;

import java.util.ArrayList;
import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.storeaffairs.model.Supervisor;
import com.xingrongjinfu.system.user.model.UserRole;

/**
 * 角色管理 业务层
 * 
 * @author y
 */
public interface IRepaymentService
{

	/**
     * 根据条件分页查询还款计划表
     * 
     * @param page 分页对象
     * @return  
     */
    public List<TableDataInfo> repaymentListQuery(PageUtilEntity pageUtilEntity); 
      
}
