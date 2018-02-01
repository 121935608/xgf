package com.xingrongjinfu.commodity.unit.dao;

import java.util.List;

import com.xingrongjinfu.commodity.unit.model.Unit;

public interface IUnitDao {
    List<Unit> getAll(String storeId) throws Exception;
}
