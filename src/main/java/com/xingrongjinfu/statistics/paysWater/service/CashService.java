package com.xingrongjinfu.statistics.paysWater.service;

import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.paysWater.model.CashFlowDto;
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
    public List<CashFlowDto> infoQuery(Map<String, String> param) {
        return cashDao.infoQuery(param);
    }
    @Override
    public CashFlow getById(String id) {
        List<CashDetail> list = cashDao.getComById(id);
        CashFlow cashFlow = cashDao.getById(id);
        String number = "";
        for (CashDetail cashDetail : list) {
            if(null != cashDetail.getNumber()){
                number = cashDetail.getNumber().toString();
                int n = number.lastIndexOf(".00");
                if(n != -1){
                    number = number.substring(0,n);
                }
                cashDetail.setCommodityNo(number);
            }
        }
        cashFlow.setCashDetail(list);
        return cashFlow;
    }
    @Override
    public List<TableDataInfo> cashCountpageInfoQuery(PageUtilEntity pageUtilEntity) {
        return cashDao.cashCountpageInfoQuery(pageUtilEntity);
    }
    @Override
    public List<Map> cashCountInfoQuery(Map<String, String> param) {
        return cashDao.cashCountInfoQuery(param);
    }

    @Override
    public List<TableDataInfo> sellWaterpageInfoQuery(PageUtilEntity pageUtilEntity) {
        return cashDao.sellWaterpageInfoQuery(pageUtilEntity);
    }


    @Override
    public List<Map> sellWaterInfoQuery(Map<String, String> param) {
        return cashDao.sellWaterInfoQuery(param);
    }

    @Override
    public List<CashDetail> saleCountpageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<CashDetail> list =  cashDao.saleCountpageInfoQuery(pageUtilEntity);
        return list;
    }
    @Override
    public List<Map> saleCountInfoQuery(Map<String, String> param) {
        return cashDao.saleCountInfoQuery(param);
    }
    @Override
    public List<CashFlow> saleGraphCombo(String storeId) {
        return cashDao.saleGraphCombo(storeId);
    }
    @Override
    public List<CashDetail> saleGraphPie(Map map) {
        return cashDao.saleGraphPie(map);
    }
    @Override
    public List<CashFlow> passengerAnalysisGraph(String storeId) {
       
        return cashDao.passengerAnalysisGraph(storeId);
    }



    @Override
    public double getTotal(PageUtilEntity pageUtilEntity) {
        
        return cashDao.getTotal(pageUtilEntity);
    }

    @Override
    public double getTotal(Map<String, String> param) {
        return cashDao.getTotal(param);
    }
}
