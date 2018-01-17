package com.xingrongjinfu.commercial.cashierManage.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.system.user.model.User;

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
     * 通过名字查询
     * 
     */
    public CashierManage findByCashierManageName(String cashierName);
    
    /**
     * 校验名称是否唯一
     */
    public String checkNameUnique(CashierManage cashierManage);
    
    /**
     * 修改状态
     * 
     */
    public int changeCashierManageStatus(CashierManage cashierManage);

    /**
     * 添加状态
     * 
     */
	public int addCashierManageInfo(CashierManage cashierManage);
	
	

}
