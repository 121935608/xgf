package com.xingrongjinfu.statistics.cashCount.dao;

import com.xingrongjinfu.statistics.cashCount.model.Amount;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("amontDao")
public class AmontDao extends DynamicObjectBaseDao  {

    public List<Amount> countCashFlowDay(String preDateStr) throws Exception {
        return (List<Amount>) findForList("AmountMapper.countCashFlowDay");
    }
}
