package com.xingrongjinfu.statistics.procurement.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

/**
 *  数据层处理
 * 
 * @author 
 */
@Repository("procurementDao")
public class ProcurementDao extends DynamicObjectBaseDao implements IProcurementDao
{

    @Override
    public List<Map> infoQuery(Map<String, String> param) {
        List<Map> procurementPageInfo = null;
        try
        {
            procurementPageInfo = (List<Map>) this.findForList("StatisticsProcurementMapper.infoQuery", param);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return procurementPageInfo;
    }

    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> procurementPageInfo = null;
        try
        {
        	procurementPageInfo = (List<TableDataInfo>) this.findForList("StatisticsProcurementMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return procurementPageInfo;

    }
    

}
