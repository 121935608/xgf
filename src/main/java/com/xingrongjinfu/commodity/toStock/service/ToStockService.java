package com.xingrongjinfu.commodity.toStock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.toStock.dao.IToStockDao;

@Service
public class ToStockService implements IToStockService {
    @Autowired
    private IToStockDao toStockDao;
}
