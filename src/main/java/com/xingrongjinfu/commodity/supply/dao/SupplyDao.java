package com.xingrongjinfu.commodity.supply.dao;

import java.util.List;

import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.supply.model.Supply;
@Repository
public class SupplyDao extends DynamicObjectBaseDao implements ISupplyDao {

    @Override
    public List<Supply> getAll(String storeId) throws Exception {
        return (List<Supply>) this.findForList("SupplyMapper.getAll", storeId);
    }

}
