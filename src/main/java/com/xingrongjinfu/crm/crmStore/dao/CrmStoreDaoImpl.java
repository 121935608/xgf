package com.xingrongjinfu.crm.crmStore.dao;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CrmStoreDaoImpl extends DynamicObjectBaseDao implements CrmStoreDao {
    @Override
    public List<TableDataInfo> selectStoreSup(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> supervisorPageInfo = null;
        try {
            supervisorPageInfo = (List<TableDataInfo>)this.findForList("StoreAffairMapper.selectStoreSup",pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supervisorPageInfo;
    }
}
