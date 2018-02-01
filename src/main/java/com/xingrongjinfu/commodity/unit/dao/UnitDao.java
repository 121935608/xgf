package com.xingrongjinfu.commodity.unit.dao;

import java.util.List;

import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.unit.model.Unit;
@Repository
public class UnitDao extends DynamicObjectBaseDao implements IUnitDao {

    @Override
    public List<Unit> getAll(String storeId) throws Exception {
        
        return (List<Unit>) this.findForList("UnitMapper.getAll", storeId);
    }

}
