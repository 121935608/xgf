package com.xingrongjinfu.commodity.supply.dao;

import java.util.List;

import com.xingrongjinfu.commodity.supply.model.Supply;

public interface ISupplyDao {
    List<Supply> getAll(String storeId) throws Exception;
}
