package com.xingrongjinfu.crm.performanceRank.service;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * @author chenmengzhen
 * @version V1.0
 * @Description: 业绩排名   业务层
 */
public interface IPerformanceRankService {
    /**
     * 根据条件分页查询拜访信息
     *
     * @param page 分页对象
     * @return 拜访信息列表
     */
    public List<TableDataInfo> pageInfoQueryBydept(PageUtilEntity pageUtilEntity);
}
