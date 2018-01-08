package com.xingrongjinfu.statistics.pays.dao;

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
@Repository("payDao")
public class PayDao extends DynamicObjectBaseDao implements IPayDao
{

	/**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> paysPageInfo = null;
        try
        {
        	paysPageInfo = (List<TableDataInfo>) this.findForList("StatisticsPaysMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return paysPageInfo;

    }
    

}
