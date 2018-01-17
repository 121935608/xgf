package com.xingrongjinfu.commercial.cashierManage.service;

import java.util.List;

import org.apache.shiro.common.UserConstants;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commercial.cashierManage.common.CashierManageConstant;
import com.xingrongjinfu.commercial.cashierManage.dao.ICashierManageDao;
import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.advertisement.common.AdvertisementConstant;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("cashierManageService")
public class CashierManageService implements ICashierManageService
{

    @Autowired
    private ICashierManageDao cashierManageDao;
  
    /**
     * 根据条件分页查询
     */
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return cashierManageDao.pageInfoQuery(pageUtilEntity);
    }
    
    /**
     * 修改状态
     * 
     */
    public int changeCashierManageStatus(CashierManage cashierManage)
    {
        return cashierManageDao.updateCashierManageInfo(cashierManage);
    }

    /**
     * 添加状态
     * 
     */
	@Override
	public int addCashierManageInfo(CashierManage cashierManage) {
		return cashierManageDao.addCashierManageInfo(cashierManage);
	}

	@Override
	public CashierManage findByCashierManageName(String cashierName) {
		return cashierManageDao.findByCashierManageName(cashierName);
	}

	@Override
	public String checkNameUnique(CashierManage cashierManage) {
		if(cashierManage.getCashierId()==null){
			cashierManage.setCashierId("-1");
    	}
    	String cashierName = cashierManage.getCashierName();
    	String cashierId = cashierManage.getCashierId();
    	cashierManage = new CashierManage();
    	cashierManage.setCashierName(cashierName);
    	CashierManage newCashierManage = cashierManageDao.findByCashierManageName(cashierName);
    	if(ObjectUtil.isNotNull(newCashierManage)&&newCashierManage.getCashierId()!=cashierId){
    		return CashierManageConstant.NAME_NOT_UNIQUE;
    	}
    	
    	return CashierManageConstant.NAME_UNIQUE;
	}
	
}
