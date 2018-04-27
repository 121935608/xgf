package com.xingrongjinfu.statistics.procurement.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.statistics.procurement.dao.IProcurementDao;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("procurementService")
public class ProcurementService implements IProcurementService
{

	@Autowired
    private IProcurementDao procurementDao;

    @Override
    public List<Map> infoQuery(Map<String, String> param) {
        return procurementDao.infoQuery(param);
    }

    /**
     * 根据条件分页查询
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return procurementDao.pageInfoQuery(pageUtilEntity);
    }

}
