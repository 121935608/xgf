package com.xingrongjinfu.commodity.register.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.fenlei.model.Fenlei;
import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.commodity.register.model.RegisterExp;
@Repository
public class RegisterDao extends DynamicObjectBaseDao implements IRegisterDao {

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("RegisterMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }
    @Override
    public int addRegister(Register register) {
        return (int)this.save("RegisterMapper.addRegister", register);
    }
    @Override
    public Register getRegisterById(String commodityId) {
        
        return (Register) this.findForObject("RegisterMapper.getRegisterById", commodityId);
    }
    @Override
    public int updateRegister(Register register) {
        
        return this.update("RegisterMapper.updateRegister", register);
    }
    @Override
    public int updateStatus(String commodityId) {
        
        return this.update("RegisterMapper.updateStatus", commodityId);
    }
    @Override
    public List<RegisterExp> getExpRegisterList(Map map) throws Exception {
        
        return (List<RegisterExp>) this.findForList("RegisterMapper.getExpRegisterList", map);
    }
    @Override
    public Map isExist(Map map) {
        
        return (Map) this.findForObject("RegisterMapper.isExist", map);
    }
    @Override
    public int updateImpExcel(List<Register> list) {
        int a = 0;
        try
        {
            a =  this.batchSave("RegisterMapper.updateImpExcel", list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return a;
    }
    @Override
    public void updateRegisterList(List<Register> list) throws Exception {
        
        this.batchSave("RegisterMapper.updateRegisterList", list);
    }
    @Override
    public void updateStockList(List<Register> list) throws Exception {
        this.batchSave("RegisterMapper.updateStockList", list);
    }
    @Override
    public List<Fenlei> getCategoryByCommodity(Map map) throws Exception {
        return (List<Fenlei>) this.findForList("RegisterMapper.getCategoryByCommodity", map);
    }
    @Override
    public int checkCategoryName(Map map) {
        
        return (int) this.findForObject("RegisterMapper.checkCategoryName", map);
    }
}
