/**
 * Copyright (C), 2018
 * FileName: IFinancialsService
 * Author:   zxuser
 * Date:     2018/1/3 14:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.financial.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;

import com.xingrongjinfu.statistics.cashCount.model.Billing;
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
public interface IFinancialService {

    /**
     * 查询财务信息
     * @param pageUtilEntity
     * @return
     */
    List<Financial> pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 更新财务信息
     */
    int updateAmountInfo(Financial financial);
    
    Financial getByNum(String id);

    BigDecimal getTotalMoney(String id);
    
    List<FinancialDetail> getDetail(String id);
    
    int addAmount(Financial financial,Billing billing);

    List<Map> infoQuery(Map<String, String> param);
}