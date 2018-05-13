package com.xingrongjinfu.crm.performanceRank.dao;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @author hyq
 * @version V1.0
 * @Description: 业绩排名  数据层
 */
public interface IPerformanceRankDao {
    /**
     * 根据条件分页查询业绩排名
     *
     * @param page 分页对象
     * @return 业绩排名列表
     */
    public List<TableDataInfo> pageInfoQueryBydept(PageUtilEntity pageUtilEntity);

    /**
     * 根据supervisorId查询业绩排名详细信息
     *
     * @param: @param supervisorId
     */
    public HashMap getPerformanceDetails(HashMap param);
    /**
     * 获取所有部门id
     */
    public List getDept() throws Exception;
}
