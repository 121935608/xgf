package com.xingrongjinfu.commodity.unit.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.unit.model.Unit;

public interface IUnitService {
    List<Unit> getAll(String storeId) throws Exception;
    
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    int getByName(Map map);
    
    int addUnit(Unit unit);
    
    Unit getByCode(String code);
    
    int updateUnit(Unit unit);
}
