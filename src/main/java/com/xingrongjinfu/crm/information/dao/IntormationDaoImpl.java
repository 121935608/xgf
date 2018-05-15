package com.xingrongjinfu.crm.information.dao;

import com.xingrongjinfu.crm.information.model.Information;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class IntormationDaoImpl extends DynamicObjectBaseDao implements InformationDao {
    @Override
    public int addInfor(Information information) {
        return this.save("InformationMapper.addInformation",information);
    }
}
