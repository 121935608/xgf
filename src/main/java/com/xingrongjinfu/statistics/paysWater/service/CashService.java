package com.xingrongjinfu.statistics.paysWater.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.statistics.paysWater.dao.ICashDao;
import com.xingrongjinfu.statistics.paysWater.model.CashDetail;
import com.xingrongjinfu.statistics.paysWater.model.CashFlow;

@Service
public class CashService implements ICashService {
    @Autowired
    private ICashDao cashDao;
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return cashDao.pageInfoQuery(pageUtilEntity);
    }
    @Override
    public CashFlow getById(String id) {
        List<CashDetail> list = cashDao.getComById(id);
        CashFlow cashFlow = cashDao.getById(id);
        cashFlow.setCashDetail(list);
        return cashFlow;
    }
    @Override
    public List<TableDataInfo> cashCountpageInfoQuery(PageUtilEntity pageUtilEntity) {
        return cashDao.cashCountpageInfoQuery(pageUtilEntity);
    }
    @Override
    public List<TableDataInfo> sellWaterpageInfoQuery(PageUtilEntity pageUtilEntity) {
        return cashDao.sellWaterpageInfoQuery(pageUtilEntity);
    }
    @Override
    public List<CashDetail> saleCountpageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<CashDetail> list =  cashDao.saleCountpageInfoQuery(pageUtilEntity);
        return list;
    }
    @Override
    public List<CashFlow> saleGraphCombo(String storeId) {
        return cashDao.saleGraphCombo(storeId);
    }
    @Override
    public List<CashDetail> saleGraphPie(String storeId) {
        return cashDao.saleGraphPie(storeId);
    }
    @Override
    public List<CashFlow> passengerAnalysisGraph(String storeId) {
       
        return cashDao.passengerAnalysisGraph(storeId);
    }
}
