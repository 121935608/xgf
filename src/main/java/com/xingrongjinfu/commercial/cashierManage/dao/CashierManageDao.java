package com.xingrongjinfu.commercial.cashierManage.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;

/**
 * 数据层处理
 * 
 * @author 
 */
@Repository("cashierManageDao")
public class CashierManageDao extends DynamicObjectBaseDao implements ICashierManageDao
{


    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> cashierManagePageInfo = null;
        try
        {
        	cashierManagePageInfo = (List<TableDataInfo>) this.findForList("CommercialCashierManageMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cashierManagePageInfo;

    }
    
    /**
     * 修改信息
     * 
     */
    public int updateCashierManageInfo(CashierManage cashierManage)
    {
        return this.update("CommercialCashierManageMapper.updateCashierManageInfo", cashierManage);
    }

    /**
     * 添加信息
     * 
     */
	@Override
	public int addCashierManageInfo(CashierManage cashierManage) {
		return this.save("CommercialCashierManageMapper.addCashierManageInfo", cashierManage);
	}

	@Override
	public CashierManage findByCashierManageName(String cashierName) {
		return (CashierManage) this.findForObject("CommercialCashierManageMapper.findByCashierName", cashierName);
	}

    @Override
    public CashierManage findByCashierId(String id) {
        
        return (CashierManage) this.findForObject("CommercialCashierManageMapper.findByCashierId", id);
    }

    @Override
    public int getByName(Map map) {
        
        return (int) this.findForObject("CommercialCashierManageMapper.getByName", map);
    }
}
