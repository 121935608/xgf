package com.xingrongjinfu.statistics.pays.service;

import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.pays.dao.IPayDao;
import com.xingrongjinfu.statistics.pays.model.Pays;
import com.xingrongjinfu.statistics.pays.model.PaysDto;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("payService")
public class PayService implements IPayService
{

	@Autowired
    private IPayDao paysDao;


    @Override
    public List<PaysDto> infoQuery(Map<String, String> param) {
        return paysDao.infoQuery(param);
    }

    /**
     * 根据条件分页查询
     */
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return paysDao.pageInfoQuery(pageUtilEntity);
    }

}
