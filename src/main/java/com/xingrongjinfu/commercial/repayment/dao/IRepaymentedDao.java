package com.xingrongjinfu.commercial.repayment.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.repayment.model.Repayment;
import com.xingrongjinfu.commodity.label.model.Label;

/**
 * 数据层
 * 
 * @author 
 */
public interface IRepaymentedDao
{
   
    /**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

	

}
