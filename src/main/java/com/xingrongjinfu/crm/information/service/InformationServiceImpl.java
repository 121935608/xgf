package com.xingrongjinfu.crm.information.service;

import com.xingrongjinfu.crm.information.dao.InformationDao;
import com.xingrongjinfu.crm.information.model.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl implements InformatioinService {
    @Autowired
    private InformationDao informationDao;

    @Override
    public int addInfor(Information information) {
        return informationDao.addInfor(information);
    }
}
