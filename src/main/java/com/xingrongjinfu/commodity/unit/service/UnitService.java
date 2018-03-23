package com.xingrongjinfu.commodity.unit.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.unit.dao.IUnitDao;
import com.xingrongjinfu.commodity.unit.model.Unit;
import com.xingrongjinfu.system.syscode.model.SysCode;
@Service
public class UnitService implements IUnitService {
    @Autowired
    private IUnitDao unitDao;
    @Override
    public List<Unit> getAll(String storeId) throws Exception {
        
        return unitDao.getAll(storeId);
    }
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        
        return unitDao.pageInfoQuery(pageUtilEntity);
    }
    @Override
    public int getByName(Map map) {
        return unitDao.getByName(map);
    }
    @Override
    public int addUnit(Unit unit) {
        return unitDao.addUnit(unit);
    }
    @Override
    public Unit getByCode(String code) {
        
        return unitDao.getByCode(code);
    }
    @Override
    public int updateUnit(Unit unit) {
        return unitDao.updateUnit(unit);
    }
    @Override
    public List<SysCode> getUnitList(String storeId) {
        return unitDao.getUnitList(storeId);
    }

}
