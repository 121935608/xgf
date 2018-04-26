package com.xingrongjinfu.statistics.paysWater.service;

import com.xingrongjinfu.statistics.paysWater.model.CashDetail;
import com.xingrongjinfu.statistics.paysWater.model.CashFlow;
import com.xingrongjinfu.statistics.paysWater.model.CashFlowDto;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

public interface ICashService {
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    CashFlow getById(String id);
    
    public List<TableDataInfo> cashCountpageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public List<TableDataInfo> sellWaterpageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public List<CashDetail> saleCountpageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public List<CashFlow> saleGraphCombo(String storeId);
    
    public List<CashFlow> passengerAnalysisGraph(String storeId);
    
    public List<CashDetail> saleGraphPie(Map map);
    
    public double getTotal(PageUtilEntity pageUtilEntity);

    List<CashFlowDto> infoQuery(Map<String, String> param);

    List<Map> cashCountInfoQuery(Map<String, String> param);

    List<Map> sellWaterInfoQuery(Map<String, String> param);
}
