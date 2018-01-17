package com.xingrongjinfu.commercial.cashierManage.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.system.user.model.User;

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

}
