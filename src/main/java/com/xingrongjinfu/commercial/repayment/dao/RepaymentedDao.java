package com.xingrongjinfu.commercial.repayment.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commercial.repayment.model.Repayment;
import com.xingrongjinfu.commodity.label.model.Label;

/**
 * 数据层处理
 * 
 * @author 
 */
@Repository("repaymentedDao")
public class RepaymentedDao extends DynamicObjectBaseDao implements IRepaymentedDao
{


    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> repaymentPageInfo = null;
        try
        {
        	repaymentPageInfo = (List<TableDataInfo>) this.findForList("CommercialRepaymentMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return repaymentPageInfo;

    }

	
}
