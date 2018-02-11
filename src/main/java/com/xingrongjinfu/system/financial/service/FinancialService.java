/**
 * Copyright (C), 2018
 * FileName: FinancialsService
 * Author:   zxuser
 * Date:     2018/1/3 14:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.financial.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.system.financial.dao.IFinancialDao;
import com.xingrongjinfu.system.financial.model.Financial;
import com.xingrongjinfu.system.financial.model.FinancialDetail;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Service("financialService")
public class FinancialService implements IFinancialService {

    @Autowired
    private IFinancialDao financialDao;
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return financialDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public int updateAmountInfo(Financial financial) {
        return financialDao.updateAmountInfo(financial);
    }

    @Override
    public Financial getByNum(String id) {
       
        return financialDao.getByNum(id);
    }

    @Override
    public List<FinancialDetail> getDetail(String id) {
        return financialDao.getDetail(id);
    }

    @Override
    public int updateAmount(Financial financial,FinancialDetail financialDetail) {
        int n = financialDao.addAmountDetail(financialDetail);
        if(n > 0)
         n = financialDao.updateAmount(financial);
        return n;
    }
}