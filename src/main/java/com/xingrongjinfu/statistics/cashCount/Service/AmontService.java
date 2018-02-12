package com.xingrongjinfu.statistics.cashCount.Service;


import com.xingrongjinfu.statistics.cashCount.dao.AmontDao;
import com.xingrongjinfu.statistics.cashCount.model.Amount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("amontService")
public class AmontService {
    @Autowired
    private AmontDao amontDao;

    public List<Amount> countCashFlowDay(String preDateStr) throws Exception {
        return amontDao.countCashFlowDay(preDateStr);
    }
}
