package com.xingrongjinfu.commercial.cashierManage.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;

/**
 *  业务层
 * 
 * @author 
 */
public interface ICashierManageService
{

    /**
     * 根据条件分页查询对象
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 修改状态
     * 
     */
    public int changeCashierManageStatus(CashierManage cashierManage);

	public int addCashierManageInfo(CashierManage cashierManage);

	

}
