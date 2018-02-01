package com.xingrongjinfu.commodity.supply.service;

import java.util.List;

import com.xingrongjinfu.commodity.supply.model.Supply;

public interface ISupplyService {
    List<Supply> getAll(String storeId) throws Exception;
}
