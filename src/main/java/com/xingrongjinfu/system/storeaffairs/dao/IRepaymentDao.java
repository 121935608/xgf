package com.xingrongjinfu.system.storeaffairs.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.system.storeaffairs.model.Repay;
import com.xingrongjinfu.system.storeaffairs.model.RepayDetail; 

/**
 * 角色管理 数据层
 * 
 * @author cj
 */
public interface IRepaymentDao
{
	/**
     * 根据条件分页查询还款计划表
     * 
     * @param page 分页对象
     * @return  
     */
    public List<TableDataInfo> repaymentListQuery(PageUtilEntity pageUtilEntity); 
    
    Repay getByRepayId(String id);
    
    int updateRepay(Repay repay);
    
    int addRepayDetail(RepayDetail repayDetail);

    String getOrderNumber(String id);

    List<RepayDetail> getRepayDetail(String id);

    List<Map> infoQuery(Map<String, String> param);
}
