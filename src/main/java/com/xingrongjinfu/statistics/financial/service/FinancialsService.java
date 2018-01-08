package com.xingrongjinfu.statistics.financial.service;

import java.util.List;

import com.xingrongjinfu.statistics.financial.dao.IFinancialsDao;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("financialsService")
public class FinancialsService implements IFinancialsService
{

	@Autowired
    private IFinancialsDao financialsDao;
  
    /**
     * 根据条件分页查询
     */
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return financialsDao.pageInfoQuery(pageUtilEntity);
    }

}
