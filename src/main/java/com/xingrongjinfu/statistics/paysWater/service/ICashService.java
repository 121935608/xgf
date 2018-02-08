package com.xingrongjinfu.statistics.paysWater.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.statistics.paysWater.model.CashDetail;
import com.xingrongjinfu.statistics.paysWater.model.CashFlow;

public interface ICashService {
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    CashFlow getById(String id);
    
    public List<TableDataInfo> cashCountpageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public List<TableDataInfo> sellWaterpageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public List<CashDetail> saleCountpageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public List<CashFlow> saleGraphCombo(String storeId);
    
    public List<CashFlow> passengerAnalysisGraph(String storeId);
    
    public List<CashDetail> saleGraphPie(String storeId);
    
}
