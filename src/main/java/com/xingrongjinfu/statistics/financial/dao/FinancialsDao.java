package com.xingrongjinfu.statistics.financial.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

/**
 *  数据层处理
 * 
 * @author 
 */
@Repository("financialsDao")
public class FinancialsDao extends DynamicObjectBaseDao implements IFinancialsDao
{

	/**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> financialPageInfo = null;
        try
        {
        	financialPageInfo = (List<TableDataInfo>) this.findForList("StatisticsFinancialMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return financialPageInfo;

    }
    

}
