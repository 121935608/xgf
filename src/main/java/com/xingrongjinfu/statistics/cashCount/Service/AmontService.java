package com.xingrongjinfu.statistics.cashCount.Service;


import com.xingrongjinfu.statistics.cashCount.dao.AmontDao;
import com.xingrongjinfu.statistics.cashCount.model.Amount;
import com.xingrongjinfu.system.setting.model.Setting;
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

    public int addAmont(Amount amount) {
        return amontDao.addAmont(amount);
    }

    public Double findXzfRate(String args) {
        return amontDao.findXzfRate(args);
    }
}
