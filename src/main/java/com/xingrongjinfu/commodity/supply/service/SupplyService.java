package com.xingrongjinfu.commodity.supply.service;

import java.util.List;

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

}
