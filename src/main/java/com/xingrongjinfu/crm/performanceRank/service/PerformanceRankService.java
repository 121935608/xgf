package com.xingrongjinfu.crm.performanceRank.service;


import com.xingrongjinfu.crm.performanceRank.dao.IPerformanceRankDao;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author hyq
 * @version V1.0
 * @Description: 业绩排名  业务层处理
 */
@Service("performanceRankService")
public class PerformanceRankService implements IPerformanceRankService {

    @Autowired
    private IPerformanceRankDao performanceRankDao;

    @Override
    public List<TableDataInfo> pageInfoQueryBydept(PageUtilEntity pageUtilEntity) {
        return performanceRankDao.pageInfoQueryBydept(pageUtilEntity);
    }

    @Override
    public HashMap getPerformanceDetails(HashMap param) {
        return performanceRankDao.getPerformanceDetails(param);
    }
}
