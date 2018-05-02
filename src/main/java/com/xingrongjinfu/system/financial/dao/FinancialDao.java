/**
 * Copyright (C), 2018
 * FileName: FinancialsDao
 * Author:   zxuser
 * Date:     2018/1/3 14:14
 * Description: 业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.financial.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.statistics.cashCount.model.Billing;
import com.xingrongjinfu.system.financial.model.Financial;
import com.xingrongjinfu.system.financial.model.FinancialDetail;

/**
 * 〈一句话功能简述〉<br> 
 * 〈业务层〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Repository
public class FinancialDao extends DynamicObjectBaseDao implements IFinancialDao {


    @Override
    public BigDecimal getTotalMoney(String id) {
        return (BigDecimal) this.findForObject("FinancialMapper.getTotalMoney", id);
    }

    @Override
    public List<Financial> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<Financial> tableDataInfo=null;
        try {
            tableDataInfo=(List<Financial>)this.findForList("StatisticsFinancialMapper.pageInfoQuery",pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableDataInfo;
    }

    @Override
    public List<Map> infoQuery(Map<String, String> param) {
        List<Map> tableDataInfo=null;
        try {
            tableDataInfo=(List<Map>)this.findForList("StatisticsFinancialMapper.infoQuery",param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableDataInfo;
    }

    @Override
    public int updateAmountInfo(Financial financial) {
        return this.update("FinancialMapper.updateAmountInfo",financial);
    }

    @Override
    public Financial getByNum(String id) {
        return (Financial) this.findForObject("FinancialMapper.getByNum", id);
    }

    @Override
    public List<FinancialDetail> getDetail(String id) {
        List<FinancialDetail> tableDataInfo=null;
        try {
            tableDataInfo=(List<FinancialDetail>)this.findForList("FinancialMapper.getDetail",id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableDataInfo;
    }

    @Override
    public int updateBilling(Billing billing) {
        
        return this.update("FinancialMapper.updateBilling", billing);
    }

    @Override
    public int addAmount(Financial financial) {
        return this.save("FinancialMapper.addAmount", financial);
    }
}