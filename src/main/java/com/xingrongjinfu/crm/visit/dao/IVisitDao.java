package com.xingrongjinfu.crm.visit.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

/**
 * 部门管理 数据层
 * 
 * @author y
 */
public interface IVisitDao
{
	/**
     * 根据条件分页查询拜访信息
     * 
     * @param page 分页对象
     * @return 拜访信息列表
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
}
