package com.xingrongjinfu.statistics.purchase.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.statistics.purchase.dao.IPurchaseDao;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("purchaseService")
public class PurchaseService implements IPurchaseService
{
	@Autowired
    private IPurchaseDao purchaseDao;

    @Override
    public List<Map> infoQuery(Map<String, String> param) {
        return purchaseDao.infoQuery(param);
    }

    /**
     * 根据条件分页查询
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return purchaseDao.pageInfoQuery(pageUtilEntity);
    }


}
