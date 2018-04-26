package com.xingrongjinfu.statistics.purchase.dao;

import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.purchase.model.Purchase;
import com.xingrongjinfu.statistics.purchase.model.SPurchaseDto;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

/**
 *  数据层处理
 * 
 * @author 
 */
@Repository("purchaseDao")
public class PurchaseDao extends DynamicObjectBaseDao implements IPurchaseDao
{

    @Override
    public List<SPurchaseDto> infoQuery(Map<String, String> param) {
        List<SPurchaseDto> purchasePageInfo = null;
        try
        {
            purchasePageInfo = (List<SPurchaseDto>) this.findForList("StatisticsPurchaseMapper.infoQuery", param);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return purchasePageInfo;
    }

    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> purchasePageInfo = null;
        try
        {
        	purchasePageInfo = (List<TableDataInfo>) this.findForList("StatisticsPurchaseMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return purchasePageInfo;

    }

}
