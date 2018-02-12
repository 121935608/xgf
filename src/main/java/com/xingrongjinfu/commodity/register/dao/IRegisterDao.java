package com.xingrongjinfu.commodity.register.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.fenlei.model.Fenlei;
import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.commodity.register.model.RegisterExp;

public interface IRegisterDao {
    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * @return 用户信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public int addRegister(Register register);
    
    public Register getRegisterById(String commodityId);
    
    public int updateRegister(Register register);
    
    public int updateStatus(String commodityId);
    
    public List<RegisterExp> getExpRegisterList(Map map)throws Exception;
    
    public Map isExist(Map map);
    
    public int updateImpExcel(List<Register> list);
    
    public void updateRegisterList(List<Register> list) throws Exception;
    
    public void updateStockList(List<Register> list) throws Exception;
    
    public List<Fenlei> getCategoryByCommodity(Map map) throws Exception;
    
    int checkCategoryName(Map map);
}
