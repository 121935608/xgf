package com.xingrongjinfu.statistics.cashCount.dao;

import com.xingrongjinfu.statistics.cashCount.model.Amount;
import com.xingrongjinfu.system.setting.model.Setting;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AmontDao extends DynamicObjectBaseDao  {

    public List<Amount> countCashFlowDay(String preDateStr) throws Exception {
        return (List<Amount>) findForList("AmountMapper.countCashFlowDay",preDateStr);
    }

    public int addAmont(Amount amount) {
        return (int)this.save("AmountMapper.addAmont",amount);
    }

    public Double findXzfRate(String args) {
        return (Double)this.findForObject("AmountMapper.findXzfRate",args);
    }
}
