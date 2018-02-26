package com.xingrongjinfu.commodity.register.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.fenlei.model.Fenlei;
import com.xingrongjinfu.commodity.register.dao.IRegisterDao;
import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.commodity.register.model.RegisterExp;
@Service
public class RegisterService implements IRegisterService {
    @Autowired
    private IRegisterDao registerDao;
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        
        return registerDao.pageInfoQuery(pageUtilEntity);
    }
    @Override
    public int addRegister(Register register) {
        
        return registerDao.addRegister(register);
    }
    @Override
    public Register getRegisterById(String commodityId) {
        
        return registerDao.getRegisterById(commodityId);
    }
    @Override
    public int updateRegister(Register register) {
        
        return registerDao.updateRegister(register);
    }
    @Override
    public int updateStatus(Map map) {
        
        return registerDao.updateStatus(map);
    }
    @Override
    public List<RegisterExp> getExpRegisterList(Map map) throws Exception {
        
        return registerDao.getExpRegisterList(map);
    }
    @Override
    public Map isExist(Map map) {
        
        return registerDao.isExist(map);
    }
    @Override
    public int updateImpExcel(List<Register> list) {
        
        return registerDao.updateImpExcel(list);
    }
    @Override
    public void updateRegisterList(List<Register> list) throws Exception {
        registerDao.updateRegisterList(list);
    }
    @Override
    public void updateStockList(List<Register> list) throws Exception {
        registerDao.updateStockList(list);
    }
    @Override
    public List<Fenlei> getCategoryByCommodity(Map map) throws Exception {
      
        return registerDao.getCategoryByCommodity(map);
    }
    @Override
    public int checkCategoryName(Map map) {
        return registerDao.checkCategoryName(map);
    }
}
