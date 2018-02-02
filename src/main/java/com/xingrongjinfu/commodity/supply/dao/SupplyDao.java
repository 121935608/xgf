package com.xingrongjinfu.commodity.supply.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.supply.model.Supply;
@Repository
public class SupplyDao extends DynamicObjectBaseDao implements ISupplyDao {

    @Override
    public List<Supply> getAll(String storeId) throws Exception {
        return (List<Supply>) this.findForList("SupplyMapper.getAll", storeId);
    }

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> supplyPageInfo = null;
        try
        {
            supplyPageInfo = (List<TableDataInfo>) this.findForList("SupplyMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return supplyPageInfo;
    }

    @Override
    public int changeSupplyStatus(Supply supply) {
        
        return this.update("SupplyMapper.changeSupplyStatus", supply);
    }

    @Override
    public Supply getByCode(String supplyCode) {
       
        return (Supply) this.findForObject("SupplyMapper.getByCode", supplyCode);
    }

    @Override
    public int updateSupply(Supply supply) {
       
        return this.update("SupplyMapper.updateSupply", supply);
    }

    @Override
    public int addSupply(Supply supply) {
        
        return this.save("SupplyMapper.addSupply", supply);
    }

}
