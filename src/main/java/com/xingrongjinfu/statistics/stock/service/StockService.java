package com.xingrongjinfu.statistics.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.statistics.stock.dao.IStockDao;

@Service
public class StockService implements IStockService{
    @Autowired
    private IStockDao stockDao;
}
