package com.xingrongjinfu.commodity.toStock.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.statistics.purchase.model.Purchase;

public interface IToStockDao {
    /**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    Purchase getCommodityById(Map map);
    
    Register loadCommodity(Map map);
    
    List<String> getAllCommodity(String storeId);
    
    int getCommodityByName(Map map);
    
    int updateCommodity(Map map);
    
    int updateCommodityStatus(Map map);
}
