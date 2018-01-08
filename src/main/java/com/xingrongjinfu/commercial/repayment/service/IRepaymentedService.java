package com.xingrongjinfu.commercial.repayment.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.repayment.model.Repayment;
import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.commodity.label.model.Label;



/**
 *  业务层
 * 
 * @author 
 */
public interface IRepaymentedService
{

    /**
     * 根据条件分页查询对象
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

	

}
