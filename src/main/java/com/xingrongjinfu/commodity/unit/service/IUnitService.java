package com.xingrongjinfu.commodity.unit.service;

import java.util.List;

import com.xingrongjinfu.commodity.unit.model.Unit;

public interface IUnitService {
    List<Unit> getAll(String storeId) throws Exception;
}
