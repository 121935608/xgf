package com.xingrongjinfu.statistics.paysWater.dao;

import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.paysWater.model.CashFlowDto;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.statistics.paysWater.model.CashDetail;
import com.xingrongjinfu.statistics.paysWater.model.CashFlow;

@Repository
public class CashDao extends DynamicObjectBaseDao implements ICashDao {

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("CashMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }

    @Override
    public List<CashFlowDto> infoQuery(Map<String, String> param) {
        List<CashFlowDto> data = null;
        try
        {
            data = (List<CashFlowDto>) this.findForList("CashMapper.infoQuery", param);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public CashFlow getById(String id) {
        return (CashFlow) this.findForObject("CashMapper.getById", id);
    }

    @Override
    public List<CashDetail> getComById(String id) {
        List<CashDetail> list = null;
        try {
            list =  (List<CashDetail>) this.findForList("CashMapper.getComById", id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<TableDataInfo> cashCountpageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("CashMapper.cashCountpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }

    @Override
    public List<Map> cashCountInfoQuery(Map<String, String> param) {
        List<Map> data = null;
        try
        {
            data = (List<Map>) this.findForList("CashMapper.cashCountinfoQuery", param);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<TableDataInfo> sellWaterpageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("CashMapper.sellWaterpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }

    @Override
    public List<Map> sellWaterInfoQuery(Map<String, String> param) {
        List<Map> data = null;
        try
        {
            data = (List<Map>) this.findForList("CashMapper.sellWaterInfoQuery", param);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<CashDetail> saleCountpageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<CashDetail> userPageInfo = null;
        try
        {
            userPageInfo = (List<CashDetail>) this.findForList("CashMapper.saleCountpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }

    @Override
    public List<CashFlow> saleGraphCombo(String storeId) {
        List<CashFlow> cashFlowList = null;
        try
        {
            cashFlowList = (List<CashFlow>) this.findForList("CashMapper.saleGraphCombo", storeId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cashFlowList;
    }

    @Override
    public List<CashDetail> saleGraphPie(Map map) {
        List<CashDetail> cashFlowList = null;
        try
        {
            cashFlowList = (List<CashDetail>) this.findForList("CashMapper.saleGraphPie", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cashFlowList;
    }

    @Override
    public List<CashFlow> passengerAnalysisGraph(String storeId) {
        List<CashFlow> cashFlowList = null;
        try
        {
            cashFlowList = (List<CashFlow>) this.findForList("CashMapper.passengerAnalysisGraph", storeId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cashFlowList;
    }

    @Override
    public double getTotal(PageUtilEntity pageUtilEntity) {
        return (double) this.findForObject("CashMapper.getTotal", pageUtilEntity);
    }

}
