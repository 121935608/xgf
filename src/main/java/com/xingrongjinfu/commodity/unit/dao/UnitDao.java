package com.xingrongjinfu.commodity.unit.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.unit.model.Unit;
import com.xingrongjinfu.system.syscode.model.SysCode;
@Repository
public class UnitDao extends DynamicObjectBaseDao implements IUnitDao {

    @Override
    public List<Unit> getAll(String storeId) throws Exception {
        
        return (List<Unit>) this.findForList("UnitMapper.getAll", storeId);
    }
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> unitPageInfo = null;
        try
        {
            unitPageInfo = (List<TableDataInfo>) this.findForList("UnitMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return unitPageInfo;
    }
    @Override
    public int getByName(Map map) {
        
        return (int) this.findForObject("UnitMapper.getByName", map);
    }
    @Override
    public int addUnit(Unit unit) {
        return this.save("UnitMapper.addUnit", unit);
    }
    @Override
    public Unit getByCode(String code) {
        return (Unit) this.findForObject("UnitMapper.getByCode", code);
    }
    @Override
    public int updateUnit(Unit unit) {
        
        return this.update("UnitMapper.updateUnit", unit);
    }
    @Override
    public List<SysCode> getUnitList(String storeId) {
        List<SysCode> optionsList = null;
        try
        {
            optionsList= (List<SysCode>) this.findForList("UnitMapper.getUnitList",storeId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return optionsList;
    }

}
