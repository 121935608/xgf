package com.xingrongjinfu.crm.visit.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

/**
 * 角色管理 业务层
 * 
 * @author y
 */
public interface IVisitService
{
    /**
     * 根据条件分页查询拜访信息
     * 
     * @param page 分页对象
     * @return 拜访信息列表
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

}
