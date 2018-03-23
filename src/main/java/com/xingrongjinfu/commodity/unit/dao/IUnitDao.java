package com.xingrongjinfu.commodity.unit.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.unit.model.Unit;
import com.xingrongjinfu.system.syscode.model.SysCode;

public interface IUnitDao {
    List<Unit> getAll(String storeId) throws Exception;
    
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    int getByName(Map map);
    
    int addUnit(Unit unit);
    
    Unit getByCode(String code);
    
    int updateUnit(Unit unit);
    
    List<SysCode> getUnitList(String storeId);
}
