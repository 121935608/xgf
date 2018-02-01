package com.xingrongjinfu.commodity.unit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.unit.dao.IUnitDao;
import com.xingrongjinfu.commodity.unit.model.Unit;
@Service
public class UnitService implements IUnitService {
    @Autowired
    private IUnitDao unitDao;
    @Override
    public List<Unit> getAll(String storeId) throws Exception {
        
        return unitDao.getAll(storeId);
    }

}
