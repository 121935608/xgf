package com.xingrongjinfu.system.storeaffairs.service;
 
import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.system.storeaffairs.model.Repay;
import com.xingrongjinfu.system.storeaffairs.model.RepayDetail; 

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
      
    Repay getByRepayId(String id);
    
    int updateRepay(Repay repay,RepayDetail repayDetail);

    String getOrderNumber(String id);

    List<RepayDetail> getRepayDetail(String id);
}
