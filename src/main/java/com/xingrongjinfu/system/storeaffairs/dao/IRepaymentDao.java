package com.xingrongjinfu.system.storeaffairs.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo; 

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
}
