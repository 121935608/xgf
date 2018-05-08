package com.xingrongjinfu.crm.performanceRank.dao;


import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author hyq
 * @version V1.0
 * @Description: 业绩排名 数据层处理
 */
@Repository("performanceRankDao")
public class PerformanceRankDao extends DynamicObjectBaseDao implements IPerformanceRankDao {
    @Override
    public List<TableDataInfo> pageInfoQueryBydept(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> rankPageInfo = null;
        try
        {
            rankPageInfo = (List<TableDataInfo>) this.findForList("PerformanceRankMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rankPageInfo;
    }
}
