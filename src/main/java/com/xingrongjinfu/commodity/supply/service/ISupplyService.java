package com.xingrongjinfu.commodity.supply.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.supply.model.Supply;

public interface ISupplyService {
    List<Supply> getAll(String storeId) throws Exception;
    
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    int changeSupplyStatus(Supply supply);
    
    Supply getByCode(String supplyCode);
    
    int updateSupply(Supply supply);
    
    int addSupply(Supply supply);
    
    int getByName(Map map);
}
