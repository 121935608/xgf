package com.xingrongjinfu.commercial.cashierManage.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;

/**
 * 数据层
 * 
 * @author 
 */
public interface ICashierManageDao
{
   
    /**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 通过名字查询
     * 
     */
    public CashierManage findByCashierManageName(String cashierName);
    
    /**
     * 修改
     * 
     */
    public int updateCashierManageInfo(CashierManage cashierManage);

    /**
     * 添加
     */
	public int addCashierManageInfo(CashierManage cashierManage);
	
	public CashierManage findByCashierId(String id);
	
	int getByName(Map map);

}
