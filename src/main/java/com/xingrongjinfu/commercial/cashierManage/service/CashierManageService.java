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
	
}
