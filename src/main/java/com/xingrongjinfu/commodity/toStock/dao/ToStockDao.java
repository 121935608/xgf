package com.xingrongjinfu.commodity.toStock.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.statistics.purchase.model.Purchase;

@Repository
public class ToStockDao extends DynamicObjectBaseDao implements IToStockDao {
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
            purchasePageInfo = (List<TableDataInfo>) this.findForList("StatisticsPurchaseMapper.toStockpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return purchasePageInfo;

    }

    @Override
    public Purchase getCommodityById(Map map) {
        return (Purchase) this.findForObject("StatisticsPurchaseMapper.getCommodityById", map);
    }

    @Override
    public Register loadCommodity(Map map) {
        return (Register) this.findForObject("StatisticsPurchaseMapper.loadCommodity", map);
    }

    @Override
    public List<String> getAllCommodity(String storeId) {
        List<String> list = null;
        try {
            list =  (List<String>) this.findForList("StatisticsPurchaseMapper.getAllCommodity", storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getCommodityByName(Map map) {
        return (int) this.findForObject("StatisticsPurchaseMapper.getCommodityByName", map);
    }

    @Override
    public int updateCommodity(Map map) {
        return this.update("StatisticsPurchaseMapper.updateCommodity", map);
    }

    @Override
    public int updateCommodityStatus(Map map) {
        return this.update("StatisticsPurchaseMapper.updateCommodityStatus", map);
    }
}
