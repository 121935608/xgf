package com.xingrongjinfu.commodity.toStock.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.model.Message;

import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.statistics.purchase.model.Purchase;

public interface IToStockService {
    /**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    Purchase getCommodityById(Map map);
    
    Register loadCommodity(Map map);
    
    List<String> getAllCommodity(String storeId);
    
    int getCommodityByName(Map map);
    
    Message updateCommodity(Map map,String orders);
    
}
