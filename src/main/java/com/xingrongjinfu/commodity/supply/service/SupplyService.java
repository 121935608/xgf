package com.xingrongjinfu.commodity.supply.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.supply.dao.ISupplyDao;
import com.xingrongjinfu.commodity.supply.model.Supply;
@Service
public class SupplyService implements ISupplyService {

    @Autowired
    private ISupplyDao supplyDao;
    @Override
    public List<Supply> getAll(String storeId) throws Exception {
        
        return supplyDao.getAll(storeId);
    }
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        
        return supplyDao.pageInfoQuery(pageUtilEntity);
    }
    @Override
    public int changeSupplyStatus(Supply supply) {
        
        return supplyDao.changeSupplyStatus(supply);
    }
    @Override
    public Supply getByCode(String supplyCode) {
        
        return supplyDao.getByCode(supplyCode);
    }
    @Override
    public int updateSupply(Supply supply) {
        
        return supplyDao.updateSupply(supply);
    }
    @Override
    public int addSupply(Supply supply) {
        
        return supplyDao.addSupply(supply);
    }
    @Override
    public int getByName(Map map) {
        
        return supplyDao.getByName(map);
    }

}
