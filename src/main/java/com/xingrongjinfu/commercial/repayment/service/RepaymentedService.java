package com.xingrongjinfu.commercial.repayment.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commercial.repayment.dao.IRepaymentedDao;
import com.xingrongjinfu.commercial.repayment.model.Repayment;
import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.commodity.label.dao.ILabelDao;
import com.xingrongjinfu.commodity.label.model.Label;


/**
 * 业务层处理
 * 
 * @author
 */
@Service("repaymentedService")
public class RepaymentedService implements IRepaymentedService
{

    @Autowired
    private IRepaymentedDao repaymentedDao;
  
    /**
     * 根据条件分页查询
     */
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return repaymentedDao.pageInfoQuery(pageUtilEntity);
    }

	
}
